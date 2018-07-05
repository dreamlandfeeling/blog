package com.xin.yxblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan("com.xin.yxblog.mapper")
@SpringBootApplication
public class YxblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(YxblogApplication.class, args);
    }
}
