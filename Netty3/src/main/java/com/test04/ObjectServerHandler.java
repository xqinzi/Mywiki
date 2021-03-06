package com.test04;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

/**
 * 对象传递服务端代码
 * 
 * @author lihzh
 * @alia OneCoder
 * @blog http://www.it165.net
 */
public class ObjectServerHandler extends SimpleChannelHandler {

	/**
	 * 当接受到消息的时候触发www.it165.net
	 */
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		Command command = (Command) e.getMessage();
		// 打印看看是不是我们刚才传过来的那个
		System.out.println(command.getActionName());
	}
}