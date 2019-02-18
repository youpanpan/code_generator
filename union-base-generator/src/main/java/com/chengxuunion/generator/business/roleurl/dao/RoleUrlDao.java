package com.chengxuunion.generator.business.roleurl.dao;

import com.chengxuunion.generator.business.roleurl.model.RoleUrl;
import com.chengxuunion.generator.business.roleurl.model.request.RoleUrlPageParam;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author youpanpan
 * @Description:    角色URLDao
 * @Date:创建时间: 2019-01-17 17:44:49
 * @Modified By:
 */
@Repository
public interface RoleUrlDao {

    /**
     * 查询角色URL列表
     *
     * @param roleUrlPageParam  参数对象
     * @return  角色URL列表
     */
    List<RoleUrl> listRoleUrl(RoleUrlPageParam roleUrlPageParam);

    /**
     * 查询角色能访问的URL列表
     *
     * @param roleId
     * @return
     */
    List<RoleUrl> listRoleUrlByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据主键查询单个角色URL对象
     *
     * @param id 主键
     * @return  单个角色URL对象
     */
    RoleUrl getRoleUrl(@Param("id") Long id);

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
     * @param roleUrlList
     * @return
     */
    int saveRoleUrlBatch(List<RoleUrl> roleUrlList);

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
    int deleteRoleUrl(@Param("id") Long id);

    /**
     * 根据角色ID删除角色URL
     *
     * @param roleId
     * @return
     */
    int deleteRoleUrlByRoleId(@Param("roleId") Long roleId);
}