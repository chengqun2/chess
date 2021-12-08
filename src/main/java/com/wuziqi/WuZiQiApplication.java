package com.wuziqi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@MapperScan("com.wuziqi.mapper")
public class WuZiQiApplication {

    public static void main(String[] args) {

        SpringApplication.run(WuZiQiApplication.class, args);
    }
}