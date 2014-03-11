package com.yxpai.cas.oauth.tencent;

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

public class QQProvider extends BaseOAuth20Provider {

	private String openId;
	
	@Override
	protected BaseOAuthProvider newProvider() {
		return new QQProvider();
	}

	@Override
	protected void internalInit() {
		this.service = new ProxyOAuth20ServiceImpl(new QQApi20(),
				new OAuthConfig(this.key, this.secret, this.callbackUrl,
						SignatureType.Header, "get_user_info", null), this.proxyHost,
				this.proxyPort);
	}

	/**
	 * 获取用户信息的url
	 * https://graph.qq.com/user/get_user_info 获取用户基本资料
	 * https://graph.qq.com/user/get_info 获取用户腾讯微博资料
	 * 如果获取微博账户资料，请修改com.yxpai.cas.oauth.tencent.QQApi20的AUTHORIZE_URL字符串后增加"&scope=get_info"内容
	 */
	@Override
	protected String getProfileUrl() {
		//return "https://graph.qq.com/user/get_info";
		return "https://graph.qq.com/user/get_user_info";
	}

	@Override
	protected UserProfile extractUserProfile(String body) {
		final QQProfile profile = new QQProfile();
		JsonNode json = JsonHelper.getFirstNode(body);
		if (json != null) {
			//使用openid作为用户的唯一ID
			profile.setId(openId);
			//profile.addAttribute("authorities", "ROLE_USER");
			for (final String attribute : YxPaiOAuthAttributesDefinitions.qqDefinition.getAllAttributes()) {
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
     * 重写父类方法，qq获取信息过程中多了一步获取openid的过程
     * 提取用户信息的方法
     * @throws HttpException
     */
    protected String sendRequestForData(final Token accessToken, String dataUrl) throws HttpException {
        logger.debug("accessToken : {} / dataUrl : {}", accessToken, dataUrl);
        openId=getOpenid(accessToken);
    	//进行oauth参数的封装
        dataUrl=dataUrl+"?openid="+openId+"&access_token="+accessToken.getToken()+"&oauth_consumer_key="+this.key;
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
     * 获取openId
     * @param accessToken
     * @return
     */
	private String getOpenid(final Token accessToken) throws HttpException {
		final ProxyOAuthRequest request = new ProxyOAuthRequest(
				Verb.GET,
				"https://graph.qq.com/oauth2.0/me?access_token="+accessToken.getToken(),
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
            logger.error("Failed to get Openid data, code : " + code + " / body : " + body);
            throw new HttpException(code, body);
        }
		//相应成功的返回结果如下，需要进行字符串的提取出json格式的字符串.
		//callback( {"xx":"xx","xx":"xx"} );
		JsonNode json = JsonHelper.getFirstNode(body.substring(10,body.length()-1));
		return (String) JsonHelper.get(json, "openid");
	}
}
