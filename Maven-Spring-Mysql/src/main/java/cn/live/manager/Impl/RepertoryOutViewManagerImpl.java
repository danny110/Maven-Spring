package cn.live.manager.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.live.bean.view.RepertoryOutView;
import cn.live.dao.BaseDao;
import cn.live.manager.RepertoryOutViewManager;

/**
 * @ClassName: RepertoryOutViewManagerImpl
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月25日 下午4:05:57
 *
 */
@Service("repertoryOutViewManager")
public class RepertoryOutViewManagerImpl extends BaseManagerImpl<RepertoryOutView, String> implements RepertoryOutViewManager {
	
	@Resource(name = "repertoryOutViewDao")  
    public void setDao(BaseDao<RepertoryOutView, String> dao) {  
        super.setDao(dao);  
    }

	/* (non-Javadoc)
	 * <p>Title: getSumBySQL</p>
	 * <p>Description: </p>
	 * @param rawMaterialId
	 * @param loginCode
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @see cn.live.manager.RepertoryOutViewManager#getSumBySQL(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Float> getSumBySQL(String rawMaterialId, String loginCode, String beginTime, String endTime) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append("sum(num) as nums ");
		sql.append("from ");
		sql.append("repertoryOut_view ");
		sql.append("where ");
		sql.append("rawMaterialId = '");
		sql.append(rawMaterialId);
		sql.append("' ");
		if (StringUtils.isNotBlank(loginCode)) {
			sql.append("and loginCode like '%");
			sql.append(loginCode);
			sql.append("%' ");
		}
		if (StringUtils.isNotBlank(beginTime)) {
			sql.append("and outDate >= '");
			sql.append(beginTime);
			sql.append("' ");
		}
		if (StringUtils.isNotBlank(endTime)) {
			sql.append("and outDate <= '");
			sql.append(endTime);
			sql.append("' ");
		}
		List<?> list = this.getBySQL(sql.toString());
		Map<String, Float> data = new HashMap<String, Float>();
		if (list == null || list.size() == 0) {
			data.put("nums", 0F);
		} else {
			data.put("nums", Float.valueOf(list.get(0) == null ? "0" : list.get(0).toString()));
		}
		return data;
	}
	
	/* (non-Javadoc)
	 * <p>Title: getSumBySQL</p>
	 * <p>Description: </p>
	 * @param rawMaterialName
	 * @param specification
	 * @param loginCode
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @see cn.live.manager.RepertoryOutViewManager#getSumBySQL(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Float> getSumBySQL(String rawMaterialName, String specification, String loginCode, String beginTime, String endTime) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append("sum(num) as nums ");
		sql.append("from ");
		sql.append("repertoryOut_view ");
		sql.append("where 1=1 ");
		if (StringUtils.isNotBlank(rawMaterialName)) {
			sql.append("and rawMaterialName like '%");
			sql.append(rawMaterialName);
			sql.append("%' ");
		}
		if (StringUtils.isNotBlank(specification)) {
			sql.append("and specification like '%");
			sql.append(specification);
			sql.append("%' ");
		}
		if (StringUtils.isNotBlank(loginCode)) {
			sql.append("and loginCode like '%");
			sql.append(loginCode);
			sql.append("%' ");
		}
		if (StringUtils.isNotBlank(beginTime)) {
			sql.append("and outDate >= '");
			sql.append(beginTime);
			sql.append("' ");
		}
		if (StringUtils.isNotBlank(endTime)) {
			sql.append("and outDate <= '");
			sql.append(endTime);
			sql.append("' ");
		}
		List<?> list = this.getBySQL(sql.toString());
		Map<String, Float> data = new HashMap<String, Float>();
		if (list == null || list.size() == 0) {
			data.put("nums", 0F);
		} else {
			data.put("nums", Float.valueOf(list.get(0) == null ? "0" : list.get(0).toString()));
		}
		return data;
	}
}
