package cn.live.manager.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.live.bean.Specification;
import cn.live.dao.BaseDao;
import cn.live.manager.SpecificationManager;

/**
 * @ClassName: SpecificationManagerImpl
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月21日 下午1:20:08
 *
 */
@Service("specificationManager")
public class SpecificationManagerImpl extends BaseManagerImpl<Specification, String> implements SpecificationManager {
	
	@Resource(name = "specificationDao")  
    public void setDao(BaseDao<Specification, String> dao) {  
        super.setDao(dao);  
    }  
}
