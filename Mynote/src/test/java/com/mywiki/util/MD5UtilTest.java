package com.mywiki.util;

import java.net.URLEncoder;
import java.util.UUID;

import org.junit.Test;

public class MD5UtilTest {

	@Test
	public void test() {
		System.out.println(MD5Util.encode("admin"));
        System.out.println(MD5Util.encode("admin", "zhanqi"));
	}
	
	@Test
	public void test01() throws Exception{
		String code = UUID.randomUUID().toString()+"asdfasdf";
		code = MD5Util.encode(code).toUpperCase();
		System.out.println(code);
		System.out.println(code.length());
		String sign = "{\"email\":\"mywiki95@gmail.com\",\"code\":\""+code+"\"}";
		System.out.println(sign);
		String key = "267ac2ed67f292ff77c4a0b8";  
		System.out.println(DESede.encryptDES(sign, key));
		System.out.println(URLEncoder.encode(DESede.encryptDES(sign, key), "UTF-8"));
	}
}
