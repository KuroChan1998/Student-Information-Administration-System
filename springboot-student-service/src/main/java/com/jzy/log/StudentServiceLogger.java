package com.jzy.log;

import com.jzy.dto.student.StudentWithGradeClassMajorCollegeDto;
import com.jzy.exception.StudentDataException;
import com.jzy.util.student.StudentUtil;
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
 * @ClassName StudentServiceLogger
 * @description 对学生业务的日志管理
 * @date 2019/9/11 16:32
 **/
@Aspect
@Component
public class StudentServiceLogger {
    private final static Logger logger = Logger.getLogger(StudentServiceLogger.class);

    @Pointcut("execution(* com.jzy.service.impl.StudentServiceImpl.updateStudentInfo(..))")
    public void serviceUpdateStudentInfoPoint(){
    }

    @Pointcut("execution(* com.jzy.service.impl.StudentServiceImpl.insertStudent(..))")
    public void serviceInsertStudentPoint(){
    }



    /**
     * @author JinZhiyun
     * @description 对更新学生业务的入参信息进行有效性检查
     * @date 17:01 2019/9/11
     * @Param [joinPoint]
     * @return void
     **/
    @Before("serviceUpdateStudentInfoPoint()")
    public void serviceUpdateStudentInfoLoggerBefore(JoinPoint joinPoint) {
        String targetMethodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        if (StringUtils.isEmpty((String) joinPoint.getArgs()[0])
                || !StudentUtil.isValidStudentUpdateInfo((StudentWithGradeClassMajorCollegeDto) joinPoint.getArgs()[1])){
            logger.error("服务端" + targetMethodName + "方法收到了非法请求，请引起警惕");
            throw new StudentDataException("修改学生信息时服务端" + targetMethodName + "方法接收到的信息有误！");
        }
    }

    @Before("serviceInsertStudentPoint()")
    public void serviceInsertStudentLoggerBefore(JoinPoint joinPoint) {
        String targetMethodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        if (!StudentUtil.isValidStudentInsertInfo((StudentWithGradeClassMajorCollegeDto) joinPoint.getArgs()[0])){
            logger.error("服务端" + targetMethodName + "方法收到了非法请求，请引起警惕");
            throw new StudentDataException("修改学生信息时服务端" + targetMethodName + "方法接收到的信息有误！");
        }
    }
}
