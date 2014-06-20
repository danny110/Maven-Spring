package cn.live.dao.Impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import cn.live.dao.BaseDao;
import cn.live.util.ResultJson;

/**
 * @ClassName: BaseDaoImpl
 * @Description: 实现了对持久化对象的操作方法接口
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月17日 下午9:18:01
 * 
 */
/**
 * @ClassName: BaseDaoImpl
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月17日 下午11:13:26
 *
 * @param <T>
 * @param <ID>
 */
public class BaseDaoImpl<T, ID extends Serializable> implements BaseDao<T, ID> {
	private Class<T> clazz;

	/**
	 * 通过构造方法指定DAO的具体实现类
	 */
	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = ((Class<T>) type.getActualTypeArguments()[0]);
	}

	/**
	 * 向DAO层注入SessionFactory
	 */
	@Resource(name="sessionFactory") 
	private SessionFactory sessionFactory;

	/**
	 * 获取当前工作的Session
	 */
	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	/* (non-Javadoc)
	 * <p>Title: create</p> 
	 * <p>Description: </p> 
	 * @param entity 
	 * @see cn.live.dao.BaseDao#create(java.lang.Object) 
	 */
	@Override
	public void create(T entity) {
		this.getSession().save(entity);

	}
	
	/* (non-Javadoc)
	 * <p>Title: findById</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return 
	 * @see cn.live.dao.BaseDao#findById(java.lang.String) 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T findById(ID id) {
		return (T) this.getSession().get(clazz, id);
	}
	
	/* (non-Javadoc)
	 * <p>Title: update</p> 
	 * <p>Description: </p> 
	 * @param entity 
	 * @see cn.live.dao.BaseDao#update(java.lang.Object) 
	 */
	@Override
	public void update(T entity) {
		this.getSession().merge(entity);
	}
	
	/* (non-Javadoc)
	 * <p>Title: getResultJson</p> 
	 * <p>Description: </p> 
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return 
	 * @see cn.live.dao.BaseDao#getResultJson(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String) 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultJson<T> getResultJson(Integer page, Integer rows, String sidx, String sord) {
		Criteria criteria = (Criteria) this.getSession().createCriteria(clazz);
		long records = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		if ("asc".equals(sord.toLowerCase())) 
			criteria.addOrder(Order.desc(sidx));
		else if ("desc".equals(sord.toLowerCase())) 
			criteria.addOrder(Order.desc(sidx));
		criteria.setProjection(null);
		criteria.setFirstResult((page - 1) * rows);
		criteria.setMaxResults(rows);
		List<T> temp = criteria.list();
		return new ResultJson<T>(page, records, records % rows == 0 ? records / rows : records / rows + 1, temp);
	}
	
}
