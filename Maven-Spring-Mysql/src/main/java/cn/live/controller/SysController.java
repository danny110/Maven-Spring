package cn.live.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.live.bean.RawMaterial;
import cn.live.manager.RawMaterialManager;
import cn.live.util.Filter;
import cn.live.util.OperateResult;
import enums.OperateCode;

/**
 * @ClassName: SysController
 * @Description: TODO 系统管理
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月18日 下午5:34:27
 *
 */
@Controller
@RequestMapping("/admin")
public class SysController {
	
	/**
	 * @Fields rawMaterialManager : 原料
	 */
	@Resource(name = "rawMaterialManager")
	private RawMaterialManager rawMaterialManager;
	
	/** 
	 * @Title: index 
	 * @Description: TODO 跳转到首页
	 * @param @return 
	 * @return String
	 * @throws 
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/index";
	}
	
	
	/** 
	 * @Title: getAllRawMaterial 
	 * @Description: TODO 获取原料列表
	 * @param @return 
	 * @return List<RawMaterial>
	 * @throws 
	 */
	@ResponseBody
	@RequestMapping(value = "/rawMaterial", method = RequestMethod.POST)
	public OperateResult<List<RawMaterial>> getAllRawMaterial() {
		OperateResult<List<RawMaterial>> operateResult = new OperateResult<List<RawMaterial>>();
		try {
			Filter filter = Filter.eq("isDeleted", false);
			operateResult.isSuccess = true;
			operateResult.returnValue = rawMaterialManager.getList(new Filter[]{filter});
		} catch (Exception e) {
			e.printStackTrace();
			operateResult.isSuccess = false;
			operateResult.errorReason = OperateCode.ERROR.toString();
		}
		return operateResult;
	}
	
}
