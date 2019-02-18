package com.chengxuunion.generator.business.role.service;

import com.chengxuunion.generator.common.model.PageResult;

import com.chengxuunion.generator.business.role.model.Role;
import com.chengxuunion.generator.business.role.model.request.RolePageParam;


/**
 * @Author youpanpan
 * @Description:    角色服务接口
 * @Date:创建时间: 2019-01-16 17:02:24
 * @Modified By:
 */
public interface RoleService {

    /**
     * 查询角色分页列表
     *
     * @param rolePageParam  参数对象
     * @return  角色列表
     */
    PageResult<Role> listRolePage(RolePageParam rolePageParam);

    /**
     * 根据主键查询单个角色对象
     *
     * @param id 主键
     * @return  单个角色对象
     */
    Role getRole(Long id);

    /**
     * 保存角色对象
     *
     * @param role 角色对象
     * @return  保存影响的记录数
     */
    int saveRole(Role role);

    /**
     * 更新角色对象
     *
     * @param role 角色对象
     * @return  更新影响的记录数
     */
    int updateRole(Role role);

    /**
     * 根据主键删除角色
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    int deleteRole(Long id);

}