package cn.live.controller.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.live.bean.RawMaterial;
import cn.live.bean.Specification;
import cn.live.manager.RawMaterialManager;
import cn.live.manager.SpecificationManager;
import cn.live.manager.SpecificationViewManager;
import cn.live.util.Filter;
import cn.live.util.OperateResult;
import cn.live.util.ResultJson;
import enums.OperateCode;

/**
 * @ClassName: SpecificationController
 * @Description: TODO 规格管理
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月18日 下午10:57:38
 *
 */

@Controller
@RequestMapping("/admin/specification")
public class SpecificationController {
	
	/**
	 * @Fields simpleDateFormat : 日期格式
	 */
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * @Fields rawMaterialManager : 原料
	 */
	@Resource(name = "rawMaterialManager")
	private RawMaterialManager rawMaterialManager;
	
	/**
	 * @Fields specificationManager : 规格
	 */
	@Resource(name = "specificationManager")
	private SpecificationManager specificationManager;
	
	/**
	 * @Fields specificationManager : 规格视图
	 */
	@Resource(name = "specificationViewManager")
	private SpecificationViewManager specificationViewManager;
	
	/** 
	 * @Title: list 
	 * @Description: TODO 规格管理列表
	 * @param @return 
	 * @return String
	 * @throws 
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "system/specification/list";
	}
	
	/** 
	 * @Title: data 
	 * @Description: TODO 返回所有的规格列表
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
			new Filter();
			Filter filter = Filter.eq("isDeleted", false);
			resultJson = specificationViewManager.getResultJson(page, rows, sidx, sord, new String[]{"id", "rawMaterialName","specificatioName","mark","enabled","createDate"}, new Filter[]{filter});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultJson;
	}
	
	/** 
	 * @Title: del 
	 * @Description: TODO 删除规格
	 * @param @param id 
	 * @return void
	 * @throws 
	 */
	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public OperateResult<String> del(String ids) {
		OperateResult<String> operateResult = new OperateResult<String>();
		try {
			if (StringUtils.isNotBlank(ids)) {
				Specification specification = specificationManager.findById(ids);
				specification.setIsDeleted(true);
				specification.setModifyDate(simpleDateFormat.format(new Date()));
				specificationManager.merge(specification);
				operateResult.isSuccess = true;
				operateResult.returnValue = OperateCode.SUCCESS.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			operateResult.isSuccess = false;
			operateResult.errorReason = OperateCode.ERROR.toString();
		}
		return operateResult;
	}
	
	/** 
	 * @Title: enabled 
	 * @Description: TODO 启用或禁用
	 * @param @param ids
	 * @param @param enabled
	 * @param @return 
	 * @return OperateResult<Boolean>
	 * @throws 
	 */
	@ResponseBody
	@RequestMapping(value = "/enabled", method = RequestMethod.POST)
	public OperateResult<String> enabled(String ids, Boolean enabled) {
		OperateResult<String> operateResult = new OperateResult<String>();
		try {
			if (enabled != null) {
				Specification specification = specificationManager.findById(ids);
				specification.setEnabled(enabled);
				specification.setModifyDate(simpleDateFormat.format(new Date()));
				specificationManager.merge(specification);
				operateResult.isSuccess = true;
				operateResult.returnValue = OperateCode.SUCCESS.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			operateResult.isSuccess = false;
			operateResult.errorReason = OperateCode.ERROR.toString();
		}
		return operateResult;
	}
	
	/** 
	 * @Title: New 
	 * @Description: TODO 跳转到新增页面
	 * @param @return 
	 * @return String
	 * @throws 
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String New(Model model) {
		try {
			Filter filter = Filter.eq("isDeleted", false);
			List<RawMaterial> rawMaterials = rawMaterialManager.getList(new Filter[] {filter});
			model.addAttribute("RawMaterial", rawMaterials);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "system/specification/new";
	}
	
	/** 
	 * @Title: add 
	 * @Description: TODO 新增一条规格记录
	 * @param @param rawMaterial
	 * @param @return 
	 * @return OperateResult<Boolean>
	 * @throws 
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public OperateResult<String> add(Specification specification) {
		OperateResult<String> operateResult = new OperateResult<String>();
		try {
			specification.setId(UUID.randomUUID().toString());
			specification.setIsDeleted(false);
			specification.setCreateDate(simpleDateFormat.format(new Date()));
			specification.setModifyDate(simpleDateFormat.format(new Date()));
			specificationManager.create(specification);
			operateResult.isSuccess = true;
			operateResult.returnValue = OperateCode.SUCCESS.toString();
		} catch (Exception e) {
			e.printStackTrace();
			operateResult.isSuccess = false;
			operateResult.errorReason = OperateCode.ERROR.toString();
		}
		return operateResult;
	}
}
