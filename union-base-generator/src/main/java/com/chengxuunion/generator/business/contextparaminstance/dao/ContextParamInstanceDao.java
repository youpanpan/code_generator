package com.chengxuunion.generator.business.contextparaminstance.dao;

import com.chengxuunion.generator.business.contextparaminstance.model.ContextParamInstance;
import com.chengxuunion.generator.business.contextparaminstance.model.request.ContextParamInstancePageParam;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author youpanpan
 * @Description:    上下文参数配置实例Dao
 * @Date:创建时间: 2019-01-08 17:31:52
 * @Modified By:
 */
@Repository
public interface ContextParamInstanceDao {

    /**
     * 查询上下文参数配置实例列表
     *
     * @param contextParamInstancePageParam  参数对象
     * @return  上下文参数配置实例列表
     */
    List<ContextParamInstance> listContextParamInstance(ContextParamInstancePageParam contextParamInstancePageParam);

    /**
     * 查询当前用户能访问的实例列表
     *
     * @param contextParamInstancePageParam
     * @return
     */
    List<ContextParamInstance> listContextParamInstanceByUser(ContextParamInstancePageParam contextParamInstancePageParam);

    /**
     * 根据主键查询单个上下文参数配置实例对象
     *
     * @param id 主键
     * @return  单个上下文参数配置实例对象
     */
    ContextParamInstance getContextParamInstance(@Param("id") Long id);

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
    int deleteContextParamInstance(@Param("id") Long id);
}