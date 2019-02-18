package com.chengxuunion.generator.component.context.database.service.jdbc.model;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-14 18:10
 * @Modified By:
 */
public class JdbcProperty {

    private String driverClassName = "com.mysql.jdbc.Driver";

    private String url = "jdbc:mysql://localhost:3306/";

    private String userName = "root";

    private String password = "root";

    public JdbcProperty() {

    }

    public JdbcProperty(String driverClassName, String url, String userName, String password) {
        this.driverClassName = driverClassName;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

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
}
