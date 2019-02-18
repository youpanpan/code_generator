package com.chengxuunion.util.id;

import java.util.UUID;

/**
 * UUID工具类
 *
 * @author kutome
 * @date 2018年8月30日
 * @version V1.0
 */
public class UUIDUtils {

	private UUIDUtils() {
		
	}
	
	/**
	 * 获取UUID
	 * 
	 * @return
	 */
	public static String getId() {
		return UUID.randomUUID().toString();
	}
	
}
