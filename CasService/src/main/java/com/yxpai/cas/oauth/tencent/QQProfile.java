package com.yxpai.cas.oauth.tencent;

import java.util.Locale;

import org.scribe.up.profile.AttributesDefinition;
import org.scribe.up.profile.BaseOAuthProfile;
import org.scribe.up.profile.CommonProfile;
import org.scribe.up.profile.Gender;
import com.yxpai.cas.oauth.YxPaiOAuthAttributesDefinitions;

public class QQProfile extends BaseOAuthProfile implements CommonProfile {
	private static final long serialVersionUID = -4062142278606475986L;

	@Override
	protected AttributesDefinition getAttributesDefinition() {
	        return YxPaiOAuthAttributesDefinitions.qqDefinition;
	}
	 
	@Override
	public String getEmail() {
		return null;
	}

	@Override
	public String getFirstName() {
		return null;
	}

	@Override
	public String getFamilyName() {
		return null;
	}

	@Override
	public String getDisplayName() {
		return (String) get(QQAttributesDefinition.NICKNAME);
	}

	@Override
	public String getUsername() {
		return (String) get(QQAttributesDefinition.NICKNAME);
	}

	@Override
	public Gender getGender() {
		String gender=(String) get(QQAttributesDefinition.GENDER);
		if("男".equals(gender))
			return Gender.MALE;
		return Gender.FEMALE;
	}

	@Override
	public Locale getLocale() {
		return null;
	}

	@Override
	public String getPictureUrl() {
		return null;
	}

	@Override
	public String getProfileUrl() {
		return null;
	}

	@Override
	public String getLocation() {
		return null;
	}
	
	public String getNickName() {
		return (String) get(QQAttributesDefinition.NICKNAME);
	}
	public String getFigureUrl1() {
		return (String) get(QQAttributesDefinition.FIGUREURL_1);
	}
	public String getFigureUrlQq1() {
		return (String) get(QQAttributesDefinition.FIGUREURL_QQ_1);
	}
	public String getIsYellowVip() {
		return (String) get(QQAttributesDefinition.IS_YELLOW_VIP);
	}
	public String getVip() {
		return (String) get(QQAttributesDefinition.VIP);
	}
	public String getYellowVipLevel() {
		return (String) get(QQAttributesDefinition.YELLOW_VIP_LEVEL);
	}
	public String getLevel() {
		return (String) get(QQAttributesDefinition.LEVEL);
	}
	public String getIsYellowYearVip(){
		return (String) get(QQAttributesDefinition.IS_YELLOW_YEAR_VIP);
	}
}
