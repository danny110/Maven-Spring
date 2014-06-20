package cn.live.dao.Impl;

import org.springframework.stereotype.Repository;

import cn.live.bean.User;
import cn.live.dao.UserDao;

/**
 * @ClassName: UserDaoImpl
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月17日 下午9:27:36
 *
 */
@Repository(value = "userDao")  
public class UserDaoImpl extends BaseDaoImpl<User, String> implements UserDao {

}
