package com.chengxuunion.generator.business.codegenerate.model.request;

import java.util.Map;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-11 10:29
 * @Modified By:
 */
public class GenerateCodeParam {

    /**
     * 模版ID
     */
    private Long templateId;

    /**
     * 代码文件名
     */
    private String codeFileName;

    /**
     * 上下文集合，key: 上下文ID，value：上下文参数Map对象
     */
    private Map<String, Object> contextMap;

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getCodeFileName() {
        return codeFileName;
    }

    public void setCodeFileName(String codeFileName) {
        this.codeFileName = codeFileName;
    }

    public Map<String, Object> getContextMap() {
        return contextMap;
    }

    public void setContextMap(Map<String, Object> contextMap) {
        this.contextMap = contextMap;
    }
}
