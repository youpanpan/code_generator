package com.chengxuunion.generator.business.contextparam.service;

import com.chengxuunion.generator.common.model.PageResult;

import com.chengxuunion.generator.business.contextparam.model.ContextParam;
import com.chengxuunion.generator.business.contextparam.model.request.ContextParamPageParam;

import java.util.List;


/**
 * @Author youpanpan
 * @Description:    上下文参数服务接口
 * @Date:创建时间: 2019-01-08 16:11:09
 * @Modified By:
 */
public interface ContextParamService {

    /**
     * 查询上下文参数分页列表
     *
     * @param contextParamPageParam  参数对象
     * @return  上下文参数列表
     */
    PageResult<ContextParam> listContextParamPage(ContextParamPageParam contextParamPageParam);

    /**
     * 查询指定上下文已启用的参数列表
     *
     * @param contextId
     * @return
     */
    List<ContextParam> listContextParamEnableByContextId(Long contextId);

    /**
     * 根据主键查询单个上下文参数对象
     *
     * @param id 主键
     * @return  单个上下文参数对象
     */
    ContextParam getContextParam(Long id);

    /**
     * 保存上下文参数对象
     *
     * @param contextParam 上下文参数对象
     * @return  保存影响的记录数
     */
    int saveContextParam(ContextParam contextParam);

    /**
     * 更新上下文参数对象
     *
     * @param contextParam 上下文参数对象
     * @return  更新影响的记录数
     */
    int updateContextParam(ContextParam contextParam);

    /**
     * 根据主键删除上下文参数
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    int deleteContextParam(Long id);

}