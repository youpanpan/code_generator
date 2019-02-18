package com.chengxuunion.generator.component.context.database.service.jdbc.model;

import com.chengxuunion.util.database.NotPersistent;

import java.util.Date;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-14 16:51
 * @Modified By:
 */
public class Table {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 对应表名的模型名称
     */
    @NotPersistent
    private String modelName;

    /**
     * 表名注释
     */
    private String comment;

    /**
     * 表存储引擎
     */
    private String engine;

    /**
     * 数据库名称
     */
    private String databaseName;

    /**
     * 表创建时间
     */
    private Date createDate;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
