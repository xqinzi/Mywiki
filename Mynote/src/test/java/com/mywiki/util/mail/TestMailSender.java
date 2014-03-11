package com.mywiki.util.mail;

import org.junit.Test;


public class TestMailSender {
	
	/**
	 * 使用smtp的方式发送
	 */
	@SuppressWarnings("static-access")
	@Test
	public void test01(){
	      Mailinfo mailInfo = new Mailinfo();    
	      mailInfo.setMailServerHost("smtp.163.com");    
	      mailInfo.setMailServerPort("25");  
	      mailInfo.setUserName("mywiki@163.com");   
	      mailInfo.setPassword("password");
	      mailInfo.setSsl(false);
	      
	      mailInfo.setFromAddress("mywiki@163.com");    
	      mailInfo.setToAddress("394907365@qq.com");    
	      mailInfo.setSubject("设置邮箱标题");
	      mailInfo.setContent("设置邮箱内容");
	         
	      //这个类主要来发送邮件   
	      MailSender sms = new MailSender();   
          sms.sendTextMail(mailInfo);//发送文体格式
          sms.sendHtmlMail(mailInfo);//发送html格式
	}
	
	/**
	 * 使用ssl的方式发送
	 */
	@SuppressWarnings("static-access")
	@Test
	public void test02(){
		//这个类主要是设置邮件   
	      Mailinfo mailInfo = new Mailinfo();    
	      mailInfo.setMailServerHost("smtp.exmail.qq.com");    
	      mailInfo.setMailServerPort("465");  
	      mailInfo.setUserName("zongyao.zhang@yxpai.com");   
	      mailInfo.setPassword("password");
	      mailInfo.setSsl(true);
	      
	      mailInfo.setFromAddress("zongyao.zhang@yxpai.com");    
	      mailInfo.setToAddress("394907365@qq.com");    
	      mailInfo.setSubject("设置邮箱标题");    
	      mailInfo.setContent("设置邮箱内容");
	         
	      //这个类主要来发送邮件
	      MailSender sms = new MailSender();
          sms.sendTextMail(mailInfo);//发送文体格式
          sms.sendHtmlMail(mailInfo);//发送html格式
	}
}
