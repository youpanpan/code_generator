package com.chengxuunion.generator.business.codegenerate.service;

import com.chengxuunion.generator.business.codegenerate.model.request.GenerateCodeParam;
import com.chengxuunion.generator.common.model.PageResult;

import com.chengxuunion.generator.business.codegenerate.model.CodeGenerate;
import com.chengxuunion.generator.business.codegenerate.model.request.CodeGeneratePageParam;

import javax.servlet.http.HttpServletResponse;


/**
 * @Author youpanpan
 * @Description:    代码生成记录服务接口
 * @Date:创建时间: 2019-01-10 14:49:05
 * @Modified By:
 */
public interface CodeGenerateService {

    /**
     * 查询代码生成记录分页列表
     *
     * @param codeGeneratePageParam  参数对象
     * @return  代码生成记录列表
     */
    PageResult<CodeGenerate> listCodeGeneratePage(CodeGeneratePageParam codeGeneratePageParam);

    /**
     * 根据主键查询单个代码生成记录对象
     *
     * @param id 主键
     * @return  单个代码生成记录对象
     */
    CodeGenerate getCodeGenerate(Long id);

    /**
     * 保存代码生成记录对象
     *
     * @param codeGenerate 代码生成记录对象
     * @return  保存影响的记录数
     */
    int saveCodeGenerate(CodeGenerate codeGenerate);

    /**
     * 更新代码生成记录对象
     *
     * @param codeGenerate 代码生成记录对象
     * @return  更新影响的记录数
     */
    int updateCodeGenerate(CodeGenerate codeGenerate);

    /**
     * 根据主键删除代码生成记录
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    int deleteCodeGenerate(Long id);

    /**
     * 生成代码
     *
     * @param generateCodeParam
     * @return
     */
    Boolean generateCode(GenerateCodeParam generateCodeParam) ;

    /**
     * 下载代码文件
     *
     * @param id
     * @param response
     * @return
     */
    Boolean downloadCode(Long id, HttpServletResponse response);
}