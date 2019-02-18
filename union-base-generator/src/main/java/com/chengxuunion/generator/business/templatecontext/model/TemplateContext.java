package com.chengxuunion.generator.business.templatecontext.model;

import com.chengxuunion.generator.business.context.model.Context;

import java.util.Date;

/**
 * @Author youpanpan
 * @Description:    模版依赖的上下文
 * @Date:创建时间: 2019-01-15 11:49:27
 * @Modified By:
 */
public class TemplateContext {

    
    /**
     * 序号，主键
     * 是否允许为空：NO
     */
    private Long id;

    /**
     * 模版ID
     * 是否允许为空：YES
     */
    private Long templateId;

    /**
     * 上下文ID
     * 是否允许为空：YES
     */
    private Long contextId;

    /**
     * 创建时间
     * 是否允许为空：YES
     */
    private Date createDate;

    /**
     * 创建用户ID
     * 是否允许为空：YES
     */
    private Long createUserId;

    /**
     * 上下文ID
     */
    private Context context;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Long getContextId() {
        return contextId;
    }

    public void setContextId(Long contextId) {
        this.contextId = contextId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}