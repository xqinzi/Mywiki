package com.test10;

import java.util.Scanner;

import org.jboss.netty.channel.Channel;

/**
 * TODO
 * Administrator 2013-3-24下午04:19:38
 */
public class ClientThread extends Thread {
	
	protected NettyClient nettyClient;
	
	protected Scanner scanner = new Scanner(System.in);

	public void init() {
		nettyClient.init();
		nettyClient.start();
	}
	
	public void run() {
		while(true) {
			Channel channel = nettyClient.getChannelFuture().getChannel();
			System.out.println("填写信息，发送消息（Enter发送）:");
			People people = getInfo();
			channel.write(people);
		}
	}
	
	public People getInfo() {
		People manPeople = new People();
		System.out.println("name: ");
		String name = scanner.next();
	    manPeople.setName(name);
	    
	    System.out.println("age: ");
		String age = scanner.next();
	    manPeople.setAge(age);
	    System.out.println("address: ");
		String address = scanner.next();
	    manPeople.setAddress(address);
	    return manPeople;
	}
	
	public void setNettyClient(NettyClient nettyClient) {
		this.nettyClient = nettyClient;
	}
}
