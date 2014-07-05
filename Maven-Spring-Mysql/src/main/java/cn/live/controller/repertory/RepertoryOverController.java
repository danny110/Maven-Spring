package cn.live.controller.repertory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.live.bean.RawMaterial;
import cn.live.manager.RawMaterialManager;
import cn.live.manager.RepertoryInViewManager;
import cn.live.manager.RepertoryOutViewManager;
import cn.live.manager.RepertoryOverViewManager;
import cn.live.util.Filter;
import cn.live.util.Order;
import cn.live.util.ResultJson;

/**
 * @ClassName: RepertoryInCollection
 * @Description: TODO 库存统计
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月22日 上午6:46:09
 *
 */
@Controller
@RequestMapping("/admin/repertory/over/")
public class RepertoryOverController {
	
	/**
	 * @Fields repertoryOverViewManager : 库存视图
	 */
	@Resource(name = "repertoryOverViewManager")
	public RepertoryOverViewManager repertoryOverViewManager;
	
	/**
	 * @Fields repertoryInViewManager : 入库视图
	 */
	@Resource(name = "repertoryInViewManager")
	private RepertoryInViewManager repertoryInViewManager;
	
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
	 * @Description: TODO 库存管理列表
	 * @param @param rawMaterialName
	 * @param @param page
	 * @param @param size
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "/list")
	public String list(String rawMaterialName, Integer page, Integer size, Model model) {
		try {
			List<Filter> filters = new ArrayList<Filter>();
			if (StringUtils.isNotBlank(rawMaterialName)) filters.add(Filter.like("rawMaterialName", "%" + rawMaterialName + "%"));
			if (StringUtils.isNotBlank(rawMaterialName)) {
				page = 1;
			}
			List<Order> orders = new ArrayList<Order>();
			orders.add(Order.desc("overNum"));
			
			page = page == null ? PAGE : page;
			size = size == null ? SIZE : size;
			
			ResultJson resultJson = repertoryOverViewManager.getResultJson(page, size, new String[]{"rawMaterialId", "rawMaterialName", "specification","units","inNum","outNum","overNum"}, filters, orders);
			model.addAttribute("rawMaterialName", rawMaterialName);
			model.addAttribute("ResultJson", resultJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "repertory/over/list";
	}
	
	/**
	 * @Title: inView
	 * @Description: TODO 进货明细
	 * @param @param id
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "/inView-{rawMaterialId}")
	public String inView(@PathVariable String rawMaterialId, String companyName,String loginCode, String beginTime, String endTime, Integer page, Integer size, Model model) {
		try {
			/*
			 * 原料数据
			 * */
			RawMaterial rawMaterial = rawMaterialManager.findById(rawMaterialId);
			/*
			 * 合计数据
			 * */
			Map<String, Float> sum = repertoryInViewManager.getSumBySQL(rawMaterialId, companyName, loginCode, beginTime, endTime);
			/*
			 * 分页数据
			 * */
			List<Filter> filters = new ArrayList<Filter>();
			filters.add(Filter.eq("rawMaterialId", rawMaterialId));
			if (StringUtils.isNotBlank(companyName)) filters.add(Filter.like("companyName", "%" + companyName + "%"));
			if (StringUtils.isNotBlank(loginCode)) filters.add(Filter.like("loginCode", "%" + loginCode + "%"));
			if (StringUtils.isNotBlank(beginTime)) filters.add(Filter.ge("inDate", beginTime));
			if (StringUtils.isNotBlank(endTime)) filters.add(Filter.le("inDate", endTime));
			if (StringUtils.isNotBlank(companyName) || StringUtils.isNotBlank(loginCode) || StringUtils.isNotBlank(beginTime) || StringUtils.isNotBlank(endTime)) {
				page = 1;
			}
			List<Order> orders = new ArrayList<Order>();
			orders.add(Order.desc("inDate"));
			
			page = page == null ? PAGE : page;
			size = size == null ? SIZE : size;
			
			ResultJson resultJson = repertoryInViewManager.getResultJson(page, size, new String[]{"id","companyName","num","unitPrice","sum","mark","loginCode","inDate"}, filters, orders);
			model.addAttribute("rawMaterialId", rawMaterialId);
			model.addAttribute("rawMaterial", rawMaterial); // 原料对象
			model.addAttribute("sum", sum); // 合计
			model.addAttribute("companyName", companyName); // 单位名称
			model.addAttribute("loginCode", loginCode); // 经手人
			model.addAttribute("beginTime", beginTime); // 开始日期
			model.addAttribute("endTime", endTime); // 结束日期
			model.addAttribute("ResultJson", resultJson); // 分页数据
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "repertory/over/inView";
	}
	
	/**
	 * @Title: outView
	 * @Description: TODO 出库明细
	 * @param @param id
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "/outView-{rawMaterialId}")
	public String outView(@PathVariable String rawMaterialId, String loginCode, String beginTime, String endTime, Integer page, Integer size, Model model) {
		try {
			/*
			 * 原料数据
			 * */
			RawMaterial rawMaterial = rawMaterialManager.findById(rawMaterialId);
			/*
			 * 合计数据
			 * */
			Map<String, Float> sum = repertoryOutViewManager.getSumBySQL(rawMaterialId, loginCode, beginTime, endTime);
			/*
			 * 分页数据
			 * */
			List<Filter> filters = new ArrayList<Filter>();
			filters.add(Filter.eq("rawMaterialId", rawMaterialId));
			if (StringUtils.isNotBlank(loginCode)) filters.add(Filter.like("loginCode", "%" + loginCode + "%"));
			if (StringUtils.isNotBlank(beginTime)) filters.add(Filter.ge("outDate", beginTime));
			if (StringUtils.isNotBlank(endTime)) filters.add(Filter.le("outDate", endTime));
			if (StringUtils.isNotBlank(loginCode) || StringUtils.isNotBlank(beginTime) || StringUtils.isNotBlank(endTime)) {
				page = 1;
			}
			List<Order> orders = new ArrayList<Order>();
			orders.add(Order.desc("outDate"));
			
			page = page == null ? PAGE : page;
			size = size == null ? SIZE : size;
			
			ResultJson resultJson = repertoryOutViewManager.getResultJson(page, size, new String[]{"id", "num", "mark","loginCode","outDate"}, filters, orders);
			model.addAttribute("rawMaterialId", rawMaterialId);
			model.addAttribute("rawMaterial", rawMaterial); // 原料对象
			model.addAttribute("sum", sum); // 合计
			model.addAttribute("loginCode", loginCode); // 经手人
			model.addAttribute("beginTime", beginTime); // 开始日期
			model.addAttribute("endTime", endTime); // 结束日期
			model.addAttribute("ResultJson", resultJson); // 分页数据
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "repertory/over/outView";
	}
}
