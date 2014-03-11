package com.mywiki.util.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-mail.xml")
public class TestEmail {
	
	@Autowired
	Email mail;
	
	//@Before
	@SuppressWarnings("resource")
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-mail.xml");
        mail = (Email)context.getBean("simpleMail");
	}
	
	/**
	 * 发送简单邮件
	 */
	@Test
	public void test01(){
        mail.sendMailSimple("Spring SMTP Mail HTML Subject", "Spring SMTP HTML Text Content", "394907365@qq.com");
	}
	
	
	/**
	 * 带附件的邮件发送
	 */
	@Test
	public void test02(){
        mail.sendMailattached("Spring SMTP Mail HTML Subject", "Spring SMTP HTML Text Content", "394907365@qq.com","mail/mail.png");
	}
	
	/**
	 * 带附件的邮件发送
	 */
	@Test
	public void test03(){
        mail.sendMailrichContent("Spring SMTP Mail HTML Subject", "Spring SMTP HTML Text Content", "394907365@qq.com","mail/mail.png");
	}
	
	/**
	 * 使用Velocity模板发送邮件
	 */
	@Test
	public void test04(){
        mail.sendMailVelocity("Spring SMTP Mail HTML Subject", "Spring SMTP HTML Text Content", "394907365@qq.com","mail/mail.vm");
	}
	
}
