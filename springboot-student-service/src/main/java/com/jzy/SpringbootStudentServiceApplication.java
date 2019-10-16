package com.jzy;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableDubbo(scanBasePackages = "com.jzy.service.impl")
@SpringBootApplication
@MapperScan(basePackages = "com.jzy.dao")
public class SpringbootStudentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootStudentServiceApplication.class, args);
    }

}
