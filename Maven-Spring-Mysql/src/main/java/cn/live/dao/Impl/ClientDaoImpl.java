package cn.live.dao.Impl;

import org.springframework.stereotype.Repository;

import cn.live.bean.Client;
import cn.live.dao.ClientDao;

/**
 * @ClassName: ClientDaoImpl
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月21日 下午3:19:18
 *
 */
@Repository(value = "clientDao") 
public class ClientDaoImpl extends BaseDaoImpl<Client, String> implements ClientDao {

}
