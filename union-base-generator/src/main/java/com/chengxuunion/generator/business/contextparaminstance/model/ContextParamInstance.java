package com.chengxuunion.generator.business.contextparaminstance.model;

import com.chengxuunion.generator.business.context.model.Context;
import com.chengxuunion.generator.business.user.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Author youpanpan
 * @Description:    上下文参数配置实例
 * @Date:创建时间: 2019-01-08 17:31:52
 * @Modified By:
 */
public class ContextParamInstance {

    
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
     * 实例名称
     * 是否允许为空：YES
     */
    private String instanceName;

    /**
     * 实例描述
     * 是否允许为空：YES
     */
    private String instanceDesc;

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
     * 开放类型，0：私有，1：项目组
     */
    private String openType;

    /**
     * 上下文配置对象
     */
    private Context context;

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

    public Long getContextId() {
        return contextId;
    }

    public void setContextId(Long contextId) {
        this.contextId = contextId;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getInstanceDesc() {
        return instanceDesc;
    }

    public void setInstanceDesc(String instanceDesc) {
        this.instanceDesc = instanceDesc;
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

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}