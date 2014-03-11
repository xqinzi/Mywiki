package com.yxpai.cas.oauth.weibo;

import java.util.concurrent.TimeUnit;

import org.scribe.model.OAuthConfig;
import org.scribe.model.ProxyOAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.up.addon_to_scribe.ProxyOAuth20ServiceImpl;
import org.scribe.up.profile.JsonHelper;
import org.scribe.up.profile.UserProfile;
import org.scribe.up.provider.BaseOAuth20Provider;
import org.scribe.up.provider.BaseOAuthProvider;
import org.scribe.up.provider.exception.HttpException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.databind.JsonNode;
import com.yxpai.cas.oauth.YxPaiOAuthAttributesDefinitions;

public class WeiboProvider extends BaseOAuth20Provider {
	
	private String userid;
	private String email;
	
	@Override
	protected BaseOAuthProvider newProvider() {
		return new WeiboProvider();
	}

	@Override
	protected void internalInit() {
		this.service = new ProxyOAuth20ServiceImpl(new WeiboApi20(),
				new OAuthConfig(this.key, this.secret, this.callbackUrl,
						SignatureType.Header, "email", null), this.proxyHost,
				this.proxyPort);

	}

	@Override
	protected String getProfileUrl() {
		return "https://api.weibo.com/2/users/show.json";
	}

	@Override
	protected UserProfile extractUserProfile(String body) {
		final WeiBoProfile profile = new WeiBoProfile();
		JsonNode json = JsonHelper.getFirstNode(body);
		if (json != null) {
			//使用userid作为用户的唯一ID
			profile.setId(userid);
			profile.addAttribute("email", email);
			//profile.addAttribute("authorities", "ROLE_USER");
			for (final String attribute : YxPaiOAuthAttributesDefinitions.weiBoDefinition.getAllAttributes()) {
                profile.addAttribute(attribute, JsonHelper.get(json, attribute));
            }
		}
		/**
		 * 用户已经登录的情况下，进行用户的绑定。获取用户ID
		 */
		SecurityContext ctx = SecurityContextHolder.getContext();
		if (ctx.getAuthentication() != null && !(ctx.getAuthentication() instanceof AnonymousAuthenticationToken)) {
			CasAuthenticationToken auth = (CasAuthenticationToken) ctx.getAuthentication();
			String uuid=(String) auth.getAssertion().getPrincipal().getAttributes().get("uuid");
			if(uuid!=null&&!("".equals(uuid)))
				profile.addAttribute("uuid", uuid);
		}
		return profile;
	}
	
	
	/**
     * 重写父类方法，新浪的认证过程，需要专门获取用户的userid才能获取用户信息
     * 提取用户信息的方法
     * @throws HttpException
     */
	@Override
    protected String sendRequestForData(final Token accessToken, String dataUrl) throws HttpException {
        logger.debug("accessToken : {} / dataUrl : {}", accessToken, dataUrl);
        userid=getUserId(accessToken);
        email=getEmail(accessToken);
    	//进行oauth参数的封装
        dataUrl=dataUrl+"?uid="+userid+"&access_token="+accessToken.getToken();
        final ProxyOAuthRequest request = new ProxyOAuthRequest(Verb.GET, dataUrl, this.proxyHost, this.proxyPort);
        if (this.connectTimeout != 0) {
            request.setConnectTimeout(this.connectTimeout, TimeUnit.MILLISECONDS);
        }
        if (this.readTimeout != 0) {
            request.setReadTimeout(this.readTimeout, TimeUnit.MILLISECONDS);
        }
        //不使用service进行oauth参数封装
        //this.service.signRequest(accessToken, request);
        final Response response = request.send();
        final int code = response.getCode();
        final String body = response.getBody();
        if (code != 200) {
            logger.error("Failed to get user data, code : " + code + " / body : " + body);
            throw new HttpException(code, body);
        }
        return body;
    }
	
	
	/**
	 * 获取用户的id
	 * @param accessToken
	 * @return
	 * @throws HttpException
	 */
	protected String getUserId(final Token accessToken) throws HttpException {
		final ProxyOAuthRequest request = new ProxyOAuthRequest(
				Verb.GET,
				"https://api.weibo.com/2/account/get_uid.json?access_token="+accessToken.getToken(),
				this.proxyHost, this.proxyPort);
		if (this.connectTimeout != 0) {
	        request.setConnectTimeout(this.connectTimeout, TimeUnit.MILLISECONDS);
	    }
	    if (this.readTimeout != 0) {
	        request.setReadTimeout(this.readTimeout, TimeUnit.MILLISECONDS);
	    }
		final Response response = request.send();
		final int code = response.getCode();
		String body = response.getBody();
		if (code != 200) {
            logger.error("Failed to get get_uid data, code : " + code + " / body : " + body);
            throw new HttpException(code, body);
        }
		JsonNode json = JsonHelper.getFirstNode(body);
		//新浪返回的uid为Long类型，需要做类型换换
		return String.valueOf(JsonHelper.get(json, "uid"));
	}
	/**
	 * 获取用户的邮箱，需要进行权限申请，所以可能获得不到邮箱
	 * @param accessToken
	 * @return
	 * @throws HttpException
	 */
	protected String getEmail(final Token accessToken) throws HttpException {
		final ProxyOAuthRequest request = new ProxyOAuthRequest(
				Verb.GET,
				"https://api.weibo.com/2/account/profile/email.json?access_token="+accessToken.getToken(),
				this.proxyHost, this.proxyPort);
		if (this.connectTimeout != 0) {
			request.setConnectTimeout(this.connectTimeout, TimeUnit.MILLISECONDS);
		}
		if (this.readTimeout != 0) {
			request.setReadTimeout(this.readTimeout, TimeUnit.MILLISECONDS);
		}
		final Response response = request.send();
		final int code = response.getCode();
		String body = response.getBody();
		/**
		 * 此处进行code的判断，如果允许获取邮箱的http响应码是200，没有权限获取的响应码是403。
		 * 200成功的相应内容是
		 * [{"email": "weibo_api_tech@sina.com"}]
		 * 403失败的相应内容是
		 * {"error": "access_denied","error_code": 21330,"request": "/2/account/profile/email.json"}
		 */
		if (code == 403 && body.indexOf("error")>0) {
			return null;
		}
		if (code != 200) {
			logger.error("Failed to get email data, code : " + code + " / body : " + body);
			throw new HttpException(code, body);
		}
		JsonNode json = JsonHelper.getFirstNode(body.substring(1,body.length()-1));
		return (String) JsonHelper.get(json, "email");
	}
}
