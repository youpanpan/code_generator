package com.chengxuunion.generator.business.contextparamvalue.service;

import com.chengxuunion.generator.business.contextparamvalue.model.ContextParamValue;

import java.util.List;


/**
 * @Author youpanpan
 * @Description:    上下参数实例配置值服务接口
 * @Date:创建时间: 2019-01-09 09:52:16
 * @Modified By:
 */
public interface ContextParamValueService {

    /**
     * 查询上下文参数启用的配置的实例值
     *
     * @param contextId 上下文ID
     * @param instanceId 参数实例ID
     * @return
     */
    List<ContextParamValue> listContextParamValueEnable(Long contextId, Long instanceId);

    /**
     * 根据主键查询单个上下参数实例配置值对象
     *
     * @param id 主键
     * @return  单个上下参数实例配置值对象
     */
    ContextParamValue getContextParamValue(Long id);

    /**
     * 保存上下参数实例配置值对象
     *
     * @param contextParamValue 上下参数实例配置值对象
     * @return  保存影响的记录数
     */
    int saveContextParamValue(ContextParamValue contextParamValue);

    /**
     * 批量保存上下文参数实例配置值对象
     *
     * @param contextParamValueList
     * @return
     */
    int saveContextParamValueBatch(List<ContextParamValue> contextParamValueList);

    /**
     * 更新上下参数实例配置值对象
     *
     * @param contextParamValue 上下参数实例配置值对象
     * @return  更新影响的记录数
     */
    int updateContextParamValue(ContextParamValue contextParamValue);

    /**
     * 根据主键删除上下参数实例配置值
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    int deleteContextParamValue(Long id);

    /**
     * 根据参数实例ID删除上下文参数配置值
     *
     * @param instanceId
     * @return
     */
    int deleteContextParamValueByInstanceId(Long instanceId);

}