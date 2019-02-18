package com.chengxuunion.generator.business.role.dao;

import com.chengxuunion.generator.business.role.model.Role;
import com.chengxuunion.generator.business.role.model.request.RolePageParam;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author youpanpan
 * @Description:    角色Dao
 * @Date:创建时间: 2019-01-16 17:02:24
 * @Modified By:
 */
@Repository
public interface RoleDao {

    /**
     * 查询角色列表
     *
     * @param rolePageParam  参数对象
     * @return  角色列表
     */
    List<Role> listRole(RolePageParam rolePageParam);

    /**
     * 根据主键查询单个角色对象
     *
     * @param id 主键
     * @return  单个角色对象
     */
    Role getRole(@Param("id") Long id);

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
    int deleteRole(@Param("id") Long id);
}