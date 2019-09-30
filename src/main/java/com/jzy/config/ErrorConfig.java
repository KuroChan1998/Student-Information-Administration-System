package com.jzy.config;

import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName ErrorConfig
 * @description 错误请求对应页面的配置
 * @date 2019/9/30 10:23
 **/
@Configuration
public class ErrorConfig implements ErrorPageRegistrar {
    //配置错误状态与对应访问路径
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
        ErrorPage error405Page = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/405");
        ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");
        registry.addErrorPages(error404Page,error405Page,error500Page);
    }

}
