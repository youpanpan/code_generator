package com.chengxuunion.util.reflect;

import com.chengxuunion.util.string.StringUtils;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-17 16:53
 * @Modified By:
 */
public class MethodUtils {

    /**
     * 获取指定字段的get方法名名称
     *
     * @param fieldName
     * @param fieldType
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static String getGetMethodName(String fieldName, Class fieldType) {
        if (fieldType == Boolean.class) {
            return "is" + StringUtils.convertBigCamelCase(fieldName).replace("is","");
        } else {
            return "get" + StringUtils.convertBigCamelCase(fieldName);
        }
    }

    /**
     * 获取指定字段的set方法名名称
     *
     * @param fieldName
     * @param fieldType
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static String getSetMethodName(String fieldName, Class fieldType) {
        if (fieldType == Boolean.class) {
            return "set" + StringUtils.convertBigCamelCase(fieldName).replace("is", "");
        } else {
            return "set" + StringUtils.convertBigCamelCase(fieldName);
        }
    }
}
