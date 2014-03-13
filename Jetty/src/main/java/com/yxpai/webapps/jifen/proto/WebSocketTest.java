package com.yxpai.webapps.jifen.proto;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class WebSocketTest {
	
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);		
		context.addServlet(new ServletHolder(new WebSocketServletMain()), "/web");

		server.start();
		server.join();
	}
}
