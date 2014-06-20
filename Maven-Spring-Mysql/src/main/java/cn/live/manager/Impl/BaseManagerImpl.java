package cn.live.manager.Impl;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import cn.live.dao.BaseDao;
import cn.live.manager.BaseManager;
import cn.live.util.ResultJson;

/**
 * @ClassName: BaseManagerImpl
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月17日 下午9:31:07
 *
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
	public T findById(ID id) {
		return dao.findById(id);
	}
	
	/* (non-Javadoc)
	 * <p>Title: update</p> 
	 * <p>Description: </p> 
	 * @param entity 
	 * @see cn.live.manager.BaseManager#update(java.lang.Object) 
	 */
	@Override
	public void update(T entity) {
		dao.update(entity);
	}
	
	
	/* (non-Javadoc)
	 * <p>Title: getResultJson</p> 
	 * <p>Description: </p> 
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return 
	 * @see cn.live.manager.BaseManager#getResultJson(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String) 
	 */
	public ResultJson getResultJson(Integer page, Integer rows, String sidx, String sord, String[] propertyNames) {
		return dao.getResultJson(page, rows, sidx, sord, propertyNames);
	}
}
