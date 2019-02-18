package com.chengxuunion.generator.business.userproject.model;

import com.chengxuunion.generator.business.user.model.User;

import java.util.Date;

/**
 * @Author youpanpan
 * @Description:    用户项目组
 * @Date:创建时间: 2019-01-17 14:52:29
 * @Modified By:
 */
public class UserProject {

    
    /**
     * 序号，主键
     * 是否允许为空：NO
     */
    private Long id;

    /**
     * 用户ID
     * 是否允许为空：YES
     */
    private Long userId;

    /**
     * 项目ID
     * 是否允许为空：YES
     */
    private Long projectId;

    /**
     * 是否项目组管理员
     * 是否允许为空：YES
     */
    private String admin;

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
     * 创建人
     * 是否允许为空：YES
     */
    private Long createUserId;

    /**
     * 用户信息
     */
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}