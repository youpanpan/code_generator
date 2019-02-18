package com.chengxuunion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-14 14:19
 * @Modified By:
 */
@SpringBootApplication
@ComponentScan({"com.chengxuunion"})
public class GeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeneratorApplication.class, args);

    }
}
