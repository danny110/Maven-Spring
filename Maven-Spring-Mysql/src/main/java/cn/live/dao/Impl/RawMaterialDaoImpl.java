package cn.live.dao.Impl;

import org.springframework.stereotype.Repository;

import cn.live.bean.RawMaterial;
import cn.live.dao.RawMaterialDao;

/**
 * @ClassName: RawMaterialDaoImpl
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月18日 下午11:20:38
 *
 */
@Repository(value = "rawMaterialDao") 
public class RawMaterialDaoImpl extends BaseDaoImpl<RawMaterial, String> implements RawMaterialDao {

}
