package cn.live.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName: SpecificationController
 * @Description: TODO 规格管理
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月18日 下午10:57:38
 *
 */

@Controller
@RequestMapping("/admin/specification")
public class SpecificationController {
	/** 
	 * @Title: list 
	 * @Description: TODO 规格管理列表
	 * @param @return 
	 * @return String
	 * @throws 
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "system/specification/list";
	}
}
