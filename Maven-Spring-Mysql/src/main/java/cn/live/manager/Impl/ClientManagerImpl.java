package cn.live.manager.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.live.bean.Client;
import cn.live.dao.BaseDao;
import cn.live.manager.ClientManager;

/**
 * @ClassName: ClientManagerImpl
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月21日 下午3:21:08
 *
 */
@Service("clientManager")
public class ClientManagerImpl extends BaseManagerImpl<Client, String> implements ClientManager {
	
	@Resource(name = "clientDao")  
    public void setDao(BaseDao<Client, String> dao) {  
        super.setDao(dao);  
    }  
}
