package com.chengxuunion.generator.business.roleurl.model;

import java.util.Date;

/**
 * @Author youpanpan
 * @Description:    角色URL
 * @Date:创建时间: 2019-01-17 17:44:49
 * @Modified By:
 */
public class RoleUrl {

    
    /**
     * 序号，主键
     * 是否允许为空：NO
     */
    private Long id;

    /**
     * 角色ID
     * 是否允许为空：YES
     */
    private Long roleId;

    /**
     * URL ID
     * 是否允许为空：YES
     */
    private Long urlId;

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

    

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUrlId() {
        return urlId;
    }

    public void setUrlId(Long urlId) {
        this.urlId = urlId;
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

    

}