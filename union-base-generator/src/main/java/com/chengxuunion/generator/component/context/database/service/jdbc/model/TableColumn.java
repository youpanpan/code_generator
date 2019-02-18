package com.chengxuunion.generator.component.context.database.service.jdbc.model;

import com.chengxuunion.util.database.NotPersistent;

import java.math.BigInteger;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-14 16:51
 * @Modified By:
 */
public class TableColumn {

    /**
     * 列名
     */
    private String columnName;

    /**
     * 列名对应的java字段名
     */
    @NotPersistent
    private String javaFieldName;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 数据类型，int，varchar等
     */
    private String dataType;

    /**
     * 对应的java类型
     */
    @NotPersistent
    private String javaType;

    /**
     * 列名类型长度
     */
    private BigInteger columnLength;

    /**
     * 是否允许为空, Mysql中NO和YES
     */
    private String isNullable;

    /**
     * 列名注释
     */
    private String comment;

    /**
     * 是否为主键，1：是，0：否
     */
    private String isPrimary;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getJavaFieldName() {
        return javaFieldName;
    }

    public void setJavaFieldName(String javaFieldName) {
        this.javaFieldName = javaFieldName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public BigInteger getColumnLength() {
        return columnLength;
    }

    public void setColumnLength(BigInteger columnLength) {
        this.columnLength = columnLength;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(String isPrimary) {
        this.isPrimary = isPrimary;
    }
}
