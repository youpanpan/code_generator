package com.chengxuunion.util.id;

import java.util.Random;

/**
 * 验证码工具
 *
 * @author yp2
 * @date 2018年11月20日
 * @version V1.0
 */
public class CaptchaUtils {

	private CaptchaUtils() {
		
	}
	
	/**
	 * 生成短信验证码
	 * 
	 * @return
	 */
	public static String getSmsCaptcha() {
		Random random = new Random();
		int captcha = random.nextInt(899999) + 100000;
		
		return String.valueOf(captcha);
	}
	
}
