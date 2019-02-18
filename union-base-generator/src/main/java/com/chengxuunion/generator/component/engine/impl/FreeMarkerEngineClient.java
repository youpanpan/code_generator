package com.chengxuunion.generator.component.engine.impl;

import com.chengxuunion.generator.component.context.ExtContext;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-23 10:02
 * @Modified By:
 */
@Component
public class FreeMarkerEngineClient extends AbstractEngineClient<Configuration> {

    @Override
    public Configuration getTemplateProcess(String templateDir) {
        Configuration configuration = new Configuration();
        TemplateLoader templateLoader = null;
        try {
            templateLoader = new FileTemplateLoader(new File(templateDir));
        } catch (IOException e) {
            logger.error("目录:" + templateDir +"不存在", e);
        }
        configuration.setTemplateLoader(templateLoader);
        return configuration;
    }

    @Override
    public void process(Configuration configuration, String filePath, ExtContext extContext, Writer out) throws Exception {
        configuration.getTemplate(filePath).process(extContext.getDataMap(), out);
    }
}
