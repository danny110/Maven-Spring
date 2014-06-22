package cn.live.controller.system;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import cn.live.util.Filter;
import cn.live.util.OperateResult;
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
	 * @Fields userManager : 用户
	 */
	@Resource(name = "userManager")
	private UserManager userManager;
	
	/** 
	 * @Title: list 
	 * @Description: TODO 用户管理列表
	 * @param @return 
	 * @return String
	 * @throws 
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "system/user/list";
	}
	

	/** 
	 * @Title: data 
	 * @Description: TODO 返回所有的用户列表
	 * @param @param page 当前页码
	 * @param @param rows 每页记录条数
	 * @param @param sidx 排序字段
	 * @param @param sord 排序类型
	 * @param @return 
	 * @return ResultJson
	 * @throws 
	 */
	@ResponseBody
	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public ResultJson data(Integer page, Integer rows, String sidx, String sord) {
		ResultJson resultJson = new ResultJson();
		try {
			new Filter();
			Filter filter = Filter.eq("isDeleted", false);
			resultJson = userManager.getResultJson(page, rows, sidx, sord, new String[]{"id", "sex", "name","phone","companyName","telephone","mark","enabled","createDate"}, new Filter[]{filter});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultJson;
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
				User user = userManager.findById(ids);
				user.setIsDeleted(true);
				user.setModifyDate(simpleDateFormat.format(new Date()));
				userManager.merge(user);
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
			if (enabled != null) {
				User user = userManager.findById(ids);
				user.setEnabled(enabled);
				user.setModifyDate(simpleDateFormat.format(new Date()));
				userManager.merge(user);
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
			user.setId(UUID.randomUUID().toString());
			user.setIsDeleted(false);
			user.setCreateDate(simpleDateFormat.format(new Date()));
			user.setModifyDate(simpleDateFormat.format(new Date()));
			userManager.create(user);
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
