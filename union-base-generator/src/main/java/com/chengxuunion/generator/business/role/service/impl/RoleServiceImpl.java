package com.chengxuunion.generator.business.role.service.impl;

import com.chengxuunion.generator.common.util.SessionUtils;
import com.chengxuunion.util.id.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chengxuunion.generator.common.model.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Date;

import com.chengxuunion.generator.business.role.model.Role;
import com.chengxuunion.generator.business.role.service.RoleService;
import com.chengxuunion.generator.business.role.dao.RoleDao;
import com.chengxuunion.generator.business.role.model.request.RolePageParam;


/**
 * @Author youpanpan
 * @Description:    角色服务实现
 * @Date:创建时间: 2019-01-16 17:02:24
 * @Modified By:
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public PageResult<Role> listRolePage(RolePageParam rolePageParam) {
        PageHelper.startPage(rolePageParam.getPageNum() , rolePageParam.getPageSize());
        List<Role> roleList = roleDao.listRole(rolePageParam);
        //得到分页的结果对象
        PageInfo<Role> pageInfo = new PageInfo<>(roleList);

        return new PageResult<Role>(rolePageParam, pageInfo.getTotal(), roleList);
    }

    /**
     * 根据主键查询单个角色对象
     *
     * @param id 主键
     * @return  单个角色对象
     */
    @Override
    public Role getRole(Long id) {
        return roleDao.getRole(id);
    }

    /**
     * 保存角色对象
     *
     * @param role 角色对象
     * @return  保存影响的记录数
     */
    @Override
    public int saveRole(Role role) {
        role.setId(IdGenerator.getInstance().nextId());
        Date nowDate = new Date();
        role.setCreateDate(nowDate);
        role.setUpdateDate(nowDate);
        role.setCreateUserId(SessionUtils.getUser().getId());
        return roleDao.saveRole(role);
    }

    /**
     * 更新角色对象
     *
     * @param role 角色对象
     * @return  更新影响的记录数
     */
    @Override
    public int updateRole(Role role) {
        role.setUpdateDate(new Date());
        return  roleDao.updateRole(role);
    }

    /**
     * 根据主键删除角色
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    @Override
    public int deleteRole(Long id) {
        return  roleDao.deleteRole(id);
    }

}

