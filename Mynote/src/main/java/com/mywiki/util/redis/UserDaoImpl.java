package com.mywiki.util.redis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

public class UserDaoImpl implements UserDao{

	//@Autowired  
	private RedisTemplate<String, String> redisTemplate;

	public void setRedisTemplate(
			RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	/**
	 * 做一个保存造作，使用Redis的SET命令
	 * 这里是通过模板类，实现方法回调。在spring框架下，可以方便的控制事务
	 * 传入参数，需要final标识，禁止方法内修改。
	 * 调用RedisConnection的set方法实现Redis的SET命令。
	 * 不管是Key，还是Value都需要进行Serialize。
	 * 序列化操作，最好使用RedisTemplate提供的Serializer来完成。
	 */
	public void save(final User user) {
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.set(
						redisTemplate.getStringSerializer().serialize("user.uid." + user.getUid()),
						redisTemplate.getStringSerializer().serialize(user.getAddress()));
				return null;
			}
		});
	}
	
	/** 
	 * 新增,返回添加成功与否
	 */
	public boolean add(final User user) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] key  = serializer.serialize(String.valueOf("user.uid."+user.getUid()));
				byte[] name = serializer.serialize(user.getAddress());
				return connection.setNX(key, name);
			}
		});
		return result;
	}
	
	/**
	 * 批量新增
	 */
	public boolean add(final List<User> list) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				for (User user : list) {
					byte[] key  = serializer.serialize(String.valueOf("user.uid."+user.getUid()));
					byte[] name = serializer.serialize(user.getAddress());
					connection.setNX(key, name);
				}
				return true;
			}
		}, false, true);
		return result;
	}
	
	/**
	 * 想要将对象从Redis中取出来，就麻烦一些，需要序列化key
	 * 记得使用泛型，如RedisCallback<User>()
	 * 使用同一的序列化/反序列化Serializer
	 * 建议使用connection.exists(key)判别键值是否存在，避免无用功
	 */
	public User read(final String uid) {
		return redisTemplate.execute(new RedisCallback<User>() {
			public User doInRedis(RedisConnection connection)throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize("user.uid." + uid);
				if (connection.exists(key)) {
					byte[] value = connection.get(key);
					String address = redisTemplate.getStringSerializer().deserialize(value);
					User user = new User();
					user.setAddress(address);
					user.setUid(uid);
					return user;
				}
				return null;
			}
		});
	}
	

	public void delete(final String uid) {
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) {
				connection.del(redisTemplate.getStringSerializer().serialize("user.uid." + uid));
				return null;
			}
		});
	}
	
	/** 
	 * 删除
	 */
	public void remove(String key) {
		List<String> list = new ArrayList<String>();
		list.add(key);
		remove(list);
	}

	/**
	 * 删除多个
	 */
	public void remove(List<String> keys) {
		redisTemplate.delete(keys);
	}
	
	/**
	 * 修改，更新就是添加，只要key一致就可以 
	 */
	public boolean update(final User user) {
		String key = String.valueOf(user.getUid());
		if (read(key) == null) {
			throw new NullPointerException("数据行不存在, key = " + key);
		}
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] key  = serializer.serialize(String.valueOf(user.getUid()));
				byte[] address = serializer.serialize(user.getAddress());
				connection.set(key, address);
				return true;
			}
		});
		return result;
	}

}
