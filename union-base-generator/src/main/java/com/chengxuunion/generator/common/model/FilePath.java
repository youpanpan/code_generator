package com.chengxuunion.generator.common.model;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-09 15:28
 * @Modified By:
 */
public class FilePath {

    /**
     * 文件存储路径
     */
    private String filePath;

    /**
     * 文件全路径
     */
    private String fileFullPath;

    public FilePath() {

    }

    public FilePath(String filePath, String fileFullPath) {
        this.filePath = filePath;
        this.fileFullPath = fileFullPath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileFullPath() {
        return fileFullPath;
    }

    public void setFileFullPath(String fileFullPath) {
        this.fileFullPath = fileFullPath;
    }
}
