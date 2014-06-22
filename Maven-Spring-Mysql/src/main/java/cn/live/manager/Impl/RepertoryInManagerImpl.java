package cn.live.manager.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.live.bean.RepertoryIn;
import cn.live.dao.BaseDao;
import cn.live.manager.RepertoryInManager;

/**
 * @ClassName: RepertoryInManagerImpl
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月22日 上午7:09:29
 *
 */
@Service("repertoryInManager")
public class RepertoryInManagerImpl extends BaseManagerImpl<RepertoryIn, String> implements RepertoryInManager {

	@Resource(name = "repertoryInDao")  
    public void setDao(BaseDao<RepertoryIn, String> dao) {  
        super.setDao(dao);  
    }  
}
