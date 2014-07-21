package cn.live.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: RoleAuthController
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年7月19日 下午4:01:27
 *
 */
@Controller
@RequestMapping("/admin/sys/roleAuth")
public class RoleAuthController {
	
	@RequestMapping("/list")
	public String list() {
		return "sys/roleAuth/list";
	}
}
