package com.chengxuunion.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.chengxuunion.util.string.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期工具类
 *
 * @author kutome
 * @date 2018年8月28日
 * @version V1.0
 */
public class DateUtils {

	private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

	/**
	 * 日期格式yyyy-MM-dd
	 */
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	
	/**
	 * 日期格式yyyy-MM-dd HH:mm:ss
	 */
	public static final String YYYY_MM_DD_HH_mm_SS = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 私有化
	 */
	private DateUtils() {
		
	}
	
	/**
	 * 解析日期字符串为日期对象
	 * 
	 * @param dateStr	日期字符串
	 * @param formatStr	日期格式字符串，例yyyy-MM-dd
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String dateStr, String formatStr) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
		try {
			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			logger.error("日期格式【"+ dateStr +"】解析异常", e);
		}

		return null;
	}
	
	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @param formatStr
	 * @return
	 * @throws ParseException
	 */
	public static String format(Date date, String formatStr) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
		try {
			return dateFormat.format(date);
		} catch (Exception e) {
			logger.error("日期格式化异常", e);
		}

		return "";
	}
	
	/**
	 * 获取指定日期之前dayNum天的日期<br/>
	 * 如果dayNum为整数，则为之前的日期，如果为负数，则为之后的日期
	 * 
	 * @param date	日期
	 * @param dayNum	之前多少天
	 * @return
	 */
	public static Date beforeDay(Date date, int dayNum) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -dayNum);
		
		return calendar.getTime();
	}
	
	/**
	 * 获取当前日期指定格式的字符串
	 * 
	 * @return
	 */
	public static String getCurDate(String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(new Date());
	}
	
	/**
	 * 计算两个日期相差的秒数(date1-date2)
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDiffSeconds(Date date1, Date date2) {
		return (int) ((date1.getTime() - date2.getTime()) / 1000);
	}
	
	
	/**
	 * 获取今日的最开始的时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		clear(calendar);
		
		return calendar.getTime();
	}
	
	/**
	 * 获取本周第一天时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		clear(calendar);
		
		return calendar.getTime();
	}
	
	/**
	 * 获取本月第一天时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		
		clear(calendar);
		return calendar.getTime();
	}
	
	/**
	 * 获取本季度的第一天时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstOfQuarter(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH) + 1;
		int firstMonth = ((month - 1) / 3) * 3;
		calendar.set(Calendar.MONTH, firstMonth);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		
		clear(calendar);
		return calendar.getTime();
	}
	
	/**
	 * 获取本年的第一天时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		
		clear(calendar);
		return calendar.getTime();
	}
	
	/**
	 * 清空日期的时、分、秒、毫秒
	 * */
	private static void clear(Calendar calendar) {
		calendar.clear(Calendar.HOUR);
		calendar.clear(Calendar.HOUR_OF_DAY);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);
	}
	
	/**
	 * 解析日期字符串字段
	 * 
	 * @param params	map集合对象
	 * @param dateFields	日期字符串字段数组
	 * @param formats		日期字符串格式数组
	 * @throws Exception
	 */
	public static void parseDateField(Map<String, Object> params, String[] dateFields, String[] formats) throws Exception {
		if (params == null || dateFields == null || formats == null || dateFields.length != formats.length) {
			return;
		}
		
		for (int i = 0; i < dateFields.length; i++) {
			Object obj = params.get(dateFields[i]);
			if (obj != null && StringUtils.isNotEmpty(obj.toString())) {
				try {
					params.put(dateFields[i], DateUtils.parse(obj.toString(), formats[i]));
				} catch (ParseException e) {
					throw new Exception("解析日期字符串【"+ obj +"，"+ formats[i] +"】出现异常", e);
				}
			} else {
				params.put(dateFields[i], null);
			}
		}
	}
	
	public static void main(String[] args) throws ParseException {
		Date curDate = DateUtils.parse("2018-09-09", YYYY_MM_DD);
				//new Date();
		System.out.println(DateUtils.format(getFirstOfYear(curDate), "yyyy-MM-dd HH:mm:ss"));
	}
}
