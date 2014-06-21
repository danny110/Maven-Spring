package cn.live.bean.view;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: SpecificationView
 * @Description: TODO 规格视图
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月21日 下午2:11:13
 * 
 */
@Entity
@Table(name = "specification_view")
public class SpecificationView implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -3208915832124865218L;

	@Id
	private String id;
	private String rawMaterialName;
	private String specificatioName;
	private String mark;
	private String createDate;
	private Boolean enabled;
	private Boolean isDeleted;
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRawMaterialName() {
		return rawMaterialName;
	}

	public void setRawMaterialName(String rawMaterialName) {
		this.rawMaterialName = rawMaterialName;
	}

	public String getSpecificatioName() {
		return specificatioName;
	}

	public void setSpecificatioName(String specificatioName) {
		this.specificatioName = specificatioName;
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
