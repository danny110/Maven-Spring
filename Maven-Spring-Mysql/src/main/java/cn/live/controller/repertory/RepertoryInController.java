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

import cn.live.bean.Client;
import cn.live.bean.RawMaterial;
import cn.live.bean.RepertoryIn;
import cn.live.bean.User;
import cn.live.enums.OperateCode;
import cn.live.manager.ClientManager;
import cn.live.manager.RawMaterialManager;
import cn.live.manager.RepertoryInManager;
import cn.live.manager.RepertoryInViewManager;
import cn.live.manager.UserManager;
import cn.live.util.Filter;
import cn.live.util.OperateResult;
import cn.live.util.Order;
import cn.live.util.ResultJson;

/**
 * @ClassName: RepertoryInCollection
 * @Description: TODO 入库信息
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月22日 上午6:46:09
 *
 */
@Controller
@RequestMapping("/admin/repertory/in/")
public class RepertoryInController {
	
	/**
	 * @Fields simpleDateFormat : 日期格式
	 */
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * @Fields PAGE : 初始化当前页码
	 */
	private static Integer PAGE = 1;
	
	/**
	 * @Fields SIZE : 初始化每页行数
	 */
	private static Integer SIZE = 10;
	
	/**
	 * @Fields repertoryInManager : 入库
	 */
	@Resource(name = "repertoryInManager")
	private RepertoryInManager repertoryInManager;
	
	
	/**
	 * @Fields rawMaterialManager : 原料
	 */
	@Resource(name = "rawMaterialManager")
	private RawMaterialManager rawMaterialManager;
	
	/**
	 * @Fields clientManager : 客户
	 */
	@Resource(name = "clientManager")
	private ClientManager clientManager;
	
	/**
	 * @Fields userManager : 用户
	 */
	@Resource(name = "userManager")
	private UserManager userManager;
	
	/**
	 * @Fields repertoryInViewManager : 入库视图
	 */
	@Resource(name = "repertoryInViewManager")
	private RepertoryInViewManager repertoryInViewManager;
	
	/**
	 * @Title: list
	 * @Description: TODO 入库管理列表
	 * @param @param companyName
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
	public String list(String companyName, String beginTime, String endTime, Integer page, Integer size, Model model) {
		try {
			List<Filter> filters = new ArrayList<Filter>();
			if (StringUtils.isNotBlank(companyName)) filters.add(Filter.like("companyName", "%" + companyName + "%"));
			if (StringUtils.isNotBlank(beginTime)) filters.add(Filter.ge("inDate", beginTime));
			if (StringUtils.isNotBlank(endTime)) filters.add(Filter.le("inDate", endTime));
			if (StringUtils.isNotBlank(companyName) || StringUtils.isNotBlank(beginTime) || StringUtils.isNotBlank(endTime)) {
				page = 0;
			}
			List<Order> orders = new ArrayList<Order>();
			orders.add(Order.desc("inDate"));
			
			page = page == null ? PAGE : page;
			size = size == null ? SIZE : size;
			
			ResultJson resultJson = repertoryInViewManager.getResultJson(page, size, new String[]{"id", "rawMaterialName", "specification", "units","companyName","num","unitPrice","sum","mark","loginCode","inDate"}, filters, orders);
			model.addAttribute("companyName", companyName);
			model.addAttribute("beginTime", beginTime);
			model.addAttribute("endTime", endTime);
			model.addAttribute("ResultJson", resultJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "repertory/in/list";
	}
	
	/** 
	 * @Title: del 
	 * @Description: TODO 删除入库
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
					RepertoryIn repertoryIn = repertoryInManager.findById(id);
					repertoryIn.setIsDeleted(true);
					repertoryIn.setModifyDate(simpleDateFormat.format(new Date()));
					repertoryInManager.merge(repertoryIn);
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
//			if (StringUtils.isNotBlank(ids) && enabled != null) {
//				for (String id : ids.split(",")) {
//					RepertoryIn repertoryIn = repertoryInManager.findById(id);
//					repertoryIn.setModifyDate(simpleDateFormat.format(new Date()));
//					repertoryInManager.merge(repertoryIn);
//				}
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
		List<Client> clients = clientManager.getList(filters);
		model.addAttribute("resultJson", resultJson);
		model.addAttribute("client", clients);
		return "repertory/in/new";
	}
	
	/** 
	 * @Title: add 
	 * @Description: TODO 新增一条入库记录
	 * @param @param rawMaterial
	 * @param @return 
	 * @return OperateResult<String>
	 * @throws 
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public OperateResult<String> add(HttpServletRequest request, RepertoryIn repertoryIn) {
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
					repertoryIn.setUserId(users.get(0).getId());
					repertoryIn.setId(UUID.randomUUID().toString());
					repertoryIn.setIsDeleted(false);
					repertoryIn.setCreateDate(simpleDateFormat.format(new Date()));
					repertoryIn.setModifyDate(simpleDateFormat.format(new Date()));
					repertoryInManager.create(repertoryIn);
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
				RepertoryIn repertoryIn = repertoryInManager.findById(id);
				
				List<Filter> filters = new ArrayList<Filter>();
				filters.add(Filter.eq("enabled", true));
				filters.add(Filter.eq("isDeleted", false));
				
				List<Order> orders = new ArrayList<Order>();
				orders.add(Order.asc("name"));
				orders.add(Order.asc("specification"));
				List<RawMaterial> rawMaterials = rawMaterialManager.getList(filters, orders);
				RawMaterial rawMaterial = new RawMaterial();
				for (RawMaterial material : rawMaterials) {
					if (material.getId().equals(repertoryIn.getRawMaterialId())) {
						rawMaterial = material;
						break;
					}
				}
				List<Client> clients = clientManager.getList(filters);
				Client client = new Client();
				for (Client data : clients) {
					if (data.getId().equals(repertoryIn.getClientId())) {
						client = data;
						break;
					}
				}
				
				model.addAttribute("RepertoryIn", repertoryIn);
				model.addAttribute("RawrawMaterial", rawMaterial);
				model.addAttribute("Client", client);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "repertory/in/view";
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
//	@RequestMapping(value = "/edit-{id}", method = RequestMethod.GET)
//	public String edit(@PathVariable String id, Model model) {
//		try {
//			if (StringUtils.isNotBlank(id)) {
//				RepertoryIn repertoryIn = repertoryInManager.findById(id);
//				
//				Filter[] filters = new Filter[]{
//					Filter.eq("enabled", true),
//					Filter.eq("isDeleted", false)
//				};
//				Order[] orders = new Order[] {
//					Order.asc("name"),
//					Order.asc("specification")
//				};
//				List<RawMaterial> rawMaterials = rawMaterialManager.getList(filters, orders);
//				List<Client> clients = clientManager.getList(filters);
//				
//				model.addAttribute("RepertoryIn", repertoryIn);
//				model.addAttribute("rawrawMaterial", rawMaterials);
//				model.addAttribute("client", clients);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "repertory/in/edit";
//	}
	
	/**
	 * @Title: update
	 * @Description: TODO 更新
	 * @param @param request
	 * @param @param id
	 * @param @param rawMaterialId
	 * @param @param clientId
	 * @param @param num
	 * @param @param unitPrice
	 * @param @param sum
	 * @param @param mark
	 * @param @return
	 * @return OperateResult<String>
	 * @throws
	 */
//	@ResponseBody
//	@RequestMapping(value = "/update", method = RequestMethod.POST)
//	public OperateResult<String> update(HttpServletRequest request, String id, String rawMaterialId, String clientId, Float num, Float unitPrice, 
//			Float sum, String mark) {
//		OperateResult<String> operateResult = new OperateResult<String>();
//		try {
//			String loginCode = request.getSession().getAttribute("_LOGINCODE").toString();
//			if (StringUtils.isNotBlank(loginCode)) {
//				Filter[] filters = new Filter[]{
//						Filter.eq("loginCode", loginCode),
//						Filter.eq("enabled", true),
//						Filter.eq("isDeleted", false)
//					};
//				List<User> users = userManager.getList(filters);
//				if (users.size() > 0) {
//					if (StringUtils.isNotBlank(id)) {
//						List<RepertoryIn> repertoryIns = repertoryInManager.getList(new Filter[]{Filter.eq("id", id)});
//						if (repertoryIns != null && repertoryIns.size() == 1) {
//							RepertoryIn repertoryIn = repertoryIns.get(0);
//							repertoryIn.setRawMaterialId(rawMaterialId);
//							repertoryIn.setClientId(users.get(0).getId());;
//							repertoryIn.setNum(num);
//							repertoryIn.setUnitPrice(unitPrice);
//							repertoryIn.setSum(sum);
//							repertoryIn.setMark(mark);
//							repertoryIn.setModifyDate(simpleDateFormat.format(new Date()));
//							repertoryInManager.merge(repertoryIn);
//							operateResult.isSuccess = true;
//							operateResult.returnValue = OperateCode.SUCCESS.toString();
//						}
//					} else {
//						operateResult.isSuccess = false;
//						operateResult.errorReason = OperateCode.NOPARAMS.toString();
//					}
//				} else {
//					operateResult.isSuccess = false;
//					operateResult.errorReason = OperateCode.UNLOGIN.toString();
//				}
//			} else {
//				operateResult.isSuccess = false;
//				operateResult.errorReason = OperateCode.UNLOGIN.toString();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			operateResult.isSuccess = false;
//			operateResult.errorReason = OperateCode.ERROR.toString();
//		}
//		return operateResult;
//	}
}
