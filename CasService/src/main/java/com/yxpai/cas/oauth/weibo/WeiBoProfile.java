package com.yxpai.cas.oauth.weibo;

import java.util.Locale;

import org.scribe.up.profile.AttributesDefinition;
import org.scribe.up.profile.BaseOAuthProfile;
import org.scribe.up.profile.CommonProfile;
import org.scribe.up.profile.Gender;
import com.yxpai.cas.oauth.YxPaiOAuthAttributesDefinitions;

public class WeiBoProfile extends BaseOAuthProfile implements CommonProfile {
	private static final long serialVersionUID = -4062142278606475986L;

	@Override
	protected AttributesDefinition getAttributesDefinition() {
	        return YxPaiOAuthAttributesDefinitions.weiBoDefinition;
	}
	 
	@Override
	public String getEmail() {
		return (String) get("email");
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
		return (String) get(WeiBoAttributesDefinition.SCREEN_NAME);
	}

	@Override
	public String getUsername() {
		return (String) get(WeiBoAttributesDefinition.NAME);
	}

	@Override
	public Gender getGender() {
		String gender=(String) get(WeiBoAttributesDefinition.GENDER);
		if("f".equals(gender))
			return Gender.FEMALE;
		return Gender.MALE;
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
		return (String) get(WeiBoAttributesDefinition.LOCATION);
	}
	public String getScreenName(){
		return (String) get(WeiBoAttributesDefinition.SCREEN_NAME);
	}
	public String getName(){
		return (String) get(WeiBoAttributesDefinition.NAME);
	}
	public String getProvince(){
		return (String) get(WeiBoAttributesDefinition.PROVINCE);
	}
	public String getCity(){
		return (String) get(WeiBoAttributesDefinition.CITY);
	}
	public String getDescription(){
		return (String) get(WeiBoAttributesDefinition.DESCRIPTION);
	}
	public String getProfileImageUrl(){
		return (String) get(WeiBoAttributesDefinition.PROFILE_IMAGE_URL);
	}
	public String getCreatedAt(){
		return (String) get(WeiBoAttributesDefinition.CREATED_AT);
	}
}
