package cn.live.base.bean;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @ClassName: BaseEntity
 * @Description: TODO 所有 Bean 对象的基类
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年4月14日 下午10:12:24
 * 
 */
@Document
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private ObjectId id = new ObjectId();
	private Date createDate;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
