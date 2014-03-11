package com.mywiki.util.mail;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * 发送Email工具类
 */
public class Email {

	private JavaMailSender javaMailSender;
	private SimpleMailMessage simpleMailMessage;
	private VelocityEngine velocityEngine;

	// Spring 依赖注入
	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

	// Spring 依赖注入
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	// Spring 依赖注入
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	/**
	 * 发送简单邮件
	 * 
	 * @param subject
	 * @param content
	 * @param to
	 */
	public void sendMailSimple(String subject, String content, String to) {
		// 设定邮件参数
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(content);
		javaMailSender.send(simpleMailMessage); // 发送HTML邮件
	}

	/**
	 * 带附件的邮件发送
	 */
	public void sendMailattached(String subject, String content, String to,
			String attache) {
		try {
			// 使用JavaMail的MimeMessage，支付更加复杂的邮件格式和内容
			MimeMessage msg = javaMailSender.createMimeMessage();
			// 创建MimeMessageHelper对象，处理MimeMessage的辅助类
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);
			// 使用辅助类MimeMessage设定参数
			helper.setFrom(simpleMailMessage.getFrom());
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content);
			// 加载文件资源，作为附件
			ClassPathResource file = new ClassPathResource(attache);
			// 加入附件
			helper.addAttachment("attachment.jpg", file);
			// 发送邮件
			javaMailSender.send(msg);
		} catch (Exception e) {
			System.out.println("异常信息：" + e);
		}
	}

	/**
	 * 发送富文本邮件
	 */
	public void sendMailrichContent(String subject, String content, String to,
			String attache) {
		try {
			MimeMessage msg = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
			helper.setFrom(simpleMailMessage.getFrom());
			helper.setTo(to);
			helper.setSubject(subject);
			// 第二个参数true，表示text的内容为html，然后注意<img/>标签，src='cid:file'，'cid'是contentId的缩写，'file'是一个标记，
			// 需要在后面的代码中调用MimeMessageHelper的addInline方法替代成文件
			helper.setText(
					"<body><p>Hello Html Email</p><img src='cid:file' width='200' height='200'/></body>",
					true);
			/**
			 * ClassPathResource：类路径资源,我这里的附件是在项目里的,所以需要用ClassPathResource
			 * 如果是系统文件资源就不能用ClassPathResource,而要用FileSystemResource,例：
			 * FileSystemResource file = new FileSystemResource(new
			 * File(attache));
			 */
			ClassPathResource image = new ClassPathResource(attache);
			helper.addInline("file", image);
			javaMailSender.send(msg);
		} catch (Exception e) {
			System.out.println("异常信息：" + e);
		}
	}

	/**
	 * 使用Velocity模板发送邮件
	 * 
	 * @param subject
	 * @param content
	 * @param to
	 * @param vm
	 */
	public void sendMailVelocity(String subject, String content, String to,
			String vm) {
		try {
			// 声明Map对象，并填入用来填充模板文件的键值对
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("user", "MZULE");
			model.put("content", "Hello");
			// Spring提供的VelocityEngineUtils将模板进行数据填充，并转换成普通的String对象
			String emailText = VelocityEngineUtils.mergeTemplateIntoString(
					velocityEngine, vm, "UTF-8", model);

			// 和上面一样的发送邮件的工作
			MimeMessage msg = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);
			helper.setFrom(simpleMailMessage.getFrom());
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(emailText, true);

			javaMailSender.send(msg);
		} catch (Exception e) {
			System.out.println("异常信息：" + e);
		}
	}
}