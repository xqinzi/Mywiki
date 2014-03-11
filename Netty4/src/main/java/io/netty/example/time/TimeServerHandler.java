package io.netty.example.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(final ChannelHandlerContext ctx) { // (1)
        final ByteBuf time = ctx.alloc().buffer(4); // (2)  获取4个字节的buf用来作为发送消息
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L)); //相当于打包数据到buffer中
        
        final ChannelFuture f = ctx.writeAndFlush(time); // (3) 
        f.addListener(new ChannelFutureListener() {
            
            public void operationComplete(ChannelFuture future) {
                assert f == future;
                ctx.close();
            }
        }); // (4)
    }

}