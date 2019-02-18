package com.chengxuunion.generator.common.util;

import com.chengxuunion.generator.common.model.FilePath;
import com.chengxuunion.util.file.FilePathUtils;
import com.chengxuunion.util.file.FileUtils;
import com.chengxuunion.util.id.IdGenerator;


/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-09 15:26
 * @Modified By:
 */
public class PathUtils {

    private PathUtils() {

    }

    /**
     * 获取文件路径（时间格式：yyyyMMdd/唯一性IDyyyyMMddHHmmssSSS.suffix）
     *
     * @param baseDir   基础存储路径
     * @param fileName  文件名称
     * @return
     */
    public static FilePath getFilePathWithDate(String baseDir, String fileName) {
        String datePath = FilePathUtils.buildDatePathDay();
        String path = baseDir + "/" + datePath;
        FileUtils.mkdir(path);

        String newFileName = IdGenerator.getInstance().nextIdString() + FilePathUtils.buildFileNameByFileName(fileName);
        String filePath = path + "/" + newFileName;
        FileUtils.createFile(filePath);

        return new FilePath(datePath + "/" + newFileName, filePath);
    }

    public static void main(String[] args) {
        System.out.println("/user/manager/{userId}/{3333}/{4444}".replaceAll("\\{.*?\\}", ".*"));
    }
}
