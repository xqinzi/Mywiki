package com.proto01;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.protobuf.ProtobufDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufEncoder;

import com.google.protobuf.ByteString;

public class NettyClient {
	
	public static void main(String[] args) {
		ClientBootstrap bootstrap = new ClientBootstrap(
				new NioClientSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool())
				);
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
			    ChannelPipeline pipeline = Channels.pipeline();
			    pipeline.addLast("decoder", new ProtobufDecoder(SocketCommand.ResponseCommand.getDefaultInstance()));
			    pipeline.addLast("encoder", new ProtobufEncoder());
			    pipeline.addLast("handler", new ProtobufChannelHandler());
			    return pipeline;
			}
		});
		
		
		// 创建无连接传输channel的辅助类(UDP),包括client和server
		ChannelFuture future = bootstrap.connect(new InetSocketAddress("127.0.0.1", 8080));
		/*
		 * 给足够的时间, 让建立连接
		 */
		try {
		    future.await(500);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}

		/* 连接成功否? */
		if(future.isSuccess()){
		    Channel w = future.getChannel();

		    SocketCommand.RequestCommand.Builder builder = SocketCommand.RequestCommand.newBuilder();
		    builder.setAuth("kkk@email.com");
		    builder.setCommand("shutdown");
		    builder.setAuthBytes(ByteString.copyFromUtf8("Hello"));

		    SocketCommand.RequestCommand command = builder.build();

		    ChannelFuture f = w.write(command);
		    //立即同步, 不等缓冲区满了. 否则还得等待缓冲区满了才会发送给远程
		    f.syncUninterruptibly();

		}
		/* 关闭连接 */
		future.getChannel().getCloseFuture().awaitUninterruptibly();
		bootstrap.releaseExternalResources();
		System.out.println("====================end=========================");
	}
}
