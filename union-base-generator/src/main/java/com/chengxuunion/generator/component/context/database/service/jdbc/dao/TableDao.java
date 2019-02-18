package com.chengxuunion.generator.component.context.database.service.jdbc.dao;

import com.chengxuunion.generator.component.context.database.service.jdbc.exception.DataBaseException;
import com.chengxuunion.generator.component.context.database.service.jdbc.model.JdbcProperty;
import com.chengxuunion.generator.component.context.database.service.jdbc.model.TableColumn;
import com.chengxuunion.util.database.JdbcUtils;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-14 16:48
 * @Modified By:
 */
@Repository
public class TableDao {

    /**
     * 查询指定表的所有字段
     *
     * @param jdbcProperty 数据库连接配置
     * @param databaseName 数据库名称
     * @param tableName 表名
     * @return
     * @throws DataBaseException
     */
    public List<TableColumn> listTableColumn(JdbcProperty jdbcProperty, String databaseName, String tableName) throws DataBaseException {
        JdbcUtils jdbcUtils = new JdbcUtils(jdbcProperty.getDriverClassName(), jdbcProperty.getUrl(), jdbcProperty.getUserName(), jdbcProperty.getPassword());

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" SELECT ");
        sqlBuilder.append(" col.table_name AS \"tableName\", col.column_name AS \"columnName\", col.data_type AS \"dataType\", ");
        sqlBuilder.append(" col.character_maximum_length AS \"columnLength\", col.is_nullable AS \"isNullable\", col.column_comment AS \"comment\", ");
        sqlBuilder.append(" CASE ");
        sqlBuilder.append(" WHEN key_usage.column_name IS NOT NULL THEN '1' ");
        sqlBuilder.append(" ELSE '0' ");
        sqlBuilder.append(" END AS \"isPrimary\"");
        sqlBuilder.append(" FROM information_schema.columns col ");
        sqlBuilder.append(" LEFT JOIN INFORMATION_SCHEMA.`KEY_COLUMN_USAGE`  key_usage  ");
        sqlBuilder.append(" ON col.`COLUMN_NAME` = key_usage.`COLUMN_NAME` ");
        sqlBuilder.append(" AND col.`TABLE_NAME` = key_usage.`TABLE_NAME` AND col.`TABLE_SCHEMA` = key_usage.`TABLE_SCHEMA`");
        sqlBuilder.append(" WHERE col.table_name = ? AND col.table_schema = ?");

        List<Object> paramList = new LinkedList<Object>();
        paramList.add(tableName);
        paramList.add(databaseName);

        return jdbcUtils.selectList(sqlBuilder.toString(), paramList,  TableColumn.class);
    }

}
