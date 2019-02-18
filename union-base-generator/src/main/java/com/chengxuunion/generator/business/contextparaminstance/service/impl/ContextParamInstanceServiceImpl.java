package com.chengxuunion.generator.business.contextparaminstance.service.impl;

import com.chengxuunion.generator.business.contextparamvalue.model.ContextParamValue;
import com.chengxuunion.generator.business.contextparamvalue.service.ContextParamValueService;
import com.chengxuunion.generator.common.constant.Constants;
import com.chengxuunion.generator.common.util.SessionUtils;
import com.chengxuunion.util.id.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chengxuunion.generator.common.model.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

import com.chengxuunion.generator.business.contextparaminstance.model.ContextParamInstance;
import com.chengxuunion.generator.business.contextparaminstance.service.ContextParamInstanceService;
import com.chengxuunion.generator.business.contextparaminstance.dao.ContextParamInstanceDao;
import com.chengxuunion.generator.business.contextparaminstance.model.request.ContextParamInstancePageParam;


/**
 * @Author youpanpan
 * @Description:    上下文参数配置实例服务实现
 * @Date:创建时间: 2019-01-08 17:31:52
 * @Modified By:
 */
@Service
public class ContextParamInstanceServiceImpl implements ContextParamInstanceService {

    @Autowired
    private ContextParamInstanceDao contextParamInstanceDao;

    @Autowired
    private ContextParamValueService contextParamValueService;

    @Override
    public PageResult<ContextParamInstance> listContextParamInstancePage(ContextParamInstancePageParam contextParamInstancePageParam) {
        PageHelper.startPage(contextParamInstancePageParam.getPageNum() , contextParamInstancePageParam.getPageSize());

        List<ContextParamInstance> contextParamInstanceList = null;
        if (SessionUtils.isAdmin()) {
            contextParamInstanceList = contextParamInstanceDao.listContextParamInstance(contextParamInstancePageParam);
        } else {
            contextParamInstancePageParam.setUserId(SessionUtils.getUser().getId());
            contextParamInstanceList = contextParamInstanceDao.listContextParamInstanceByUser(contextParamInstancePageParam);
        }

        //得到分页的结果对象
        PageInfo<ContextParamInstance> pageInfo = new PageInfo<>(contextParamInstanceList);

        return new PageResult<>(contextParamInstancePageParam, pageInfo.getTotal(), contextParamInstanceList);
    }

    @Override
    public List<ContextParamInstance> listContextParamInstanceByContextId(Long contextId) {
        ContextParamInstancePageParam contextParamInstancePageParam = new ContextParamInstancePageParam();
        contextParamInstancePageParam.setContextId(contextId);
        contextParamInstancePageParam.setStatus(Constants.ENABLE);
        if (SessionUtils.isAdmin()) {
            return contextParamInstanceDao.listContextParamInstance(contextParamInstancePageParam);
        } else {
            contextParamInstancePageParam.setUserId(SessionUtils.getUser().getId());
            return contextParamInstanceDao.listContextParamInstanceByUser(contextParamInstancePageParam);
        }
    }

    /**
     * 根据主键查询单个上下文参数配置实例对象
     *
     * @param id 主键
     * @return  单个上下文参数配置实例对象
     */
    @Override
    public ContextParamInstance getContextParamInstance(Long id) {
        return contextParamInstanceDao.getContextParamInstance(id);
    }

    /**
     * 保存上下文参数配置实例对象
     *
     * @param contextParamInstance 上下文参数配置实例对象
     * @return  保存影响的记录数
     */
    @Override
    public int saveContextParamInstance(ContextParamInstance contextParamInstance) {
        contextParamInstance.setId(IdGenerator.getInstance().nextId());
        Date nowDate = new Date();
        contextParamInstance.setCreateDate(nowDate);
        contextParamInstance.setUpdateDate(nowDate);

        Long userId = SessionUtils.getUser().getId();
        contextParamInstance.setCreateUserId(userId);

        // 生成实例时默认生成默认的参数值
        List<ContextParamValue> contextParamValueList = contextParamValueService.listContextParamValueEnable(contextParamInstance.getContextId(), null);
        for (ContextParamValue contextParamValue : contextParamValueList) {
            contextParamValue.setParamValue(contextParamValue.getContextParam().getParamDefaultValue());
            contextParamValue.setInstanceId(contextParamInstance.getId());
            contextParamValue.setCreateUserId(userId);
        }
        contextParamValueService.saveContextParamValueBatch(contextParamValueList);

        return contextParamInstanceDao.saveContextParamInstance(contextParamInstance);
    }

    /**
     * 更新上下文参数配置实例对象
     *
     * @param contextParamInstance 上下文参数配置实例对象
     * @return  更新影响的记录数
     */
    @Override
    public int updateContextParamInstance(ContextParamInstance contextParamInstance) {
        checkAuth(contextParamInstance.getId());
        contextParamInstance.setUpdateDate(new Date());
        return  contextParamInstanceDao.updateContextParamInstance(contextParamInstance);
    }

    /**
     * 根据主键删除上下文参数配置实例
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    @Override
    public int deleteContextParamInstance(Long id) {
        checkAuth(id);
        contextParamValueService.deleteContextParamValueByInstanceId(id);
        return  contextParamInstanceDao.deleteContextParamInstance(id);
    }

    /**
     * 判断当前用户是否有权限操作
     *
     * @param id
     */
    private void checkAuth(Long id) {
        if (!SessionUtils.isAdmin()) {
            Long createUserId = getContextParamInstance(id).getCreateUserId();
            if (!SessionUtils.getUser().getId().equals(createUserId)) {
                throw new RuntimeException("参数实例不是本人创建，操作失败！");
            }
        }
    }
}

