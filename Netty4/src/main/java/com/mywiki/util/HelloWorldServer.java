package com.mywiki.util;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Netty4 服务端代码
 *
 * @author lihzh
 * @date 2013年11月15日 下午1:10:06
 * @website http://www.coderli.com
 */
public class HelloWorldServer {
 
      public static void main(String[] args) {
           // EventLoop 代替原来的 ChannelFactory
          EventLoopGroup bossGroup = new NioEventLoopGroup();
          EventLoopGroup workerGroup = new NioEventLoopGroup();
           try {
               ServerBootstrap serverBootstrap = new ServerBootstrap();
               // server端采用简洁的连写方式，client端才用分段普通写法。
              serverBootstrap.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel. class )
                        .childHandler( new ChannelInitializer<SocketChannel>() {
                              @Override
                              public void initChannel(SocketChannel ch)
                                       throws Exception {
                                  ch.pipeline().addLast( new HelloServerHandler());
                             }
                        }).option(ChannelOption. SO_KEEPALIVE , true );
 
              ChannelFuture f = serverBootstrap.bind(8000).sync();
              f.channel().closeFuture().sync();
          } catch (InterruptedException e) {
          } finally {
              workerGroup.shutdownGracefully();
              bossGroup.shutdownGracefully();
          }
     }
 
      private static class HelloServerHandler extends
              ChannelInboundHandlerAdapter {
 
           /**
           * 当绑定到服务端的时候触发，打印"Hello world, I'm client."
           *
           * @alia OneCoder
           * @author lihzh
           * @date 2013年11月16日 上午12:50:47
           */
           @Override
           public void channelActive(ChannelHandlerContext ctx) throws Exception {
              System. out .println("Hello world, I'm server.");
          }
     }
 
}