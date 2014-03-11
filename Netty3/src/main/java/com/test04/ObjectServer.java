package com.test04;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ClassResolvers;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;

/**
 * @author lihzh
 * @alia OneCoder
 * @blog http://www.coderli.com
 */
public class ObjectServer {

	public static void main(String args[]) {
		// Server服务启动器
		ServerBootstrap bootstrap = new ServerBootstrap(
				new NioServerSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool()));
		// 设置一个处理客户端消息和各种消息事件的类(Handler)
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
		    
		    public ChannelPipeline getPipeline() throws Exception {
		    	
		        return Channels.pipeline(
		        		new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())),
		        		new ObjectServerHandler());
		    }
		});
		// 开放8000端口供客户端访问。
		bootstrap.bind(new InetSocketAddress(8000));
	}
}