package cn.live.controller.repertory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.live.bean.RawMaterial;
import cn.live.bean.RepertoryOut;
import cn.live.bean.User;
import cn.live.enums.OperateCode;
import cn.live.manager.RawMaterialManager;
import cn.live.manager.RepertoryOutManager;
import cn.live.manager.RepertoryOutViewManager;
import cn.live.manager.UserManager;
import cn.live.util.Filter;
import cn.live.util.OperateResult;
import cn.live.util.Order;
import cn.live.util.ResultJson;

/**
 * @ClassName: RepertoryInCollection
 * @Description: TODO 出库信息
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月22日 上午6:46:09
 *
 */
@Controller
@RequestMapping("/admin/repertory/out/")
public class RepertoryOutController {
	
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
	 * @Fields repertoryOutManager : 出库
	 */
	@Resource(name = "repertoryOutManager")
	private RepertoryOutManager repertoryOutManager;
	
	/**
	 * @Fields rawMaterialManager : 原料
	 */
	@Resource(name = "rawMaterialManager")
	private RawMaterialManager rawMaterialManager;
	
	/**
	 * @Fields repertoryOutViewManager : 出库视图
	 */
	@Resource(name = "repertoryOutViewManager")
	public RepertoryOutViewManager repertoryOutViewManager;
	
	/**
	 * @Fields userManager : 用户
	 */
	@Resource(name = "userManager")
	private UserManager userManager;
	
	/**
	 * @Title: list
	 * @Description: TODO 出库管理列表
	 * @param @param rawMaterialName
	 * @param @param beginTime
	 * @param @param endTime
	 * @param @param page
	 * @param @param size
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "/list")
	public String list(String rawMaterialName, String beginTime, String endTime, Integer page, Integer size, Model model) {
		try {
			List<Filter> filters = new ArrayList<Filter>();
			if (StringUtils.isNotBlank(rawMaterialName)) filters.add(Filter.like("rawMaterialName", "%" + rawMaterialName + "%"));
			if (StringUtils.isNotBlank(beginTime)) filters.add(Filter.ge("createDate", beginTime));
			if (StringUtils.isNotBlank(endTime)) filters.add(Filter.le("createDate", endTime));
			if (StringUtils.isNotBlank(rawMaterialName) || StringUtils.isNotBlank(beginTime) || StringUtils.isNotBlank(endTime)) {
				page = 0;
			}
			List<Order> orders = new ArrayList<Order>();
			orders.add(Order.desc("createDate"));
			
			page = page == null ? PAGE : page;
			size = size == null ? SIZE : size;
			
			ResultJson resultJson = repertoryOutViewManager.getResultJson(page, size, new String[]{"id", "rawMaterialName", "specification","num","units","mark","loginCode","createDate"}, filters, orders);
			model.addAttribute("rawMaterialName", rawMaterialName);
			model.addAttribute("beginTime", beginTime);
			model.addAttribute("endTime", endTime);
			model.addAttribute("ResultJson", resultJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "repertory/out/list";
	}
	
	/** 
	 * @Title: del 
	 * @Description: TODO 删除出库
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
					RepertoryOut repertoryOut = repertoryOutManager.findById(id);
					repertoryOut.setIsDeleted(true);
					repertoryOut.setModifyDate(simpleDateFormat.format(new Date()));
					repertoryOutManager.merge(repertoryOut);
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
	 * @return OperateResult<String>
	 * @throws 
	 */
//	@ResponseBody
//	@RequestMapping(value = "/enabled", method = RequestMethod.POST)
//	public OperateResult<String> enabled(String ids, Boolean enabled) {
//		OperateResult<String> operateResult = new OperateResult<String>();
//		try {
//			if (enabled != null) {
//				RepertoryOut repertoryOut = repertoryOutManager.findById(ids);
//				repertoryOut.setModifyDate(simpleDateFormat.format(new Date()));
//				repertoryOutManager.merge(repertoryOut);
//				operateResult.isSuccess = true;
//				operateResult.returnValue = OperateCode.SUCCESS.toString();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			operateResult.isSuccess = false;
//			operateResult.errorReason = OperateCode.ERROR.toString();
//		}
//		return operateResult;
//	}
	
	/** 
	 * @Title: New 
	 * @Description: TODO 跳转到新增页面
	 * @param @return 
	 * @return String
	 * @throws 
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String New(Model model) {
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(Filter.eq("enabled", true));
		filters.add(Filter.eq("isDeleted", false));
		
		List<Order> orders = new ArrayList<Order>();
		orders.add(Order.asc("name"));
		orders.add(Order.asc("specification"));
		
		ResultJson resultJson = rawMaterialManager.getResultJson(new String[]{"id", "name", "specification", "units"}, filters, orders);
		model.addAttribute("resultJson", resultJson);
		return "repertory/out/new";
	}
	
	/** 
	 * @Title: add 
	 * @Description: TODO 新增一条出库记录
	 * @param @param rawMaterial
	 * @param @return 
	 * @return OperateResult<String>
	 * @throws 
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public OperateResult<String> add(HttpServletRequest request, RepertoryOut repertoryOut) {
		OperateResult<String> operateResult = new OperateResult<String>();
		try {
			String loginCode = request.getSession().getAttribute("_LOGINCODE").toString();
			if (StringUtils.isNotBlank(loginCode)) {
				List<Filter> filters = new ArrayList<Filter>();
				filters.add(Filter.eq("loginCode", loginCode));
				filters.add(Filter.eq("enabled", true));
				filters.add(Filter.eq("isDeleted", false));
				
				List<User> users = userManager.getList(filters);
				if (users.size() > 0) {
					repertoryOut.setUserId(users.get(0).getId());
					repertoryOut.setId(UUID.randomUUID().toString());
					repertoryOut.setIsDeleted(false);
					repertoryOut.setCreateDate(simpleDateFormat.format(new Date()));
					repertoryOut.setModifyDate(simpleDateFormat.format(new Date()));
					repertoryOutManager.create(repertoryOut);
					operateResult.isSuccess = true;
					operateResult.returnValue = OperateCode.SUCCESS.toString();
				} else {
					operateResult.isSuccess = false;
					operateResult.errorReason = OperateCode.UNLOGIN.toString();
				}
				
			} else {
				operateResult.isSuccess = false;
				operateResult.errorReason = OperateCode.UNLOGIN.toString();
			}
			
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
				RepertoryOut repertoryOut = repertoryOutManager.findById(id);
				
				List<Filter> filters = new ArrayList<Filter>();
				filters.add(Filter.eq("enabled", true));
				filters.add(Filter.eq("isDeleted", false));
				
				List<Order> orders = new ArrayList<Order>();
				orders.add(Order.asc("name"));
				orders.add(Order.asc("specification"));
				
				List<RawMaterial> rawMaterials = rawMaterialManager.getList(filters, orders);
				RawMaterial rawMaterial = new RawMaterial();
				for (RawMaterial material : rawMaterials) {
					if (material.getId().equals(repertoryOut.getRawMaterialId())) {
						rawMaterial = material;
						break;
					}
				}
				
				model.addAttribute("RepertoryOut", repertoryOut);
				model.addAttribute("RawrawMaterial", rawMaterial);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "repertory/out/view";
	}
}
