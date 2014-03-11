package com.yxpai.cas.oauth.tencent;

import org.scribe.up.profile.OAuthAttributesDefinition;
import org.scribe.up.profile.converter.Converters;

public class QQAttributesDefinition extends OAuthAttributesDefinition {
	/**
	 * 昵称
	 */
	public static final String NICKNAME = "nickname";
	/**
	 * 性别。 如果获取不到则默认返回"男"
	 */
	public static final String GENDER = "gender";
	/**
	 * 大小为50×50像素的QQ空间头像URL
	 */
	public static final String FIGUREURL_1 = "figureurl_1";
	/**
	 * 大小为40×40像素的QQ头像URL
	 */
	public static final String FIGUREURL_QQ_1 = "figureurl_qq_1";
	/**
	 * 标识用户是否为黄钻用户（0：不是；1：是）
	 */
	public static final String IS_YELLOW_VIP = "is_yellow_vip";
	/**
	 * 标识用户是否为黄钻用户（0：不是；1：是）
	 */
	public static final String VIP = "vip";
	/**
	 * 黄钻等级
	 */
	public static final String YELLOW_VIP_LEVEL = "yellow_vip_level";
	/**
	 * 黄钻等级
	 */
	public static final String LEVEL = "level";
	/**
	 * 标识是否为年费黄钻用户（0：不是； 1：是）
	 */
	public static final String IS_YELLOW_YEAR_VIP = "is_yellow_year_vip";
	
	public static final String AUTHORITIES = "authorities";
	/**
	 * Oauth用户关联的系统用户
	 */
	public static final String UUID = "uuid";
	
	public QQAttributesDefinition() {
		addAttribute(NICKNAME, Converters.stringConverter);
		addAttribute(GENDER, Converters.stringConverter);
		addAttribute(FIGUREURL_1, Converters.stringConverter);
		addAttribute(FIGUREURL_QQ_1, Converters.stringConverter);
		addAttribute(IS_YELLOW_VIP, Converters.stringConverter);
		addAttribute(VIP, Converters.stringConverter);
		addAttribute(YELLOW_VIP_LEVEL, Converters.stringConverter);
		addAttribute(LEVEL, Converters.stringConverter);
		addAttribute(IS_YELLOW_YEAR_VIP, Converters.stringConverter);
		addAttribute(AUTHORITIES, Converters.stringConverter);
		addAttribute(UUID, Converters.stringConverter);
	}
}
