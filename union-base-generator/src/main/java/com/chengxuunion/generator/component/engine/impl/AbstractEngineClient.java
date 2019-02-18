package com.chengxuunion.generator.component.engine.impl;

import com.chengxuunion.generator.business.template.model.Template;
import com.chengxuunion.generator.common.model.FilePath;
import com.chengxuunion.generator.component.context.ExtContext;
import com.chengxuunion.generator.component.engine.EngineClient;
import com.chengxuunion.generator.component.engine.util.EngineUtil;
import com.chengxuunion.util.file.FilePathUtils;
import com.chengxuunion.util.file.FileUtils;
import com.chengxuunion.util.file.ZipUtils;
import com.chengxuunion.util.id.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.util.Map;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-23 11:02
 * @Modified By:
 */
public abstract class AbstractEngineClient<T> implements EngineClient{

    protected static Logger logger = LoggerFactory.getLogger(AbstractEngineClient.class);

    @Value("${template.unzip.dir}")
    private String templateUnZipDir;

    @Value("${template.dir}")
    private String templateDir;

    @Value("${code.dir}")
    private String codeDir;

    @Override
    public FilePath parseTemplate(Template template, ExtContext extContext) throws Exception {
        // 先将模版文件进行压缩放置在本地的一个临时目录中
        String tempDir = templateUnZipDir + File.separator + FilePathUtils.buildDatePathDay() + File.separator + IdGenerator.getInstance().nextIdString();
        FileUtils.mkdir(tempDir);

        String zipPath = templateDir + File.separator + template.getTemplatePath();

        Map<String, Object> dataMap = extContext.getDataMap();
        try {
            ZipUtils.decompress(zipPath, tempDir, dataMap);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("解压文件" + zipPath + "发生异常", e);
        }

        // 获取模版处理对象
        T templateProcess = getTemplateProcess(tempDir);

        // 生成代码文件路径
        String codeDirPath = codeDir + File.separator + FilePathUtils.buildDatePathDay() + File.separator + IdGenerator.getInstance().nextIdString();
        FileUtils.mkdir(codeDirPath);

        OutputStream out = null;
        try {
            processTemplateDir(templateProcess, extContext, new File(tempDir), tempDir, codeDirPath);

            // 删除模版文件解压文件
            FileUtils.deleteFile(tempDir);

            /*String codeZipFileName = FilePathUtils.buildDatePathDay() + File.separator + IdGenerator.getInstance().nextIdString() + ".zip";
            String codeZipPath = codeDir + File.separator + codeZipFileName;
            FileUtils.createFile(codeZipPath);
            out = new FileOutputStream(new File(codeZipPath));
            ZipUtils.compress(codeDirPath, out);*/

            return new FilePath(codeDirPath, codeDirPath);
        } catch (Exception e) {
            throw e;
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 处理模版目录
     *
     * @param templateProcess    模版引擎处理对象
     * @param extContext         模版引擎上下文
     * @param templateDirFile   模版目录对象
     * @param templateDirPath   模版文件临时目录地址
     * @param codeDirPath       代码目录地址
     */
    private void processTemplateDir(T templateProcess, ExtContext extContext,
                                    File templateDirFile, String templateDirPath, String codeDirPath) throws Exception {
        if (!templateDirFile.exists()) {
            return;
        }

        File[] files = templateDirFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                processTemplateDir(templateProcess, extContext, file, templateDirPath, codeDirPath);
            } else {
                processTemplateFile(templateProcess, extContext, file, templateDirPath, codeDirPath);
            }

        }
    }

    /**
     * 处理模版文件
     *
     * @param templateProcess    模版引擎处理对象
     * @param extContext         模版引擎上下文
     * @param file              文件对象
     * @param templateDirPath   模版文件临时目录地址
     * @param codeDirPath       代码目录地址
     */
    private void processTemplateFile(T templateProcess, ExtContext extContext,
                                     File file, String templateDirPath, String codeDirPath) throws Exception{
        FileWriter fileWriter = null;
        logger.info(templateDirPath+","+codeDirPath);

        try {
            // 设置当前包名
            File templateDirFile = new File(templateDirPath);
            String packageName = file.getAbsolutePath().replace(templateDirFile.getAbsolutePath(), "");
            String curPackageName = packageName.substring(0, packageName.lastIndexOf(File.separator));
            extContext.setVariable("curPackageName", curPackageName.replaceAll("\\\\", "\\."));

            // 在代码文件夹中新建相应的文件夹及文件
            // 替换文件后缀
            packageName = EngineUtil.replaceSuffix(packageName);

            String curFilePath = codeDirPath + File.separator + packageName;
            File curFile = new File(curFilePath);
            if (!curFile.exists()) {
                FileUtils.mkdir(curFile.getParent());
                curFile.createNewFile();
            }
            fileWriter = new FileWriter(curFile);

            // 处理模版文件（相对路径）
            process(templateProcess, file.getAbsolutePath().replace(templateDirFile.getAbsolutePath(), ""), extContext, fileWriter);
        } catch (Exception e) {
            logger.error("处理模版文件" + file.getAbsolutePath() + "发生异常", e);
            throw e;
        }  finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取模版引擎处理对象
     *
     * @param templateDir
     * @return
     */
    public abstract T getTemplateProcess(String templateDir);

    /**
     * 处理模版引擎文件
     *
     * @param templateProcess    模版引擎处理对象
     * @param filePath           模版文件路径
     * @param extContext         模版引擎上下文对象
     * @param out                文件输出流
     */
    public abstract void process(T templateProcess, String filePath, ExtContext extContext, Writer out) throws Exception;
}
