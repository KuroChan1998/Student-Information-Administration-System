package com.jzy;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableDubbo(scanBasePackages = "com.jzy.service.impl")
//@ImportResource(locations = "classpath:spring-dubbo.xml") //不使用properties文件而使用xml的配置
@SpringBootApplication
@MapperScan(basePackages = "com.jzy.dao")
public class SpringbootUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootUserServiceApplication.class, args);
    }

}
