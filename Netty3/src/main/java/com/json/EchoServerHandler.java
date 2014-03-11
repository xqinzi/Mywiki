package com.json;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.alibaba.fastjson.JSON;

public class EchoServerHandler extends SimpleChannelHandler {
	private static final Logger LOGGER = Logger.getLogger(EchoServer.class);

	private static final AtomicInteger COUNT = new AtomicInteger();

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		String inputString = (String) e.getMessage();

		try {
			TransportMessage request = JSON.parseObject(inputString,
					TransportMessage.class);

			String response = null;
			if (request.getCode() % 2 == 0) {
				response = JSON.toJSONString(new TransportMessage(7100,
						"request.getState()%2 == 0" + request.getContent()));
			} else {
				response = JSON.toJSONString(new TransportMessage(8100, request
						.getContent()));
			}

			Channel channel = e.getChannel();

			byte[] arr = new StringBuilder(response).append("\tcount:")
					.append(COUNT.getAndIncrement()).append("\n").toString()
					.getBytes();
			ChannelBuffer word = ChannelBuffers.buffer(arr.length);
			word.writeBytes(arr);
			channel.write(word);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(Thread.currentThread().getName() + ":"
						+ e.getRemoteAddress() + " receives message : "
						+ inputString + " -- send message : " + response);
			}
			
			System.out.println(Thread.currentThread().getName() + ":"
						+ e.getRemoteAddress() + " receives message : "
						+ inputString + " -- send message : " + response);
			
		} catch (Exception e1) {
			LOGGER.error("Error:" + e.getMessage(), e1);
		}
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		ctx.sendUpstream(e);
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		ctx.sendUpstream(e);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		LOGGER.error("Error:" + e.getCause().getMessage(), e.getCause());
		Channel ch = e.getChannel();
		ch.close();
	}
}