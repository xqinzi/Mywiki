package com.proto01;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.protobuf.ProtobufDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufEncoder;


public class NettyServer {

	public static void main(String[] args) {
		int port = 8080;
	    ServerBootstrap bootstrap = new ServerBootstrap(
	                new NioServerSocketChannelFactory(
	                        Executors.newCachedThreadPool(),
	                        Executors.newCachedThreadPool())
	                );

	    bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
	    	public ChannelPipeline getPipeline() throws Exception {
	    	    ChannelPipeline pipeline = Channels.pipeline();
	    	    pipeline.addLast("decoder", new ProtobufDecoder(SocketCommand.RequestCommand.getDefaultInstance()));
	    	    pipeline.addLast("encoder", new ProtobufEncoder());
	    	    pipeline.addLast("handler", new ProtobufChannelHandler());
	    	    return pipeline;
	    	}
		});
	    bootstrap.setOption("child.tcpNoDelay", true); //注意child前缀
	    bootstrap.setOption("child.keepAlive", true); //注意child前缀

	    //启动端口 8080
	    bootstrap.bind(new InetSocketAddress(port));
	    System.out.println("listening port: " + port + "  server is starting……");
	}
}
