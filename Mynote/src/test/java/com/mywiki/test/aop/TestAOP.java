package com.mywiki.test.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-AOP.xml")
public class TestAOP {
	
	@Autowired
	PersonServer bean;
	
	
	@Test  
    public void save(){  
        bean.save("save");  
    }
	
	@Test  
    public void update(){  
        bean.update("update",1);  
    } 
}
