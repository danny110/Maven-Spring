package cn.live.controller.information;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: ProductController
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年7月19日 下午3:50:06
 *
 */
@Controller
@RequestMapping("/admin/information/product")
public class ProductInfoController {

	@RequestMapping("/list")
	public String list() {
		return "information/product/list";
	}
}
