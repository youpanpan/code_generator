package com.chengxuunion.generator.business.context.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Author youpanpan
 * @Description:    上下文实现配置
 * @Date:创建时间: 2019-01-07 17:43:15
 * @Modified By:
 */
public class Context {

    
    /**
     * 序号，主键
     * 是否允许为空：NO
     */
    private Long id;

    /**
     * 全类名
     * 是否允许为空：NO
     */
    private String className;

    /**
     * 上下文参数全类名
     */
    private String paramClassName;

    /**
     * 上下文名称
     * 是否允许为空：NO
     */
    private String contextName;

    /**
     * 描述
     * 是否允许为空：YES
     */
    private String contextDesc;

    /**
     * 状态，0：禁用，1启用
     * 是否允许为空：NO
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

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getParamClassName() {
        return paramClassName;
    }

    public void setParamClassName(String paramClassName) {
        this.paramClassName = paramClassName;
    }

    public String getContextName() {
        return contextName;
    }

    public void setContextName(String contextName) {
        this.contextName = contextName;
    }

    public String getContextDesc() {
        return contextDesc;
    }

    public void setContextDesc(String contextDesc) {
        this.contextDesc = contextDesc;
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
}