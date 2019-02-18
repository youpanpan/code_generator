package com.chengxuunion.util.file;

import com.chengxuunion.util.date.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @Author youpanpan
 * @Description:    文件路径工具
 * @Date:创建时间: 2019-01-09 15:04
 * @Modified By:
 */
public class FilePathUtils {

    @SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(FilePathUtils.class);

    private FilePathUtils() {

    }

    /**
     * 构建路径yyyyMMdd
     *
     * @return
     */
    public static String buildDatePathDay() {
        Date nowDate = new Date();
        return DateUtils.format(nowDate, "yyyyMMdd");
    }

    /**
     * 构建文件名：yyyyMMddHHmmssSSS文件后缀
     *
     * @param fileName
     * @return
     */
    public static String buildFileNameByFileName(String fileName) {
        String suffix = FileUtils.getSuffix(fileName);
        return buildFileNameBySuffix(suffix);
    }

    /**
     * 构建基于时间的文件名称格式：yyyyMMddHHmmssSSS.suffix
     *
     * @param suffix    文件后缀,例"zip"
     * @return
     */
    public static String buildFileNameBySuffix(String suffix) {
        StringBuilder filePath = new StringBuilder();
        Date nowDate = new Date();
        filePath.append(DateUtils.format(nowDate, "yyyyMMddHHmmssSSS"));
        filePath.append(".");
        filePath.append(suffix);

        return filePath.toString();
    }
}
