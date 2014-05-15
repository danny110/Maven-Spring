package cn.live.base.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import cn.live.base.dao.BaseDao;

/**
 * @ClassName: BaseDaoImpl
 * @Description: TODO 数据库操作接口
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @param <T>
 * @date 2014年4月4日 下午10:28:24
 * 
 */
@Repository
public class BaseDaoImpl<T> implements BaseDao<T> {
	@Autowired
	protected MongoTemplate mongoTemplate;

	/**
	 * @Title: setMongoTemplate
	 * @Description: TODO 为属性自动注入 Bean 服务
	 * @param @param t
	 * @return void
	 * @throws
	 */
	public void setMongoTemplate(@Qualifier("mongoTemplate") MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	/*
	 * (non-Javadoc) <p>Title: findOne</p> <p>Description: 获取第一条记录</p>
	 * 
	 * @return
	 * 
	 * @see cn.live.base.dao.BaseDao#findOne()
	 */
	@Override
	public T findOne(Query query, Class<T> entityClass) {
		return (T) mongoTemplate.findOne(query, entityClass);
	}

	/*
	 * (non-Javadoc) <p>Title: save</p> <p>Description: 保存或更新对象</p>
	 * 
	 * @param t
	 * 
	 * @see cn.live.base.dao.BaseDao#save(java.lang.Object)
	 */
	@Override
	public void save(T t) {
		mongoTemplate.save(t);
	}

	/*
	 * (non-Javadoc) <p>Title: remove</p> <p>Description: 删除对象</p>
	 * 
	 * @param query
	 * 
	 * @param entityClass
	 * 
	 * @see cn.live.base.dao.BaseDao#deleted(java.lang.Object)
	 */
	@Override
	public void remove(Query query, Class<T> entityClass) {
		mongoTemplate.remove(query, entityClass);
	}

	/*
	 * (non-Javadoc) <p>Title: remove</p> <p>Description: </p>
	 * 
	 * @see cn.live.base.dao.BaseDao#remove()
	 */
	@Override
	public void remove(Query query, String collectionName) {
		mongoTemplate.remove(query, collectionName);
	}

}
