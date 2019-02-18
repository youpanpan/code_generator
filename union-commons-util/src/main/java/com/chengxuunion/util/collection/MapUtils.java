package com.chengxuunion.util.collection;

import java.util.Map;


/**
 * Map集合工具类
 *
 * @author kutome
 * @date 2018年8月14日
 * @version V1.0
 */
public class MapUtils {
	
	/**
	 * 私有化
	 */
	private MapUtils() {
		
	}

	/**
	 * 获取Map中指定属性的String类型的值
	 * 
	 * @param params
	 * @param property
	 * @return
	 */
	public static String getString(Map<String, Object> params, String property) {
		if (params == null) {
			return null;
		}
		
		Object value = params.get(property);
		if (value == null) {
			return null;
		}
		
		return value.toString();
	}
	
	/**
	 * 获取Map中指定属性的Integer类型的值
	 * 
	 * @param params
	 * @param property
	 * @return
	 */
	public static Integer getInt(Map<String, Object> params, String property) {
		if (params == null) {
			return null;
		}
		
		Object value = params.get(property);
		if (value == null) {
			return null;
		}
		
		return Integer.parseInt(value.toString());
	}
	
	/**
	 * 获取Map中指定属性的Long类型的值
	 * 
	 * @param params
	 * @param property
	 * @return
	 */
	public static Long getLong(Map<String, Object> params, String property) {
		if (params == null) {
			return null;
		}
		
		Object value = params.get(property);
		if (value == null) {
			return null;
		}
		
		return Long.parseLong(value.toString());
	}
	
	
	/**
	 * 为map中的属性property的值前后添加%
	 * 
	 * @param params
	 * @param property
	 */
	public static void fillLike(Map<String, Object> params, String property) {
		if (params == null || params.get(property) == null) {
			return;
		}
		
		params.put(property, "%" + params.get(property).toString() + "%");
	}
}
