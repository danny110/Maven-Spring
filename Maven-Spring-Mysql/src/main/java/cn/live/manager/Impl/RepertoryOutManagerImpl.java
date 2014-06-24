package cn.live.manager.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.live.bean.RepertoryOut;
import cn.live.dao.BaseDao;
import cn.live.manager.RepertoryOutManager;

/**
 * @ClassName: RepertoryOutManagerImpl
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月25日 上午12:09:01
 *
 */
@Service("repertoryOutManager")
public class RepertoryOutManagerImpl extends BaseManagerImpl<RepertoryOut, String> implements RepertoryOutManager {
	
	@Resource(name = "repertoryOutDao")  
    public void setDao(BaseDao<RepertoryOut, String> dao) {  
        super.setDao(dao);  
    }  
}
