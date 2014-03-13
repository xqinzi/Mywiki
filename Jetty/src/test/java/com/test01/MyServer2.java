package com.test01;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

/**
 * 嵌入式运行Jetty多Connector
 * @author root
 *
 */
public class MyServer2 {
	public static void main(String[] args) throws Exception {
		Server server = new Server();

		ServerConnector connector1=new ServerConnector(server);
		connector1.setPort(8080);
		
		ServerConnector connector2=new ServerConnector(server);
		connector2.setPort(9090);

		server.setConnectors(new Connector[] { connector1, connector2 });

		server.setHandler(new HelloHandler());

		server.start();
		server.join();
	}
}