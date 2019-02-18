package com.chengxuunion.generator.business.engine.dao;

import com.chengxuunion.generator.business.engine.model.Engine;
import com.chengxuunion.generator.business.engine.model.request.EnginePageParam;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author youpanpan
 * @Description:    模版解析引擎Dao
 * @Date:创建时间: 2019-01-15 09:38:46
 * @Modified By:
 */
@Repository
public interface EngineDao {

    /**
     * 查询模版解析引擎列表
     *
     * @param enginePageParam  参数对象
     * @return  模版解析引擎列表
     */
    List<Engine> listEngine(EnginePageParam enginePageParam);

    /**
     * 根据主键查询单个模版解析引擎对象
     *
     * @param id 主键
     * @return  单个模版解析引擎对象
     */
    Engine getEngine(@Param("id") Long id);

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
    int deleteEngine(@Param("id") Long id);
}