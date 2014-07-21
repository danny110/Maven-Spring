package cn.live.controller.repertory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: ProductController
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年7月19日 下午3:32:00
 *
 */
@Controller
@RequestMapping("/admin/repertory/product/")
public class ProductController {
	
	@RequestMapping("/list")
	public String list() {
		return "repertory/product/list";
	}
}
