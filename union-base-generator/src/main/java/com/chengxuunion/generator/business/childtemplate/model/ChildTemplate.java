package com.chengxuunion.generator.business.childtemplate.model;

import com.chengxuunion.generator.business.template.model.Template;

import java.util.Date;

/**
 * @Author youpanpan
 * @Description:    子模版
 * @Date:创建时间: 2019-01-09 17:25:12
 * @Modified By:
 */
public class ChildTemplate {

    
    /**
     * 序号，主键
     * 是否允许为空：NO
     */
    private Long id;

    /**
     * 父模版ID
     * 是否允许为空：YES
     */
    private Long parentTemplateId;

    /**
     * 子模版ID
     * 是否允许为空：YES
     */
    private Long childTemplateId;

    /**
     * 状态，0：启用，1：禁用
     * 是否允许为空：YES
     */
    private String status;

    /**
     * 创建时间
     * 是否允许为空：YES
     */
    private Date createDate;

    /**
     * 修改时间
     * 是否允许为空：YES
     */
    private Date updateDate;

    /**
     * 创建用户ID
     */
    private Long createUserId;

    /**
     * 字模版对象
     */
    private Template template;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentTemplateId() {
        return parentTemplateId;
    }

    public void setParentTemplateId(Long parentTemplateId) {
        this.parentTemplateId = parentTemplateId;
    }

    public Long getChildTemplateId() {
        return childTemplateId;
    }

    public void setChildTemplateId(Long childTemplateId) {
        this.childTemplateId = childTemplateId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }
}