package cn.live.manager;

import java.io.Serializable;

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
	 * @Title: update 
	 * @Description: TODO 更新对象到数据库
	 * @param @param entity 
	 * @return void
	 * @throws 
	 */
	void update(T entity);
	
	/** 
	 * @Title: getResultJson 
	 * @Description: TODO 获取数据对象
	 * @param @param page 当前页码
	 * @param @param rows 每页记录条数
	 * @param @param sidx 排序字段
	 * @param @param sord 排序类型
	 * @param @return 
	 * @return ResultJson<T>
	 * @throws 
	 */
	ResultJson<T> getResultJson(Integer page, Integer rows, String sidx, String sord);
}
