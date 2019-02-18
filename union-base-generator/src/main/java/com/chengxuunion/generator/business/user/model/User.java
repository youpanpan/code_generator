package com.chengxuunion.generator.business.user.model;

import com.chengxuunion.generator.business.userrole.model.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * @Author youpanpan
 * @Description:    用户
 * @Date:创建时间: 2019-01-16 13:54:23
 * @Modified By:
 */
public class User {

    
    /**
     * 序号，主键
     * 是否允许为空：NO
     */
    private Long id;

    /**
     * 用户名
     * 是否允许为空：YES
     */
    private String userName;

    /**
     * 注册邮箱
     * 是否允许为空：YES
     */
    private String email;

    /**
     * 密码
     * 是否允许为空：YES
     */
    private String password;

    /**
     * 用户头像路径
     * 是否允许为空：YES
     */
    private String headPhoto;

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
     * 状态，0：禁用，1：启用
     * 是否允许为空：YES
     */
    private String status;

    /**
     * 用户角色列表
     */
    private List<UserRole> userRoleList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRole> userRoleList) {
        this.userRoleList = userRoleList;
    }
}