package cn.live.util;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: ResultJson
 * @Description: TODO 返回对象
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月19日 上午8:21:50
 * 
 */
public class ResultJson<T> implements Serializable {

	/**
	 * @Fields serialVersionUID : 序列化 ID
	 */
	private static final long serialVersionUID = -1565420438894013217L;
	private int page; // 当前页码
	private long records; // 总数据
	private long total; // 总页码
	private List<T> rows; // 数据集
	
	/** 
	 * <p>Title: </p> 
	 * <p>Description: 默认构造器</p>  
	 */
	public ResultJson(){};
	
	/** 
	 * <p>Title: </p> 
	 * <p>Description: 自定义构造器</p> 
	 * @param page
	 * @param records
	 * @param totlal
	 * @param rows 
	 */
	public ResultJson(int page, long records, long totlal, List<T> rows){
		this.page = page;
		this.records = records;
		this.total = totlal;
		this.rows = rows;
	};
	

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public long getRecords() {
		return records;
	}

	public void setRecords(long records) {
		this.records = records;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
