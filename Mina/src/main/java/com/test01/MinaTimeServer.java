package com.test01;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaTimeServer {
	
	private static final int PORT = 9123;  
	
	public static void main(String[] args) throws Exception {
		
		//1 需要一个用于监听连入的连接的对象。因为本程序基于 TCP/IP，我们在程序中添加了 SocketAcceptor。
		IoAcceptor acceptor = new NioSocketAcceptor();  
		
		//2 第一个过滤器。过滤器将会日志记录所有信息，比如 session 的新建、接收到的消息、发送的消息、session 的关闭。
		acceptor.getFilterChain().addLast( "logger", new LoggingFilter() );  
        //  接下来的过滤器是一个 ProtocolCodecFilter。这个过滤器将会把二进制或者协议特定的数据翻译为消息对象，
		//  反之亦然。我们使用一个现有的 TextLine 工厂因为它将为你处理基于文本的消息
		acceptor.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new TextLineCodecFactory( Charset.forName( "UTF-8" ))));  
        
		
		//3 我们将定义用于侍服客户端连接和当前时间的请求的处理器。处理器类是一个必须实行 IoHandler 接口的类。对于几乎所有的使用 MINA 的程序，
		// 这里都会变成程序的重负载的地方，因为它将侍服所有来自客户端的请求。也扩展 IoHandlerAdapter 类，可以简化需要为满足在一个类中传递实现了 IoHandler 接口的需求而要编写的代码量。 
		acceptor.setHandler(  new TimeServerHandler() );  
		
		
		// 这些方法设置了 IoHandler属性，为 session 设置了输入缓冲区大小以及 idle 属性。指定缓冲区大小以通知底层操作系统为传入的数据分配多少空间。
		// 第二行指定了什么时候检查空闲 session。在对 setIdleTime 的调用中，第一个参数定义了再断定 session 是否闲置时要检查的行为，第二个参数定义了在 session 被视为空闲之前以毫秒为单位的时间长度内必须发生。
		acceptor.getSessionConfig().setReadBufferSize( 2048 );  
        acceptor.getSessionConfig().setIdleTime( IdleStatus.BOTH_IDLE, 10 );
		
		//4 定义处理类并绑定 NioSocketAcceptor 到一个端口
		acceptor.bind( new InetSocketAddress(PORT) );  
		
	}
}
