package com.chengxuunion.generator.business.context.service;

import com.chengxuunion.generator.common.model.PageResult;

import com.chengxuunion.generator.business.context.model.Context;
import com.chengxuunion.generator.business.context.model.request.ContextPageParam;

import java.util.List;

/**
 * @Author youpanpan
 * @Description:    上下文实现配置服务接口
 * @Date:创建时间: 2019-01-07 17:43:15
 * @Modified By:
 */
public interface ContextService {

    /**
     * 查询上下文实现配置分页列表
     *
     * @param contextPageParam  参数
     * @return  上下文实现配置列表
     */
    PageResult<Context> listContextPage(ContextPageParam contextPageParam);

    /**
     * 查询所有启用的上下文
     *
     * @return
     */
    List<Context> listContextEnable();

    /**
     * 根据主键查询单个上下文实现配置对象
     *
     * @param id 主键
     * @return  单个上下文实现配置对象
     */
    Context getContext(Long id);

    /**
     * 保存上下文实现配置对象
     *
     * @param context 上下文实现配置对象
     * @return  保存影响的记录数
     */
    int saveContext(Context context);

    /**
     * 更新上下文实现配置对象
     *
     * @param context 上下文实现配置对象
     * @return  更新影响的记录数
     */
    int updateContext(Context context);

    /**
     * 根据主键删除上下文实现配置
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    int deleteContext(Long id);

}