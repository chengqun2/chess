package com.wuziqi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class WuZiQiApplication {

    public static void main(String[] args) {

        SpringApplication.run(WuZiQiApplication.class, args);
    }
}