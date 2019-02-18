package com.chengxuunion.util.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.slf4j.LoggerFactory;

/**
 * .properties文件读取器
 *
 * @author kutome
 * @date 2018年8月29日
 * @version V1.0
 */
public class PropertiesReader {
	
	/**
	 * .properties文件路径
	 */
	private String fileName;
	
	/**
	 * 上次修改的时间
	 */
	private long lastModifyTime = 0;
	
    /**
     * 文件表示的Properties对象
     */
    private Properties prop = null;
	
	/**
	 * 实例化文件读取器
	 * 
	 * @param fileName	properties文件路径
	 * @throws IOException 
	 */
	public PropertiesReader(String fileName) throws IOException {
		this.fileName = fileName;
		
		//加载文件
		prop = new Properties();
		File file = new File(this.getClass().getResource(fileName).getPath());
		InputStreamReader in = null;
		if (file.exists()) {
			this.lastModifyTime = file.lastModified();
			in = new InputStreamReader(new FileInputStream(file), "UTF-8");
		} else {
			in = new InputStreamReader(this.getClass().getResourceAsStream(fileName), "UTF-8");			
		}
		
		prop.load(in);
	}
	
	/**
	 * 获取指定key的值
	 * 
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		checkUpdate();
		return prop.getProperty(key);
	}
	
	/**
	 * 获取指定key的int值
	 * 
	 * @param key
	 * @return
	 */
	public int getIntValue(String key) {
		checkUpdate();
		return Integer.parseInt(prop.getProperty(key));
	}
	
	/**
	 * 获取指定key的boolean值
	 * 
	 * @param key
	 * @return
	 */
	public boolean getBoolValue(String key) {
		checkUpdate();
		return Boolean.parseBoolean(prop.getProperty(key));
	}
	
	/**
	 * 获取配置文件中所有的key=value配置，并放置到map中
	 * 
	 * @return
	 */
	public Map<String, String> getParamMap() {
		Map<String, String> map = new HashMap<String, String>();
		for (Entry<Object, Object> entry : prop.entrySet()) {
			map.put((String)entry.getKey(), (String)entry.getValue());
		}
		return map;
	}
	
	/**
	 * 检查更新，如果文件发生了变更则更新
	 */
	private void checkUpdate() {
		File file = new File(this.getClass().getResource(fileName).getPath());
		if (file.exists()) {
			if (file.lastModified() != this.lastModifyTime) {
				try {
					InputStreamReader  in = new InputStreamReader(new FileInputStream(file), "UTF-8");
					prop.load(in);
				} catch (IOException e) {
					LoggerFactory.getLogger(this.getClass()).error("检查更新properties文件发生异常", e);
				}
			}
		}
		
	}

}
