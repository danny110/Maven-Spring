package cn.live.bean.view;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.live.enums.Units;

/**
 * @ClassName: RepertoryInView
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月24日 下午11:30:46
 * 
 */
@Entity
@Table(name = "repertoryIn_view")
public class RepertoryInView implements Serializable {

	/**
	 * @Fields serialVersionUID : 序列化 ID
	 */
	private static final long serialVersionUID = 1601406468933615299L;

	@Id
	private String id; // 入库表ID
	private String rawMaterialName; // 原料名称
	private String specification; // 规格
	private Units units; // 单位
	private String clientName; // 客户名称
	private Float num; // 数量
	private Float unitPrice; // 单价
	private Float sum; // 合计
	private String mark; // 备注
	private String loginCode; // 创建帐号
	private String createDate; // 创建时间

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

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Units getUnits() {
		return units;
	}

	public void setUnits(Units units) {
		this.units = units;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Float getNum() {
		return num;
	}

	public void setNum(Float num) {
		this.num = num;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Float getSum() {
		return sum;
	}

	public void setSum(Float sum) {
		this.sum = sum;
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
