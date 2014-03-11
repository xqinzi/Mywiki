package com.test10;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
  
public class RunServer {  
  
    public static void main(String[] args) {  
          
        String contextFile = "/com/test10/spring-server.xml";  
          
        ApplicationContext context = null;  
        try {  
            context = new ClassPathXmlApplicationContext(contextFile);  
        } catch (Exception e) {  
            System.out.println("RunServer has some exception");  
            e.printStackTrace();  
        }  
        final NettyServer server =(NettyServer)context.getBean("nettyServer");  
          
        Runtime.getRuntime().addShutdownHook(new Thread() {  
  
            @Override  
            public void run() {  
                try {  
                    server.stop();  
                } catch (Exception e) {  
                    System.out.println("run main stop error!");  
                }  
            }  
  
        }  );  
        server.init();  
        server.start();  
    }  
  
} 