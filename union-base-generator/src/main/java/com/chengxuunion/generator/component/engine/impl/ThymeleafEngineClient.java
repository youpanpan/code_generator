package com.chengxuunion.generator.component.engine.impl;

import com.chengxuunion.generator.component.context.ExtContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import java.io.*;
import java.util.Map;

/**
 * @Author youpanpan
 * @Description:    thymeleaf模版引擎客户端
 * @Date:创建时间: 2019-01-11 09:47
 * @Modified By:
 */
@Component
public class ThymeleafEngineClient extends AbstractEngineClient<TemplateEngine> {

    @Value("${template.unzip.dir}")
    private String templateUnZipDir;

    @Value("${template.dir}")
    private String templateDir;

    @Value("${code.dir}")
    private String codeDir;

    @Override
    public TemplateEngine getTemplateProcess(String templateDir) {
        // 初始化模版引擎
        FileTemplateResolver templateResolver = new FileTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.TEXT);
        templateResolver.setPrefix(templateDir);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    @Override
    public void process(TemplateEngine templateProcess, String filePath, ExtContext extContext, Writer out) throws Exception {
        // 处理模版文件（相对路径）
        Context context = new Context();
        for (Map.Entry<String, Object> entry : extContext.getDataMap().entrySet()) {
            context.setVariable(entry.getKey(), entry.getValue());
        }
        templateProcess.process(filePath, context, out);
    }

}
