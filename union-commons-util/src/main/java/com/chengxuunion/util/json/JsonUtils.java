package com.chengxuunion.util.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * JSON工具类
 *
 * @author kutome
 * @date 2018年9月16日
 * @version V1.0
 */
public class JsonUtils {

	/**
	 * 解析json字符串
	 * 
	 * @param json
	 * @return
	 */
	public static JSONObject parse(String json) {
		return JSON.parseObject(json);
	}
	
	/**
	 * 将json字符串转换成实体bean
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T  parse(String json, Class<T> clazz) {
		return JSON.parseObject(json, clazz);
	}
	
}
