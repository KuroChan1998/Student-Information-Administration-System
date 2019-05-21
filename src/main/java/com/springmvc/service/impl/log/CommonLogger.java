package com.springmvc.service.impl.log;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @ClassName CommonLogger
 * @Author JinZhiyun
 * @Description 通用日志管理
 * @Date 2019/5/21 10:13
 * @Version 1.0
 **/
@Aspect
@Component
public class CommonLogger {
    private final static Logger logger = Logger.getLogger(CommonLogger.class);

    private static long METHOD_TIME = 1000;

    @Pointcut("execution(* com.springmvc.service..*.*(..))")
    public void commonPoint() {
    }

    /**
     * @return java.lang.Object
     * @Author JinZhiyun
     * @Description 业务方法耗时aop实现
     * @Date 10:19 2019/5/21
     * @Param [pjp]
     **/
    @Around("commonPoint()")
    public Object doAround(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();
        Object obj = null;
        try {
            obj = pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long spendTime = endTime - startTime;
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
        if (spendTime > METHOD_TIME) {
            logger.warn(methodName + "方法耗时严重：" + (endTime - startTime) + "ms");
        } else {
            logger.info(methodName + "方法耗时正常：" + (endTime - startTime) + "ms");
        }
        return obj;
    }
}
