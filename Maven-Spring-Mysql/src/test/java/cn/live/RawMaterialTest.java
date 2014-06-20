package cn.live;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.live.bean.RawMaterial;
import cn.live.manager.RawMaterialManager;

/**
 * @ClassName: RawMaterialTest
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月18日 下午11:18:11
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class RawMaterialTest {

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Resource(name = "rawMaterialManager")
	private RawMaterialManager rawMaterialManager;
	
	@Test
	public void test1() {
		RawMaterial rawMaterial = new RawMaterial();
		rawMaterial.setId(UUID.randomUUID().toString());
		rawMaterial.setName("线材");
		rawMaterial.setMark("暂无备注");
		rawMaterial.setCreateDate(simpleDateFormat.format(new Date()));
		rawMaterial.setEnabled(true);
		rawMaterial.setIsDeleted(false);
		rawMaterial.setModifyDate(simpleDateFormat.format(new Date()));
		
		rawMaterialManager.create(rawMaterial);
	}
	
	@Test
	public void test2() {
//		ResultJson<RawMaterial> resultJson = new ResultJson<RawMaterial>();
//		resultJson = rawMaterialManager.getResultJson(1, 10, "id", "asc");
//		System.out.println(resultJson);
	}
}
