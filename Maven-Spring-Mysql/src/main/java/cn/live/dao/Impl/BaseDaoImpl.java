package cn.live.dao.Impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.Assert;

import cn.live.dao.BaseDao;
import cn.live.util.BaseUtils;
import cn.live.util.Filter;
import cn.live.util.ResultJson;

/**
 * @ClassName: BaseDaoImpl
 * @Description: TODO 实现了对持久化对象的操作方法接口
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月21日 上午9:01:44
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
		this.getSession().flush();

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
	 * <p>Title: merge</p> 
	 * <p>Description: </p> 
	 * @param entity 
	 * @see cn.live.dao.BaseDao#merge(java.lang.Object) 
	 */
	@Override
	public void merge(T entity) {
		Assert.notNull(entity);
		this.getSession().merge(entity);
		this.getSession().flush();
	}
	
	/* (non-Javadoc)
	 * <p>Title: getList</p> 
	 * <p>Description: </p> 
	 * @param filters
	 * @return 
	 * @see cn.live.dao.BaseDao#getList(java.util.List) 
	 */
	@Override
	public List<T> getList(Filter[] filters) {
		return getList(filters, null);
	}
	
	/* (non-Javadoc)
	 * <p>Title: getList</p> 
	 * <p>Description: </p> 
	 * @param filters
	 * @param orders
	 * @return 
	 * @see cn.live.dao.BaseDao#getList(java.util.List, java.util.List) 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getList(Filter[] filters, cn.live.util.Order[] orders) {
		Criteria criteria = (Criteria) this.getSession().createCriteria(clazz);
		if (filters != null) {
            setQueryCriteria(criteria, Arrays.asList(filters));
        }
		if (orders != null) {
			setQueryOrders(criteria, Arrays.asList(orders));
		}
		List<T> temp = criteria.list();
		return temp;
	}
	
	
	/* (non-Javadoc)
	 * <p>Title: getResultJson</p> 
	 * <p>Description: </p> 
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param propertyNames
	 * @param filters
	 * @return 
	 * @see cn.live.dao.BaseDao#getResultJson(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String[], cn.live.util.Filter[]) 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultJson getResultJson(Integer page, Integer rows, String sidx, String sord, String[] propertyNames, Filter[] filters) {
		Criteria criteria = (Criteria) this.getSession().createCriteria(clazz);
		if (filters != null) {
            setQueryCriteria(criteria, Arrays.asList(filters));
        }
		long records = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		if ("asc".equals(sord.toLowerCase())) 
			criteria.addOrder(Order.desc(sidx));
		else if ("desc".equals(sord.toLowerCase())) 
			criteria.addOrder(Order.desc(sidx));
		criteria.setProjection(null);
		criteria.setFirstResult((page - 1) * rows);
		criteria.setMaxResults(rows);
		List<T> temp = criteria.list();
		ResultJson resultJson = new ResultJson(page, records, records % rows == 0 ? records / rows : records / rows + 1);
		fillJQueryGridData(resultJson, temp, (String[]) propertyNames);
        return resultJson;
	}
	
	/** 
	 * @Title: fillJQueryGridData 
	 * @Description: TODO 填充数据
	 * @param @param resultJson
	 * @param @param list
	 * @param @param propertyNames 
	 * @return void
	 * @throws 
	 */
	private void fillJQueryGridData(ResultJson resultJson, List<T> list, String[] propertyNames) {
		Assert.notNull(propertyNames);
		Assert.notNull(list);
		List<Map<String, Object>> rows = BaseUtils.list2ResultJson(list, propertyNames);
		resultJson.setRows(rows);
	}
	
	/** 
	 * @Title: setQueryCriteria 
	 * @Description: TODO 设置过滤器
	 * @param @param criteria
	 * @param @param filters 
	 * @return void
	 * @throws 
	 */
	@SuppressWarnings("rawtypes")
	private void setQueryCriteria(Criteria criteria, List<Filter> filters) {
		if ((criteria == null) || (filters == null) || (filters.isEmpty()))
            return;
        for (Filter filter : filters) {
            if ((filter != null) && (!StringUtils.isEmpty(filter.getProperty()))) {
                if ((filter.getOperator() == Filter.Operator.eq) && (filter.getValue() != null)) {
                	criteria.add(Restrictions.eq(filter.getProperty(), filter.getValue()));
                } else if ((filter.getOperator() == Filter.Operator.ne) && (filter.getValue() != null)) {
                	criteria.add(Restrictions.ne(filter.getProperty(), filter.getValue()));
                } else if ((filter.getOperator() == Filter.Operator.gt) && (filter.getValue() != null)) {
                	criteria.add(Restrictions.gt(filter.getProperty(), filter.getValue()));
                } else if ((filter.getOperator() == Filter.Operator.lt) && (filter.getValue() != null)) {
                	criteria.add(Restrictions.lt(filter.getProperty(), filter.getValue()));
                } else if ((filter.getOperator() == Filter.Operator.ge) && (filter.getValue() != null)) {
                	criteria.add(Restrictions.ge(filter.getProperty(), filter.getValue()));
                } else if ((filter.getOperator() == Filter.Operator.le) && (filter.getValue() != null)) {
                	criteria.add(Restrictions.le(filter.getProperty(), filter.getValue()));
                } else if ((filter.getOperator() == Filter.Operator.like) && (filter.getValue() != null) && ((filter.getValue() instanceof String))) {
                	criteria.add(Restrictions.like(filter.getProperty(), filter.getValue()));
                } else if ((filter.getOperator() == Filter.Operator.in) && (filter.getValue() != null)) {
                    Object value = filter.getValue();
                    if (value instanceof Collection) {
                    	criteria.add(Restrictions.in(filter.getProperty(), (Collection)filter.getValue()));
                    } else {
                    	criteria.add(Restrictions.in(filter.getProperty(), (Object[])filter.getValue()));
                    }
                } else if (filter.getOperator() == Filter.Operator.isNull) {
                	criteria.add(Restrictions.isNull(filter.getProperty()));
                } else if (filter.getOperator() == Filter.Operator.isNotNull) {
                	criteria.add(Restrictions.isNotNull(filter.getProperty()));
                } else if (filter.getOperator() == Filter.Operator.between) {
                	criteria.add(Restrictions.between(filter.getProperty(), filter.getStart(), filter.getEnd()));
                }
            }
        }
	}
	
	/** 
	 * @Title: setQueryOrders 
	 * @Description: TODO 设置排序
	 * @param @param criteria
	 * @param @param orders 
	 * @return void
	 * @throws 
	 */
	private void setQueryOrders(Criteria criteria, List<cn.live.util.Order> orders) {
		 if ((criteria == null) || (orders == null) || (orders.isEmpty())) {
	            return;
	        }
	        for (cn.live.util.Order order : orders) {
	            if (order.getDirection() == cn.live.util.Order.Direction.asc) {
	            	criteria.addOrder(Order.asc(order.getProperty()));
	            } else if (order.getDirection() == cn.live.util.Order.Direction.desc) {
	            	criteria.addOrder(Order.desc(order.getProperty()));
	            }
	        }
	}
	
}
