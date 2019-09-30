package com.jzy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.jzy.dao")
//@ComponentScan(basePackages = "com.jzy")
public class App {
    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }
}
