package com.mywiki.util.redis;

import java.util.List;

public interface UserDao {
	/**
	 * @param uid
	 * @param address
	 */
	void save(User user);
	
	boolean add(User user);
	
	boolean add(List<User> list);

	/**
	 * @param uid
	 * @return
	 */
	User read(String uid);

	/**
	 * @param uid
	 */
	void delete(String uid);
	
	void remove(String key);
	
	void remove(List<String> keys);
	
	boolean update(User user);
}