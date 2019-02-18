package com.chengxuunion.generator.business.codegenerate.model.request;

import com.chengxuunion.generator.common.model.PageParam;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-10 14:49:05
 * @Modified By:
 */
public class CodeGeneratePageParam extends PageParam{

    /**
     * 模版名称
     */
    private String templateName;

    /**
     * 用户ID
     */
    private Long userId;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}