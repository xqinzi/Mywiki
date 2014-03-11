package com.test01;

import java.util.Date;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class TimeServerHandler extends IoHandlerAdapter {
	
	// 处理正常的远程连接过程时抛出的异常。如果这一方法没有定义，可能无法正常报告异常。
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		cause.printStackTrace();
	}
	
	//messageReceived 方法会从客户端接收数据并将当前时间回写给客户端。如果接收自客户端的消息是单词 "quit"，那么当前 session 将被关闭。
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String str = message.toString();
		if (str.trim().equalsIgnoreCase("quit")) {
			session.close(true);
			return;
		}
		Date date = new Date();
		session.write(date.toString());
		System.out.println("Message written...");
	}

	//一旦 session 保持空闲状态到达 acceptor.getSessionConfig().setIdleTime( IdleStatus.BOTH_IDLE, 10 ); 所定义的时间长度，sessionIdle 方法会被调用。
	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		System.out.println("IDLE " + session.getIdleCount(status));
	}
}
