package com.udp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class NettyTestRun 
{
	@SuppressWarnings("resource")
    public static void main( String[] args )
    {
		ApplicationContext context=new ClassPathXmlApplicationContext("classpath*:nettyTest-context.xml");
        IServer server=(IServer)context.getBean("serverNettyImpl");
        server.start();
    }
}
