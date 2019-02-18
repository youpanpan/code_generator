package com.chengxuunion.generator.business.codegenerate.model;

import com.chengxuunion.generator.business.template.model.Template;
import com.chengxuunion.generator.business.user.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Author youpanpan
 * @Description:    代码生成记录
 * @Date:创建时间: 2019-01-10 14:49:05
 * @Modified By:
 */
public class CodeGenerate {

    
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
     * 代码文件名称
     */
    private String codeFileName;

    /**
     * 生成的代码路径
     * 是否允许为空：YES
     */
    private String codePath;

    /**
     * 代码文件大小，单位B
     */
    private Long codeSize;

    /**
     * 创建时间
     * 是否允许为空：YES
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//进行格式化转换
    private Date createDate;

    /**
     * 创建用户ID
     */
    private Long createUserId;

    /**
     * 模版
     */
    private Template template;

    /**
     * 创建用户
     */
    private User user;

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

    public String getCodeFileName() {
        return codeFileName;
    }

    public void setCodeFileName(String codeFileName) {
        this.codeFileName = codeFileName;
    }

    public String getCodePath() {
        return codePath;
    }

    public void setCodePath(String codePath) {
        this.codePath = codePath;
    }

    public Long getCodeSize() {
        return codeSize;
    }

    public void setCodeSize(Long codeSize) {
        this.codeSize = codeSize;
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

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}