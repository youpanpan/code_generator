package com.chengxuunion.generator.business.template.model;

import com.chengxuunion.generator.business.childtemplate.model.ChildTemplate;
import com.chengxuunion.generator.business.engine.model.Engine;
import com.chengxuunion.generator.business.templatecontext.model.TemplateContext;
import com.chengxuunion.generator.business.user.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

/**
 * @Author youpanpan
 * @Description:    模版
 * @Date:创建时间: 2019-01-09 14:04:49
 * @Modified By:
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Template {

    
    /**
     * 序号，主键
     * 是否允许为空：NO
     */
    private Long id;

    /**
     * 模版名称
     * 是否允许为空：YES
     */
    private String templateName;

    /**
     * 模版描述
     * 是否允许为空：YES
     */
    private String templateDesc;

    /**
     * 模版文件路径
     * 是否允许为空：YES
     */
    private String templatePath;

    /**
     * 模版文件名称
     */
    private String templateFileName;

    /**
     * 模版文件大小，单位B
     */
    private Long templateFileSize;

    /**
     * 模版类型，1：单模版，2:组合模版
     * 是否允许为空：YES
     */
    private String templateType;

    /**
     * 状态，0：禁用，1：启用
     * 是否允许为空：YES
     */
    private String status;

    /**
     * 创建时间
     * 是否允许为空：YES
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//进行格式化转换
    private Date createDate;

    /**
     * 修改时间
     * 是否允许为空：YES
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//进行格式化转换
    private Date updateDate;

    /**
     * 排序号
     * 是否允许为空：YES
     */
    private Integer orderNum;

    /**
     * 模版引擎ID
     */
    private Long engineId;

    /**
     * 创建用户ID
     */
    private Long createUserId;

    /**
     * 开放类型
     */
    private String openType;

    /**
     * 模版引擎对象
     */
    private Engine engine;

    /**
     * 创建用户
     */
    private User user;

    /**
     * 字模版列表
     */
    private List<ChildTemplate> childTemplateList;

    /**
     * 所依赖的上下文列表
     */
    private List<TemplateContext> templateContextList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateDesc() {
        return templateDesc;
    }

    public void setTemplateDesc(String templateDesc) {
        this.templateDesc = templateDesc;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getTemplateFileName() {
        return templateFileName;
    }

    public void setTemplateFileName(String templateFileName) {
        this.templateFileName = templateFileName;
    }

    public Long getTemplateFileSize() {
        return templateFileSize;
    }

    public void setTemplateFileSize(Long templateFileSize) {
        this.templateFileSize = templateFileSize;
    }

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

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Long getEngineId() {
        return engineId;
    }

    public void setEngineId(Long engineId) {
        this.engineId = engineId;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public List<ChildTemplate> getChildTemplateList() {
        return childTemplateList;
    }

    public void setChildTemplateList(List<ChildTemplate> childTemplateList) {
        this.childTemplateList = childTemplateList;
    }

    public List<TemplateContext> getTemplateContextList() {
        return templateContextList;
    }

    public void setTemplateContextList(List<TemplateContext> templateContextList) {
        this.templateContextList = templateContextList;
    }
}