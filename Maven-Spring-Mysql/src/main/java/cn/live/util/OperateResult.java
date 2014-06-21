package cn.live.util;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @ClassName: OperateResult
 * @Description: TODO 返回值类定义
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月21日 上午8:20:40
 *
 * @param <T>
 */
public class OperateResult<T> {
    /**
     * 是否成功
	 */
	@JsonProperty("isSuccess")
	public boolean isSuccess = false;

	/**
	 * 返回的内容
	 */
	@JsonProperty("returnValue")
	public T returnValue;

	/**
	 * 失败时错误码
	 */
	@JsonProperty("errorCode")
	public int errorCode;

	/**
	 * 失败时错误原因
	 */
	@JsonProperty("errorReason")
	public String errorReason;
}
