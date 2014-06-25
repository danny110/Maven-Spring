package cn.live.controller.repertory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	 * @param @return 
	 * @return String
	 * @throws 
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "repertory/out/list";
	}
	
	/** 
	 * @Title: data 
	 * @Description: TODO 返回所有的出库信息列表
	 * @param @param page
	 * @param @param rows
	 * @param @param sidx
	 * @param @param sord
	 * @param @return 
	 * @return ResultJson
	 * @throws 
	 */
	@ResponseBody
	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public ResultJson data(Integer page, Integer rows, String sidx, String sord) {
		ResultJson resultJson = new ResultJson();
		try {
			resultJson = repertoryOutViewManager.getResultJson(page, rows, sidx, sord, new String[]{"id", "rawMaterialName", "specification","num","mark","loginCode","createDate"}, new Filter[]{});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultJson;
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
				RepertoryOut repertoryOut = repertoryOutManager.findById(ids);
				repertoryOut.setIsDeleted(true);
				repertoryOut.setModifyDate(simpleDateFormat.format(new Date()));
				repertoryOutManager.merge(repertoryOut);
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
				RepertoryOut repertoryOut = repertoryOutManager.findById(ids);
				repertoryOut.setModifyDate(simpleDateFormat.format(new Date()));
				repertoryOutManager.merge(repertoryOut);
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
		Filter[] filters = new Filter[]{
			Filter.eq("enabled", true),
			Filter.eq("isDeleted", false)
		};
		Order[] orders = new Order[] {
				Order.asc("name"),
				Order.asc("specification")
		};
		List<RawMaterial> rawMaterials = rawMaterialManager.getList(filters, orders);
		model.addAttribute("rawrawMaterial", rawMaterials);
		return "repertory/out/new";
	}
	
	/** 
	 * @Title: add 
	 * @Description: TODO 新增一条客户记录
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
				Filter[] filters = new Filter[]{
						Filter.eq("loginCode", loginCode),
						Filter.eq("enabled", true),
						Filter.eq("isDeleted", false)
					};
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
}
