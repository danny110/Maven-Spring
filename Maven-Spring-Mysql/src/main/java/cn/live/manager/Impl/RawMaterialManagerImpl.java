package cn.live.manager.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.live.bean.RawMaterial;
import cn.live.dao.BaseDao;
import cn.live.manager.RawMaterialManager;

/**
 * @ClassName: RawMaterialManagerImpl
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月18日 下午11:24:20
 *
 */
@Service("rawMaterialManager")
@Transactional
public class RawMaterialManagerImpl extends BaseManagerImpl<RawMaterial, String> implements RawMaterialManager {
	
	@Resource(name = "rawMaterialDao")  
    public void setDao(BaseDao<RawMaterial, String> dao) {  
        super.setDao(dao);  
    }  
}
