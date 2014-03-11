package com.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.alibaba.fastjson.JSON;

public class SocketClient {

	
	private final Socket socket;

	public SocketClient(String host, int port) throws UnknownHostException,
			IOException {
		this.socket = new Socket(host, port);
		this.socket.setTcpNoDelay(true);
		this.socket.setTrafficClass(0x08 | 0x10);
		this.socket.setReuseAddress(true);
		
	}

	private PrintWriter getWriter(Socket socket) throws IOException {
		return new PrintWriter(socket.getOutputStream());
	}

	private BufferedReader getReader(Socket socket) throws IOException {
		return new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
	}

	private void talk() {
		try {
			BufferedReader br = getReader(socket);
			PrintWriter pw = getWriter(socket);
			for (int i = 0; i < 100; i++) {
				TransportMessage tm = new TransportMessage(i, "Hello_" + i);
				String msg = JSON.toJSONString(tm);
				pw.println(msg);
				pw.flush();
				System.out.println("send msg : " + msg);

				System.out.println("receive msg : " + br.readLine());

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != socket) {
				try {
					socket.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

	}

	public static void main(String[] args) throws UnknownHostException,
			IOException {
		String host = "127.0.0.1";
		int port = 10007;

		for (int i = 0; i < 100; i++) {
			new SocketClient(host, port).talk();
		}

	}
}