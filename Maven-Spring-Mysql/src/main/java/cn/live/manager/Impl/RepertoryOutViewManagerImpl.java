package cn.live.manager.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.live.bean.view.RepertoryOutView;
import cn.live.dao.BaseDao;
import cn.live.manager.RepertoryOutViewManager;

/**
 * @ClassName: RepertoryOutViewManagerImpl
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月25日 下午4:05:57
 *
 */
@Service("repertoryOutViewManager")
public class RepertoryOutViewManagerImpl extends BaseManagerImpl<RepertoryOutView, String> implements RepertoryOutViewManager {
	
	@Resource(name = "repertoryOutViewDao")  
    public void setDao(BaseDao<RepertoryOutView, String> dao) {  
        super.setDao(dao);  
    }  
}
