package cn.live.controller.repertory;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
				page = 0;
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
	
}
