package com.chengxuunion.generator.business.user.dao;

import com.chengxuunion.generator.business.user.model.User;
import com.chengxuunion.generator.business.user.model.request.UserPageParam;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author youpanpan
 * @Description:    用户Dao
 * @Date:创建时间: 2019-01-16 13:54:23
 * @Modified By:
 */
@Repository
public interface UserDao {

    /**
     * 查询用户列表
     *
     * @param userPageParam  参数对象
     * @return  用户列表
     */
    List<User> listUser(UserPageParam userPageParam);

    /**
     * 根据主键查询单个用户对象
     *
     * @param id 主键
     * @return  单个用户对象
     */
    User getUser(@Param("id") Long id);

    /**
     * 根据用户名或邮箱查询用户信息
     *
     * @param account
     * @return
     */
    User getUserByUserNameOrEmail(@Param("account") String account);

    /**
     * 保存用户对象
     *
     * @param user 用户对象
     * @return  保存影响的记录数
     */
    int saveUser(User user);

    /**
     * 更新用户对象
     *
     * @param user 用户对象
     * @return  更新影响的记录数
     */
    int updateUser(User user);

    /**
     * 根据主键删除用户
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    int deleteUser(@Param("id") Long id);
}