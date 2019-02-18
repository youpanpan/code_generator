package com.chengxuunion.generator.business.userrole.service.impl;

import com.chengxuunion.generator.business.user.model.User;
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

import com.chengxuunion.generator.business.userrole.model.UserRole;
import com.chengxuunion.generator.business.userrole.service.UserRoleService;
import com.chengxuunion.generator.business.userrole.dao.UserRoleDao;
import com.chengxuunion.generator.business.userrole.model.request.UserRolePageParam;


/**
 * @Author youpanpan
 * @Description:    用户角色服务实现
 * @Date:创建时间: 2019-01-17 16:59:14
 * @Modified By:
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public PageResult<UserRole> listUserRolePage(UserRolePageParam userRolePageParam) {
        PageHelper.startPage(userRolePageParam.getPageNum() , userRolePageParam.getPageSize());
        List<UserRole> userRoleList = userRoleDao.listUserRole(userRolePageParam);
        //得到分页的结果对象
        PageInfo<UserRole> pageInfo = new PageInfo<>(userRoleList);

        return new PageResult<UserRole>(userRolePageParam, pageInfo.getTotal(), userRoleList);
    }

    @Override
    public List<UserRole> listUserRoleByUserId(Long userId) {
        return userRoleDao.listUserRoleByUserId(userId);
    }

    /**
     * 根据主键查询单个用户角色对象
     *
     * @param id 主键
     * @return  单个用户角色对象
     */
    @Override
    public UserRole getUserRole(Long id) {
        return userRoleDao.getUserRole(id);
    }

    /**
     * 保存用户角色对象
     *
     * @param userRole 用户角色对象
     * @return  保存影响的记录数
     */
    @Override
    public int saveUserRole(UserRole userRole) {
        userRole.setId(IdGenerator.getInstance().nextId());
        Date nowDate = new Date();
        userRole.setCreateDate(nowDate);
        userRole.setCreateUserId(SessionUtils.getUser().getId());
        return userRoleDao.saveUserRole(userRole);
    }

    @Override
    public int saveUserRoleBatch(User user) {
        userRoleDao.deleteUserRoleByUserId(user.getId());

        if (CollectionUtils.isNotEmpty(user.getUserRoleList())) {
            Date nowDate = new Date();
            Long userId = SessionUtils.getUser().getId();
            for (UserRole userRole : user.getUserRoleList()) {
                userRole.setId(IdGenerator.getInstance().nextId());
                userRole.setCreateDate(nowDate);
                userRole.setCreateUserId(userId);
            }
            userRoleDao.saveUserRoleBatch(user.getUserRoleList());
        }
        return 1;
    }

    /**
     * 更新用户角色对象
     *
     * @param userRole 用户角色对象
     * @return  更新影响的记录数
     */
    @Override
    public int updateUserRole(UserRole userRole) {
        return  userRoleDao.updateUserRole(userRole);
    }

    /**
     * 根据主键删除用户角色
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    @Override
    public int deleteUserRole(Long id) {
        return  userRoleDao.deleteUserRole(id);
    }

}

