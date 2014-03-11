package com.mywiki.util.mongodb;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-mongodb.xml")
public class MongoTest1 {

	@Resource
	MongoTemplate mongoTemplate;
	
	@Test
	public void test() {
		DB db = mongoTemplate.getDb();
		System.out.println(db);
		DBCollection s = mongoTemplate.getCollection("foo");
		DBCursor c = s.find();
		while (c.hasNext()) {
			DBObject obj = c.next();
			System.out.println(obj);
		}
	}
	
}