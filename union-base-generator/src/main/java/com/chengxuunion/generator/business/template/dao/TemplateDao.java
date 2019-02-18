package com.chengxuunion.generator.business.template.dao;

import com.chengxuunion.generator.business.template.model.Template;
import com.chengxuunion.generator.business.template.model.request.TemplatePageParam;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author youpanpan
 * @Description:    模版Dao
 * @Date:创建时间: 2019-01-09 14:04:49
 * @Modified By:
 */
@Repository
public interface TemplateDao {

    /**
     * 查询模版列表
     *
     * @param templatePageParam  参数对象
     * @return  模版列表
     */
    List<Template> listTemplate(TemplatePageParam templatePageParam);

    /**
     * 查询当前登录用户所能访问的模版列表
     *
     * @param templatePageParam
     * @return
     */
    List<Template> listTemplateByUser(TemplatePageParam templatePageParam);

    /**
     * 根据主键查询单个模版对象
     *
     * @param id 主键
     * @return  单个模版对象
     */
    Template getTemplate(@Param("id") Long id);

    /**
     * 保存模版对象
     *
     * @param template 模版对象
     * @return  保存影响的记录数
     */
    int saveTemplate(Template template);

    /**
     * 更新模版对象
     *
     * @param template 模版对象
     * @return  更新影响的记录数
     */
    int updateTemplate(Template template);

    /**
     * 根据主键删除模版
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    int deleteTemplate(@Param("id") Long id);
}