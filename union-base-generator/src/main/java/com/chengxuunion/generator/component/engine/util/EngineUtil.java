package com.chengxuunion.generator.component.engine.util;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-11 11:20
 * @Modified By:
 */
public class EngineUtil {

    private EngineUtil() {

    }

    /**
     * 替换文件后缀
     *
     * @param fileName
     * @return
     */
    public static String replaceSuffix(String fileName) {
        if (fileName.endsWith(".txml")) {
            fileName = fileName.replace(".txml", ".xml");
        } else if (fileName.endsWith(".tjava")) {
            fileName = fileName.replace(".tjava", ".java");
        } else if (fileName.endsWith(".thtml")) {
            fileName = fileName.replace(".thtml", ".html");
        } else if (fileName.endsWith(".tjs")) {
            fileName = fileName.replace(".tjs", ".js");
        }
        return fileName;
    }
}
