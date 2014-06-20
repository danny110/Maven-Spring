package cn.live.controller.system;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.live.manager.RawMaterialManager;
import cn.live.util.ResultJson;

/**
 * @ClassName: RawMaterial
 * @Description: TODO 原料管理
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月18日 下午10:49:02
 *
 */
@Controller
@RequestMapping("/admin/rawMaterial")
public class RawMaterialController {

	@Resource(name = "rawMaterialManager")
	private RawMaterialManager rawMaterialManager;
	
	/** 
	 * @Title: list 
	 * @Description: TODO 原料管理列表
	 * @param @return 
	 * @return String
	 * @throws 
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "system/rawMaterial/list";
	}
	
	/** 
	 * @Title: data 
	 * @Description: TODO 返回所有的原料列表
	 * @param @param page 当前页码
	 * @param @param rows 每页记录条数
	 * @param @param sidx 排序字段
	 * @param @param sord 排序类型
	 * @param @return 
	 * @return ResultJson<RawMaterial>
	 * @throws 
	 */
	@ResponseBody
	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public ResultJson data(Integer page, Integer rows, String sidx, String sord) {
		ResultJson resultJson = new ResultJson();
		try {
			resultJson = rawMaterialManager.getResultJson(page, rows, sidx, sord, new String[]{"id","name","mark","enabled","createDate"});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultJson;
	}
}
