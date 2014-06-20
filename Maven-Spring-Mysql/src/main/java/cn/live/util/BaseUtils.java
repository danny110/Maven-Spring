package cn.live.util;

import java.security.MessageDigest;

/**
 * @ClassName: BaseUtils
 * @Description: 基础工具类
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月17日 下午10:36:03
 *
 */
public class BaseUtils {
	
	/** 
	 * @Title: getMD5 
	 * @Description: TODO MD5 加密
	 * @param @param source
	 * @param @return 
	 * @return String
	 * @throws 
	 */
	public static String getMD5(byte[] source) {
		String s = null;
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest();
			char str[] = new char[16 * 2];
			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			s = new String(str);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
}
