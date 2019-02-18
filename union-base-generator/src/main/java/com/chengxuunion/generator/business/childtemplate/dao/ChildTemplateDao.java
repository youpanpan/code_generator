package com.chengxuunion.generator.business.childtemplate.dao;

import com.chengxuunion.generator.business.childtemplate.model.ChildTemplate;
import com.chengxuunion.generator.business.childtemplate.model.request.ChildTemplatePageParam;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author youpanpan
 * @Description:    子模版Dao
 * @Date:创建时间: 2019-01-09 17:25:12
 * @Modified By:
 */
@Repository
public interface ChildTemplateDao {

    /**
     * 查询子模版列表
     *
     * @param childTemplatePageParam  参数对象
     * @return  子模版列表
     */
    List<ChildTemplate> listChildTemplate(ChildTemplatePageParam childTemplatePageParam);

    /**
     * 根据父模版ID查询子模版列表
     *
     * @param parentTemplateId
     * @return
     */
    List<ChildTemplate> listChildTemplateByParentId(@Param("parentTemplateId") Long parentTemplateId);

    /**
     * 根据主键查询单个子模版对象
     *
     * @param id 主键
     * @return  单个子模版对象
     */
    ChildTemplate getChildTemplate(@Param("id") Long id);

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
     * 批量更新子模版对象
     *
     * @param childTemplateList
     * @return
     */
    int updateChildTemplateBatch(List<ChildTemplate> childTemplateList);

    /**
     * 根据主键删除子模版
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    int deleteChildTemplate(@Param("id") Long id);

    /**
     * 根据父模版删除所有的子模版
     *
     * @param parentTemplateId
     * @return
     */
    int deleteChildTemplateByParentId(@Param("parentTemplateId") Long parentTemplateId);
}