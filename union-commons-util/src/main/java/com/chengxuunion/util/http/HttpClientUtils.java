package com.chengxuunion.util.http;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

/**
 * HTTP客户端工具类
 *
 * @author kutome
 * @date 2018年9月16日
 * @version V1.0
 */
public class HttpClientUtils {
	
	// utf-8字符编码
    public static final String CHARSET_UTF_8 = "utf-8";

    // HTTP内容类型。
    public static final String CONTENT_TYPE_TEXT_HTML = "text/xml";

    // HTTP内容类型。相当于form表单的形式，提交数据
    public static final String CONTENT_TYPE_FORM_URL = "application/x-www-form-urlencoded";

    // HTTP内容类型。相当于form表单的形式，提交数据
    public static final String CONTENT_TYPE_JSON_URL = "application/json;charset=utf-8";
    

    // 连接管理器
    private static PoolingHttpClientConnectionManager pool;

    // 请求配置
    private static RequestConfig requestConfig;
    
    static {
        
        try {
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    builder.build());
            // 配置同时支持 HTTP 和 HTPPS
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create().register(
                    "http", PlainConnectionSocketFactory.getSocketFactory()).register(
                    "https", sslsf).build();
            // 初始化连接管理器
            pool = new PoolingHttpClientConnectionManager(
                    socketFactoryRegistry);
            // 将最大连接数增加到200，实际项目最好从配置文件中读取这个值
            pool.setMaxTotal(200);
            // 设置最大路由
            pool.setDefaultMaxPerRoute(2);
            // 根据默认超时限制初始化requestConfig
            int socketTimeout = 10000;
            int connectTimeout = 10000;
            int connectionRequestTimeout = 10000;
            requestConfig = RequestConfig.custom().setConnectionRequestTimeout(
                    connectionRequestTimeout).setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();

        } catch (Exception e) {
           LoggerFactory.getLogger(HttpClientUtils.class).error("初始化HttpClient失败", e);
        } 
        

        // 设置请求超时时间
        requestConfig = RequestConfig.custom()
        		.setSocketTimeout(50000)
        		.setConnectTimeout(50000)
                .setConnectionRequestTimeout(50000).build();
    }

	/**
	 * 获取HttpClient实例
	 * 
	 * @return
	 */
	public static CloseableHttpClient getHttpClient() {
	    
	    CloseableHttpClient httpClient = HttpClients.custom()
	            // 设置连接池管理
	            .setConnectionManager(pool)
	            // 设置请求配置
	            .setDefaultRequestConfig(requestConfig)
	            // 设置重试次数
	            .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
	            .build();
	    
	    return httpClient;
	}

	/**
	 * 发送POST请求
	 * 
	 * @param url	请求地址
	 * @return
	 * @throws Exception 
	 */
	public static String sendHttpPost(String url) throws Exception {
		// 创建httpPost
        HttpPost httpPost = new HttpPost(url);
        return sendHttpPost(httpPost);
	}
	
	/**
	 * 发送POST请求
	 * 
	 * @param url
	 * @param params	 参数(格式:key1=value1&key2=value2)
	 * @return
	 * @throws Exception 
	 */
	public static String sendHttpPost(String url, String params) throws Exception {
		HttpPost httpPost = new HttpPost(url);// 创建httpPost
        try {
            // 设置参数
            if (params != null && params.trim().length() > 0) {
                StringEntity stringEntity = new StringEntity(params, "UTF-8");
                stringEntity.setContentType(CONTENT_TYPE_FORM_URL);
                httpPost.setEntity(stringEntity);
            }
        } catch (Exception e) {
            throw e;
        }
        return sendHttpPost(httpPost);
	}
	
	/**
	 * 发送POST请求
	 * 
	 * @param url
	 * @param paramMap
	 * @return
	 * @throws Exception 
	 */
	public static String sendHttpPost(String url, Map<String, String> paramMap) throws Exception {
		String parem = convertStringParamter(paramMap);
        return sendHttpPost(url, parem);
	}
	
	/**
	 * 发送GET请求
	 * 
	 * @param url	请求地址
	 * @return
	 * @throws Exception 
	 */
	public static String sendHttpGet(String url) throws Exception {
		// 创建get请求
        HttpGet httpGet = new HttpGet(url);
        return sendHttpGet(httpGet);
	}
	
	/**
	 * 发送GET请求获取流数据
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static byte[] sendHttpGetForInputStream(String url) throws Exception {
		// 创建get请求
        HttpGet httpGet = new HttpGet(url);
        return sendHttpGetForInputStream(httpGet);
	}
	
	/**
	 * 使用multipart/form-data来上传文件
	 * 
	 * @param url	请求地址
	 * @param reqParams	请求参数，map集合，包含各种类型的body
	 * @return
	 * @throws Exception
	 */
	public static String postFileMultiPart(String url, Map<String, ContentBody> reqParams) throws Exception {
		CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        
        // 响应内容
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = getHttpClient();
            HttpPost httpPost = new HttpPost(url);
            // 配置请求信息
            httpPost.setConfig(requestConfig);
            
            
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            for(Entry<String,ContentBody> param : reqParams.entrySet()){
            	multipartEntityBuilder.addPart(param.getKey(), param.getValue());
            }
            HttpEntity reqEntity = multipartEntityBuilder.build();
            httpPost.setEntity(reqEntity);

            // 执行请求
            response = httpClient.execute(httpPost);
            // 得到响应实例
            HttpEntity entity = response.getEntity();

            // 判断响应状态
            if (response.getStatusLine().getStatusCode() >= 300) {
                throw new Exception(
                        "HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
            }

            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
                EntityUtils.consume(entity);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                // 释放资源
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                throw e;
            }
        }
        return responseContent;
        
	}
	
	/**
     * 发送Post请求
     * 
     * @param httpPost
     * @return
	 * @throws Exception 
     */
    private static String sendHttpPost(HttpPost httpPost) throws Exception {

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        // 响应内容
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = getHttpClient();
            // 配置请求信息
            httpPost.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpPost);
            // 得到响应实例
            HttpEntity entity = response.getEntity();

            // 判断响应状态
            if (response.getStatusLine().getStatusCode() >= 300) {
                throw new Exception(
                        "HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
            }

            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
                EntityUtils.consume(entity);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                // 释放资源
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                throw e;
            }
        }
        return responseContent;
    }
	
    /**
     * 发送Get请求
     * 
     * @param httpGet
     * @return
     * @throws Exception 
     */
    private static String sendHttpGet(HttpGet httpGet) throws Exception {

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        // 响应内容
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = getHttpClient();
            // 配置请求信息
            httpGet.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpGet);
            // 得到响应实例
            HttpEntity entity = response.getEntity();

            // 判断响应状态
            if (response.getStatusLine().getStatusCode() >= 300) {
                throw new Exception(
                        "HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
            }

            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
                EntityUtils.consume(entity);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                // 释放资源
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
            	throw e;
            }
        }
        return responseContent;
    }
    
    /**
     * 发送Get请求
     * 
     * @param httpGet
     * @return
     * @throws Exception 
     */
    private static byte[] sendHttpGetForInputStream(HttpGet httpGet) throws Exception {

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        // 响应内容
        byte[] data = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = getHttpClient();
            // 配置请求信息
            httpGet.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpGet);
            // 得到响应实例
            HttpEntity entity = response.getEntity();

            // 判断响应状态
            if (response.getStatusLine().getStatusCode() >= 300) {
                throw new Exception(
                        "HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
            }

            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
            	data = EntityUtils.toByteArray(entity);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                // 释放资源
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
            	throw e;
            }
        }
        return data;
    }

    /**
     * 将map集合的键值对转化成：key1=value1&key2=value2 的形式
     * 
     * @param parameterMap
     *            需要转化的键值对集合
     * @return 字符串
     */
    @SuppressWarnings("rawtypes")
	private static String convertStringParamter(Map parameterMap) {
        StringBuffer parameterBuffer = new StringBuffer();
        if (parameterMap != null) {
            Iterator iterator = parameterMap.keySet().iterator();
            String key = null;
            String value = null;
            while (iterator.hasNext()) {
                key = (String) iterator.next();
                if (parameterMap.get(key) != null) {
                    value = (String) parameterMap.get(key);
                } else {
                    value = "";
                }
                parameterBuffer.append(key).append("=").append(value);
                if (iterator.hasNext()) {
                    parameterBuffer.append("&");
                }
            }
        }
        return parameterBuffer.toString();
    }
    
    public static void main(String[] args) throws Exception {
    	Map<String, ContentBody> reqParams = new HashMap<String, ContentBody>();
    	reqParams.put("file", new FileBody(new File("C:\\Users\\kutom\\Desktop\\243048394439606272.jpg")));
    	reqParams.put("userId", new StringBody("154304637815003787", ContentType.MULTIPART_FORM_DATA));
    	reqParams.put("token", new StringBody("ondhkfbzqh3ru204y2toa9uqjiwwzo8fq193pb30xrr7mwpupc", ContentType.MULTIPART_FORM_DATA));
		String result = postFileMultiPart("http://localhost:8086/api/v1/user/upload", reqParams);
		System.out.println(result);
	}
}
