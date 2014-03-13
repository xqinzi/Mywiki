package com.yxpai.webapps.jifen.proto;

import java.io.IOException;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;

public class WebSocketimpl implements WebSocketListener {
	
	private Session session;
	@Override
	public void onWebSocketBinary(byte[] payload, int offset, int len) {
		System.out.println("receive message binary ...");
		String message = new String(payload);
		System.out.println(message);
		/*try {
			Test.Message msg = Test.Message.parseFrom(payload);
			switch(msg.getId()){
			case 101:
				Test.Person person = Test.Person.parseFrom(msg.getData().getBytes());
				System.out.println(person.getId());
				System.out.println(person.getName());
				System.out.println(person.getEmail());
				break;
			}
			
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	@Override
	public void onWebSocketClose(int statusCode, String reason) {
		System.out.println("socket closed. status code:" + statusCode + " reason:" + reason);
	}

	@Override
	public void onWebSocketConnect(Session session) {
		System.out.println("new connect ...");
		this.session = session;
	}

	@Override
	public void onWebSocketError(Throwable error) {
		System.out.println("error...");
	}

	@Override
	public void onWebSocketText(String message) {
		System.out.println("receive message text ...");
		System.out.println(message);
		try {
			session.getRemote().sendString(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}