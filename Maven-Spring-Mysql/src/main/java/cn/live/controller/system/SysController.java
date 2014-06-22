package cn.live.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.live.bean.RawMaterial;
import cn.live.bean.User;
import cn.live.enums.OperateCode;
import cn.live.manager.RawMaterialManager;
import cn.live.manager.UserManager;
import cn.live.util.BaseUtils;
import cn.live.util.Filter;
import cn.live.util.OperateResult;

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
	 * @Fields _LOGINCODE : 用户帐号标识
	 */
	public static final String _LOGINCODE = "_LOGINCODE";
	
	/**
	 * @Fields rawMaterialManager : 原料
	 */
	@Resource(name = "rawMaterialManager")
	private RawMaterialManager rawMaterialManager;
	
	/**
	 * @Fields userManager : 用户
	 */
	@Resource(name = "userManager")
	private UserManager userManager;
	
	/** 
	 * @Title: index 
	 * @Description: TODO 跳转到登陆
	 * @param @return 
	 * @return String
	 * @throws 
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "system/index";
	}
	
	
	
	/** 
	 * @Title: login 
	 * @Description: TODO 登陆验证
	 * @param @param username
	 * @param @param password
	 * @param @return 
	 * @return OperateResult<String>
	 * @throws 
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public OperateResult<String> login(HttpServletRequest request, String loginCode, String password) {
		OperateResult<String> operateResult = new OperateResult<String>();
		try {
			Filter[] filters = new Filter[] {Filter.eq("loginCode", loginCode), 
					Filter.eq("password", BaseUtils.getMD5(password.getBytes())),
					Filter.eq("enabled", true), 
					Filter.eq("isDeleted", false)};
			List<User> user = userManager.getList(filters);
			if (user.size() == 1) {
				operateResult.isSuccess = true;
				operateResult.returnValue = OperateCode.USERSUCCESS.toString();
				request.getSession().setAttribute(_LOGINCODE, user.get(0).getLoginCode());
				
			} else if (user.size() > 1) {
				operateResult.isSuccess = false;
				operateResult.errorReason = OperateCode.USEREXCEPTION.toString();
				request.getSession().setAttribute("_LOGINSTORE", null);
			} else {
				operateResult.isSuccess = false;
				operateResult.errorReason = OperateCode.USERERROR.toString();
				request.getSession().setAttribute(_LOGINCODE, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			operateResult.isSuccess = false;
			operateResult.errorReason = OperateCode.USERERROR.toString();
			request.getSession().setAttribute(_LOGINCODE, null);
		}
		return operateResult;
	}
	
	/** 
	 * @Title: home 
	 * @Description: TODO 跳转到首页
	 * @param @return 
	 * @return String
	 * @throws 
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {
		return "system/home";
	}
	
	/** 
	 * @Title: logout 
	 * @Description: TODO 退出系统
	 * @param @param request
	 * @param @return 
	 * @return String
	 * @throws 
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().setAttribute(_LOGINCODE, null);
		return "system/index";
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
