package com.example.springdemo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableRetry
@EnableScheduling
@ServletComponentScan
@MapperScan("com.example.springdemo.demos.web.db.mapper")
@Slf4j
public class SpringDemoApplication {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        SpringApplication app = new SpringApplication(SpringDemoApplication.class);
        app.setAdditionalProfiles("dev");
        app.run(args);


    }
}
