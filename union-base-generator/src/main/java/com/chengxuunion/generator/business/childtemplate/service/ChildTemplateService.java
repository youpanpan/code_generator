package com.chengxuunion.generator.business.childtemplate.service;

import com.chengxuunion.generator.common.model.PageResult;

import com.chengxuunion.generator.business.childtemplate.model.ChildTemplate;
import com.chengxuunion.generator.business.childtemplate.model.request.ChildTemplatePageParam;

import java.util.List;


/**
 * @Author youpanpan
 * @Description:    子模版服务接口
 * @Date:创建时间: 2019-01-09 17:25:12
 * @Modified By:
 */
public interface ChildTemplateService {

    /**
     * 查询子模版分页列表
     *
     * @param childTemplatePageParam  参数对象
     * @return  子模版列表
     */
    PageResult<ChildTemplate> listChildTemplatePage(ChildTemplatePageParam childTemplatePageParam);

    /**
     * 根据父模版ID查询子模版列表
     *
     * @param parentTemplateId
     * @return
     */
    List<ChildTemplate> listChildTemplateByParentId(Long parentTemplateId);

    /**
     * 根据主键查询单个子模版对象
     *
     * @param id 主键
     * @return  单个子模版对象
     */
    ChildTemplate getChildTemplate(Long id);

    /**
     * 保存子模版对象
     *
     * @param childTemplate 子模版对象
     * @return  保存影响的记录数
     */
    int saveChildTemplate(ChildTemplate childTemplate);

    /**
     * 批量保存字模版对象
     *
     * @param childTemplateList
     * @return
     */
    int saveChildTemplateBatch(List<ChildTemplate> childTemplateList);

    /**
     * 更新子模版对象
     *
     * @param childTemplate 子模版对象
     * @return  更新影响的记录数
     */
    int updateChildTemplate(ChildTemplate childTemplate);

    /**
     * 根据主键删除子模版
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    int deleteChildTemplate(Long id);

    /**
     * 根据父模版删除所有的子模版
     *
     * @param parentTemplateId
     * @return
     */
    int deleteChildTemplateByParentId(Long parentTemplateId);
}