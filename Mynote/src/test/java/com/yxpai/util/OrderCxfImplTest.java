package com.yxpai.util;

import java.io.IOException;
import org.junit.Test;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class OrderCxfImplTest {

	@Test
	public void test() throws HttpException, IOException {
		GetMethod get = new GetMethod("http://pay.yxpai.com/cxf/pay/list/all");
		get.setRequestHeader("accept", "application/json");
		HttpClient hc = new HttpClient();
		hc.getParams().setContentCharset("UTF-8"); // 设置编码
		int code = hc.executeMethod(get);
		System.err.println("返回的状态码：" + code);
		if (code == 200) {
			String str = get.getResponseBodyAsString();
			System.err.println("返回信息：\n" + str);
		}
		get.releaseConnection();
	}
	@Test
	public void save2() throws Exception {

		String name = "王健";// 因为是get类型，所以不能包含中文

		String age = "35";

		String url = "http://localhost:9004/users/add/";

		PostMethod pm = new PostMethod(url);

		pm.setRequestHeader("accept", "application/json");

		pm.setRequestHeader("Encoding", "UTF-8");

		pm.setParameter("name", name);

		pm.setParameter("age", age);

		HttpClient hc = new HttpClient();

		hc.getParams().setContentCharset("UTF-8");// 设置编码,否则会返回中文乱码//TODO:切记

		int code = hc.executeMethod(pm);

		System.err.println("Post方式的返回值是:" + code);

		if (code == 200) {

			String ss = pm.getResponseBodyAsString();

			System.err.println(">>:" + ss);

		}

		pm.releaseConnection();

	}

	@Test
	public void save() throws Exception {

		String name = "Jack";// 因为是get类型，所以不能包含中文

		String age = "35";

		String url = "http://localhost:9004/users/save/" + name + "/" + age;

		GetMethod get = new GetMethod(url);

		get.setRequestHeader("accept", "application/json");

		HttpClient hc = new HttpClient();

		hc.getParams().setContentCharset("UTF-8"); // 设置编码

		// .setRequestHeader("Content","text/html;charset=UTF-8");

		int code = hc.executeMethod(get);

		System.err.println("返回的状态码是：" + code);

		if (code == 200) {

			String str = get.getResponseBodyAsString();

			System.err.println("返回的信息是:\n" + str);

		}
	}
}
