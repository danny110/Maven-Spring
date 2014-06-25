package cn.live.controller.repertory;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.live.manager.RepertoryOverViewManager;
import cn.live.util.Filter;
import cn.live.util.ResultJson;

/**
 * @ClassName: RepertoryInCollection
 * @Description: TODO 库存统计
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月22日 上午6:46:09
 *
 */
@Controller
@RequestMapping("/admin/repertory/over/")
public class RepertoryOverController {
	
	/**
	 * @Fields repertoryOverViewManager : 库存视图
	 */
	@Resource(name = "repertoryOverViewManager")
	public RepertoryOverViewManager repertoryOverViewManager;
	
	
	/** 
	 * @Title: list 
	 * @Description: TODO 库存管理列表
	 * @param @return 
	 * @return String
	 * @throws 
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "repertory/over/list";
	}
	
	/** 
	 * @Title: data 
	 * @Description: TODO 返回所有的库存信息列表
	 * @param @param page
	 * @param @param rows
	 * @param @param sidx
	 * @param @param sord
	 * @param @return 
	 * @return ResultJson
	 * @throws 
	 */
	@ResponseBody
	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public ResultJson data(Integer page, Integer rows, String sidx, String sord) {
		ResultJson resultJson = new ResultJson();
		try {
			resultJson = repertoryOverViewManager.getResultJson(page, rows, sidx, sord, new String[]{"rawMaterialId", "rawMaterialName", "specification","units","inNum","outNum","overNum"}, new Filter[]{});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultJson;
	}
	
}
