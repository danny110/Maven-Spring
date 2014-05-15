/**   
 * @Title: UserDaoImpl.java
 * @Package cn.live.base.dao.Impl
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年4月15日 下午9:19:08
 * @version V1.0
 */
package cn.live.base.dao.Impl;

import org.springframework.stereotype.Repository;

import cn.live.base.bean.User;
import cn.live.base.dao.UserDao;

/**
 * @ClassName: UserDaoImpl
 * @Description: TODO 用户类的数据库操作接口的实现
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年4月15日 下午9:19:08
 * 
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

}
