package cn.live.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.live.enums.Gender;


/**
 * @ClassName: Client
 * @Description: TODO 来往单位（客户）表
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月21日 下午3:12:39
 * 
 */
@Entity
@Table(name = "client")
public class Client implements Serializable {

	/**
	 * @Fields serialVersionUID : 序列化 ID
	 */
	private static final long serialVersionUID = -151692618450246497L;

	@Id
	private String id;
	private String companyName; // 单位名称
	private String name; // 联系人
	private Gender sex; // 性别
	private String phone; // 手机号码
	private String telephone; // 座机号码
	private String mark; // 备注
	private String createDate; // 创建时间
	private Boolean enabled; // 标记，启用/禁用
	private Boolean isDeleted; // 是否删除，默认为false
	private String modifyDate; // 最近一次修改时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Enumerated(EnumType.STRING)
	public Gender getSex() {
		return sex;
	}

	public void setSex(Gender sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
