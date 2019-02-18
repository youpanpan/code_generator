package com.chengxuunion.generator.business.contextparamvalue.model;

import com.chengxuunion.generator.business.contextparam.model.ContextParam;

import java.util.Date;

/**
 * @Author youpanpan
 * @Description:    上下参数实例配置值
 * @Date:创建时间: 2019-01-09 09:52:16
 * @Modified By:
 */
public class ContextParamValue {

    
    /**
     * 序号，主键
     * 是否允许为空：NO
     */
    private Long id;

    /**
     * 参数配置实例ID
     * 是否允许为空：YES
     */
    private Long instanceId;

    /**
     * 参数ID
     * 是否允许为空：YES
     */
    private Long paramId;

    /**
     * 参数值
     * 是否允许为空：YES
     */
    private String paramValue;

    /**
     * 创建时间
     * 是否允许为空：YES
     */
    private Date createDate;

    /**
     * 修改I时间
     * 是否允许为空：YES
     */
    private Date updateDate;

    /**
     * 创建用户ID
     */
    private Long createUserId;

    /**
     * 上下文参数
     */
    private ContextParam contextParam;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(Long instanceId) {
        this.instanceId = instanceId;
    }

    public Long getParamId() {
        return paramId;
    }

    public void setParamId(Long paramId) {
        this.paramId = paramId;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
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

    public ContextParam getContextParam() {
        return contextParam;
    }

    public void setContextParam(ContextParam contextParam) {
        this.contextParam = contextParam;
    }
}