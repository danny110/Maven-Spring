package cn.live.dao.Impl;

import org.springframework.stereotype.Repository;

import cn.live.bean.Specification;
import cn.live.dao.SpecificationDao;

/**
 * @ClassName: SpecificationDaoImpl
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月21日 下午1:17:41
 *
 */
@Repository(value = "specificationDao") 
public class SpecificationDaoImpl extends BaseDaoImpl<Specification, String> implements SpecificationDao {

}
