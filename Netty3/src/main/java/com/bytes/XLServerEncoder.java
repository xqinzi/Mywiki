package com.bytes;
import java.nio.ByteBuffer;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelDownstreamHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  @author hankchen
 *  2012-2-3 上午10:48:15
 */

/**
 * 服务器端编码器
 */
public class XLServerEncoder extends SimpleChannelDownstreamHandler {
    Logger logger=LoggerFactory.getLogger(XLServerEncoder.class);
    
    @Override
    public void writeRequested(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        XLResponse response=(XLResponse)e.getMessage();
        ByteBuffer headBuffer=ByteBuffer.allocate(16);
        /**
         * 先组织报文头
         */
        headBuffer.put(response.getEncode());
        headBuffer.put(response.getEncrypt());
        headBuffer.put(response.getExtend1());
        headBuffer.put(response.getExtend2());
        headBuffer.putInt(response.getSessionid());
        headBuffer.putInt(response.getResult());
        
        /**
         * 组织报文的数据部分
         */
        ChannelBuffer dataBuffer=ProtocolUtil.encode(response.getEncode(),response.getValues()); 
        int length=dataBuffer.readableBytes();
        headBuffer.putInt(length);
        /**
         * 非常重要
         * ByteBuffer需要手动flip()，ChannelBuffer不需要
         */
        headBuffer.flip();
        ChannelBuffer totalBuffer=ChannelBuffers.dynamicBuffer();
        totalBuffer.writeBytes(headBuffer);
        logger.info("totalBuffer size="+totalBuffer.readableBytes());
        totalBuffer.writeBytes(dataBuffer);
        logger.info("totalBuffer size="+totalBuffer.readableBytes());
        Channels.write(ctx, e.getFuture(), totalBuffer);
    }

}