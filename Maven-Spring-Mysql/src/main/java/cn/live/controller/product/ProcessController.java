package cn.live.controller.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: ProcessController
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年7月19日 下午3:29:40
 *
 */
@Controller
@RequestMapping("/admin/product/process/")
public class ProcessController {

	@RequestMapping("/list")
	public String list() {
		return "product/process/list";
	}
}
