package com.chengxuunion.generator.business.project.service;

import com.chengxuunion.generator.common.model.PageResult;

import com.chengxuunion.generator.business.project.model.Project;
import com.chengxuunion.generator.business.project.model.request.ProjectPageParam;


/**
 * @Author youpanpan
 * @Description:    项目组服务接口
 * @Date:创建时间: 2019-01-17 11:46:59
 * @Modified By:
 */
public interface ProjectService {

    /**
     * 查询项目组分页列表
     *
     * @param projectPageParam  参数对象
     * @return  项目组列表
     */
    PageResult<Project> listProjectPage(ProjectPageParam projectPageParam);

    /**
     * 根据主键查询单个项目组对象
     *
     * @param id 主键
     * @return  单个项目组对象
     */
    Project getProject(Long id);

    /**
     * 保存项目组对象
     *
     * @param project 项目组对象
     * @return  保存影响的记录数
     */
    int saveProject(Project project);

    /**
     * 更新项目组对象
     *
     * @param project 项目组对象
     * @return  更新影响的记录数
     */
    int updateProject(Project project);

    /**
     * 根据主键删除项目组
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    int deleteProject(Long id);

}