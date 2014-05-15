package cn.live.service;

import org.springframework.data.mongodb.core.query.Query;

/**
 * @ClassName: BaseService
 * @Description: TODO 引用数据库操作的接口
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年4月15日 下午9:30:46
 * 
 */
public interface BaseService<T> {
	/**
	 * @Title: save
	 * @Description: TODO 保存或更新对象
	 * @param T
	 * @return void
	 * @throws
	 */
	void save(T t);

	/**
	 * @Title: findOne
	 * @Description: TODO 获取第一条记录
	 * @param query
	 * @param entityClass
	 * @return T
	 * @throws
	 */
	T findOne(Query query, Class<T> entityClass);
	
	/**
	 * @Title: remove
	 * @Description: TODO 删除对象
	 * @param query
	 * @param entityClass
	 * @return T
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
