package cn.live.controller.report;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: ProductPorcessController
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年7月19日 下午3:42:04
 *
 */
@Controller
@RequestMapping("/admin/report/productProcess/")
public class ProductPorcessController {
	
	@RequestMapping("/list")
	public String list() {
		return "report/productProcess";
	}

}
