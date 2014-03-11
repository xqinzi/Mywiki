package com.mywiki.util.mongodb;

import com.mongodb.WriteResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public class RepositoryImpl implements Repository<Tree> {
	MongoTemplate mongoTemplate;

	// Repository repository;
	//
	// public void setRepository(Repository repository) {
	// this.repository = repository;
	// }

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	// 查询
	public List<Tree> getAllObjects() {
		return mongoTemplate.findAll(Tree.class);
	}

	// 增加
	public void saveObject(Tree tree) {
		mongoTemplate.insert(tree);
	}

	// 查询
	public Tree getObject(String id) {
		return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)),
				Tree.class);
	}

	// 修改
	public WriteResult updateObject(String id, String name) {
		return mongoTemplate.updateFirst(
				new Query(Criteria.where("id").is(id)),
				Update.update("name", name), Tree.class);
	}

	// 删除
	public void deleteObject(String id) {
		mongoTemplate
				.remove(new Query(Criteria.where("id").is(id)), Tree.class);
	}

	public void createCollection() {
		if (!mongoTemplate.collectionExists(Tree.class)) {
			mongoTemplate.createCollection(Tree.class);
		}
	}

	public void dropCollection() {
		if (mongoTemplate.collectionExists(Tree.class)) {
			mongoTemplate.dropCollection(Tree.class);
		}
	}

}
