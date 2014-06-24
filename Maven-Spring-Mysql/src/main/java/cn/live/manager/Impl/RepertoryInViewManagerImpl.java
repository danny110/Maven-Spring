package cn.live.manager.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.live.bean.view.RepertoryInView;
import cn.live.dao.BaseDao;
import cn.live.manager.RepertoryInViewManager;

/**
 * @ClassName: RepertoryInViewManagerImpl
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月24日 下午11:37:17
 *
 */
@Service("repertoryInViewManager")
public class RepertoryInViewManagerImpl extends BaseManagerImpl<RepertoryInView, String> implements RepertoryInViewManager {

	@Resource(name = "repertoryInViewDao")  
    public void setDao(BaseDao<RepertoryInView, String> dao) {  
        super.setDao(dao);  
    }  
}
