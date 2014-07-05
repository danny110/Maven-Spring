package cn.live.manager;

import java.util.Map;

import cn.live.bean.view.RepertoryInView;

/**
 * @ClassName: RepertoryInViewManager
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月24日 下午11:36:36
 *
 */
public interface RepertoryInViewManager extends BaseManager<RepertoryInView, String> {

	/**
	 * @Title: getSumBySQL
	 * @Description: TODO 通过 SQL 获取合计
	 * @param @param rawMaterialId 原料
	 * @param @param companyName 进货单位名称
	 * @param @param loginCode 经手人
	 * @param @param beginTime 开始日期
	 * @param @param endTime 结束日期
	 * @param @return
	 * @return Map<String,Float>
	 * @throws
	 */
	Map<String, Float> getSumBySQL(String rawMaterialId, String companyName, String loginCode, String beginTime, String endTime);
}
