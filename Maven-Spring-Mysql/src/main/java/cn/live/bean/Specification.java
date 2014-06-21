package cn.live.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: Specification
 * @Description: TODO 规格
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月21日 上午11:16:59
 * 
 */
@Entity
@Table(name = "specification")
public class Specification implements Serializable {

	/**
	 * @Fields serialVersionUID : 序列化 ID
	 */
	private static final long serialVersionUID = -6752997647875582030L;

	@Id
	private String id;
	private String rawMateriald; // 原料 ID
	private String name; // 名称
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

	public String getRawMateriald() {
		return rawMateriald;
	}

	public void setRawMateriald(String rawMateriald) {
		this.rawMateriald = rawMateriald;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
