package cn.live.manager.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.live.bean.view.SpecificationView;
import cn.live.dao.BaseDao;
import cn.live.manager.SpecificationViewManager;

/**
 * @ClassName: SpecificationViewManagerImpl
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月21日 下午2:19:13
 *
 */
@Service("specificationViewManager")
public class SpecificationViewManagerImpl extends BaseManagerImpl<SpecificationView, String> implements SpecificationViewManager {

	@Resource(name = "specificationViewDao")  
    public void setDao(BaseDao<SpecificationView, String> dao) {  
        super.setDao(dao);  
    }  
}
