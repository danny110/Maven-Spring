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

import cn.live.bean.Client;
import cn.live.enums.OperateCode;
import cn.live.manager.ClientManager;
import cn.live.util.Filter;
import cn.live.util.OperateResult;
import cn.live.util.ResultJson;

/**
 * @ClassName: ClientController
 * @Description: TODO 客户管理
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
	 * @Fields rawMaterialManager : 用户
	 */
	@Resource(name = "clientManager")
	private ClientManager clientManager;
	
	/** 
	 * @Title: list 
	 * @Description: TODO 客户管理列表
	 * @param @return 
	 * @return String
	 * @throws 
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "system/client/list";
	}
	
	/** 
	 * @Title: data 
	 * @Description: TODO 返回所有的客户列表
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
			resultJson = clientManager.getResultJson(page, rows, sidx, sord, new String[]{"id", "sex", "name","phone","companyName","telephone","mark","enabled","createDate"}, new Filter[]{filter});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultJson;
	}
	
	/** 
	 * @Title: del 
	 * @Description: TODO 删除客户
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
				Client client = clientManager.findById(ids);
				client.setIsDeleted(true);
				client.setModifyDate(simpleDateFormat.format(new Date()));
				clientManager.merge(client);
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
				Client client = clientManager.findById(ids);
				client.setEnabled(enabled);
				client.setModifyDate(simpleDateFormat.format(new Date()));
				clientManager.merge(client);
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
		return "system/client/new";
	}
	
	/** 
	 * @Title: add 
	 * @Description: TODO 新增一条客户记录
	 * @param @param rawMaterial
	 * @param @return 
	 * @return OperateResult<Boolean>
	 * @throws 
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public OperateResult<String> add(Client client) {
		OperateResult<String> operateResult = new OperateResult<String>();
		try {
			client.setId(UUID.randomUUID().toString());
			client.setIsDeleted(false);
			client.setCreateDate(simpleDateFormat.format(new Date()));
			client.setModifyDate(simpleDateFormat.format(new Date()));
			clientManager.create(client);
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
