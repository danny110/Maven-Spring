package cn.live.bean.view;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.live.enums.Units;

/**
 * @ClassName: RepertoryOutView
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月24日 下午11:30:46
 * 
 */
@Entity
@Table(name = "repertoryOut_view")
public class RepertoryOutView implements Serializable {

	/**
	 * @Fields serialVersionUID : 序列化 ID
	 */
	private static final long serialVersionUID = 1601406468933615299L;

	@Id
	private String id; // 入库表ID
	private String rawMaterialId; // 原料编号
	private String rawMaterialName; // 原料名称
	private String specification; // 规格
	private Float num; // 数量
	private Units units; // 单位
	private String mark; // 备注
	private String loginCode; // 创建帐号
	private String outDate; // 创建时间

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

	public String getRawMaterialName() {
		return rawMaterialName;
	}

	public void setRawMaterialName(String rawMaterialName) {
		this.rawMaterialName = rawMaterialName;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Float getNum() {
		return num;
	}

	public void setNum(Float num) {
		this.num = num;
	}

	public Units getUnits() {
		return units;
	}

	public void setUnits(Units units) {
		this.units = units;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
