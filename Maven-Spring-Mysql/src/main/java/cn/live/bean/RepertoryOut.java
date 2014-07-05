package cn.live.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: RepertoryOut
 * @Description: TODO 出库信息
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月22日 上午6:59:53
 * 
 */
@Entity
@Table(name = "repertoryOut")
public class RepertoryOut implements Serializable {

	/**
	 * @Fields serialVersionUID : 序列化 ID
	 */
	private static final long serialVersionUID = -4172625748945627320L;

	@Id
	private String id;
	private String rawMaterialId; // 原料 ID
	private Float num; // 数量
	private String outDate; // 出库日期
	private String mark; // 备注
	private String userId; // 创建用户
	private String createDate; // 创建时间
	private Boolean isDeleted; // 是否删除，默认为false
	private String modifyDate; // 最近一次修改时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRawMaterialId() {
		return rawMaterialId;
	}

	public void setRawMaterialId(String rawMaterialId) {
		this.rawMaterialId = rawMaterialId;
	}

	public Float getNum() {
		return num;
	}

	public void setNum(Float num) {
		this.num = num;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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
