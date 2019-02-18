package com.chengxuunion.generator.component.context.database.service.jdbc.service;

import com.chengxuunion.generator.component.context.database.service.jdbc.exception.DataBaseException;
import com.chengxuunion.generator.component.context.database.service.jdbc.model.JdbcProperty;
import com.chengxuunion.generator.component.context.database.service.jdbc.model.Table;

import java.util.List;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-14 16:49
 * @Modified By:
 */
public interface DataBaseService {

    /**
     * 查询指定数据库下的所有表
     *
     * @param jdbcProperty  数据库连接配置信息
     * @param databaseName  数据库名称
     * @return
     * @throws DataBaseException
     */
    List<Table> listTable(JdbcProperty jdbcProperty, String databaseName) throws DataBaseException;

    /**
     * 查询指定数据库下的某个表的描述
     *
     * @param jdbcProperty
     * @param databaseName
     * @return
     * @throws DataBaseException
     */
    Table getTable(JdbcProperty jdbcProperty, String databaseName, String tableName) throws DataBaseException;
}
