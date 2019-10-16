package com.jzy.log;

import com.jzy.dto.college.CollegeDto;
import com.jzy.exception.CollegeDataException;
import com.jzy.util.college.CollegeUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName CollegeServiceLogger
 * @description 对学院业务的日志管理
 * @date 2019/9/11 16:35
 **/
@Aspect
@Component
public class CollegeServiceLogger {
    private final static Logger logger = Logger.getLogger(CollegeServiceLogger.class);

    @Pointcut("execution(* com.jzy.service.impl.CollegeServiceImpl.updateCollegeInfo(..))")
    public void serviceUpdateCollegeInfoPoint() {
    }

    @Pointcut("execution(* com.jzy.service.impl.CollegeServiceImpl.insertCollege(..))")
    public void serviceInsertCollegePoint() {
    }

    /**
     * @return void
     * @author JinZhiyun
     * @description 对修改学院业务的入参信息进行有效性检查
     * @date 22:45 2019/9/11
     * @Param [joinPoint]
     **/
    @Before("serviceUpdateCollegeInfoPoint()")
    public void serviceUpdateCollegeInfoLoggerBefore(JoinPoint joinPoint) {
        String targetMethodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        if (StringUtils.isEmpty((String) joinPoint.getArgs()[0])
                || StringUtils.isEmpty((String) joinPoint.getArgs()[1])
                || !CollegeUtil.isValidCollegeUpdateInfo((CollegeDto) joinPoint.getArgs()[2])) {
            logger.error("服务端" + targetMethodName + "方法收到了非法请求参数，请引起警惕");
            throw new CollegeDataException("修改学院信息时服务端" + targetMethodName + "方法接收的参数存在不合法行为！");
        }
    }

    /**
     * @return void
     * @author JinZhiyun
     * @description 对插入学院的入参信息进行有效性检查
     * @date 22:48 2019/9/11
     * @Param [joinPoint]
     **/
    @Before("serviceInsertCollegePoint()")
    public void serviceInsertCollegeLoggerBefore(JoinPoint joinPoint) {
        String targetMethodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        if (!CollegeUtil.isValidCollegeUpdateInfo((CollegeDto) joinPoint.getArgs()[0])) {
            logger.error("服务端" + targetMethodName + "方法收到了非法请求参数，请引起警惕");
            throw new CollegeDataException("插入学院时服务端" + targetMethodName + "方法接收的参数存在不合法行为！");
        }
    }
}
