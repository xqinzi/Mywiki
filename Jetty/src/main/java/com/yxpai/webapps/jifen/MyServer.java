package com.yxpai.webapps.jifen;

import org.eclipse.jetty.server.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyServer {  
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-webapp-jifen.xml");
        Server server = (Server) context.getBean("server");
        
        server.start();
        server.join();
    }  
}