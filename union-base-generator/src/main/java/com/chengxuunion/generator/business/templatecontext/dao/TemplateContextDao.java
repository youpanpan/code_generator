package com.chengxuunion.generator.business.templatecontext.dao;

import com.chengxuunion.generator.business.templatecontext.model.TemplateContext;
import com.chengxuunion.generator.business.templatecontext.model.request.TemplateContextPageParam;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author youpanpan
 * @Description:    模版依赖的上下文Dao
 * @Date:创建时间: 2019-01-15 11:49:27
 * @Modified By:
 */
@Repository
public interface TemplateContextDao {

    /**
     * 查询模版依赖的上下文列表
     *
     * @param templateContextPageParam  参数对象
     * @return  模版依赖的上下文列表
     */
    List<TemplateContext> listTemplateContext(TemplateContextPageParam templateContextPageParam);

    /**
     * 查询指定模版ID依赖的上下文列表
     *
     * @param templateId
     * @return
     */
    List<TemplateContext> listTemplateContextByTemplateId(@Param("templateId") Long templateId);

    /**
     * 根据主键查询单个模版依赖的上下文对象
     *
     * @param id 主键
     * @return  单个模版依赖的上下文对象
     */
    TemplateContext getTemplateContext(@Param("id") Long id);

    /**
     * 保存模版依赖的上下文对象
     *
     * @param templateContext 模版依赖的上下文对象
     * @return  保存影响的记录数
     */
    int saveTemplateContext(TemplateContext templateContext);

    /**
     * 批量保存模版依赖的上下文对象
     *
     * @param templateContextList
     * @return
     */
    int saveTemplateContextBatch(List<TemplateContext> templateContextList);

    /**
     * 更新模版依赖的上下文对象
     *
     * @param templateContext 模版依赖的上下文对象
     * @return  更新影响的记录数
     */
    int updateTemplateContext(TemplateContext templateContext);

    /**
     * 根据主键删除模版依赖的上下文
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    int deleteTemplateContext(@Param("id") Long id);

    /**
     * 删除指定模版ID依赖的上下文
     *
     * @param tempalteId    模版ID
     * @return
     */
    int deleteTemplateContextByTemplateId(@Param("templateId") Long tempalteId);
}