package com.chengxuunion.generator.business.userproject.dao;

import com.chengxuunion.generator.business.userproject.model.UserProject;
import com.chengxuunion.generator.business.userproject.model.request.UserProjectPageParam;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author youpanpan
 * @Description:    用户项目组Dao
 * @Date:创建时间: 2019-01-17 14:52:29
 * @Modified By:
 */
@Repository
public interface UserProjectDao {

    /**
     * 查询用户项目组列表
     *
     * @param userProjectPageParam  参数对象
     * @return  用户项目组列表
     */
    List<UserProject> listUserProject(UserProjectPageParam userProjectPageParam);

    /**
     * 查询项目下的所有用户
     *
     * @param projectId 项目ID
     * @return
     */
    List<UserProject> listUserProjectByProjectId(@Param("projectId") Long projectId);

    /**
     * 根据主键查询单个用户项目组对象
     *
     * @param id 主键
     * @return  单个用户项目组对象
     */
    UserProject getUserProject(@Param("id") Long id);

    /**
     * 保存用户项目组对象
     *
     * @param userProject 用户项目组对象
     * @return  保存影响的记录数
     */
    int saveUserProject(UserProject userProject);

    /**
     * 批量保存用户项目组
     *
     * @param userProjectList
     * @return
     */
    int saveUserProjectBatch(List<UserProject> userProjectList);

    /**
     * 更新用户项目组对象
     *
     * @param userProject 用户项目组对象
     * @return  更新影响的记录数
     */
    int updateUserProject(UserProject userProject);

    /**
     * 根据主键删除用户项目组
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    int deleteUserProject(@Param("id") Long id);

    /**
     * 删除项目下的所有用户
     *
     * @param projectId 项目ID
     * @return
     */
    int deleteUserProjectByProjectId(@Param("projectId") Long projectId);
}