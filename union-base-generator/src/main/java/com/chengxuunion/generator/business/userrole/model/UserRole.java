package com.chengxuunion.generator.business.userrole.model;

import com.chengxuunion.generator.business.role.model.Role;

import java.util.Date;

/**
 * @Author youpanpan
 * @Description:    用户角色
 * @Date:创建时间: 2019-01-17 16:59:14
 * @Modified By:
 */
public class UserRole {

    
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
     * 角色ID
     * 是否允许为空：YES
     */
    private Long roleId;

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
     * 角色信息
     */
    private Role role;

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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}