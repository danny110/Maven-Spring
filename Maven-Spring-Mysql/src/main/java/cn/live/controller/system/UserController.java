package cn.live.controller.system;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import cn.live.bean.User;
import cn.live.enums.OperateCode;
import cn.live.manager.UserManager;
import cn.live.util.BaseUtils;
import cn.live.util.Filter;
import cn.live.util.OperateResult;
import cn.live.util.Order;
import cn.live.util.ResultJson;

/**
 * @ClassName: UserController
 * @Description: TODO 用户管理
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月18日 下午5:34:07
 *
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {
	
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
	 * @Fields userManager : 用户
	 */
	@Resource(name = "userManager")
	private UserManager userManager;
	
	
	/** 
	 * @Title: list
	 * @Description: TODO 用户管理列表
	 * @param @param loginCode 帐号
	 * @param @param enabled 是否启用
	 * @param @param page 当前页码
	 * @param @param size 每页行数
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws 
	 */
	@RequestMapping(value = "/list")
	public String list(String loginCode, Boolean enabled, Integer page, Integer size, Model model) {
		try {
			List<Filter> filters = new ArrayList<Filter>();
			if (StringUtils.isNotBlank(loginCode)) {
				filters.add(Filter.like("loginCode", "%" + loginCode + "%"));
			}
			if (enabled != null) {
				filters.add(Filter.eq("enabled", enabled));
			}
			filters.add(Filter.eq("isDeleted", false));
			
			List<Order> orders = new ArrayList<Order>();
			orders.add(Order.desc("modifyDate"));
			
			page = page == null ? PAGE : page;
			size = size == null ? SIZE : size;
			
			ResultJson resultJson = userManager.getResultJson(page, size, new String[]{"id", "loginCode", "mark","enabled","createDate"}, filters, orders);
			model.addAttribute("loginCode", loginCode);
			model.addAttribute("enabled", enabled);
			model.addAttribute("ResultJson", resultJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "system/user/list";
	}
	
	/** 
	 * @Title: del 
	 * @Description: TODO 删除用户
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
					User user = userManager.findById(id);
					user.setIsDeleted(true);
					user.setModifyDate(simpleDateFormat.format(new Date()));
					userManager.merge(user);
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
					User user = userManager.findById(id);
					user.setEnabled(enabled);
					user.setModifyDate(simpleDateFormat.format(new Date()));
					userManager.merge(user);
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
		return "system/user/new";
	}
	

	/** 
	 * @Title: add 
	 * @Description: TODO 新增一条用户记录
	 * @param @param client
	 * @param @return 
	 * @return OperateResult<String>
	 * @throws 
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public OperateResult<String> add(User user) {
		OperateResult<String> operateResult = new OperateResult<String>();
		try {
			// 判断帐号是否存在
			List<User> users = userManager.getList(new Filter[]{Filter.eq("loginCode", user.getLoginCode())});
			if (users == null || users.size() == 0) {
				user.setId(UUID.randomUUID().toString());
				user.setPassword(BaseUtils.getMD5(user.getPassword().getBytes()));
				user.setIsDeleted(false);
				user.setCreateDate(simpleDateFormat.format(new Date()));
				user.setModifyDate(simpleDateFormat.format(new Date()));
				userManager.create(user);
				operateResult.isSuccess = true;
				operateResult.returnValue = OperateCode.SUCCESS.toString();
			} else {
				operateResult.isSuccess = false;
				operateResult.errorReason = OperateCode.EXISTLOGINCODE.toString();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			operateResult.isSuccess = false;
			operateResult.errorReason = OperateCode.ERROR.toString();
		}
		return operateResult;
	}

}
