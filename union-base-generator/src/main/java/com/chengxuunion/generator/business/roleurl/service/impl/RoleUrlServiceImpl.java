package com.chengxuunion.generator.business.roleurl.service.impl;

import com.chengxuunion.generator.business.role.model.Role;
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

import com.chengxuunion.generator.business.roleurl.model.RoleUrl;
import com.chengxuunion.generator.business.roleurl.service.RoleUrlService;
import com.chengxuunion.generator.business.roleurl.dao.RoleUrlDao;
import com.chengxuunion.generator.business.roleurl.model.request.RoleUrlPageParam;


/**
 * @Author youpanpan
 * @Description:    角色URL服务实现
 * @Date:创建时间: 2019-01-17 17:44:49
 * @Modified By:
 */
@Service
public class RoleUrlServiceImpl implements RoleUrlService {

    @Autowired
    private RoleUrlDao roleUrlDao;

    @Override
    public PageResult<RoleUrl> listRoleUrlPage(RoleUrlPageParam roleUrlPageParam) {
        PageHelper.startPage(roleUrlPageParam.getPageNum() , roleUrlPageParam.getPageSize());
        List<RoleUrl> roleUrlList = roleUrlDao.listRoleUrl(roleUrlPageParam);
        //得到分页的结果对象
        PageInfo<RoleUrl> pageInfo = new PageInfo<>(roleUrlList);

        return new PageResult<RoleUrl>(roleUrlPageParam, pageInfo.getTotal(), roleUrlList);
    }

    @Override
    public List<RoleUrl> listRoleUrlByRoleId(Long roleId) {
        return roleUrlDao.listRoleUrlByRoleId(roleId);
    }

    /**
     * 根据主键查询单个角色URL对象
     *
     * @param id 主键
     * @return  单个角色URL对象
     */
    @Override
    public RoleUrl getRoleUrl(Long id) {
        return roleUrlDao.getRoleUrl(id);
    }

    /**
     * 保存角色URL对象
     *
     * @param roleUrl 角色URL对象
     * @return  保存影响的记录数
     */
    @Override
    public int saveRoleUrl(RoleUrl roleUrl) {
        roleUrl.setId(IdGenerator.getInstance().nextId());
        Date nowDate = new Date();
        roleUrl.setCreateDate(nowDate);
        roleUrl.setCreateUserId(SessionUtils.getUser().getId());
        return roleUrlDao.saveRoleUrl(roleUrl);
    }

    @Override
    public int saveRoleUrlBatch(Role role) {
        roleUrlDao.deleteRoleUrlByRoleId(role.getId());
        if (CollectionUtils.isNotEmpty(role.getRoleUrlList())) {
            Date nowDate = new Date();
            for (RoleUrl roleUrl : role.getRoleUrlList()) {
                roleUrl.setId(IdGenerator.getInstance().nextId());
                roleUrl.setRoleId(role.getId());
                roleUrl.setCreateDate(nowDate);
            }
            roleUrlDao.saveRoleUrlBatch(role.getRoleUrlList());
        }

        return 1;
    }

    /**
     * 更新角色URL对象
     *
     * @param roleUrl 角色URL对象
     * @return  更新影响的记录数
     */
    @Override
    public int updateRoleUrl(RoleUrl roleUrl) {
        return  roleUrlDao.updateRoleUrl(roleUrl);
    }

    /**
     * 根据主键删除角色URL
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    @Override
    public int deleteRoleUrl(Long id) {
        return  roleUrlDao.deleteRoleUrl(id);
    }

}

