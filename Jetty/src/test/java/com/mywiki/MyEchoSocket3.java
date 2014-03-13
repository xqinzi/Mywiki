package com.mywiki;

import java.io.IOException;

import org.eclipse.jetty.util.FutureCallback;
import org.eclipse.jetty.websocket.core.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.core.annotations.WebSocket;
import org.eclipse.jetty.websocket.core.api.WebSocketConnection;

@WebSocket(maxTextSize = 64 * 1024)
public class MyEchoSocket3 {
	@OnWebSocketMessage
	public void onText(WebSocketConnection conn, String message) {
		if (conn.isOpen()) {
			return;
		}
		try {
			conn.write(null, new FutureCallback(), message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnWebSocketMessage
	public void onTextMethod(String message) {
		// simple TEXT message received
	}

	@OnWebSocketMessage
	public void onTextMethod(WebSocketConnection connection, String message) {
		// simple TEXT message received, with Connection
		// that it occurred on.
	}

	@OnWebSocketMessage
	public void onBinaryMethod(byte data[], int offset, int length) {
		// simple BINARY message received
	}

	@OnWebSocketMessage
	public void onBinaryMethod(WebSocketConnection connection, byte data[],
			int offset, int length) {
		// simple BINARY message received, with Connection
		// that it occurred on.
	}
}
