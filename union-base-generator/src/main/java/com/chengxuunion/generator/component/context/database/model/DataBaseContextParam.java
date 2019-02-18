package com.chengxuunion.generator.component.context.database.model;

/**
 * @Author youpanpan
 * @Description:    数据库上下文参数
 * @Date:创建时间: 2018-12-19 17:24
 * @Modified By:
 */
public class DataBaseContextParam {

    /**
     * 数据库驱动类
     */
    private String driverClassName = "com.mysql.jdbc.Driver";

    /**
     * 数据库连接地址
     */
    private String url = "jdbc:mysql://localhost:3306/";

    /**
     * 数据库连接用户名
     */
    private String userName = "root";

    /**
     * 数据库连接密码
     */
    private String password = "root";

    /**
     * 数据库名称
     */
    private String databaseName;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 基础包名，例com.chengxuunion
     */
    private String basePackageName;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getBasePackageName() {
        return basePackageName;
    }

    public void setBasePackageName(String basePackageName) {
        this.basePackageName = basePackageName;
    }
}
