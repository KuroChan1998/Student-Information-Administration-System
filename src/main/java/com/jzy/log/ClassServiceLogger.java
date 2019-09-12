package com.jzy.log;

import com.jzy.dto.clazz.ClassWithGradeMajorCollegeDto;
import com.jzy.exception.ClassDataException;
import com.jzy.util.clazz.ClassUtil;
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
 * @ClassName ClassServiceLogger
 * @description 对班级业务的日志管理
 * @date 2019/9/11 16:34
 **/
@Aspect
@Component
public class ClassServiceLogger {
    private final static Logger logger = Logger.getLogger(ClassServiceLogger.class);

    @Pointcut("execution(* com.jzy.service.impl.ClassServiceImpl.updateClassInfo(..))")
    public void serviceUpdateClassInfoPoint() {
    }

    @Pointcut("execution(* com.jzy.service.impl.ClassServiceImpl.insertClass(..))")
    public void serviceInsertClassPoint() {
    }

    /**
     * @author JinZhiyun
     * @description 对修改班级业务的入参信息进行有效性检查
     * @date 22:03 2019/9/11
     * @Param [joinPoint]
     * @return void
     **/
    @Before("serviceUpdateClassInfoPoint()")
    public void serviceUpdateClassInfoLoggerBefore(JoinPoint joinPoint) {
        String targetMethodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        if (StringUtils.isEmpty((String) joinPoint.getArgs()[0])
                || StringUtils.isEmpty((String) joinPoint.getArgs()[1])
                || !ClassUtil.isValidClassUpdateInfo((ClassWithGradeMajorCollegeDto) joinPoint.getArgs()[2])) {
            logger.error("服务端" + targetMethodName + "方法收到了非法请求参数，请引起警惕");
            throw new ClassDataException("修改班级信息时服务端" + targetMethodName + "方法接收的参数存在不合法行为！");
        }
    }

    /**
     * @author JinZhiyun
     * @description 对插入班级业务的入参信息进行有效性检查
     * @date 22:05 2019/9/11
     * @Param [joinPoint]
     * @return void
     **/
    @Before("serviceInsertClassPoint()")
    public void serviceInsertClassLoggerBefore(JoinPoint joinPoint) {
        String targetMethodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        if (!ClassUtil.isValidClassInsertInfo((ClassWithGradeMajorCollegeDto) joinPoint.getArgs()[0])) {
            logger.error("服务端" + targetMethodName + "方法收到了非法请求参数，请引起警惕");
            throw new ClassDataException("插入班级信息时服务端" + targetMethodName + "方法接收的参数存在不合法行为！");
        }
    }
}
