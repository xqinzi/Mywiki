package com.test02;

import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * 创建一个Server类，用来通过spring来启动Jetty server
 * @author root
 *
 */
public class MyServer {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		new ClassPathXmlApplicationContext("spring.xml");
	}
}