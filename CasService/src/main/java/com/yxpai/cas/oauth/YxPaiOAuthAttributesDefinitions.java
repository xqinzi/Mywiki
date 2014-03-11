package com.yxpai.cas.oauth;

import org.scribe.up.profile.AttributesDefinition;
import org.scribe.up.profile.dropbox.DropBoxAttributesDefinition;
import org.scribe.up.profile.facebook.FacebookAttributesDefinition;
import org.scribe.up.profile.github.GitHubAttributesDefinition;
import org.scribe.up.profile.google.GoogleAttributesDefinition;
import org.scribe.up.profile.google2.Google2AttributesDefinition;
import org.scribe.up.profile.linkedin.LinkedInAttributesDefinition;
import org.scribe.up.profile.twitter.TwitterAttributesDefinition;
import org.scribe.up.profile.windowslive.WindowsLiveAttributesDefinition;
import org.scribe.up.profile.wordpress.WordPressAttributesDefinition;
import org.scribe.up.profile.yahoo.YahooAttributesDefinition;
import com.yxpai.cas.oauth.tencent.QQAttributesDefinition;
import com.yxpai.cas.oauth.weibo.WeiBoAttributesDefinition;

/**
 * org.scribe.up.profile.OAuthAttributesDefinitions是final类，重新定义一个类，增加属性定义
 */
public class YxPaiOAuthAttributesDefinitions{
	
	public final static AttributesDefinition facebookDefinition = new FacebookAttributesDefinition();
    
    public final static AttributesDefinition githubDefinition = new GitHubAttributesDefinition();
    
    public final static AttributesDefinition googleDefinition = new GoogleAttributesDefinition();
    
    public final static AttributesDefinition google2Definition = new Google2AttributesDefinition();
    
    public final static AttributesDefinition linkedinDefinition = new LinkedInAttributesDefinition();
    
    public final static AttributesDefinition twitterDefinition = new TwitterAttributesDefinition();
    
    public final static AttributesDefinition yahooDefinition = new YahooAttributesDefinition();
    
    public final static AttributesDefinition windowsLiveDefinition = new WindowsLiveAttributesDefinition();
    
    public final static AttributesDefinition wordPressDefinition = new WordPressAttributesDefinition();
    
    public final static AttributesDefinition dropBoxDefinition = new DropBoxAttributesDefinition();

    public final static QQAttributesDefinition qqDefinition = new QQAttributesDefinition();
    
    public final static WeiBoAttributesDefinition weiBoDefinition = new WeiBoAttributesDefinition();
}
