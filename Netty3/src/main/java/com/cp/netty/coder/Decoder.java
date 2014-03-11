package com.cp.netty.coder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;


/** 
* 	作者:chenpeng  
*	E-mail:46731706@qq.com  
* 	创建时间：2012-7-12 上午11:22:14  
* 	协议解码器  
*   Decoder解码器继承于FrameDecoder，FrameDecoder是Netty codec包中的辅助类，它是个ChannelUpstreamHandler，
*   decode方法是FrameDecoder子类需要实现的。在本程序采用的是LengthFieldBasedFrameDecoder。
*   LengthFieldBasedFrameDecoder是基于长度字段的解码器。如果协议格式类似“内容长度”+内容、“固定头”+“内容长度”+动态内容这样的格式，
*   就可以使用该解码器 。至于其他类型的解码器，这里不再一一介绍。 
*/ 
public class Decoder extends LengthFieldBasedFrameDecoder {
	// 第一个参数为信息最大长度，超过这个长度回报异常，
	// 第二参数为长度属性的起始（偏移）位，我们的协议中长度是0到第3个字节，所以这里写0，
	// 第三个参数为“长度属性”的长度，我们是4个字节，所以写4，
	// 第四个参数为长度调节值，在总长被定义为包含包头长度时，修正信息长度，
	// 第五个参数为跳过的字节数，根据需要我们跳过前4个字节，以便接收端直接接受到不含“长度属性”的内容。

	public Decoder(int maxFrameLength, int lengthFieldOffset,
			int lengthFieldLength) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer) throws Exception {
		ChannelBuffer buffs = (ChannelBuffer)super.decode(ctx, channel, buffer); 
		return buffs;
	}

}
