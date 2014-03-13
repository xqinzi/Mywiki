package com.yxpai.webapps.jifen.proto;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class WebSocketServletMain extends WebSocketServlet {

	private static final long serialVersionUID = -2964802839253009970L;

	public void configure(WebSocketServletFactory factory) {
		factory.register(WebSocketimpl.class);
	}
}