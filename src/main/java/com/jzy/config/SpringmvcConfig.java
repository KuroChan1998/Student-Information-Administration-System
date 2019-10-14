package com.jzy.config;

import com.jzy.interceptor.*;
import com.jzy.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName SpringmvcConfig
 * @description 配置springmvc拦截器等
 * @date 2019/9/30 10:37
 **/
@Configuration
public class SpringmvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private OtherService otherService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //LoginInterceptor
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**") //拦截项目中的哪些请求
                .excludePathPatterns("/login").excludePathPatterns("/reg").excludePathPatterns("/forget")
                .excludePathPatterns("/loginTest").excludePathPatterns("/regTest").excludePathPatterns("/sendVerifyCodeToEmail")
                .excludePathPatterns("/emailVerifyCodeTest").excludePathPatterns("/resetPassword").excludePathPatterns("/formRepeatSubmit")
                .excludePathPatterns("/**.ico");  //对项目中的哪些请求不拦截

        //CsrfInterceptor
        registry.addInterceptor(new CsrfInterceptor())
                .addPathPatterns("/user/reset*").addPathPatterns("/*/update*")
                .addPathPatterns("/*/insert*").addPathPatterns("/*/delete*"); //拦截项目中的哪些请求

        //EmailVerifyCodeInterceptor
        registry.addInterceptor(new EmailVerifyCodeInterceptor())
                .addPathPatterns("/resetPassword").addPathPatterns("/user/reset*"); //拦截项目中的哪些请求

        //TokenInterceptor
        registry.addInterceptor(new TokenInterceptor())
                .addPathPatterns("/**"); //拦截项目中的哪些请求

        //AuthorityInterceptor
        registry.addInterceptor(new AuthorityInterceptor(otherService))
                .addPathPatterns("/*/modify").addPathPatterns("/*/insert")
                .addPathPatterns("/*/update").addPathPatterns("/*/delete"); //拦截项目中的哪些请求;
    }
}
