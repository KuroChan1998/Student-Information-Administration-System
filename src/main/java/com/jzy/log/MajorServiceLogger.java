package com.jzy.log;

import com.jzy.dto.major.MajorWithCollegeDto;
import com.jzy.exception.MajorDataException;
import com.jzy.util.major.MajorUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName MajorServiceLogger
 * @description 对专业业务的日志管理
 * @date 2019/9/11 16:34
 **/
@Aspect
@Component
public class MajorServiceLogger {
    private final static Logger logger = Logger.getLogger(MajorServiceLogger.class);

    @Pointcut("execution(* com.jzy.service.impl.MajorServiceImpl.updateMajorInfo(..))")
    public void serviceUpdateMajorInfoPoint() {
    }

    @Pointcut("execution(* com.jzy.service.impl.MajorServiceImpl.insertMajor(..))")
    public void serviceInsertMajorPoint() {
    }

    /**
     * @author JinZhiyun
     * @description 对修改专业业务的入参信息进行有效性检查
     * @date 22:27 2019/9/11
     * @Param [joinPoint]
     * @return void
     **/
    @Before("serviceUpdateMajorInfoPoint()")
    public void serviceUpdateMajorInfoLoggerBefore(JoinPoint joinPoint) {
        String targetMethodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        if (StringUtils.isEmpty((String) joinPoint.getArgs()[0])
                || StringUtils.isEmpty((String) joinPoint.getArgs()[1])
                || !MajorUtil.isValidMajorUpdateInfo((MajorWithCollegeDto) joinPoint.getArgs()[2])) {
            logger.error("服务端" + targetMethodName + "方法收到了非法请求参数，请引起警惕");
            throw new MajorDataException("修改专业信息时服务端" + targetMethodName + "方法接收的参数存在不合法行为！");
        }
    }

    /**
     * @author JinZhiyun
     * @description 对插入专业业务的入参信息进行有效性检查
     * @date 22:29 2019/9/11
     * @Param [joinPoint]
     * @return void
     **/
    @Before("serviceInsertMajorPoint()")
    public void serviceInsertMajorLoggerBefore(JoinPoint joinPoint) {
        String targetMethodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        if (!MajorUtil.isValidMajorInsertInfo((MajorWithCollegeDto) joinPoint.getArgs()[0])) {
            logger.error("服务端" + targetMethodName + "方法收到了非法请求参数，请引起警惕");
            throw new MajorDataException("插入专业时服务端" + targetMethodName + "方法接收的参数存在不合法行为！");
        }
    }
}
