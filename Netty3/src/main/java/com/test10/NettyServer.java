package com.test10;


import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ClassResolvers;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;

/**
 * netty传输对象
 * @author root
 *
 */
public class NettyServer {
	private int port = 8080;
	private ServerBootstrap bootstrap;
	private SimpleChannelHandler handler;
	/**
	 * 初始化服务器端
	 */
	public void init() {
		bootstrap = new ServerBootstrap(
				new NioServerSocketChannelFactory(
						Executors.newCachedThreadPool(), //boss 监听请求，并分派给slave进行处理
				        Executors.newCachedThreadPool()//slave 处理请求，将其丢到线程池中处理
				) 
		); 

		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				/*典型的过滤式处理*/
				// 传递对象，注释掉字符流的编码器
				//pipeline.addLast("encode", new StringEncoder());
				//pipeline.addLast("decode", new StringDecoder());
				
				/*添加自定义的handler，对请求进行处理*/  
                pipeline.addLast("encode", new ObjectEncoder());  
                pipeline.addLast("decode", new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));  
				
				/*添加自定义的handler，对请求进行处理*/
				pipeline.addLast("handler", handler);
				return pipeline;
			}
		});
		
		/*使用tcp长连接*/
		bootstrap.setOption("child.tcpNoDelay", true);
		bootstrap.setOption("child.keepAlive", true);
		bootstrap.setOption("reuseAddress", true);
	}
	
	/**
	 * 绑定端口，启动netty服务
	 */
	public void start() {
		bootstrap.bind(new InetSocketAddress(port));
		System.out.println("服务器启动,端口:" + port);
	}
	
	/**
	 * 关闭netty，释放资源。	
	 */
	public void stop() {
		bootstrap.releaseExternalResources();
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setHandler(SimpleChannelHandler handler) {
		this.handler = handler;
	}
}