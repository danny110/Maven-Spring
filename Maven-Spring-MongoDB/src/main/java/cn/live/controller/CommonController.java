package cn.live.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: CommonController
 * @Description: TODO 公共的控制层
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年5月7日 下午10:50:02
 *
 */
@Controller
@RequestMapping(value = "/admin")
public class CommonController {
	
	@RequestMapping(value = "/login")
	public Boolean longin() {
		return true;
	}

}
