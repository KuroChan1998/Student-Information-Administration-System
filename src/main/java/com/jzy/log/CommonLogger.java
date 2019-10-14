package com.jzy.log;

import com.jzy.dto.other.MyPage;
import com.jzy.exception.DataException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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

    private static final long METHOD_TIME = 1000;

    @Pointcut("execution(* com.jzy.service..*.*(..))")
    public void serviceAllPoints() {
    }

    @Pointcut("execution(* com.jzy.service..*.selectAll*Info(..)) || execution(* com.jzy.service..*.select*OwnInfo*(..))")
    public void serviceMyPageTestPoint() {
    }

    @Pointcut("execution(* com.jzy.service..*.update*Info(..)) && !execution(* com.jzy.service..*.updateResetUserInfo(..))")
    public void serviceUpdateInfoPoint() {
    }

    @Pointcut("execution(* com.jzy.service..*.deleteOne*(..)) || execution(* com.jzy.service..*.deleteMany*(..))")
    public void serviceDeleteOnePoint() {
    }

    /**
     * @return java.lang.Object
     * @Author JinZhiyun
     * @Description 业务方法耗时aop实现
     * @Date 10:19 2019/5/21
     * @Param [pjp]
     **/
    @Around("serviceAllPoints()")
    public Object serviceTimeTestAround(ProceedingJoinPoint pjp) {
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
            logger.warn(methodName + "业务方法耗时严重：" + (endTime - startTime) + "ms");
        } else {
            logger.info(methodName + "业务方法耗时正常：" + (endTime - startTime) + "ms");
        }
        return obj;
    }

    /**
     * @return void
     * @author JinZhiyun
     * @description 对所有分页查询业务的分页信息进行有效性检查
     * @date 15:55 2019/9/11
     * @Param [joinPoint]
     **/
    @Before("serviceMyPageTestPoint()")
    public void serviceMyPageTestLoggerBefore(JoinPoint joinPoint) {
        String targetMethodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        if (joinPoint.getArgs()[0].getClass() == MyPage.class) {
            if (!MyPage.isValidMyPage((MyPage) joinPoint.getArgs()[0])) {
                logger.error("服务端" + targetMethodName + "方法收到了非法请求参数，请引起警惕");
                throw new DataException("服务端" + targetMethodName + "方法接收到的分页信息有误！");
            }
        }
    }

    /**
     * @return void
     * @author JinZhiyun
     * @description 对删除单个学生、教师等所有业务方法入参所进行有效性检查
     * @date 16:13 2019/9/11
     * @Param [joinPoint]
     **/
    @Before("serviceDeleteOnePoint()")
    public void serviceDeleteOneLoggerBefore(JoinPoint joinPoint) {
        String targetMethodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        if (joinPoint.getArgs()[0].getClass() == String.class) {
            if (StringUtils.isEmpty((String) joinPoint.getArgs()[0])) {
                logger.error("服务端" + targetMethodName + "方法收到了非法请求参数，请引起警惕");
                throw new DataException("服务端" + targetMethodName + "方法接收到的信息有误！");
            }
        }
    }
}
