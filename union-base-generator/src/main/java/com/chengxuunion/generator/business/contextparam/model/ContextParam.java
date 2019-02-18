package com.chengxuunion.generator.business.contextparam.model;

import com.chengxuunion.generator.business.context.model.Context;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Author youpanpan
 * @Description:    上下文参数
 * @Date:创建时间: 2019-01-08 16:11:09
 * @Modified By:
 */
public class ContextParam {

    
    /**
     * 序号，主键
     * 是否允许为空：NO
     */
    private Long id;

    /**
     * 上下文ID
     * 是否允许为空：YES
     */
    private Long contextId;

    /**
     * 参数名称
     * 是否允许为空：NO
     */
    private String paramName;

    /**
     * 参数标识
     * 是否允许为空：YES
     */
    private String paramCode;

    /**
     * 参数默认值
     * 是否允许为空：YES
     */
    private String paramDefaultValue;

    /**
     * 参数类型，1：字符串，2：数值，3：数组
     * 是否允许为空：YES
     */
    private String paramType;

    /**
     * 参数描述
     * 是否允许为空：YES
     */
    private String paramDesc;

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
     * 创建用户ID
     */
    private Long createUserId;

    /**
     * 上下文参数
     */
    private Context context;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContextId() {
        return contextId;
    }

    public void setContextId(Long contextId) {
        this.contextId = contextId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getParamDefaultValue() {
        return paramDefaultValue;
    }

    public void setParamDefaultValue(String paramDefaultValue) {
        this.paramDefaultValue = paramDefaultValue;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
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