package com.test08;

public class Config {

	public static String getRealPath(String uri) {
		StringBuilder sb = new StringBuilder("/DPAN/MyProject/STS/netty3/src/test/resources/http");
		sb.append(uri);
		if (!uri.endsWith("/")) {
			sb.append('/');
		}
		return sb.toString();
	}
}