package cn.live.test;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.live.base.bean.User;
import cn.live.service.UserService;

/**
 * @ClassName: UserTest
 * @Description: TODO 用户测试
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年4月14日 下午10:39:28
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserTest {

	@Resource
	private UserService userService;

	@Test
	public void test1() {
		try {
			for (int i = 0; i < 10; i++) {
				User user = new User();
				user.setName("Hello World,And do it!");
				user.setAge(0);
				user.setCreateDate(new Date());

				userService.save(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test2() {
		User user = userService.findOne(new Query(), User.class);
		System.out.println(user);
		System.out.println(user.getName());
	}

	@Test
	public void test3() {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("id").is(0L));
			userService.remove(query, User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test4() {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("id").is(2L));
			userService.remove(query, "name");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
