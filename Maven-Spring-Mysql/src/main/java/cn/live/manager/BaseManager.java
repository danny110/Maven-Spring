package cn.live.manager;

import java.io.Serializable;
import java.util.List;

import cn.live.util.Filter;
import cn.live.util.Order;
import cn.live.util.ResultJson;


/**
 * @ClassName: BaseManager
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月17日 下午9:29:41
 * 
 */
public interface BaseManager<T, ID extends Serializable> {
	
	/**
	 * @Title: create
	 * @Description: TODO
	 * @param @param entity
	 * @return void
	 * @throws
	 */
	void create(T entity);
	
	/** 
	 * @Title: findById 
	 * @Description: TODO 根据主键获取对象
	 * @param @param id
	 * @param @return 
	 * @return T
	 * @throws 
	 */
	T findById(ID id);
	
	/** 
	 * @Title: merge 
	 * @Description: TODO 更新对象到数据库
	 * @param @param entity 
	 * @return void
	 * @throws 
	 */
	void merge(T entity);
	
	/**
	 * @Title: getList
	 * @Description: TODO 获取列表
	 * @param @return
	 * @return List<T>
	 * @throws
	 */
	List<T> getList();
	
	/** 
	 * @Title: getList 
	 * @Description: TODO 获取列表
	 * @param @param filters
	 * @param @return 
	 * @return List<T>
	 * @throws 
	 */
	List<T> getList(List<Filter> filters);
	
	/** 
	 * @Title: getList 
	 * @Description: TODO 获取列表
	 * @param @param filters
	 * @param @param orders
	 * @param @return 
	 * @return List<T>
	 * @throws 
	 */
	List<T> getList(List<Filter> filters, List<Order> orders);
	
	/**
	 * @Title: getResultJson
	 * @Description: TODO
	 * @param @param page
	 * @param @param rows
	 * @param @param propertyNames
	 * @param @param filters
	 * @param @param orders
	 * @param @return
	 * @return ResultJson
	 * @throws
	 */
	ResultJson getResultJson(Integer page, Integer rows, String[] propertyNames, List<Filter> filters, List<Order> orders);
	
	/**
	 * @Title: getResultJson
	 * @Description: TODO
	 * @param @param page
	 * @param @param rows
	 * @param @param propertyNames
	 * @param @param orders
	 * @param @return
	 * @return ResultJson
	 * @throws
	 */
	ResultJson getResultJson(Integer page, Integer rows, String[] propertyNames, List<Order> orders);
	
	/**
	 * @Title: getBySQL
	 * @Description: TODO 通过 SQL 查询
	 * @param @param sql
	 * @param @return
	 * @return List<?>
	 * @throws
	 */
	List<?> getBySQL(String sql);
}
