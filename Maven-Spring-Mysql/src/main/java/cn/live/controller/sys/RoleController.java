package cn.live.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: RoleController
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年7月19日 下午4:00:01
 *
 */
@Controller
@RequestMapping("/admin/sys/role")
public class RoleController {
	
	@RequestMapping("/list")
	public String list() {
		return "sys/role/list";
	}

}
