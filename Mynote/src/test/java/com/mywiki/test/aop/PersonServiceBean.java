package com.mywiki.test.aop;
//接口实现类
public class PersonServiceBean implements PersonServer {

	public void save(String name) {

		System.out.println("我是save方法");
		// throw new RuntimeException();
	}

	public void update(String name, Integer id) {
		System.out.println("我是update()方法");
	}

	public String getPersonName(Integer id) {

		System.out.println("我是getPersonName()方法");
		return "xxx";
	}

}