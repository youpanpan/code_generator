package com.chengxuunion.generator.component.context.database.service;

import com.chengxuunion.generator.component.context.AbstractDataContext;
import com.chengxuunion.generator.component.context.ExtContext;
import com.chengxuunion.generator.component.context.database.model.DataBaseContextParam;
import com.chengxuunion.generator.component.context.database.service.jdbc.exception.DataBaseException;
import com.chengxuunion.generator.component.context.database.service.jdbc.model.JdbcProperty;
import com.chengxuunion.generator.component.context.database.service.jdbc.model.Table;
import com.chengxuunion.generator.component.context.database.service.jdbc.model.TableColumn;
import com.chengxuunion.generator.component.context.database.service.jdbc.service.DataBaseService;
import com.chengxuunion.generator.component.context.database.service.jdbc.service.TableService;
import com.chengxuunion.generator.component.context.database.model.MethodDesc;
import com.chengxuunion.util.database.JavaJdbcTypeMapping;
import com.chengxuunion.util.reflect.MethodUtils;
import com.chengxuunion.util.string.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-19 17:20
 * @Modified By:
 */
@Component
public class DataBaseDataContext extends AbstractDataContext {

    @Autowired
    private TableService tableService;

    @Autowired
    private DataBaseService dataBaseService;

    /**
     * 数据库上下文参数对象
     */
    private DataBaseContextParam dataBaseContextParam;

    @SuppressWarnings("rawtypes")
	@Override
    public ExtContext getContext() throws Exception {
        ExtContext context = this.getDataContext().getContext();
        if (dataBaseContextParam == null) {
            return context;
        }

        String databaseName = dataBaseContextParam.getDatabaseName();
        String tableName = dataBaseContextParam.getTableName();

        try {
            // 1、设置当前基础包名
            String basePackage = (dataBaseContextParam.getBasePackageName() + "." + tableName.replaceAll("_","")).toLowerCase();
            context.setVariable("basePackage", basePackage);

            // 2、设置当前表描述
            JdbcProperty jdbcProperty = new JdbcProperty(dataBaseContextParam.getDriverClassName(), dataBaseContextParam.getUrl(), dataBaseContextParam.getUserName(), dataBaseContextParam.getPassword());
            Table table = dataBaseService.getTable(jdbcProperty, databaseName, tableName);
            if (table == null) {
                throw new Exception("数据库表【"+ tableName +"】不存在");
            }
            table.setModelName(StringUtils.convertBigCamelCase(table.getTableName()));
            context.setVariable("table", table);

            // 模型名称，大驼峰命名
            context.setVariable("modelName", table.getModelName());

            // 模型变量名称，小驼峰命名
            context.setVariable("modelVarName", StringUtils.convertSmallCamelCase(table.getTableName()));

            context.setVariable("modelNameSmall", StringUtils.convertSmallCamelCase(table.getTableName()).toLowerCase());
            context.setVariable("modelDesc", table.getComment());

            // 3、设置当前表字段描述
            List<TableColumn> tableColumns = tableService.listTableColumn(jdbcProperty, databaseName, tableName);
            List<MethodDesc> methodDescs = new ArrayList<>();
            for (TableColumn tableColumn : tableColumns) {
                tableColumn.setJavaFieldName(StringUtils.convertSmallCamelCase(tableColumn.getColumnName()));
                Class typeClass = JavaJdbcTypeMapping.getInstance().getJdbcToJavaMap().get(tableColumn.getDataType().toUpperCase());
                tableColumn.setJavaType(typeClass.getSimpleName());

                // get方法
                MethodDesc getMethod = new MethodDesc();
                getMethod.setQualifier("public");
                getMethod.setReturnType(typeClass.getSimpleName());
                getMethod.setMethodName(MethodUtils.getGetMethodName(tableColumn.getJavaFieldName(), typeClass));
                getMethod.setParamDesc("");
                getMethod.setMethodBody("return " + tableColumn.getJavaFieldName() + ";");
                methodDescs.add(getMethod);

                // set方法
                MethodDesc setMethod = new MethodDesc();
                setMethod.setQualifier("public");
                setMethod.setReturnType("void");
                setMethod.setMethodName(MethodUtils.getSetMethodName(tableColumn.getJavaFieldName(), typeClass));
                setMethod.setParamDesc(typeClass.getSimpleName() + " " + tableColumn.getJavaFieldName());
                setMethod.setMethodBody("this." + tableColumn.getJavaFieldName() + " = " + tableColumn.getJavaFieldName() + ";");
                methodDescs.add(setMethod);
            }
            context.setVariable("columns", tableColumns);
            context.setVariable("columnGetSetMethos", methodDescs);
        } catch (DataBaseException e) {
            logger.error("数据库上下文获取数据发生异常", e);
            throw e;
        }

        return context;
    }

    public DataBaseContextParam getDataBaseContextParam() {
        return dataBaseContextParam;
    }

    public void setDataBaseContextParam(DataBaseContextParam dataBaseContextParam) {
        this.dataBaseContextParam = dataBaseContextParam;
    }
}
