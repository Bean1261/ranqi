package com.weichai.ranqi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.weichai.ranqi.mapper")

public class RanqiApplication {
    public static void main(String[] args) {
        SpringApplication.run(RanqiApplication.class, args);
    }
}
