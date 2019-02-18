package com.chengxuunion.generator.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-08 09:00
 * @Modified By:
 */
@Configuration
@MapperScan("com.chengxuunion.generator.**.dao")
@EnableTransactionManagement
public class MybatisConfig {
}
