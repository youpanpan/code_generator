package com.chengxuunion.generator.business.userrole.dao;

import com.chengxuunion.generator.business.userrole.model.UserRole;
import com.chengxuunion.generator.business.userrole.model.request.UserRolePageParam;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author youpanpan
 * @Description:    用户角色Dao
 * @Date:创建时间: 2019-01-17 16:59:14
 * @Modified By:
 */
@Repository
public interface UserRoleDao {

    /**
     * 查询用户角色列表
     *
     * @param userRolePageParam  参数对象
     * @return  用户角色列表
     */
    List<UserRole> listUserRole(UserRolePageParam userRolePageParam);

    /**
     * 查询用户角色列表
     *
     * @param userId
     * @return
     */
    List<UserRole> listUserRoleByUserId(@Param("userId") Long userId);

    /**
     * 根据主键查询单个用户角色对象
     *
     * @param id 主键
     * @return  单个用户角色对象
     */
    UserRole getUserRole(@Param("id") Long id);

    /**
     * 保存用户角色对象
     *
     * @param userRole 用户角色对象
     * @return  保存影响的记录数
     */
    int saveUserRole(UserRole userRole);

    /**
     * 批量保存用户角色
     *
     * @param userRoleList
     * @return
     */
    int saveUserRoleBatch(List<UserRole> userRoleList);

    /**
     * 更新用户角色对象
     *
     * @param userRole 用户角色对象
     * @return  更新影响的记录数
     */
    int updateUserRole(UserRole userRole);

    /**
     * 根据主键删除用户角色
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    int deleteUserRole(@Param("id") Long id);

    /**
     * 删除用户的权限
     *
     * @param userId
     * @return
     */
    int deleteUserRoleByUserId(@Param("userId") Long userId);
}