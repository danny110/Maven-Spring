package cn.live.bean.view;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.live.enums.Units;

/**
 * @ClassName: RepertoryOverView
 * @Description: TODO 库存统计
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月25日 下午10:18:05
 * 
 */
@Entity
@Table(name = "repertoryOver_view")
public class RepertoryOverView implements Serializable {

	/**
	 * @Fields serialVersionUID : 序列化 ID
	 */
	private static final long serialVersionUID = -4238608431547145280L;

	@Id
	private String rawMaterialId; // 原料ID
	private String rawMaterialName; // 原料名称
	private String specification; // 规格
	private Units units; // 单位
	private Float inNum; // 入库数量
	private Float outNum; // 出库数量
	private Float overNum; // 库存数量

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

	public Units getUnits() {
		return units;
	}

	public void setUnits(Units units) {
		this.units = units;
	}

	public Float getInNum() {
		return inNum;
	}

	public void setInNum(Float inNum) {
		this.inNum = inNum;
	}

	public Float getOutNum() {
		return outNum;
	}

	public void setOutNum(Float outNum) {
		this.outNum = outNum;
	}

	public Float getOverNum() {
		return overNum;
	}

	public void setOverNum(Float overNum) {
		this.overNum = overNum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
