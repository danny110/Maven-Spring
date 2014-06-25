package cn.live.manager.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.live.bean.view.RepertoryOverView;
import cn.live.dao.BaseDao;
import cn.live.manager.RepertoryOverViewManager;

/**
 * @ClassName: RepertoryOverViewManagerImpl
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月25日 下午10:29:56
 *
 */
@Service("repertoryOverViewManager")
public class RepertoryOverViewManagerImpl extends BaseManagerImpl<RepertoryOverView, String> implements RepertoryOverViewManager {

	@Resource(name = "repertoryOverViewDao")  
    public void setDao(BaseDao<RepertoryOverView, String> dao) {  
        super.setDao(dao);  
    }  
}
