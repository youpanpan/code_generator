package com.chengxuunion.util.database;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-17 17:26
 * @Modified By:
 */
@Documented
@Target(FIELD)
@Retention(RUNTIME)
public @interface NotPersistent {
}
