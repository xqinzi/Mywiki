package com.udp;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
/**
 * ChannelPipelineFactory实现类，包装ChannelHandler，处理I/O事件。
 * @author root
 *
 */
@Component("serverChannelPipelineFactory")
public class ServerChannelPipelineFactory implements ChannelPipelineFactory
{
	@Autowired
	@Qualifier("receiverHandler")
	private ReceiverHandler handler;
	
	public ChannelPipeline getPipeline() throws Exception 
	{
		ChannelPipeline pipeline=Channels.pipeline();
		pipeline.addLast("handler", this.handler);
		return pipeline;
	}
	public void setHandler(ReceiverHandler handler) {
		this.handler = handler;
	}
}
