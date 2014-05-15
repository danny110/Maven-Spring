package cn.live.service.Impl;

import org.springframework.data.mongodb.core.query.Query;

import cn.live.base.dao.BaseDao;
import cn.live.service.BaseService;

/**
 * @ClassName: BaseServiceImpl
 * @Description: TODO 引用数据库操作的接口实现
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @param <T>
 * @date 2014年4月23日 下午9:26:05
 * 
 */
public class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao<T> baseDao;

	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	/*
	 * (non-Javadoc) <p>Title: save</p> <p>Description: 保存或更新对象</p>
	 * 
	 * @param t
	 * 
	 * @see cn.live.service.BaseService#save(java.lang.Object)
	 */
	@Override
	public void save(T t) {
		baseDao.save(t);
	}

	/*
	 * (non-Javadoc) <p>Title: findOne</p> <p>Description: 获取第一条记录</p>
	 * 
	 * @param query
	 * 
	 * @return
	 * 
	 * @see
	 * cn.live.service.BaseService#findOne(org.springframework.data.mongodb.
	 * core.query.Query)
	 */
	@Override
	public T findOne(Query query, Class<T> entityClass) {

		return baseDao.findOne(query, entityClass);
	}

	/*
	 * (non-Javadoc) <p>Title: deleted</p> <p>Description: 删除对象</p>
	 * 
	 * @param query
	 * 
	 * @param entityClass
	 * 
	 * @see cn.live.service.BaseService#deleted(java.lang.Object)
	 */
	@Override
	public void remove(Query query, Class<T> entityClass) {
		baseDao.remove(query, entityClass);
	}

	/*
	 * (non-Javadoc) <p>Title: remove</p> <p>Description: </p>
	 * 
	 * @param query
	 * 
	 * @param collectionName
	 * 
	 * @see
	 * cn.live.service.BaseService#remove(org.springframework.data.mongodb.core
	 * .query.Query, java.lang.String)
	 */
	@Override
	public void remove(Query query, String collectionName) {
		baseDao.remove(query, collectionName);
	}
}
