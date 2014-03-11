package com.mywiki.util.mail;

/**   
 * 封装Mail使用的信息
 */
import java.util.Properties;

public class Mailinfo {

	final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

	// 发送邮件的服务器和端口
	private String mailServerHost;
	private String mailServerPort;
	// 是否使用ssl加密发送
	private boolean ssl;
	// 登陆邮件发送服务器的用户名和密码
	private String userName;
	private String password;
	// 邮件发送者的地址
	private String fromAddress;
	
	// 邮件接收者的地址
	private String toAddress;
	
	// 邮件主题
	private String subject;
	// 邮件的文本内容
	private String content;
	
	// 邮件附件的文件名
	private String[] attachFileNames;

	/**
	 * 获得邮件会话属性
	 */
	public Properties getProperties() {
		// Properties p = new Properties();
		Properties p = System.getProperties();

		p.setProperty("mail.smtp.host", mailServerHost);
		p.setProperty("mail.smtp.port", mailServerPort);
		p.setProperty("mail.smtp.socketFactory.port", mailServerPort);
		p.setProperty("mail.smtp.auth", "true");
		if(ssl){
			p.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
			p.setProperty("mail.smtp.socketFactory.fallback", "false");
		}
		return p;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;    
	}

	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(String[] fileNames) {
		this.attachFileNames = fileNames;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String textContent) {
		this.content = textContent;
	}

	public boolean isSsl() {
		return ssl;
	}

	public void setSsl(boolean ssl) {
		this.ssl = ssl;
	}
}