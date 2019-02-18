package com.chengxuunion.generator.business.roleurl.service;

import com.chengxuunion.generator.business.role.model.Role;
import com.chengxuunion.generator.common.model.PageResult;

import com.chengxuunion.generator.business.roleurl.model.RoleUrl;
import com.chengxuunion.generator.business.roleurl.model.request.RoleUrlPageParam;

import java.util.List;


/**
 * @Author youpanpan
 * @Description:    角色URL服务接口
 * @Date:创建时间: 2019-01-17 17:44:49
 * @Modified By:
 */
public interface RoleUrlService {

    /**
     * 查询角色URL分页列表
     *
     * @param roleUrlPageParam  参数对象
     * @return  角色URL列表
     */
    PageResult<RoleUrl> listRoleUrlPage(RoleUrlPageParam roleUrlPageParam);

    /**
     * 查询角色能访问的URL列表
     *
     * @param roleId
     * @return
     */
    List<RoleUrl> listRoleUrlByRoleId(Long roleId);

    /**
     * 根据主键查询单个角色URL对象
     *
     * @param id 主键
     * @return  单个角色URL对象
     */
    RoleUrl getRoleUrl(Long id);

    /**
     * 保存角色URL对象
     *
     * @param roleUrl 角色URL对象
     * @return  保存影响的记录数
     */
    int saveRoleUrl(RoleUrl roleUrl);

    /**
     * 批量保存角色URL
     *
     * @param role
     * @return
     */
    int saveRoleUrlBatch(Role role);

    /**
     * 更新角色URL对象
     *
     * @param roleUrl 角色URL对象
     * @return  更新影响的记录数
     */
    int updateRoleUrl(RoleUrl roleUrl);

    /**
     * 根据主键删除角色URL
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    int deleteRoleUrl(Long id);

}