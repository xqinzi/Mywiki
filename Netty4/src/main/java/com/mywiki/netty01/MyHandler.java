package com.mywiki.netty01;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

class MyHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		// super.channelRead(ctx, msg);
		try {
			System.out.println(((ByteBuf) msg).readableBytes());
			System.out.println(((ByteBuf) msg).toString(io.netty.util.CharsetUtil.US_ASCII));
			System.out.println(((ByteBuf) msg).toString(io.netty.util.CharsetUtil.US_ASCII));

			// ctx.write(msg);
			// ctx.flush();

		} finally {
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
	}
}