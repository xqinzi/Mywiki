package com.mywiki.util.redis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-redis.xml")
public class UserDaoTest {
	@Autowired
	private UserDao userDao;

	@Test
	public void crud() {
		// -------------- Create ---------------
		String uid = "u123456";
		String address1 = "上海";
		User user = new User();
		user.setAddress(address1);
		user.setUid(uid);
		System.out.println("-------------- Create ---------------");
		System.out.println(user);
		userDao.save(user);
		
		// ---------------Read ---------------
		user = userDao.read(uid);
		System.out.println("---------------Read ---------------");
		System.out.println(user);
		assertEquals(address1, user.getAddress());

		// --------------Update ------------
		String address2 = "北京";
		user.setAddress(address2);
		userDao.save(user);
		user = userDao.read(uid);
		System.out.println("--------------Update ------------");
		System.out.println(user);
		assertEquals(address2, user.getAddress());

		// --------------Delete ------------
		userDao.delete(uid);
		user = userDao.read(uid);
		assertNull(user);
	}
	
	@Test
	public void crud2() {
		// -------------- Create ---------------
		User user1 = new User();
		user1.setUid("u123456");
		user1.setAddress("上海");
		
		User user2 = new User();
		user2.setUid("u222222");
		user2.setAddress("北京");
		List<User> ls=new ArrayList<User>();
		ls.add(user1);
		ls.add(user2);
		System.out.println("-------------- Create ---------------");
		userDao.add(ls);

		
		System.out.println("---------------Read ---------------");
		user2 = userDao.read("u222222");
		System.out.println(user2);
	}
}