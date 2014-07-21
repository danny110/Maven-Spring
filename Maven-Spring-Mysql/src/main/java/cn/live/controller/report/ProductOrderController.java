package cn.live.controller.report;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: ProductOrderController
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年7月19日 下午3:40:35
 *
 */
@Controller
@RequestMapping("/admin/report/productOrder/")
public class ProductOrderController {
	
	@RequestMapping("/list")
	public String list() {
		return "report/productOrder";
	}

}
