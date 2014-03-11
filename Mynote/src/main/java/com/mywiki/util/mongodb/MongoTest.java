package com.mywiki.util.mongodb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-mongodb.xml")
public class MongoTest {
	
	@Autowired
	Repository repository;
	
	@Test
	public void test01(){
        System.out.println("进来了");
        // cleanup collection before insertion
        repository.dropCollection();
        // create collection
        repository.createCollection();
        //增加
        repository.saveObject(new Tree("1", "Apple Tree", 10));
        System.out.println("1. " + repository.getAllObjects());

        //增加和查询
        repository.saveObject(new Tree("2", "Orange Tree", 3));
        System.out.println("2. " + repository.getAllObjects());
        System.out.println("Tree with id 1" + repository.getObject("1"));

        //修改
        repository.updateObject("1", "Peach Tree");
        System.out.println("3. " + repository.getAllObjects());

        //删除
        repository.deleteObject("2");
        System.out.println("4. " + repository.getAllObjects());
    }
}