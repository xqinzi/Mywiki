package com.yxpai.cas.oauth.weibo;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.extractors.AccessTokenExtractor;
import org.scribe.extractors.JsonTokenExtractor;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Verb;
import org.scribe.utils.OAuthEncoder;
import org.scribe.utils.Preconditions;

public class WeiboApi20 extends DefaultApi20 {

	private static final String AUTHORIZE_URL = "https://api.weibo.com/oauth2/authorize?client_id=%s&redirect_uri=%s&response_type=code";
	private static final String SCOPED_AUTHORIZE_URL = AUTHORIZE_URL + "&scope=%s";

	/**
	 * 新浪的access_token返回的是json格式的字符串，必须通过json进行解析
	 */
	@Override
	public AccessTokenExtractor getAccessTokenExtractor() {
		return new JsonTokenExtractor();
	}
	/**
	 * 重写父类方法，新浪的请求必须是POST请求
	 */
	@Override
	public Verb getAccessTokenVerb()
	{
		return Verb.POST;
	}
	
	@Override
	public String getAccessTokenEndpoint() {
		return "https://api.weibo.com/oauth2/access_token";
	}

	@Override
	public String getAuthorizationUrl(OAuthConfig config) {
		Preconditions
				.checkValidUrl(config.getCallback(),
						"Must provide a valid url as callback. Sina WeiBo does not support OOB");
		// Append scope if present
		if (config.hasScope()) {
			return String.format(SCOPED_AUTHORIZE_URL, config.getApiKey(),
					OAuthEncoder.encode(config.getCallback()),
					OAuthEncoder.encode(config.getScope()));
		} else {
			return String.format(AUTHORIZE_URL, config.getApiKey(),
					OAuthEncoder.encode(config.getCallback()));
		}
	}
}