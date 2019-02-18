package com.chengxuunion.generator.component.engine;

import com.chengxuunion.generator.business.template.model.Template;
import com.chengxuunion.generator.common.model.FilePath;
import com.chengxuunion.generator.component.context.ExtContext;

/**
 * @Author youpanpan
 * @Description:    模版引擎客户端接口
 * @Date:创建时间: 2019-01-11 09:42
 * @Modified By:
 */
public interface EngineClient {

    /**
     * 解析模版
     *
     * @param template  模版
     * @param extContext    内容容器
     * @return  生成的代码文件路径
     */
    FilePath parseTemplate(Template template, ExtContext extContext) throws Exception;

}
