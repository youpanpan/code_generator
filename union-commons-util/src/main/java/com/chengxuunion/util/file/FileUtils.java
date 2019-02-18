package com.chengxuunion.util.file;

import java.io.*;


/**
 * 文件工具类
 *
 * @author kutome
 * @date 2018年8月31日
 * @version V1.0
 */
public class FileUtils {

	/**
	 * 如果指定的路径不存在则创建，否则不做任何处理
	 * 
	 * @param path
	 */
	public static void mkdir(String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * 如果文件不存在则创建
	 *
	 * @param filePath
	 */
	public static void createFile(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 删除指定路径的文件
	 * 
	 * @param path
	 */
	public static void deleteFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		
		file.delete();
	}
	
	/**
	 * 获取文件后缀
	 * 
	 * @param fileName	文件名，例 你好.doc
	 * @return	例 doc
	 */
	public static String getSuffix(String fileName) {
		if (fileName.contains(".")) {
			return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		} 
		
		return "";
	}

	/**
	 * 保存文件
	 *
	 * @param filePath
	 * @param inputStream
	 * @throws IOException
	 */
	public static void saveFile(String filePath, InputStream inputStream) throws IOException {
		OutputStream outputStream = null;
		File file = new File(filePath);
		try {
			byte[] buf = new byte[1024];
			int len = -1;
			outputStream = new FileOutputStream(file);
			while((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
		} catch (IOException e) {
			throw e;
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	/**
	 * 保存文件
	 * 
	 * @param filePath
	 * @param data
	 * @throws Exception
	 */
	public static void saveFile(String filePath, byte[] data) throws Exception {
		File file = new File(filePath);
		if (!file.exists()) {
			file.createNewFile();
		}
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(file);
			outputStream.write(data);
		} catch (Exception e) {
			throw e;
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
		
		
	}
	
}
