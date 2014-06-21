package cn.live.dao;

import java.io.Serializable;
import java.util.List;

import cn.live.util.Filter;
import cn.live.util.Order;
import cn.live.util.ResultJson;

/**
 * @ClassName: BaseDao
 * @Description: 封装了对持久化对象的操作方法接口
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月17日 下午9:12:42
 * 
 */
public interface BaseDao<T, ID extends Serializable> {
	
	/** 
	 * @Title: create 
	 * @Description: TODO 保存对象
	 * @param @param entity 
	 * @return void
	 * @throws 
	 */
	void create(T entity);
	
	/** 
	 * @Title: findById 
	 * @Description: TODO 通过主键获取对象
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
	 * @param @param filters
	 * @param @return 
	 * @return List<T>
	 * @throws 
	 */
	List<T> getList(Filter[] filters);
	
	/** 
	 * @Title: getList 
	 * @Description: TODO 获取列表
	 * @param @param filters
	 * @param @param orders
	 * @param @return 
	 * @return List<T>
	 * @throws 
	 */
	List<T> getList(Filter[] filters, Order[] orders);
	
	/** 
	 * @Title: getResultJson 
	 * @Description: TODO 获取数据对象
	 * @param @param page 当前页码
	 * @param @param rows 每页记录条数
	 * @param @param sidx 排序字段
	 * @param @param sord 排序类型
	 * @param @param propertyNames 属性名称
	 * @param @param filters g过滤器
	 * @param @return 
	 * @return ResultJson
	 * @throws 
	 */
	ResultJson getResultJson(Integer page, Integer rows, String sidx, String sord, String[] propertyNames, Filter[] filters);
}
