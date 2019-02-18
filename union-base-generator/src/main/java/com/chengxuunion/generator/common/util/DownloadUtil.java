package com.chengxuunion.generator.common.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-16 16:00
 * @Modified By:
 */
public class DownloadUtil {

    private DownloadUtil() {

    }

    /**
     * 下载指定文件
     *
     * @param response  输出流
     * @param fileFullPath  文件全路径
     * @param fileName  文件名
     * @return  成功返回true，否则返回false
     */
    public static Boolean download(HttpServletResponse response, String fileFullPath, String fileName) {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            File file = new File(fileFullPath);
            long fileLength = file.length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));

            outputStream = response.getOutputStream();
            inputStream = new FileInputStream(file);
            byte[] buf = new byte[1024];
            int len = -1;
            while((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                return false;
            }
        }

        return true;
    }
}
