package com.springmvc.service.impl.log;

import com.springmvc.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @ClassName UserServiceLogger
 * @Author JinZhiyun
 * @Description //TODO
 * @Date 2019/4/12 10:51
 * @Version 1.0
 **/
@Aspect
@Component
public class UserServiceLogger {

    private final static Logger logger=Logger.getLogger(UserServiceImpl.class);

    @Before("execution(* com.springmvc.service.UserService.findUserByIdAndPassword(..))")
    public void afterLogger(JoinPoint joinPoint){
        logger.info("trying login...");
        logger.info("调用"+joinPoint.getTarget()+"的"+joinPoint.getSignature().getName()+"方法。方法入参："+Arrays.toString(joinPoint.getArgs()));
    }

}
