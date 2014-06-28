package cn.live.enums;

/**
 * @ClassName: Units
 * @Description: TODO
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月21日 下午4:24:49
 *
 */
public enum Units {
	
	KG("Kg"),TAO("套"),ZHI("只"),GE("个"),JIAN("件"),UNKNOWN("缺省");
	
	/**
	 * @Fields code : 字符串
	 */
	private String code;
	
	/**
	 * <p>Title: </p>
	 * <p>Description: </p>
	 * @param code
	 */
	private Units(String code) {
		this.code = code;
	}
	
	/* (non-Javadoc)
	 * <p>Title: toString</p>
	 * <p>Description: </p>
	 * @return
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.code;
	}
}
