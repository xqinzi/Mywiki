package com.test01;

import org.eclipse.jetty.server.Server;
/**
 * 嵌入式运行Jetty
 * @author root
 *
 */
public class MyServer {
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		server.setHandler(new HelloHandler());
		server.start();
		server.join();
	}
}