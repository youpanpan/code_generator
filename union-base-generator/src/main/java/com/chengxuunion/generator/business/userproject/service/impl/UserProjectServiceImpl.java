package com.chengxuunion.generator.business.userproject.service.impl;

import com.chengxuunion.generator.common.util.SessionUtils;
import com.chengxuunion.util.collection.CollectionUtils;
import com.chengxuunion.util.id.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chengxuunion.generator.common.model.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Date;

import com.chengxuunion.generator.business.userproject.model.UserProject;
import com.chengxuunion.generator.business.userproject.service.UserProjectService;
import com.chengxuunion.generator.business.userproject.dao.UserProjectDao;
import com.chengxuunion.generator.business.userproject.model.request.UserProjectPageParam;


/**
 * @Author youpanpan
 * @Description:    用户项目组服务实现
 * @Date:创建时间: 2019-01-17 14:52:29
 * @Modified By:
 */
@Service
public class UserProjectServiceImpl implements UserProjectService {

    @Autowired
    private UserProjectDao userProjectDao;

    @Override
    public PageResult<UserProject> listUserProjectPage(UserProjectPageParam userProjectPageParam) {
        PageHelper.startPage(userProjectPageParam.getPageNum() , userProjectPageParam.getPageSize());
        List<UserProject> userProjectList = userProjectDao.listUserProject(userProjectPageParam);
        //得到分页的结果对象
        PageInfo<UserProject> pageInfo = new PageInfo<>(userProjectList);

        return new PageResult<UserProject>(userProjectPageParam, pageInfo.getTotal(), userProjectList);
    }

    @Override
    public List<UserProject> listUserProjectByProjectId(Long projectId) {
        return userProjectDao.listUserProjectByProjectId(projectId);
    }

    /**
     * 根据主键查询单个用户项目组对象
     *
     * @param id 主键
     * @return  单个用户项目组对象
     */
    @Override
    public UserProject getUserProject(Long id) {
        return userProjectDao.getUserProject(id);
    }

    /**
     * 保存用户项目组对象
     *
     * @param userProject 用户项目组对象
     * @return  保存影响的记录数
     */
    @Override
    public int saveUserProject(UserProject userProject) {
        userProject.setId(IdGenerator.getInstance().nextId());
        Date nowDate = new Date();
        userProject.setCreateDate(nowDate);
        userProject.setUpdateDate(nowDate);
        userProject.setCreateUserId(SessionUtils.getUser().getId());
        return userProjectDao.saveUserProject(userProject);
    }

    @Override
    public int saveUserProjectBatch(List<UserProject> userProjectList) {
        if (CollectionUtils.isEmpty(userProjectList)) {
            return 0;
        }

        Long projectId = userProjectList.get(0).getProjectId();
        userProjectDao.deleteUserProjectByProjectId(projectId);

        Date nowDate = new Date();
        Long userId = SessionUtils.getUser().getId();
        for (UserProject userProject : userProjectList) {
            userProject.setId(IdGenerator.getInstance().nextId());
            userProject.setCreateDate(nowDate);
            userProject.setUpdateDate(nowDate);
            userProject.setCreateUserId(userId);
        }
        userProjectDao.saveUserProjectBatch(userProjectList);
        return 1;
    }

    /**
     * 更新用户项目组对象
     *
     * @param userProject 用户项目组对象
     * @return  更新影响的记录数
     */
    @Override
    public int updateUserProject(UserProject userProject) {
        userProject.setUpdateDate(new Date());
        return  userProjectDao.updateUserProject(userProject);
    }

    /**
     * 根据主键删除用户项目组
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    @Override
    public int deleteUserProject(Long id) {
        return  userProjectDao.deleteUserProject(id);
    }

}

