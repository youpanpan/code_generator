package com.chengxuunion.generator.business.template.service;

import com.chengxuunion.generator.common.model.PageResult;

import com.chengxuunion.generator.business.template.model.Template;
import com.chengxuunion.generator.business.template.model.request.TemplatePageParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * @Author youpanpan
 * @Description:    模版服务接口
 * @Date:创建时间: 2019-01-09 14:04:49
 * @Modified By:
 */
public interface TemplateService {

    /**
     * 查询模版分页列表
     *
     * @param templatePageParam  参数对象
     * @return  模版列表
     */
    PageResult<Template> listTemplatePage(TemplatePageParam templatePageParam);

    /**
     * 根据主键查询单个模版对象
     *
     * @param id 主键
     * @return  单个模版对象
     */
    Template getTemplate(Long id);

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
    int deleteTemplate(Long id);

    /**
     * 上传模版文件
     *
     * @param file
     * @return
     */
    Map<String, Object> uploadTemplate(MultipartFile file);

    /**
     * 下载模版文件
     *
     * @param id
     * @param response
     * @return
     */
    Boolean downloadTemplate(Long id, HttpServletResponse response);


}