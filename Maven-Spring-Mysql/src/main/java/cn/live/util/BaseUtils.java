package cn.live.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

import enums.Gender;
import enums.Units;

/**
 * @ClassName: BaseUtils
 * @Description: 基础工具类
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月17日 下午10:36:03
 *
 */
public final class BaseUtils {
	
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
	
	 /** 
	 * @Title: list2ResultJson 
	 * @Description: TODO list 转换成 ResultJson
	 * @param @param list
	 * @param @param propertyNames
	 * @param @return 
	 * @return List<Map<String,Object>>
	 * @throws 
	 */
	public static List<Map<String, Object>> list2ResultJson(List<?> list, String... propertyNames) {
		 List<Map<String, Object>> rows = new ArrayList<Map<String,Object>>();
	        for (Object o : list) {
	        	Map<String, Object> row = new HashMap<String, Object>();
	            for (String propertyName : propertyNames) {
	                Object val = getPropertyValueByCascadeName(o, propertyName);
	                row.put(propertyName, val instanceof Gender ? val.toString() : val instanceof Units ? val.toString() : val);
	            }
	            rows.add(row);
	        }
	        return rows;
	    }
	 
	public static Object getPropertyValueByCascadeName(Object o, String cascadeName) {
		Assert.notNull(o);
		Assert.notNull(cascadeName);
		Object propertyValue = o;
		for (String simplePropertyName : cascadeName.split("\\.")) {
			propertyValue = getPropertyValueBySimpleName(propertyValue, simplePropertyName);
		}
		return propertyValue;
	}
	 
	public static Object getPropertyValueBySimpleName(Object o, String propertyName) {
		try {
			if (o == null) {
				return null;
			}
			PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(o.getClass()).getPropertyDescriptors();
			if (propertyDescriptors == null) {
				return null;
			}
			for (PropertyDescriptor descriptor : propertyDescriptors) {
				if (!propertyName.equals(descriptor.getName()) || descriptor.getReadMethod() == null) {
					continue;
				}
				Method method = descriptor.getReadMethod();
				return method.invoke(o);
			}

		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
