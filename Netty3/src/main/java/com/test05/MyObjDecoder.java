package com.test05;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelUpstreamHandler;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;

/**
 * Object解码类
 * 
 * @author lihzh
 * @alia OneCoder
 * @blog http://www.it165.net
 */
public class MyObjDecoder implements ChannelUpstreamHandler {

	public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e)
			throws Exception {
		if (e instanceof MessageEvent) {
			MessageEvent mEvent = (MessageEvent) e;
			if (!(mEvent.getMessage() instanceof ChannelBuffer)) {
				ctx.sendUpstream(mEvent);
				return;
			}
			ChannelBuffer buffer = (ChannelBuffer) mEvent.getMessage();
			ByteArrayInputStream input = new ByteArrayInputStream(buffer.array());
			ObjectInputStream ois = new ObjectInputStream(input);
			Object obj = ois.readObject();
			Channels.fireMessageReceived(e.getChannel(), obj);
		}
	}
}