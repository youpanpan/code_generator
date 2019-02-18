package com.chengxuunion.util.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-14 17:28
 * @Modified By:
 */
public class JdbcUtils {

    private static final Logger logger = LoggerFactory.getLogger(JdbcUtils.class);

    private String driverClassName = "com.mysql.jdbc.Driver";

    private String url;

    private String userName;

    private String password;

    private Connection conn;
    private PreparedStatement statement;

    public JdbcUtils(String driverClassName, String url, String userName, String password) {
        this.driverClassName = driverClassName;
        this.url = url;
        this.userName = userName;
        this.password = password;

        try {
            Class.forName(this.driverClassName);
        } catch (ClassNotFoundException e) {
            logger.error("加载驱动类:" + driverClassName + "发生异常");
        }
    }

    /**
     * 获取数据库连接对象
     *
     * @return
     */
    private Connection getConnection() {
        try {
            conn = DriverManager.getConnection(this.url, this.userName, this.password);
        } catch (SQLException e) {
            logger.error("获取数据库连接【"+ this.url +", "+ this.userName +"】发生异常", e);
            throw new RuntimeException("数据库连接异常【"+ this.url + "," + this.userName + "," + this.password +"】");
        }
        return conn;
    }

    /**
     * 查询列表
     *
     * @param sql   sql语句
     * @param paramList sql语句参数列表
     * @param clazz 反射的类型
     * @param <T>
     * @return  列表
     */
    public <T> List<T> selectList(String sql, List<Object> paramList, Class<T> clazz) {
        if (conn == null) {
            getConnection();
        }
        List<T> list = new ArrayList<T>();

        try {
            statement = conn.prepareStatement(sql);
            int paramIndex = 1;
            for (Object param : paramList) {
                statement.setObject(paramIndex, param);
                paramIndex++;
            }

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                T obj = clazz.newInstance();
                Field[] fields = obj.getClass().getDeclaredFields();
                for (Field field : fields) {
                    NotPersistent notPersistent = field.getDeclaredAnnotation(NotPersistent.class);
                    if (notPersistent == null) {
                        field.setAccessible(true);
                        field.set(obj, resultSet.getObject(field.getName()));
                    }
                }
                list.add(obj);
            }
            resultSet.close();
        } catch (Exception e) {
            logger.error("执行sql【" + sql + "】发生异常", e);
            throw new RuntimeException(e.getMessage(), e);
        }

        return list;
    }

    /**
     * 查询单个对象
     *
     * @param sql   sql语句
     * @param paramList sql语句参数列表
     * @param clazz 反射的类型
     * @param <T>
     * @return  单个对象
     */
    public <T> T selectOne(String sql, List<Object> paramList, Class<T> clazz) {
        if (conn == null) {
            getConnection();
        }
        T obj = null;

        try {
            statement = conn.prepareStatement(sql);
            int paramIndex = 1;
            for (Object param : paramList) {
                statement.setObject(paramIndex, param);
                paramIndex++;
            }

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                obj = clazz.newInstance();
                Field[] fields = obj.getClass().getDeclaredFields();
                for (Field field : fields) {
                    NotPersistent notPersistent = field.getDeclaredAnnotation(NotPersistent.class);
                    if (notPersistent == null) {
                        field.setAccessible(true);
                        field.set(obj, resultSet.getObject(field.getName()));
                    }
                }
            }
            resultSet.close();
        } catch (Exception e) {
            logger.error("执行sql【" + sql + "】发生异常", e);
            throw new RuntimeException(e.getMessage(), e);
        }

        return obj;
    }

    /**
     * 关闭连接
     */
    public void close() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            logger.error("关闭数据库连接发生异常", e);
        }
    }

}
