package cn.live.manager.Impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.live.dao.BaseDao;
import cn.live.manager.BaseManager;
import cn.live.util.Filter;
import cn.live.util.Order;
import cn.live.util.ResultJson;

/**
 * @ClassName: BaseManagerImpl
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月17日 下午9:31:07
 *
 */
/**
 * @ClassName: BaseManagerImpl
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月26日 下午10:51:46
 *
 * @param <T>
 * @param <ID>
 */
@Transactional
public class BaseManagerImpl<T, ID extends Serializable> implements BaseManager<T, ID>{
	
	private BaseDao<T, ID> dao;  
	
	public void setDao(BaseDao<T, ID> dao) {
        this.dao = dao;  
    } 
	
	/* (non-Javadoc)
	 * <p>Title: create</p> 
	 * <p>Description: </p> 
	 * @param entity 
	 * @see cn.live.manager.BaseManager#create(java.lang.Object) 
	 */
	public void create(T entity) {
		dao.create(entity);
	};
	
	/* (non-Javadoc)
	 * <p>Title: findById</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return 
	 * @see cn.live.manager.BaseManager#findById(java.io.Serializable) 
	 */
	@Override
	@Transactional(readOnly = true)
	public T findById(ID id) {
		return dao.findById(id);
	}
	
	/* (non-Javadoc)
	 * <p>Title: merge</p> 
	 * <p>Description: </p> 
	 * @param entity 
	 * @see cn.live.manager.BaseManager#merge(java.lang.Object) 
	 */
	@Override
	public void merge(T entity) {
		dao.merge(entity);
	}
	
	/* (non-Javadoc)
	 * <p>Title: getList</p>
	 * <p>Description: </p>
	 * @return
	 * @see cn.live.manager.BaseManager#getList()
	 */
	@Override
	public List<T> getList() {
		return dao.getList(null, null);
	}
	
	/* (non-Javadoc)
	 * <p>Title: getList</p> 
	 * <p>Description: </p> 
	 * @param filters
	 * @return 
	 * @see cn.live.manager.BaseManager#getList(java.util.List) 
	 */
	@Override
	public List<T> getList(List<Filter> filters) {
		return dao.getList(filters, null);
	}
	
	/* (non-Javadoc)
	 * <p>Title: getList</p> 
	 * <p>Description: </p> 
	 * @param filters
	 * @param orders
	 * @return 
	 * @see cn.live.manager.BaseManager#getList(java.util.List, java.util.List) 
	 */
	@Override
	public List<T> getList(List<Filter> filters, List<Order> orders) {
		return dao.getList(filters, orders);
	}
	
	/* (non-Javadoc)
	 * <p>Title: getResultJson</p>
	 * <p>Description: </p>
	 * @param page
	 * @param rows
	 * @param propertyNames
	 * @param filters
	 * @param orders
	 * @return
	 * @see cn.live.manager.BaseManager#getResultJson(java.lang.Integer, java.lang.Integer, java.lang.String[], java.util.List, java.util.List)
	 */
	@Override
	public ResultJson getResultJson(Integer page, Integer rows, String[] propertyNames, List<Filter> filters, List<Order> orders) {
		return dao.getResultJson(page, rows, propertyNames, filters, orders);
	}
	
	/* (non-Javadoc)
	 * <p>Title: getResultJson</p>
	 * <p>Description: </p>
	 * @param page
	 * @param rows
	 * @param propertyNames
	 * @param orders
	 * @return
	 * @see cn.live.manager.BaseManager#getResultJson(java.lang.Integer, java.lang.Integer, java.lang.String[], java.util.List)
	 */
	@Override
	public ResultJson getResultJson(Integer page, Integer rows, String[] propertyNames, List<Order> orders) {
		return dao.getResultJson(page, rows, propertyNames, null, orders);
	}

	/* (non-Javadoc)
	 * <p>Title: getBySQL</p>
	 * <p>Description: </p>
	 * @param sql
	 * @return
	 * @see cn.live.manager.BaseManager#getBySQL(java.lang.String)
	 */
	@Override
	public List<?> getBySQL(String sql) {
		return dao.getBySQL(sql);
	}
}
