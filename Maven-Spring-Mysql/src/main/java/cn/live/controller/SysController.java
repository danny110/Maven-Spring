package cn.live.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName: SysController
 * @Description: TODO 系统管理
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月18日 下午5:34:27
 *
 */
@Controller
@RequestMapping("/admin")
public class SysController {

	/** 
	 * @Title: index 
	 * @Description: TODO 跳转到首页
	 * @param @return 
	 * @return String
	 * @throws 
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/index";
	}
}
