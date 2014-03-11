package com.json;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.Delimiters;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

public class EchoServer {
	private static final Logger LOGGER = Logger.getLogger(EchoServer.class);

	private static final int PORT = 10007;
	private static final String FRAMER = "framer";
	private static final String DECODER = "decoder";
	private static final String ENCODER = "encoder";
	private static final String HANDLER = "handler";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		LOGGER.info("start netty echo server.");
		ChannelFactory factory = new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool());

		ServerBootstrap bootstrap = new ServerBootstrap(factory);

		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast(FRAMER, new DelimiterBasedFrameDecoder(
						Integer.MAX_VALUE, Delimiters.lineDelimiter()));
				pipeline.addLast(DECODER, new StringDecoder());
				pipeline.addLast(ENCODER, new StringEncoder());
				pipeline.addLast(HANDLER, new EchoServerHandler());
				return pipeline;
			}
		});

		bootstrap.setOption("child.tcpNoDelay", true);
		bootstrap.setOption("child.keepAlive", true);

		bootstrap.bind(new InetSocketAddress(PORT));

	}

}
