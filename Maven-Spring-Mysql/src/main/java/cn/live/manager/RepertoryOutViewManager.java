package cn.live.manager;

import java.util.Map;

import cn.live.bean.view.RepertoryOutView;

/**
 * @ClassName: RepertoryOutViewManager
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月25日 下午4:05:10
 *
 */
public interface RepertoryOutViewManager extends BaseManager<RepertoryOutView, String> {

	/**
	 * @Title: getSumBySQL
	 * @Description: TODO 通过 SQL 获取合计
	 * @param @param rawMaterialId 原料
	 * @param @param loginCode 经手人
	 * @param @param beginTime 开始日期
	 * @param @param endTime 结束日期
	 * @param @return
	 * @return Map<String,Float>
	 * @throws
	 */
	Map<String, Float> getSumBySQL(String rawMaterialId, String loginCode, String beginTime, String endTime);
	
	/**
	 * @Title: getSumBySQL
	 * @Description: TODO 通过 SQL 获取合计
	 * @param @param rawMaterialName
	 * @param @param specification
	 * @param @param loginCode
	 * @param @param beginTime
	 * @param @param endTime
	 * @param @return
	 * @return Map<String,Float>
	 * @throws
	 */
	Map<String, Float> getSumBySQL(String rawMaterialName, String specification, String loginCode, String beginTime, String endTime);
}
