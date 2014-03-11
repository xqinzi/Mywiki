package com.mywiki.util.redis;

import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
/**
 * 基础使用
 * @author mywiki95@gmail.com
 *
 */
public class RedisTest {
	
	/**
	 * 简单使用Jedis
	 */
	@Test
	public void test01(){
		Jedis jedis = new Jedis("127.0.0.1");
		String keys = "name";
		// 存数据
		jedis.set(keys, "snowolf");

		// 取数据
		String value = jedis.get(keys);
		System.out.println(value);
		
		// 删数据
		jedis.del(keys);
		value = jedis.get(keys);
		System.out.println(value);
	}
	
	
	/**
	 * 池化使用Jedis
	 */
	@Test
	public void test02(){
		JedisPool pool;
		ResourceBundle bundle = ResourceBundle.getBundle("redis2");
		if (bundle == null) {
			throw new IllegalArgumentException("[redis.properties] is not found!");
		}
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(Integer.valueOf(bundle.getString("redis.pool.maxActive")));
		config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));
		config.setMaxWait(Long.valueOf(bundle.getString("redis.pool.maxWait")));
		config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));
		config.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));
		pool = new JedisPool(config, bundle.getString("redis.ip"),Integer.valueOf(bundle.getString("redis.port")));
		
		// 从池中获取一个Jedis对象
		Jedis jedis = pool.getResource();
		String keys = "name";

		// 存数据
		jedis.set(keys, "snowolf");

		// 取数据
		String value = jedis.get(keys);
		System.out.println(value);
		
		// 删数据
		jedis.del(keys);
		value = jedis.get(keys);
		System.out.println(value);

		// 切记，最后使用后，释放Jedis对象
		pool.returnResource(jedis);
	}
	/**
	 * 集群
	 * Memcached完全基于分布式集群，而Redis是Master-Slave，如果想把Reids，做成集群模式，
	 * 无外乎多做几套Master-Slave，每套Master-Slave完成各自的容灾处理，通过Client工具，完成一致性哈希。
	 * PS:Memcached是在Server端完成Sharding，Redis只能依靠各个Client做Sharding。
	 * 可能会在Redis 3.0系列支持Server端Sharding。
	 */
	@Test
	public void test03(){
		
		ResourceBundle bundle = ResourceBundle.getBundle("redis2");
		if (bundle == null) {
			throw new IllegalArgumentException("[redis.properties] is not found!");
		}
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(Integer.valueOf(bundle.getString("redis.pool.maxActive")));
		config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));
		config.setMaxWait(Long.valueOf(bundle.getString("redis.pool.maxWait")));
		config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));
		config.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));
		
		//保留前面的JedisPoolConfig，新增两个Redis的IP（redis1.ip，redis2.ip），完成两个JedisShardInfo实例，并将其丢进List中：
		JedisShardInfo jedisShardInfo1 = new JedisShardInfo(
				bundle.getString("redis1.ip"), Integer.valueOf(bundle.getString("redis.port")));
		JedisShardInfo jedisShardInfo2 = new JedisShardInfo(
				bundle.getString("redis2.ip"), Integer.valueOf(bundle.getString("redis.port")));
		List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
		list.add(jedisShardInfo1);
		list.add(jedisShardInfo2);
		
		
		// JedisPool pool = new JedisPool(config, bundle.getString("redis.ip"),Integer.valueOf(bundle.getString("redis.port")));
		// 初始化ShardedJedisPool代替JedisPool
		ShardedJedisPool pool = new ShardedJedisPool(config, list);  
		 
		// Jedis jedis = pool.getResource();
		// 改由ShardedJedis，获取Jedis对象
		ShardedJedis jedis = pool.getResource(); 
		
		String keys = "name";

		// 存数据
		jedis.set(keys, "snowolf");

		// 取数据
		String value = jedis.get(keys);
		System.out.println(value);
		
		// 删数据
		jedis.del(keys);
		value = jedis.get(keys);
		System.out.println(value);

		// 切记，最后使用后，释放Jedis对象
		pool.returnResource(jedis);
	}
	
	/**
	 * 使用spring的方式
	 */
	@Test
	public void test04(){
		ClassPathXmlApplicationContext app=new ClassPathXmlApplicationContext("applicationContext-redis.xml");  
		ShardedJedisPool pool = (ShardedJedisPool) app.getBean("shardedJedisPool");   
		
		// 从池中获取一个Jedis对象  
	    ShardedJedis jedis = pool.getResource();  
	    String keys = "name";

		// 存数据
		jedis.set(keys, "snowolf");

		// 取数据
		String value = jedis.get(keys);
		System.out.println(value);
		
		// 删数据
		jedis.del(keys);
		value = jedis.get(keys);
		System.out.println(value);
		// 释放对象池  
	    pool.returnResource(jedis);  
	}
}
