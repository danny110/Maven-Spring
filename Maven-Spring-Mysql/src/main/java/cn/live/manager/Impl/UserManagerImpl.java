package cn.live.manager.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.live.bean.User;
import cn.live.dao.BaseDao;
import cn.live.manager.UserManager;

/**
 * @ClassName: UserManagerImpl
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月17日 下午9:37:44
 *
 */
@Service("userManager")
@Transactional
public class UserManagerImpl extends BaseManagerImpl<User, String> implements UserManager{
	
	@Resource(name = "userDao")  
    public void setDao(BaseDao<User, String> dao) {  
        super.setDao(dao);  
    }  
}
