package com.chengxuunion.util.collection;

import java.util.List;
import java.util.Map;

/**
 * 集合工具类
 *
 * @author kutome
 * @date 2018年8月14日
 * @version V1.0
 */
public class CollectionUtils {
	
	/**
	 * 私有化
	 */
	private CollectionUtils() {
	}

	/**
	 * 判断List集合是否为空
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(List list) {
		return list == null || list.isEmpty();
	}
	
	/**
	 * 判断List集合是为非空
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(List list) {
		return !isEmpty(list);
	}
	
	/**
	 * 判断Map是否为空
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Map map) {
		return map == null || map.size() == 0;
	}
	
}
