package com.chengxuunion.util.string;

/**
 * 字符串工具类
 *
 * @author kutome
 * @date 2018年8月14日
 * @version V1.0
 */
public class StringUtils {
	
	/**
	 * 私有化
	 */
	private StringUtils() {
		
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.equals("") ? true : false;
	}
	
	/**
	 * 判断字符串是否为非空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	/**
	 * 判断两个字符串的值是否相同
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean isEquals(String str1, String str2) {
		if (str1 != null) {
			return str1.equals(str2);
		}
		
		if (str2 != null) {
			return str2.equals(str1);
		}
		
		return true;
	}
	
	/**
	 * 给str前后添加模糊匹配符%
	 * 
	 * @param str
	 * @return	%str%
	 */
	public static String fillLike(String str) {
		if (isEmpty(str)) {
			return "";
		}
		
		return "%" + str + "%";
	}

	/**
	 * 转换为大驼峰命名法的值（每个单词的第一个字母大写），同时_隔开的名称也按照大驼峰命名法
	 *
	 * @param name
	 * @return
	 */
	public static String convertBigCamelCase(String name) {
		if (StringUtils.isEmpty(name)) {
			return name;
		}

		String[] names = name.split("_");
		StringBuilder nameBuilder = new StringBuilder();
		for (String subName : names) {
			nameBuilder.append(upperCase(subName));
		}

		return nameBuilder.toString();
	}

	/**
	 * 转换为小驼峰命名法的值(第一个单词的第一个字母小写)，同时_隔开的名称也按照大驼峰命名法
	 *
	 * @param name
	 * @return
	 */
	public static String convertSmallCamelCase(String name) {
		if (StringUtils.isEmpty(name)) {
			return name;
		}

		String[] names = name.split("_");
		StringBuilder nameBuilder = new StringBuilder();
		int index = 0;
		for (String subName : names) {
			if (index == 0) {
				nameBuilder.append(subName);
			} else {
				nameBuilder.append(upperCase(subName));
			}
			index++;
		}

		return nameBuilder.toString();
	}

	/**
	 * 字符串首字母大写
	 *
	 * @param str
	 * @return
	 */
	public static String upperCase(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}

}
