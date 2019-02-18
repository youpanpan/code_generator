package com.chengxuunion.generator.component.context.database.service.jdbc.service;

import com.chengxuunion.generator.component.context.database.service.jdbc.exception.DataBaseException;
import com.chengxuunion.generator.component.context.database.service.jdbc.model.JdbcProperty;
import com.chengxuunion.generator.component.context.database.service.jdbc.model.TableColumn;

import java.util.List;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-14 16:49
 * @Modified By:
 */
public interface TableService {

    /**
     * 查询指定表的所有字段
     *
     * @param jdbcProperty 数据库连接信息
     * @param databaseName 数据库名
     * @param tableName 表名
     * @return
     * @throws DataBaseException
     */
    List<TableColumn> listTableColumn(JdbcProperty jdbcProperty, String databaseName, String tableName) throws DataBaseException;
}
