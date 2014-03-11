package com.mywiki.netty01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
/**
 * 服务器接收并打印命令行输入的字符
 *
 */
public class DemoServer1 {

	public static void main(String[] args) throws InterruptedException {
		ServerBootstrap bootstrap = new ServerBootstrap();
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup(1);
		try {
			bootstrap.group(bossGroup, workerGroup);
			bootstrap.channel(NioServerSocketChannel.class);

			bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new MyHandler());
				}

			});
			bootstrap.option(ChannelOption.SO_BACKLOG, 128);
			bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
			ChannelFuture cfuture = bootstrap.bind(8080).sync();
			cfuture.channel().closeFuture().sync();

		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}

	}

}