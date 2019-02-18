package com.chengxuunion.generator.business.codegenerate.dao;

import com.chengxuunion.generator.business.codegenerate.model.CodeGenerate;
import com.chengxuunion.generator.business.codegenerate.model.request.CodeGeneratePageParam;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author youpanpan
 * @Description:    代码生成记录Dao
 * @Date:创建时间: 2019-01-10 14:49:05
 * @Modified By:
 */
@Repository
public interface CodeGenerateDao {

    /**
     * 查询代码生成记录列表
     *
     * @param codeGeneratePageParam  参数对象
     * @return  代码生成记录列表
     */
    List<CodeGenerate> listCodeGenerate(CodeGeneratePageParam codeGeneratePageParam);

    /**
     * 查询当前登录用户所生成的代码
     *
     * @param codeGeneratePageParam
     * @return
     */
    List<CodeGenerate> listCodeGenerateByUser(CodeGeneratePageParam codeGeneratePageParam);

    /**
     * 根据主键查询单个代码生成记录对象
     *
     * @param id 主键
     * @return  单个代码生成记录对象
     */
    CodeGenerate getCodeGenerate(@Param("id") Long id);

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
    int deleteCodeGenerate(@Param("id") Long id);
}