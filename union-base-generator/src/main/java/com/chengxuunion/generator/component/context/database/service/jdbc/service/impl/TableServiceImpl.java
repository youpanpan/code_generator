package com.chengxuunion.generator.component.context.database.service.jdbc.service.impl;

import com.chengxuunion.generator.component.context.database.service.jdbc.dao.TableDao;
import com.chengxuunion.generator.component.context.database.service.jdbc.exception.DataBaseException;
import com.chengxuunion.generator.component.context.database.service.jdbc.model.JdbcProperty;
import com.chengxuunion.generator.component.context.database.service.jdbc.model.TableColumn;
import com.chengxuunion.generator.component.context.database.service.jdbc.service.TableService;
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
public class TableServiceImpl implements TableService{

    @Autowired
    private TableDao tableDao;

    @Override
    public List<TableColumn> listTableColumn(JdbcProperty jdbcProperty, String databaseName, String tableName) throws DataBaseException {
        return tableDao.listTableColumn(jdbcProperty, databaseName, tableName);
    }
}
