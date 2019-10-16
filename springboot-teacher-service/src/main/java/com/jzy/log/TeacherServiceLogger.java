package com.jzy.log;

import com.jzy.dto.teacher.TeacherWithTitleMajorCollegeDto;
import com.jzy.exception.TeacherDataException;
import com.jzy.util.teacher.TeacherUtil;
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
 * @ClassName TeacherServiceLogger
 * @description 对教师业务的日志管理
 * @date 2019/9/11 16:32
 **/
@Aspect
@Component
public class TeacherServiceLogger {
    private final static Logger logger = Logger.getLogger(TeacherServiceLogger.class);

    @Pointcut("execution(* com.jzy.service.impl.TeacherServiceImpl.updateTeacherInfo(..))")
    public void serviceUpdateTeacherInfoPoint() {
    }

    @Pointcut("execution(* com.jzy.service.impl.TeacherServiceImpl.insertTeacher(..))")
    public void serviceInsertTeacherPoint(){}

    /**
     * @author JinZhiyun
     * @description 对更新教师业务的入参信息进行有效性检查
     * @date 19:12 2019/9/11
     * @Param [joinPoint]
     * @return void
     **/
    @Before("serviceUpdateTeacherInfoPoint()")
    public void serviceUpdateTeacherInfoLoggerBefore(JoinPoint joinPoint) {
        String targetMethodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        if (StringUtils.isEmpty((String) joinPoint.getArgs()[0])
                || !TeacherUtil.isValidTeacherUpdateInfo((TeacherWithTitleMajorCollegeDto) joinPoint.getArgs()[1])) {
            logger.error("服务端" + targetMethodName + "方法收到了非法请求参数，请引起警惕");
            throw new TeacherDataException("修改教师信息时服务端" + targetMethodName + "方法接收的参数存在不合法行为！");
        }
    }

    /**
     * @author JinZhiyun
     * @description 对添加教师业务的入参信息进行有效性检查
     * @date 19:12 2019/9/11
     * @Param [joinPoint]
     * @return void
     **/
    @Before("serviceInsertTeacherPoint()")
    public void serviceInsertTeacherLoggerBefore(JoinPoint joinPoint) {
        String targetMethodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        if (!TeacherUtil.isValidTeacherInsertInfo((TeacherWithTitleMajorCollegeDto) joinPoint.getArgs()[0])) {
            logger.error("服务端" + targetMethodName + "方法收到了非法请求参数，请引起警惕");
            throw new TeacherDataException("添加教师信息时服务端" + targetMethodName + "方法接收的参数存在不合法行为！");
        }
    }
}
