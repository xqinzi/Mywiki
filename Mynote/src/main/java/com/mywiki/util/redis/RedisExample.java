package com.mywiki.util.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mywiki.model.User;

/**
 * 直接保存对象的方式
 * @author mywiki95@gmail.com
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-redis.xml")
public class RedisExample {
	
	//@Autowired
	//UserManager um;
	@Autowired
	private RedisTemplate<String, User> template;

	public User getUser(String userId) {
		ValueOperations<String, User> voper = template.opsForValue();
		User userObj = voper.get(userId);
		if (userObj == null) {	// 不存在从数据库中读入相关userId的User对象，并进行缓存
			//userObj = um.get(Long.valueOf(userId));
			voper.set(userId, userObj);
		}
		return userObj;
	}
	@Test
	public void test01(){
		User u= new User();
		u.setEmail("mywiki95@gmail.com");
		u.setNickname("mywiki95");
		u.setPassword("redhat");
		ValueOperations<String, User> voper = template.opsForValue();
		voper.set("mywiki95@gmail.com", u);

		User userObj = voper.get("mywiki95@gmail.com");
		System.out.println(userObj.getEmail());
		System.out.println(userObj.getNickname());
		System.out.println(userObj.getPassword());
	}
}