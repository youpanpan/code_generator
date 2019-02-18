package com.chengxuunion.util.database;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.util.*;

/**
 * @Author youpanpan
 * @Description:    JAVA类型与JDBC类型映射关系
 * @Date:创建时间: 2018-12-17 13:56
 * @Modified By:
 */
@SuppressWarnings("rawtypes")
public class JavaJdbcTypeMapping {

    /**
     * jdbc转java类型映射表，key：jdbc类型，value: java Class对象
     */
	private Map<String, Class> jdbcToJavaMap = new HashMap<>();

    /**
     * java转jdbc类型映射表，key：java Class对象，value: jdbc类型集合
     */
    private Map<Class, Set<String>> javaToJdbcMap = new HashMap<>();

    private static volatile JavaJdbcTypeMapping instance = null;

    private JavaJdbcTypeMapping() {
        init();
    }

    /**
     * 获取单例对象
     *
     * @return
     */
    public static JavaJdbcTypeMapping getInstance() {
        if (instance == null) {
            synchronized (JavaJdbcTypeMapping.class) {
                if (instance == null) {
                    instance = new JavaJdbcTypeMapping();
                }
            }
        }

        return instance;
    }

    private void init() {
        jdbcToJavaMap.put("VARCHAR", String.class);
        jdbcToJavaMap.put("CHAR", String.class);
        jdbcToJavaMap.put("TEXT", String.class);
        jdbcToJavaMap.put("LONGVARCHAR", String.class);
        jdbcToJavaMap.put("BLOB", Byte[].class);
        jdbcToJavaMap.put("BINARY", Byte[].class);
        jdbcToJavaMap.put("VARBINARY", Byte[].class);
        jdbcToJavaMap.put("LONGVARBINARY", Byte[].class);
        jdbcToJavaMap.put("BIGINT", Long.class);
        jdbcToJavaMap.put("INTEGER", Integer.class);
        jdbcToJavaMap.put("INT", Integer.class);
        jdbcToJavaMap.put("SMALLINT", Short.class);
        jdbcToJavaMap.put("TINYINT", Byte.class);
        jdbcToJavaMap.put("NUMBERIC", BigDecimal.class);
        jdbcToJavaMap.put("DEVIMAL", BigDecimal.class);
        jdbcToJavaMap.put("BIT", Boolean.class);
        jdbcToJavaMap.put("BOOLEAN", Boolean.class);
        jdbcToJavaMap.put("FLOAT", Double.class);
        jdbcToJavaMap.put("DOUBLE", Double.class);
        jdbcToJavaMap.put("REAL", Float.class);
        jdbcToJavaMap.put("DATE", Date.class);
        jdbcToJavaMap.put("TIME", Date.class);
        jdbcToJavaMap.put("DATETIME", Date.class);
        jdbcToJavaMap.put("TIMESTAMP", Date.class);
        jdbcToJavaMap.put("CLOB", Clob.class);
        jdbcToJavaMap.put("BLOB", Blob.class);
        jdbcToJavaMap.put("ARRAY", Array.class);

        for (Map.Entry<String, Class> entry : jdbcToJavaMap.entrySet()) {
            if (!javaToJdbcMap.containsKey(entry.getValue())) {
                javaToJdbcMap.put(entry.getValue(), new HashSet<String>());
            }
            javaToJdbcMap.get(entry.getValue()).add(entry.getKey());
        }
    }



    public Map<String, Class> getJdbcToJavaMap() {
        return jdbcToJavaMap;
    }

    public Map<Class, Set<String>> getJavaToJdbcMap() {
        return javaToJdbcMap;
    }

    public static void main(String[] args) {
        JavaJdbcTypeMapping mapping = new JavaJdbcTypeMapping();
        System.out.println(mapping.getJdbcToJavaMap());
        System.out.println(mapping.getJavaToJdbcMap());
    }
}
