package com.chengxuunion.generator.business.project.service.impl;

import com.chengxuunion.generator.common.util.SessionUtils;
import com.chengxuunion.util.id.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chengxuunion.generator.common.model.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Date;

import com.chengxuunion.generator.business.project.model.Project;
import com.chengxuunion.generator.business.project.service.ProjectService;
import com.chengxuunion.generator.business.project.dao.ProjectDao;
import com.chengxuunion.generator.business.project.model.request.ProjectPageParam;


/**
 * @Author youpanpan
 * @Description:    项目组服务实现
 * @Date:创建时间: 2019-01-17 11:46:59
 * @Modified By:
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Override
    public PageResult<Project> listProjectPage(ProjectPageParam projectPageParam) {
        PageHelper.startPage(projectPageParam.getPageNum() , projectPageParam.getPageSize());
        List<Project> projectList = projectDao.listProject(projectPageParam);
        //得到分页的结果对象
        PageInfo<Project> pageInfo = new PageInfo<>(projectList);

        return new PageResult<Project>(projectPageParam, pageInfo.getTotal(), projectList);
    }

    /**
     * 根据主键查询单个项目组对象
     *
     * @param id 主键
     * @return  单个项目组对象
     */
    @Override
    public Project getProject(Long id) {
        return projectDao.getProject(id);
    }

    /**
     * 保存项目组对象
     *
     * @param project 项目组对象
     * @return  保存影响的记录数
     */
    @Override
    public int saveProject(Project project) {
        project.setId(IdGenerator.getInstance().nextId());
        Date nowDate = new Date();
        project.setCreateDate(nowDate);
        project.setUpdateDate(nowDate);
        project.setCreateUserId(SessionUtils.getUser().getId());
        return projectDao.saveProject(project);
    }

    /**
     * 更新项目组对象
     *
     * @param project 项目组对象
     * @return  更新影响的记录数
     */
    @Override
    public int updateProject(Project project) {
        project.setUpdateDate(new Date());
        return  projectDao.updateProject(project);
    }

    /**
     * 根据主键删除项目组
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    @Override
    public int deleteProject(Long id) {
        return  projectDao.deleteProject(id);
    }

}

