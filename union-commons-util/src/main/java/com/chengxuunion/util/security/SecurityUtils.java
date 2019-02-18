package com.chengxuunion.util.security;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 安全工具类（主要是加解密）
 *
 * @author kutome
 * @date 2018年8月29日
 * @version V1.0
 */
public class SecurityUtils {
	
	private final static String MD5 = "MD5";
	
	private final static String SHA = "SHA";
	
	private final static String DES = "DES";
	
	/**
	 * DES密钥，长度是8的倍数
	 */
	private final static String DES_PASSWORD = "12345678";
	
	/**
	 * BASE64加密
	 * 
	 * @param plainText	明文
	 * @return	密文
	 */
	public static String encryptBase64(String plainText){
        BASE64Encoder encoder=new BASE64Encoder();
        
        String cipherText = encoder.encode(plainText.getBytes());
        
        return cipherText;
    }
	
	/**
	 * BASE64解密
	 * 
	 * @param cipherText	密文
	 * @return	明文
	 * @throws IOException 
	 */
	public static String decryptBase64(String cipherText) throws IOException {
		BASE64Decoder decoder=new BASE64Decoder();
		
        String plainText=new String(decoder.decodeBuffer(cipherText));
        
        return plainText;
	}
	
	/**
	 * MD5加密
	 * 
	 * @param plainText	明文
	 * @return	密文
	 */
	public static String encryptMD5(String plainText) {
		return encrypt(plainText, MD5);
	}
	
	/**
	 * SHA加密
	 * 
	 * @param plainText	明文
	 * @return	密文
	 */
	public static String encryptSHA(String plainText) {
		return encrypt(plainText, SHA);
	}
	
	
	/**
	 * HmacMD5加密
	 * 
	 * @param plainText	明文
	 * @param secretKeyText	密钥
	 * @return	密文
	 */
	public static String encryptHmacMD5(String plainText, String secretKeyText) {
		try {
            
			//手动生成密钥(十位)
            byte[] key=Hex.decodeHex(secretKeyText.toCharArray());  
            
            //还原密钥
            SecretKey secretKey = new SecretKeySpec(key, "HmacMD5"); 
            
          //实例化mac
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());  
            
            //初始化mac
            mac.init(secretKey);
            byte[] hmacMD5Bytes=mac.doFinal(plainText.getBytes());
            
            return Hex.encodeHexString(hmacMD5Bytes);
        } catch (Exception e) {
            LoggerFactory.getLogger(SecurityUtils.class).error("HmacMD5加密异常", e);
        } 
		
		return "";
	}
	
	/**
	 * DES加密
	 * 
	 * @param plainText	明文
	 * @return	密文
	 */
	public static String encryptDES(String plainText) {
		try { 
            //key转换成密钥
            DESKeySpec desKeySpec=new DESKeySpec(DES_PASSWORD.getBytes());
            SecretKeyFactory factory=SecretKeyFactory.getInstance(DES);
            
            //转换后的密钥
            SecretKey key = factory.generateSecret(desKeySpec);      
            
            //加密
            //算法类型/工作方式/填充方式
            Cipher cipher=Cipher.getInstance("DES/ECB/PKCS5Padding"); 
            
            //指定为加密模式
            cipher.init(Cipher.ENCRYPT_MODE, key);   
            byte[] result=cipher.doFinal(plainText.getBytes());
            
            //转换为十六进制
            return Hex.encodeHexString(result);  
        } catch (Exception e) {
            LoggerFactory.getLogger(SecurityUtils.class).error("DES加密出现异常", e);
        }
		
		return "";
	}
	
	/**
	 * DES解密
	 * 
	 * @param cipherText	密文
	 * @return	明文
	 */
	public static String decryptDES(String cipherText) {
		try {  
            //key转换成密钥
            DESKeySpec desKeySpec=new DESKeySpec(DES_PASSWORD.getBytes());
            SecretKeyFactory factory=SecretKeyFactory.getInstance(DES);
            
            //转换后的密钥
            SecretKey key = factory.generateSecret(desKeySpec);      
            
            //加密
            //算法类型/工作方式/填充方式
            Cipher cipher=Cipher.getInstance("DES/ECB/PKCS5Padding"); 
            
            //解密
            //相同密钥，指定为解密模式
            cipher.init(Cipher.DECRYPT_MODE, key);  
            
            //根据加密内容解密
            byte[] plainText = cipher.doFinal(cipherText.getBytes());   
            
            //转换字符串
            return new String(plainText);  
        } catch (Exception e) {
            LoggerFactory.getLogger(SecurityUtils.class).error("DES解密出现异常", e);
        }
		
		return "";
	}
	
	/**
	 * MessageDigest摘要算法加密
	 * 
	 * @param plainText	明文
	 * @param algorithm	加密算法
	 * @return	密文
	 */
	private static String encrypt(String plainText, String algorithm) {
		try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            
            byte[] digest = md.digest(plainText.getBytes());
            
            return Hex.encodeHexString(digest);           
        } catch (NoSuchAlgorithmException e) {
            LoggerFactory.getLogger(SecurityUtils.class).error("没有"+ algorithm +"加密算法", e);
        }
		
		return "";
	}

}
