package com.cp;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.log4j.xml.DOMConfigurator;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cp.netty.ServerPipelineFactory;

 
/** 
* 	作者:chenpeng  
*	E-mail:46731706@qq.com  
* 	创建时间：2012-7-12 下午12:22:53  
* 	类说明  netty game 
*/ 
public class ServerTest {


	@SuppressWarnings("resource")
	public static void main(String[] args) {
		DOMConfigurator.configureAndWatch("config/log4j.xml");
		// ApplicationContext factory = new FileSystemXmlApplicationContext(new String[] { "config/propholder.xml" });
		// 用ClassPathXmlApplicationContext，好处是propholder.xml放在classpath
		ApplicationContext factory = new ClassPathXmlApplicationContext(new String[] { "config/propholder.xml" });
	    ServerBootstrap bootstrap = new ServerBootstrap(
	            new NioServerSocketChannelFactory( 
	                    Executors.newCachedThreadPool(), 
	                    Executors.newCachedThreadPool())
	            ); 
	    ServerPipelineFactory httpServerPipelineFactory=(ServerPipelineFactory)factory.getBean("serverPipelineFactory");
	    bootstrap.setPipelineFactory(httpServerPipelineFactory); 
	    //启动端口 8888
	    bootstrap.bind(new InetSocketAddress(8888)); 
	    System.out.println("Server is starting at 8888");
	}
}