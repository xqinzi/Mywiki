package com.yxpai.cas.oauth.weibo;

import org.scribe.up.profile.OAuthAttributesDefinition;
import org.scribe.up.profile.converter.Converters;

public class WeiBoAttributesDefinition extends OAuthAttributesDefinition {
	/**
	 * 昵称
	 */
	public static final String SCREEN_NAME = "screen_name";
	/**
	 * 名称
	 */
	public static final String NAME = "name";
	/**
	 * 省
	 */
	public static final String PROVINCE = "province";
	/**
	 * 城市
	 */
	public static final String CITY = "city";
	/**
	 * 地址
	 */
	public static final String LOCATION = "location";
	/**
	 * 签名
	 */
	public static final String DESCRIPTION = "description";
	/**
	 * 微博头像
	 */
	public static final String PROFILE_IMAGE_URL = "profile_image_url";
	/**
	 * 性别
	 */
	public static final String GENDER = "gender";
	/**
	 * 账号创建时间
	 */
	public static final String CREATED_AT = "created_at";
	
	public static final String AUTHORITIES = "authorities";
	
	/**
	 * Oauth用户关联的系统用户
	 */
	public static final String UUID = "uuid";
	
	public WeiBoAttributesDefinition() {
		addAttribute(SCREEN_NAME, Converters.stringConverter);
		addAttribute(NAME, Converters.stringConverter);
		addAttribute(PROVINCE, Converters.stringConverter);
		addAttribute(CITY, Converters.stringConverter);
		addAttribute(LOCATION, Converters.stringConverter);
		addAttribute(DESCRIPTION, Converters.stringConverter);
		addAttribute(PROFILE_IMAGE_URL, Converters.stringConverter);
		addAttribute(GENDER, Converters.stringConverter);
		addAttribute(CREATED_AT, Converters.stringConverter);
		addAttribute(AUTHORITIES, Converters.stringConverter);
		addAttribute(UUID, Converters.stringConverter);
	}
}
