package cn.live.controller.repertory;

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

import cn.live.bean.RepertoryIn;
import cn.live.enums.OperateCode;
import cn.live.manager.RepertoryInManager;
import cn.live.util.OperateResult;

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
	 * @Fields repertoryInManager : 入库
	 */
	@Resource(name = "repertoryInManager")
	private RepertoryInManager repertoryInManager;
	
	/** 
	 * @Title: list 
	 * @Description: TODO 入库管理列表
	 * @param @return 
	 * @return String
	 * @throws 
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
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
				RepertoryIn repertoryIn = repertoryInManager.findById(ids);
				repertoryIn.setIsDeleted(true);
				repertoryIn.setModifyDate(simpleDateFormat.format(new Date()));
				repertoryInManager.merge(repertoryIn);
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
				RepertoryIn repertoryIn = repertoryInManager.findById(ids);
				repertoryIn.setModifyDate(simpleDateFormat.format(new Date()));
				repertoryInManager.merge(repertoryIn);
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
		return "repertory/in/new";
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
	public OperateResult<String> add(RepertoryIn repertoryIn) {
		OperateResult<String> operateResult = new OperateResult<String>();
		try {
			repertoryIn.setId(UUID.randomUUID().toString());
			repertoryIn.setIsDeleted(false);
			repertoryIn.setCreateDate(simpleDateFormat.format(new Date()));
			repertoryIn.setModifyDate(simpleDateFormat.format(new Date()));
			repertoryInManager.create(repertoryIn);
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
