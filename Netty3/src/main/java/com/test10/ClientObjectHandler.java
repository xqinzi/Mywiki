package com.test10;

import java.util.Date;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

/**
 * TODO Administrator 2013-3-24下午04:37:18
 */
public class ClientObjectHandler extends SimpleChannelHandler {

	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		
		Object object = e.getMessage();
		System.out.println("Get message:" +"\n"+"\n");
		System.out.println("" + new Date().toString() + "\n" +
				"***************" +
				object.toString() +
				"***************");
	}
}