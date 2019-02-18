package com.chengxuunion.generator.component.context.database.service.jdbc.service.impl;

import com.chengxuunion.generator.component.context.database.service.jdbc.dao.DataBaseDao;
import com.chengxuunion.generator.component.context.database.service.jdbc.model.JdbcProperty;
import com.chengxuunion.generator.component.context.database.service.jdbc.model.Table;
import com.chengxuunion.generator.component.context.database.service.jdbc.service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-14 16:49
 * @Modified By:
 */
@Service
public class DataBaseServiceImpl implements DataBaseService {

    @Autowired
    private DataBaseDao dataBaseDao;

    @Override
    public List<Table> listTable(JdbcProperty jdbcProperty, String databaseName) {
        return dataBaseDao.listTable(jdbcProperty, databaseName);
    }

    @Override
    public Table getTable(JdbcProperty jdbcProperty, String databaseName, String tableName) {
        return dataBaseDao.getTable(jdbcProperty, databaseName, tableName);
    }
}
