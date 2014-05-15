/**   
 * @Title: UserServiceImpl.java
 * @Package cn.live.service.Impl
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年4月23日 下午9:28:00
 * @version V1.0
 */
package cn.live.service.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.live.base.bean.User;
import cn.live.base.dao.UserDao;
import cn.live.service.UserService;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODO （用户类）引用数据库操作的接口实现
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年4月23日 下午9:28:00
 * 
 */

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@SuppressWarnings("unused")
	private UserDao userdao;

	@Resource
	public void setUserDao(UserDao userdao) {
		this.userdao = userdao;
		super.setBaseDao(userdao);
	}

}
