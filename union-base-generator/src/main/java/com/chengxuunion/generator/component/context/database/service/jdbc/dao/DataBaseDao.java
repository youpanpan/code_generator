package com.chengxuunion.generator.component.context.database.service.jdbc.dao;

import com.chengxuunion.generator.component.context.database.service.jdbc.exception.DataBaseException;
import com.chengxuunion.generator.component.context.database.service.jdbc.model.JdbcProperty;
import com.chengxuunion.generator.component.context.database.service.jdbc.model.Table;
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
public class DataBaseDao {

    /**
     * 查询指定数据库下的所有表
     *
     * @param jdbcProperty  数据库连接配置
     * @param databaseName  数据库名称
     * @return
     * @throws DataBaseException
     */
    public List<Table> listTable(JdbcProperty jdbcProperty, String databaseName) {
        JdbcUtils jdbcUtils = new JdbcUtils(jdbcProperty.getDriverClassName(), jdbcProperty.getUrl(), jdbcProperty.getUserName(), jdbcProperty.getPassword());

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" SELECT ");
        sqlBuilder.append(" table_name AS \"tableName\", table_comment AS \"comment\", ");
        sqlBuilder.append(" `engine` AS \"engine\", table_schema AS \"databaseName\", create_time AS \"createDate\" ");
        sqlBuilder.append(" FROM information_schema.tables ");
        sqlBuilder.append(" WHERE table_schema = ?");

        List<Object> paramList = new LinkedList<Object>();
        paramList.add(databaseName);

        return jdbcUtils.selectList(sqlBuilder.toString(), paramList,  Table.class);
    }

    /**
     * 查询指定数据库下的某个表的描述
     *
     * @param jdbcProperty
     * @param databaseName
     * @return
     * @throws DataBaseException
     */
    public Table getTable(JdbcProperty jdbcProperty, String databaseName, String tableName) {
        JdbcUtils jdbcUtils = new JdbcUtils(jdbcProperty.getDriverClassName(), jdbcProperty.getUrl(), jdbcProperty.getUserName(), jdbcProperty.getPassword());

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" SELECT ");
        sqlBuilder.append(" table_name AS \"tableName\", table_comment AS \"comment\", ");
        sqlBuilder.append(" `engine` AS \"engine\", table_schema AS \"databaseName\", create_time AS \"createDate\" ");
        sqlBuilder.append(" FROM information_schema.tables ");
        sqlBuilder.append(" WHERE table_schema = ? AND table_name = ?");

        List<Object> paramList = new LinkedList<Object>();
        paramList.add(databaseName);
        paramList.add(tableName);

        return jdbcUtils.selectOne(sqlBuilder.toString(), paramList,  Table.class);
    }
}
