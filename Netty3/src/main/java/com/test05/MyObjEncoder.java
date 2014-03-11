package com.test05;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelDownstreamHandler;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;

/**
 * Object编码类
 * 
 * @author lihzh
 * @alia OneCoder
 * @blog http://www.it165.net
 */
public class MyObjEncoder implements ChannelDownstreamHandler {

	public void handleDownstream(ChannelHandlerContext ctx, ChannelEvent e)
			throws Exception {
		// 处理收发信息的情形
		if (e instanceof MessageEvent) {
			MessageEvent mEvent = (MessageEvent) e;
			Object obj = mEvent.getMessage();
			if (!(obj instanceof Command)) {
				ctx.sendDownstream(e);
				return;
			}
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(obj);
			oos.flush();
			oos.close();
			ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
			buffer.writeBytes(out.toByteArray());
			e.getChannel().write(buffer);
		} else {
			// 其他事件，自动流转。比如，bind，connected
			ctx.sendDownstream(e);
		}
	}
}