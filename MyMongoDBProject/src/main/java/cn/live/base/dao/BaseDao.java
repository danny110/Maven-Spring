package cn.live.base.dao;

import org.springframework.data.mongodb.core.query.Query;

/**
 * @ClassName: BaseDao
 * @Description: TODO 数据库操作接口
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @param <T>
 * @date 2014年4月4日 下午10:26:34
 * 
 */
public interface BaseDao<T> {

	/**
	 * @Title: findOne
	 * @Description: TODO 获取第一条记录
	 * @param query 查询条件
	 * @param entityClass 操作对象
	 * @return T
	 * @throws
	 */
	T findOne(Query query, Class<T> entityClass);

	/**
	 * @Title: save
	 * @Description: TODO 保存或更新对象
	 * @param t
	 * @return void
	 * @throws
	 */
	void save(T t);
	
	/**
	 * @Title: remove
	 * @Description: TODO 删除对象
	 * @param query 查询对象
	 * @param entityClass 操作对象
	 * @return void
	 * @throws
	 */
	void remove(Query query, Class<T> entityClass);
	
	/**
	 * @Title: remove
	 * @Description: TODO 删除指定列
	 * @param query
	 * @param collectionName
	 * @return void
	 * @throws
	 */
	void remove(Query query, String collectionName);

}
