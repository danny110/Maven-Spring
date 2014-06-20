package cn.live;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.live.bean.User;
import cn.live.manager.UserManager;
import cn.live.util.BaseUtils;

/**
 * @ClassName: UserTest
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月17日 下午9:11:32
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class UserTest {
	
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Resource(name = "userManager")
	private UserManager userManager;
	
	/** 
	 * @Title: test1 
	 * @Description: TODO 保存测试
	 * @param  
	 * @return void
	 * @throws 
	 */
	@Test
	public void test1(){
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setLoginCode("admin");
		user.setPassword(BaseUtils.getMD5("123456".getBytes()));
		user.setCreateDate(simpleDateFormat.format(new Date()));
		user.setEnabled(true);
		user.setIsDeleted(false);
		user.setModifyDate(simpleDateFormat.format(new Date()));
		
		userManager.create(user);
	}
	
	/** 
	 * @Title: test2 
	 * @Description: TODO 主键查询测试
	 * @param  
	 * @return void
	 * @throws 
	 */
	@Test
	public void test2() {
		User user = userManager.findById("bb821af0-1272-4bab-bb8e-2db46e766bc9");
		System.out.println(user.toString());
	}
	
	/** 
	 * @Title: test3 
	 * @Description: TODO 更新测试
	 * @param  
	 * @return void
	 * @throws 
	 */
	@Test
	public void test3() {
		User user = userManager.findById("bb821af0-1272-4bab-bb8e-2db46e766bc9");
		user.setIsDeleted(true);
		userManager.update(user);
		
		user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setLoginCode("admin");
		user.setPassword(BaseUtils.getMD5("123456".getBytes()));
		user.setCreateDate(simpleDateFormat.format(new Date()));
		user.setEnabled(true);
		user.setIsDeleted(false);
		user.setModifyDate(simpleDateFormat.format(new Date()));
		userManager.update(user);
	}
	
	public static void main(String[] args) {
		System.out.println(simpleDateFormat.format(new Date()));
	}

}
