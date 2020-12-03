package com.lih;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.lih.dao")
@SpringBootApplication
public class YingxueDomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(YingxueDomeApplication.class, args);
    }

}
