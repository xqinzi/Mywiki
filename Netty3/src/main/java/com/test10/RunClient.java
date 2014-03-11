package com.test10;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
  
/** 
 * TODO 
 * Administrator 2013-3-23下午07:50:49 
 */  
public class RunClient {  
      
    public static void main(String[] args) {  
          
        String contextFile = "/com/test10/spring-client.xml";  
          
        ApplicationContext context = null;  
        try {  
            context = new ClassPathXmlApplicationContext(contextFile);  
        } catch (Exception e) {  
            System.out.println("RunMain has some exception");  
            e.printStackTrace();  
        }  
        ClientThread client = (ClientThread)context.getBean("clientThread");  
          
        client.init();  
        client.start();  
    }  
}  