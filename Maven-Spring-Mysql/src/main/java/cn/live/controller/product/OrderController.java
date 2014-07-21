package cn.live.controller.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: OrderController
 * @Description: TODO 成品订单
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年7月19日 下午3:27:18
 *
 */
@Controller
@RequestMapping("/admin/product/order/")
public class OrderController {

	@RequestMapping("/list")
	public String list() {
		return "product/order/list";
	}
}
