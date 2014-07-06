package cn.live.controller.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.live.manager.RawMaterialManager;
import cn.live.manager.RepertoryOutViewManager;
import cn.live.util.Filter;
import cn.live.util.Order;
import cn.live.util.ResultJson;

/**
 * @ClassName: ReportController
 * @Description: TODO 进货报表
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年7月5日 下午11:07:27
 *
 */
@Controller
@RequestMapping("/admin/report/out/")
public class OutReportController {
	
	/**
	 * @Fields repertoryOutViewManager : 出库视图
	 */
	@Resource(name = "repertoryOutViewManager")
	public RepertoryOutViewManager repertoryOutViewManager;
	
	/**
	 * @Fields rawMaterialManager : 原料
	 */
	@Resource(name = "rawMaterialManager")
	private RawMaterialManager rawMaterialManager;
	
	/**
	 * @Fields PAGE : 初始化当前页码
	 */
	private static Integer PAGE = 1;
	
	/**
	 * @Fields SIZE : 初始化每页行数
	 */
	private static Integer SIZE = 10;

	/**
	 * @Title: list
	 * @Description: TODO 出货报表
	 * @param @param enabled
	 * @param @param rawMaterialName
	 * @param @param specification
	 * @param @param loginCode
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
	public String list(Boolean enabled, String rawMaterialName, String specification, String loginCode, String beginTime, String endTime, Integer page, Integer size, Model model) {
		try {
			/*
			 * 合计数据
			 * */
			Map<String, Float> sum = repertoryOutViewManager.getSumBySQL(rawMaterialName, specification, loginCode, beginTime, endTime);
			/*
			 * 分页数据
			 * */
			List<Filter> filters = new ArrayList<Filter>();
			if (enabled == null || enabled) {
				if (StringUtils.isNotBlank(rawMaterialName)) filters.add(Filter.like("rawMaterialName", "%" + rawMaterialName + "%"));
				if (StringUtils.isNotBlank(specification)) filters.add(Filter.like("specification", "%" + specification + "%"));
				if (StringUtils.isNotBlank(loginCode)) filters.add(Filter.like("loginCode", "%" + loginCode + "%"));
			} else {
				if (StringUtils.isNotBlank(rawMaterialName)) filters.add(Filter.eq("rawMaterialName", rawMaterialName));
				if (StringUtils.isNotBlank(specification)) filters.add(Filter.eq("specification", specification));
				if (StringUtils.isNotBlank(loginCode)) filters.add(Filter.eq("loginCode", loginCode));
			}
			if (StringUtils.isNotBlank(beginTime)) filters.add(Filter.ge("outDate", beginTime));
			if (StringUtils.isNotBlank(endTime)) filters.add(Filter.le("outDate", endTime));
			if (StringUtils.isNotBlank(loginCode) || StringUtils.isNotBlank(beginTime) || StringUtils.isNotBlank(endTime)) {
//				page = 1;
			}
			List<Order> orders = new ArrayList<Order>();
			orders.add(Order.desc("outDate"));
			
			page = page == null ? PAGE : page;
			size = size == null ? SIZE : size;
			
			ResultJson resultJson = repertoryOutViewManager.getResultJson(page, size, new String[]{"id", "rawMaterialName", "specification","num","units","mark","loginCode","outDate"}, filters, orders);
			model.addAttribute("sum", sum); // 合计
			model.addAttribute("enabled", enabled);
			model.addAttribute("rawMaterialName", rawMaterialName); // 原料名称
			model.addAttribute("specification", specification); // 原料规格
			model.addAttribute("loginCode", loginCode); // 经手人
			model.addAttribute("beginTime", beginTime); // 开始日期
			model.addAttribute("endTime", endTime); // 结束日期
			model.addAttribute("ResultJson", resultJson); // 分页数据
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "report/outList";
	}
	
}
