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

import cn.live.bean.Client;
import cn.live.enums.OperateCode;
import cn.live.manager.ClientManager;
import cn.live.util.Filter;
import cn.live.util.OperateResult;
import cn.live.util.Order;
import cn.live.util.ResultJson;

/**
 * @ClassName: ClientController
 * @Description: TODO 来往单位（客户）管理
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月18日 下午10:56:07
 *
 */
@Controller
@RequestMapping("/admin/client")
public class ClientController {
	
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
	 * @Fields clientManager : 来往单位（客户）
	 */
	@Resource(name = "clientManager")
	private ClientManager clientManager;
	
	/**
	 * @Title: list 来往单位（客户）列表
	 * @Description: TODO
	 * @param @param companyName 单位名称
	 * @param @param name 联系人
	 * @param @param enabled 是否启用
	 * @param @param page 当前页码
	 * @param @param size
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "/list")
	public String list(String companyName, String name, Boolean enabled, Integer page, Integer size, Model model) {
		List<Filter> filters = new ArrayList<Filter>();
		if (StringUtils.isNotBlank(companyName)) {
			filters.add(Filter.like("companyName", "%" + companyName + "%"));
		}
		if (StringUtils.isNotBlank(name)) {
			filters.add(Filter.like("name", "%" + name + "%"));
		}
		if (enabled != null) {
			filters.add(Filter.eq("enabled", enabled));
		}
		if (StringUtils.isNotBlank(name) || enabled != null) {
//			page = 1;
		}
		filters.add(Filter.eq("isDeleted", false));
		
		List<Order> orders = new ArrayList<Order>();
		orders.add(Order.desc("createDate"));
		
		page = page == null ? PAGE : page;
		size = size == null ? SIZE : size;
		
		ResultJson resultJson = clientManager.getResultJson(page, size, new String[]{"id", "sex", "name","phone","companyName","telephone","mark","enabled","createDate"}, filters, orders);
		model.addAttribute("name", name);
		model.addAttribute("enabled", enabled);
		model.addAttribute("ResultJson", resultJson);
		return "base/client/list";
	}
	
	/** 
	 * @Title: del 
	 * @Description: TODO 删除来往单位（客户）
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
					Client client = clientManager.findById(id);
					client.setIsDeleted(true);
					client.setModifyDate(simpleDateFormat.format(new Date()));
					clientManager.merge(client);
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
	@ResponseBody
	@RequestMapping(value = "/enabled", method = RequestMethod.POST)
	public OperateResult<String> enabled(String ids, Boolean enabled) {
		OperateResult<String> operateResult = new OperateResult<String>();
		try {
			if (StringUtils.isNotBlank(ids) && enabled != null) {
				for (String id : ids.split(",")) {
					Client client = clientManager.findById(id);
					client.setEnabled(enabled);
					client.setModifyDate(simpleDateFormat.format(new Date()));
					clientManager.merge(client);
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
	public String New(Model model) {
		return "base/client/new";
	}
	

	/** 
	 * @Title: add 
	 * @Description: TODO 新增一条来往单位（客户）记录
	 * @param @param client
	 * @param @return 
	 * @return OperateResult<String>
	 * @throws 
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public OperateResult<String> add(Client client) {
		OperateResult<String> operateResult = new OperateResult<String>();
		try {
			// 判断帐号是否存在
			List<Filter> filters = new ArrayList<Filter>();
			filters.add(Filter.eq("companyName", client.getCompanyName()));
			
			List<Client> clients = clientManager.getList(filters);
			if (clients == null || clients.size() == 0) {
				client.setId(UUID.randomUUID().toString());
				client.setIsDeleted(false);
				client.setCreateDate(simpleDateFormat.format(new Date()));
				client.setModifyDate(simpleDateFormat.format(new Date()));
				clientManager.create(client);
				operateResult.isSuccess = true;
				operateResult.returnValue = OperateCode.SUCCESS.toString();
			} else {
				operateResult.isSuccess = false;
				operateResult.errorReason = OperateCode.EXISTCONPANYNAME.toString();
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
				Client client = clientManager.findById(id);
				model.addAttribute("Client", client);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "base/client/view";
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
	/*@RequestMapping(value = "/edit-{id}", method = RequestMethod.GET)
	public String edit(@PathVariable String id, Model model) {
		try {
			if (StringUtils.isNotBlank(id)) {
				Client client = clientManager.findById(id);
				model.addAttribute("Client", client);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "base/client/edit";
	}*/
	
	/**
	 * @Title: update
	 * @Description: TODO 更新
	 * @param @param id
	 * @param @param name
	 * @param @param sex
	 * @param @param companyName
	 * @param @param phone
	 * @param @param telephone
	 * @param @param mark
	 * @param @param enabled
	 * @param @return
	 * @return OperateResult<String>
	 * @throws
	 */
	/*@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public OperateResult<String> update(String id, String name, String sex, String companyName,
			String phone, String telephone, String mark, String enabled) {
		OperateResult<String> operateResult = new OperateResult<String>();
		try {
			if (StringUtils.isNotBlank(id)) {
				List<Filter> filters = new ArrayList<Filter>();
				filters.add(Filter.eq("id", id));
				List<Client> clients = clientManager.getList(filters);
				if (clients != null && clients.size() == 1) {
					Client client = clients.get(0);
					client.setName(name);
					client.setSex(Gender.valueOf(sex));
					client.setCompanyName(companyName);
					client.setPhone(phone);
					client.setTelephone(telephone);
					client.setMark(mark);
					client.setEnabled(Boolean.valueOf(enabled));
					client.setModifyDate(simpleDateFormat.format(new Date()));
					clientManager.merge(client);
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
	}*/
}
