package com.test04;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * 运行标准的war文件
 * @author root
 *
 */
public class WebAppContextWithWarServer {
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);

		WebAppContext context = new WebAppContext();
		//指定context路径为"/myapp"
		context.setContextPath("/myapp");
		//指定了war文件的路径
		context.setWar("E:/share/test/struts2-blank.war");
		server.setHandler(context);

		server.start();
		server.join();
	}
}