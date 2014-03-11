package com.proto01;
import org.apache.log4j.Logger;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class ProtobufChannelHandler extends SimpleChannelHandler {

	private static final Logger log = Logger.getLogger(ProtobufChannelHandler.class);
    /**
     * 当接受到消息, 输出消息
     * @param ctx
     * @param e
     */
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        Object message = e.getMessage();
        //为了节省代码量,这里这里判断是request还是response.
        if(message instanceof SocketCommand.RequestCommand) {
            SocketCommand.RequestCommand c = (SocketCommand.RequestCommand)message ;
            log.info(c.getAuth() + ", " + c.getCommand());

            SocketCommand.ResponseCommand.Builder builder =
                    SocketCommand.ResponseCommand.newBuilder();

            builder.setSuccess(true);
            builder.setMessage("OK");

            SocketCommand.ResponseCommand response = builder.build();

            ctx.getChannel().write(response);
        } else if(message instanceof SocketCommand.ResponseCommand) {
            SocketCommand.ResponseCommand responseCommand = (SocketCommand.ResponseCommand)message ;
            log.info(responseCommand.getSuccess() + ", " + responseCommand.getMessage());
        }
    }
}