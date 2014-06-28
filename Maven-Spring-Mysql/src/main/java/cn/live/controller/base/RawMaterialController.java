package cn.live.controller.base;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.live.bean.RawMaterial;
import cn.live.enums.OperateCode;
import cn.live.enums.Units;
import cn.live.manager.RawMaterialManager;
import cn.live.util.Filter;
import cn.live.util.OperateResult;
import cn.live.util.Order;
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
	
	/**
	 * @Fields simpleDateFormat : 日期格式
	 */
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * @Fields PAGE : 初始化当前页码
	 */
	private static Integer PAGE = 0;
	
	/**
	 * @Fields SIZE : 初始化每页行数
	 */
	private static Integer SIZE = 10;
	
	/**
	 * @Fields rawMaterialManager : 原料
	 */
	@Resource(name = "rawMaterialManager")
	private RawMaterialManager rawMaterialManager;
	
	/**
	 * @Title: list
	 * @Description: TODO 原料管理列表
	 * @param @param name
	 * @param @param enabled
	 * @param @param page
	 * @param @param size
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "/list")
	public String list(String name, Boolean enabled, Integer page, Integer size, Model model) {
		List<Filter> filters = new ArrayList<Filter>();
		if (StringUtils.isNotBlank(name)) {
			filters.add(Filter.like("name", "%" + name + "%"));
		}
		if (enabled != null) {
			filters.add(Filter.eq("enabled", enabled));
		}
		filters.add(Filter.eq("isDeleted", false));
		
		List<Order> orders = new ArrayList<Order>();
		orders.add(Order.desc("modifyDate"));
		
		page = page == null ? PAGE : page;
		size = size == null ? SIZE : size;
		
		ResultJson resultJson = rawMaterialManager.getResultJson(page, size, new String[]{"id","name","specification","units","mark","enabled","createDate"}, filters, orders);
		model.addAttribute("name", name);
		model.addAttribute("enabled", enabled);
		model.addAttribute("ResultJson", resultJson);
		return "base/rawMaterial/list";
	}
	
	/** 
	 * @Title: del 
	 * @Description: TODO 删除原料
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
				for (String id : ids.split(",")) {
					RawMaterial rawMaterial = rawMaterialManager.findById(id);
					rawMaterial.setIsDeleted(true);
					rawMaterial.setModifyDate(simpleDateFormat.format(new Date()));
					rawMaterialManager.merge(rawMaterial);
				}
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
			if (StringUtils.isNotBlank(ids) && enabled != null) {
				for (String id : ids.split(",")) {
					RawMaterial rawMaterial = rawMaterialManager.findById(id);
					rawMaterial.setEnabled(enabled);
					rawMaterial.setModifyDate(simpleDateFormat.format(new Date()));
					rawMaterialManager.merge(rawMaterial);
				}
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
	public String New() {
		return "base/rawMaterial/new";
	}
	
	/** 
	 * @Title: add 
	 * @Description: TODO 新增一条原料记录
	 * @param @param rawMaterial
	 * @param @return 
	 * @return OperateResult<Boolean>
	 * @throws 
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public OperateResult<String> add(RawMaterial rawMaterial) {
		OperateResult<String> operateResult = new OperateResult<String>();
		try {
			rawMaterial.setId(UUID.randomUUID().toString());
			rawMaterial.setIsDeleted(false);
			rawMaterial.setCreateDate(simpleDateFormat.format(new Date()));
			rawMaterial.setModifyDate(simpleDateFormat.format(new Date()));
			rawMaterialManager.create(rawMaterial);
			operateResult.isSuccess = true;
			operateResult.returnValue = OperateCode.SUCCESS.toString();
		} catch (Exception e) {
			e.printStackTrace();
			operateResult.isSuccess = false;
			operateResult.errorReason = OperateCode.ERROR.toString();
		}
		return operateResult;
	}
	
	/**
	 * @Title: view
	 * @Description: TODO 浏览页面
	 * @param @param id
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "/view-{id}", method = RequestMethod.GET)
	public String view(@PathVariable String id, Model model) {
		try {
			if (StringUtils.isNotBlank(id)) {
				RawMaterial rawMaterial = rawMaterialManager.findById(id);
				model.addAttribute("RawMaterial", rawMaterial);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "base/rawMaterial/view";
	}
	
	/**
	 * @Title: edit
	 * @Description: TODO 编辑页面
	 * @param @param id
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "/edit-{id}", method = RequestMethod.GET)
	public String edit(@PathVariable String id, Model model) {
		try {
			if (StringUtils.isNotBlank(id)) {
				RawMaterial rawMaterial = rawMaterialManager.findById(id);
				model.addAttribute("RawMaterial", rawMaterial);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "base/rawMaterial/edit";
	}
	
	/**
	 * @Title: update
	 * @Description: TODO 更新
	 * @param @param id
	 * @param @param name
	 * @param @param specification
	 * @param @param units
	 * @param @param mark
	 * @param @param enabled
	 * @param @return
	 * @return OperateResult<String>
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public OperateResult<String> update(String id, String name, String specification, String units, String mark, String enabled) {
		OperateResult<String> operateResult = new OperateResult<String>();
		try {
			if (StringUtils.isNotBlank(id)) {
				List<RawMaterial> rawMaterials = rawMaterialManager.getList(new Filter[]{Filter.eq("id", id)});
				if (rawMaterials != null && rawMaterials.size() == 1) {
					RawMaterial rawMaterial = rawMaterials.get(0);
					rawMaterial.setName(name);
					rawMaterial.setSpecification(specification);;
					rawMaterial.setUnits(Units.valueOf(units));;
					rawMaterial.setMark(mark);
					rawMaterial.setEnabled(Boolean.valueOf(enabled));
					rawMaterial.setModifyDate(simpleDateFormat.format(new Date()));
					rawMaterialManager.merge(rawMaterial);
					operateResult.isSuccess = true;
					operateResult.returnValue = OperateCode.SUCCESS.toString();
				}
			} else {
				operateResult.isSuccess = false;
				operateResult.errorReason = OperateCode.NOPARAMS.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			operateResult.isSuccess = false;
			operateResult.errorReason = OperateCode.ERROR.toString();
		}
		return operateResult;
	}
}
