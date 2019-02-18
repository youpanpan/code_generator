package com.chengxuunion.generator.business.role.model;

import com.chengxuunion.generator.business.roleurl.model.RoleUrl;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * @Author youpanpan
 * @Description:    角色
 * @Date:创建时间: 2019-01-16 17:02:24
 * @Modified By:
 */
public class Role {

    /**
     * 序号，主键
     * 是否允许为空：NO
     */
    private Long id;

    /**
     * 角色标识符
     * 是否允许为空：YES
     */
    private String roleCode;

    /**
     * 角色名称
     * 是否允许为空：YES
     */
    private String roleName;

    /**
     * 角色描述
     * 是否允许为空：YES
     */
    private String roleDesc;

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
     * 创建人
     * 是否允许为空：YES
     */
    private Long createUserId;

    /**
     * 角色URL列表
     */
    private List<RoleUrl> roleUrlList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
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

    public List<RoleUrl> getRoleUrlList() {
        return roleUrlList;
    }

    public void setRoleUrlList(List<RoleUrl> roleUrlList) {
        this.roleUrlList = roleUrlList;
    }
}