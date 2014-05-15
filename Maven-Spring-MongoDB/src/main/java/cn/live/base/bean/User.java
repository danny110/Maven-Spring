package cn.live.base.bean;

import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @ClassName: User
 * @Description: TODO 用户类
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年4月14日 下午10:26:22
 * 
 */
@Document(collection = "user")
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String name;
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}


}
