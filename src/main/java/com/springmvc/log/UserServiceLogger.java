package com.springmvc.log;

import com.springmvc.util.MySimpleUtil;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UserServiceLogger
 * @Author JinZhiyun
 * @Description 对用户业务的日志管理
 * @Date 2019/4/12 10:51
 * @Version 1.0
 **/
@Aspect
@Component
public class UserServiceLogger {

    private final static Logger logger = Logger.getLogger(UserServiceLogger.class);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 用户登录日志管理
     * @Date 10:19 2019/5/21
     * @Param [joinPoint]
     **/
    @Before("execution(* com.springmvc.service.UserService.selectUserByNameAndPassword(..))")
    public void afterLogger(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logger.info("ip:" + MySimpleUtil.getIpAddress(request) + " is trying to login...");
        //logger.info("调用" + joinPoint.getTarget() + "的" + joinPoint.getSignature().getName() + "方法。方法入参：" + Arrays.toString(joinPoint.getArgs()));
    }

}
