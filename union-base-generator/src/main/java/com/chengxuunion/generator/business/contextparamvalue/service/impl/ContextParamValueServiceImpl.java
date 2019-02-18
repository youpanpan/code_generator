package com.chengxuunion.generator.business.contextparamvalue.service.impl;

import com.chengxuunion.generator.business.contextparaminstance.model.ContextParamInstance;
import com.chengxuunion.generator.business.contextparaminstance.service.ContextParamInstanceService;
import com.chengxuunion.generator.common.util.SessionUtils;
import com.chengxuunion.util.collection.CollectionUtils;
import com.chengxuunion.util.id.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.chengxuunion.generator.business.contextparamvalue.model.ContextParamValue;
import com.chengxuunion.generator.business.contextparamvalue.service.ContextParamValueService;
import com.chengxuunion.generator.business.contextparamvalue.dao.ContextParamValueDao;


/**
 * @Author youpanpan
 * @Description:    上下参数实例配置值服务实现
 * @Date:创建时间: 2019-01-09 09:52:16
 * @Modified By:
 */
@Service
public class ContextParamValueServiceImpl implements ContextParamValueService {

    @Autowired
    private ContextParamValueDao contextParamValueDao;

    @Autowired
    private ContextParamInstanceService contextParamInstanceService;

    @Override
    public List<ContextParamValue> listContextParamValueEnable(Long contextId, Long instanceId) {
        return contextParamValueDao.listContextParamValueEnable(contextId, instanceId);
    }

    /**
     * 根据主键查询单个上下参数实例配置值对象
     *
     * @param id 主键
     * @return  单个上下参数实例配置值对象
     */
    @Override
    public ContextParamValue getContextParamValue(Long id) {
        return contextParamValueDao.getContextParamValue(id);
    }

    /**
     * 保存上下参数实例配置值对象
     *
     * @param contextParamValue 上下参数实例配置值对象
     * @return  保存影响的记录数
     */
    @Override
    public int saveContextParamValue(ContextParamValue contextParamValue) {
        contextParamValue.setId(IdGenerator.getInstance().nextId());
        Date nowDate = new Date();
        contextParamValue.setCreateDate(nowDate);
        contextParamValue.setUpdateDate(nowDate);
        contextParamValue.setCreateUserId(SessionUtils.getUser().getId());
        return contextParamValueDao.saveContextParamValue(contextParamValue);
    }

    @Override
    public int saveContextParamValueBatch(List<ContextParamValue> contextParamValueList) {

        List<ContextParamValue> updateList = new ArrayList<>();
        List<ContextParamValue> insertList = new ArrayList<>();

        // 验证是否有权限操作
        if (CollectionUtils.isEmpty(contextParamValueList)) {
            return 1;
        }
        Long instanceId = contextParamValueList.get(0).getInstanceId();
        checkAuth(instanceId);

        Date nowDate = new Date();
        for (ContextParamValue contextParamValue : contextParamValueList) {
            if (contextParamValue.getId() == null) {
                contextParamValue.setId(IdGenerator.getInstance().nextId());
                contextParamValue.setCreateDate(nowDate);
                contextParamValue.setUpdateDate(nowDate);
                insertList.add(contextParamValue);
            } else {
                contextParamValue.setUpdateDate(nowDate);
                updateList.add(contextParamValue);
            }
        }

        if (CollectionUtils.isNotEmpty(updateList)) {
            contextParamValueDao.updateContextParamValueBatch(updateList);
        }

        if (CollectionUtils.isNotEmpty(insertList)) {
            contextParamValueDao.saveContextParamValueBatch(insertList);
        }

        return 1;
    }

    /**
     * 更新上下参数实例配置值对象
     *
     * @param contextParamValue 上下参数实例配置值对象
     * @return  更新影响的记录数
     */
    @Override
    public int updateContextParamValue(ContextParamValue contextParamValue) {
        contextParamValue.setUpdateDate(new Date());
        return  contextParamValueDao.updateContextParamValue(contextParamValue);
    }

    /**
     * 根据主键删除上下参数实例配置值
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    @Override
    public int deleteContextParamValue(Long id) {
        return  contextParamValueDao.deleteContextParamValue(id);
    }

    @Override
    public int deleteContextParamValueByInstanceId(Long instanceId) {
        return contextParamValueDao.deleteContextParamValueByInstanceId(instanceId);
    }

    /**
     * 判断当前用户是否有权限操作
     *
     * @param instanceId    参数实例ID
     */
    private void checkAuth(Long instanceId) {
        if (!SessionUtils.isAdmin()) {
            ContextParamInstance contextParamInstance = contextParamInstanceService.getContextParamInstance(instanceId);
            if (contextParamInstance != null) {
                Long createUserId = contextParamInstance.getCreateUserId();
                if (!SessionUtils.getUser().getId().equals(createUserId)) {
                    throw new RuntimeException("参数实例不是本人创建，操作失败！");
                }
            }
        }
    }
}

