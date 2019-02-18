package com.chengxuunion.generator.business.engine.service;

import com.chengxuunion.generator.common.model.PageResult;

import com.chengxuunion.generator.business.engine.model.Engine;
import com.chengxuunion.generator.business.engine.model.request.EnginePageParam;

import java.util.List;


/**
 * @Author youpanpan
 * @Description:    模版解析引擎服务接口
 * @Date:创建时间: 2019-01-15 09:38:46
 * @Modified By:
 */
public interface EngineService {

    /**
     * 查询模版解析引擎分页列表
     *
     * @param enginePageParam  参数对象
     * @return  模版解析引擎列表
     */
    PageResult<Engine> listEnginePage(EnginePageParam enginePageParam);

    /**
     * 查询所有启用的模版引擎
     *
     * @return
     */
    List<Engine> listEngineEnable();

    /**
     * 根据主键查询单个模版解析引擎对象
     *
     * @param id 主键
     * @return  单个模版解析引擎对象
     */
    Engine getEngine(Long id);

    /**
     * 保存模版解析引擎对象
     *
     * @param engine 模版解析引擎对象
     * @return  保存影响的记录数
     */
    int saveEngine(Engine engine);

    /**
     * 更新模版解析引擎对象
     *
     * @param engine 模版解析引擎对象
     * @return  更新影响的记录数
     */
    int updateEngine(Engine engine);

    /**
     * 根据主键删除模版解析引擎
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    int deleteEngine(Long id);

}