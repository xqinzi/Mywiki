package com.mywiki.util.mongodb;

import com.mongodb.WriteResult;
import java.util.List;

public interface Repository<T> {
	// 方法接口
	public List<T> getAllObjects();

	public void saveObject(T object);

	public T getObject(String id);

	public WriteResult updateObject(String id, String name);

	public void deleteObject(String id);

	public void createCollection();

	public void dropCollection();

}