package com.chengxuunion.generator.business.contextparaminstance.service;

import com.chengxuunion.generator.common.model.PageResult;

import com.chengxuunion.generator.business.contextparaminstance.model.ContextParamInstance;
import com.chengxuunion.generator.business.contextparaminstance.model.request.ContextParamInstancePageParam;

import java.util.List;


/**
 * @Author youpanpan
 * @Description:    上下文参数配置实例服务接口
 * @Date:创建时间: 2019-01-08 17:31:52
 * @Modified By:
 */
public interface ContextParamInstanceService {

    /**
     * 查询上下文参数配置实例分页列表
     *
     * @param contextParamInstancePageParam  参数对象
     * @return  上下文参数配置实例列表
     */
    PageResult<ContextParamInstance> listContextParamInstancePage(ContextParamInstancePageParam contextParamInstancePageParam);

    /**
     * 根据上下文ID查询上下文参数配置实例列表
     *
     * @param contextId
     * @return
     */
    List<ContextParamInstance> listContextParamInstanceByContextId(Long contextId);

    /**
     * 根据主键查询单个上下文参数配置实例对象
     *
     * @param id 主键
     * @return  单个上下文参数配置实例对象
     */
    ContextParamInstance getContextParamInstance(Long id);

    /**
     * 保存上下文参数配置实例对象
     *
     * @param contextParamInstance 上下文参数配置实例对象
     * @return  保存影响的记录数
     */
    int saveContextParamInstance(ContextParamInstance contextParamInstance);

    /**
     * 更新上下文参数配置实例对象
     *
     * @param contextParamInstance 上下文参数配置实例对象
     * @return  更新影响的记录数
     */
    int updateContextParamInstance(ContextParamInstance contextParamInstance);

    /**
     * 根据主键删除上下文参数配置实例
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    int deleteContextParamInstance(Long id);

}