package cn.live.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
