package com.chengxuunion.generator.business.userproject.service;

import com.chengxuunion.generator.common.model.PageResult;

import com.chengxuunion.generator.business.userproject.model.UserProject;
import com.chengxuunion.generator.business.userproject.model.request.UserProjectPageParam;

import java.util.List;


/**
 * @Author youpanpan
 * @Description:    用户项目组服务接口
 * @Date:创建时间: 2019-01-17 14:52:29
 * @Modified By:
 */
public interface UserProjectService {

    /**
     * 查询用户项目组分页列表
     *
     * @param userProjectPageParam  参数对象
     * @return  用户项目组列表
     */
    PageResult<UserProject> listUserProjectPage(UserProjectPageParam userProjectPageParam);

    /**
     * 查询项目下的所有用户
     *
     * @param projectId 项目ID
     * @return
     */
    List<UserProject> listUserProjectByProjectId(Long projectId);

    /**
     * 根据主键查询单个用户项目组对象
     *
     * @param id 主键
     * @return  单个用户项目组对象
     */
    UserProject getUserProject(Long id);

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
    int deleteUserProject(Long id);

}