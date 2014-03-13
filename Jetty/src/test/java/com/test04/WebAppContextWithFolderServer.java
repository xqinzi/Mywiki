package com.test04;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * 运行一个webapp目录
 * @author root
 *
 */
public class WebAppContextWithFolderServer {
	public static void main(String[] args) throws Exception {
		/**
		Server server = new Server();  
		SelectChannelConnector connector = new SelectChannelConnector();  
		connector.setPort(8080);  
		server.addConnector(connector);  
		WebAppContext context = new WebAppContext();  
		context.setContextPath("/");  
		context.setDescriptor("web/WEB-INF/web.xml");  
		context.setResourceBase("web");  
		context.setConfigurationDiscovered(true);  
		server.setHandler(context);  
		server.start();
		 */
		
		Server server = new Server(8080);

		WebAppContext context = new WebAppContext();
		//指定context路径为"/myapp"
		context.setContextPath("/myapp");
		//指定了webapp目录
		context.setDescriptor("E:/share/test/struts2-blank/WEB-INF/web.xml");
		context.setResourceBase("E:/share/test/struts2-blank");
		
		// 是否启用JSR315的servlet3.0自动扫描发现的配置功能，默认为true
		context.setConfigurationDiscovered(true);  
		context.setParentLoaderPriority(true);
		server.setHandler(context);

		server.start();
		server.join();
	}
}