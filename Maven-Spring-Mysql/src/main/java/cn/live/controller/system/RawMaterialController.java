package cn.live.controller.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.live.bean.RawMaterial;
import cn.live.manager.RawMaterialManager;
import cn.live.util.Filter;
import cn.live.util.OperateResult;
import cn.live.util.ResultJson;
import enums.OperateCode;

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
	 * @Fields rawMaterialManager : 原料
	 */
	@Resource(name = "rawMaterialManager")
	private RawMaterialManager rawMaterialManager;
	
	/** 
	 * @Title: list 
	 * @Description: TODO 原料管理列表
	 * @param @return 
	 * @return String
	 * @throws 
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "system/rawMaterial/list";
	}
	
	/** 
	 * @Title: data 
	 * @Description: TODO 返回所有的原料列表
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
			resultJson = rawMaterialManager.getResultJson(page, rows, sidx, sord, new String[]{"id","name","units","mark","enabled","createDate"}, new Filter[]{filter});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultJson;
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
				RawMaterial rawMaterial = rawMaterialManager.findById(ids);
				rawMaterial.setIsDeleted(true);
				rawMaterial.setModifyDate(simpleDateFormat.format(new Date()));
				rawMaterialManager.merge(rawMaterial);
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
				RawMaterial rawMaterial = rawMaterialManager.findById(ids);
				rawMaterial.setEnabled(enabled);
				rawMaterial.setModifyDate(simpleDateFormat.format(new Date()));
				rawMaterialManager.merge(rawMaterial);
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
		return "system/rawMaterial/new";
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
}
