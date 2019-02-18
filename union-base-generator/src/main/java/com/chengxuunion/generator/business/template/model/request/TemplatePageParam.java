package com.chengxuunion.generator.business.template.model.request;

import com.chengxuunion.generator.common.model.PageParam;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-09 14:04:49
 * @Modified By:
 */
public class TemplatePageParam extends PageParam{

    /**
     * 模版类型
     */
    private String templateType;

    /**
     * 模版状态，0：禁用，1：启用
     */
    private String status;

    /**
     * 模版名称
     */
    private String templateName;

    /**
     * 用户ID
     */
    private Long userId;

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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