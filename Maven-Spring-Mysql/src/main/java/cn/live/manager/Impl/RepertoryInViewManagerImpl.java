package cn.live.manager.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.live.bean.view.RepertoryInView;
import cn.live.dao.BaseDao;
import cn.live.manager.RepertoryInViewManager;

/**
 * @ClassName: RepertoryInViewManagerImpl
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月24日 下午11:37:17
 *
 */
@Service("repertoryInViewManager")
public class RepertoryInViewManagerImpl extends BaseManagerImpl<RepertoryInView, String> implements RepertoryInViewManager {
	
	@Resource(name = "repertoryInViewDao")  
    public void setDao(BaseDao<RepertoryInView, String> dao) {  
        super.setDao(dao);  
    }  

	/* (non-Javadoc)
	 * <p>Title: getSumBySQL</p>
	 * <p>Description: </p>
	 * @param rawMaterialId
	 * @param companyName
	 * @param loginCode
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @see cn.live.manager.RepertoryInViewManager#getSumBySQL(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Float> getSumBySQL(String rawMaterialId, String companyName, String loginCode, String beginTime, String endTime) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append("sum(num) as nums, sum(sum) as sums ");
		sql.append("from ");
		sql.append("repertoryIn_view ");
		sql.append("where ");
		sql.append("rawMaterialId = '");
		sql.append(rawMaterialId);
		sql.append("' ");
		if (StringUtils.isNotBlank(companyName)) {
			sql.append("and companyName like '%");
			sql.append(companyName);
			sql.append("%' ");
		}
		if (StringUtils.isNotBlank(loginCode)) {
			sql.append("and loginCode like '%");
			sql.append(loginCode);
			sql.append("%' ");
		}
		if (StringUtils.isNotBlank(beginTime)) {
			sql.append("and inDate >= '");
			sql.append(beginTime);
			sql.append("' ");
		}
		if (StringUtils.isNotBlank(endTime)) {
			sql.append("and inDate <= '");
			sql.append(endTime);
			sql.append("' ");
		}
		List<?> list = this.getBySQL(sql.toString());
		Map<String, Float> data = new HashMap<String, Float>();
		if (list == null || list.size() == 0) {
			data.put("nums", 0F);
			data.put("sums", 0F);
		} else {
			Object[] objects = (Object[]) list.get(0);
			data.put("nums", Float.valueOf(objects[0] == null ? "0" : objects[0].toString()));
			data.put("sums", Float.valueOf(objects[1] == null ? "0" : objects[1].toString()));
		}
		return data;
	}
	
	/* (non-Javadoc)
	 * <p>Title: getSumBySQL</p>
	 * <p>Description: </p>
	 * @param companyName
	 * @param loginCode
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @see cn.live.manager.RepertoryInViewManager#getSumBySQL(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Float> getSumBySQL(String companyName, String loginCode, String beginTime, String endTime) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append("sum(num) as nums, sum(sum) as sums ");
		sql.append("from ");
		sql.append("repertoryIn_view ");
		sql.append("where 1=1 ");
		if (StringUtils.isNotBlank(companyName)) {
			sql.append("and companyName like '%");
			sql.append(companyName);
			sql.append("%' ");
		}
		if (StringUtils.isNotBlank(loginCode)) {
			sql.append("and loginCode like '%");
			sql.append(loginCode);
			sql.append("%' ");
		}
		if (StringUtils.isNotBlank(beginTime)) {
			sql.append("and inDate >= '");
			sql.append(beginTime);
			sql.append("' ");
		}
		if (StringUtils.isNotBlank(endTime)) {
			sql.append("and inDate <= '");
			sql.append(endTime);
			sql.append("' ");
		}
		List<?> list = this.getBySQL(sql.toString());
		Map<String, Float> data = new HashMap<String, Float>();
		if (list == null || list.size() == 0) {
			data.put("nums", 0F);
			data.put("sums", 0F);
		} else {
			Object[] objects = (Object[]) list.get(0);
			data.put("nums", Float.valueOf(objects[0] == null ? "0" : objects[0].toString()));
			data.put("sums", Float.valueOf(objects[1] == null ? "0" : objects[1].toString()));
		}
		return data;
	}
}
