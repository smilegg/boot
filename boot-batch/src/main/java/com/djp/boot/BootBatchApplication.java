package com.djp.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
@MapperScan("com.djp.boot.mapper*")
public class BootBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootBatchApplication.class, args);
    }

}
