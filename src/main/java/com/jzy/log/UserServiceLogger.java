package com.jzy.log;

import com.jzy.entity.User;
import com.jzy.exception.UserDataException;
import com.jzy.util.other.MySimpleUtil;
import com.jzy.util.user.UserUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
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

    @Pointcut("execution(* com.jzy.service.impl.UserServiceImpl.userLoginTest(..))")
    public void serviceLoginTestPoint(){
    }

    @Pointcut("execution(* com.jzy.service.impl.UserServiceImpl.insertUserRegInfo(..))")
    public void serviceInsertUserRegInfoPoint(){}

    @Pointcut("execution(* com.jzy.service.impl.UserServiceImpl.updateResetPasswordByEmail(..))")
    public void serviceUpdateResetPasswordByEmailPoint(){
    }

    @Pointcut("execution(* com.jzy.service.impl.UserServiceImpl.updateResetUserInfo(..))")
    public void serviceUpdateResetUserInfoPoint(){}

    @Pointcut("execution(* com.jzy.service.impl.UserServiceImpl.updateResetUserPassword(..))")
    public void serviceUpdateResetUserPasswordPoint(){}

    @Pointcut("execution(* com.jzy.service.impl.UserServiceImpl.updateResetEmailByEmail(..))")
    public void serviceUpdateResetEmailByEmailPoint(){}

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 用户登录日志管理，包含入参校验
     * @Date 10:19 2019/5/21
     * @Param [joinPoint]
     **/
    @Before("serviceLoginTestPoint()")
    public void serviceLoginTestLoggerBefore(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logger.info("ip:" + MySimpleUtil.getIpAddress(request) + " is trying to login...");

        String targetMethodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        if (joinPoint.getArgs()[0].getClass() == User.class) {
            if (!UserUtil.isValidUserLoginNameAndPassword((User) joinPoint.getArgs()[0])) { //服务端输入校验
                logger.error("服务端" + targetMethodName + "方法收到了非法请求参数，请引起警惕");
                throw new UserDataException("登录时服务端" + targetMethodName + "方法接收的用户信息存在为空或者null！");
            }
        }
    }

    /**
     * @author JinZhiyun
     * @description 对注册用户业务的入参信息进行有效性检查
     * @date 18:14 2019/9/11
     * @Param [joinPoint]
     * @return void
     **/
    @Before("serviceInsertUserRegInfoPoint()")
    public void serviceInsertUserRegInfoLoggerBefore(JoinPoint joinPoint) {
        String targetMethodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        if (joinPoint.getArgs()[0].getClass() == User.class) {
            if (!UserUtil.isValidUserRegInfo((User) joinPoint.getArgs()[0])) { //服务端输入校验
                logger.error("服务端" + targetMethodName + "方法收到了非法请求参数，请引起警惕");
                throw new UserDataException("用户注册时服务端" + targetMethodName + "方法接收的用户信息存在为空或者null！");
            }
        }
    }

    /**
     * @author JinZhiyun
     * @description 对根据用户邮箱重置用户密码的入参信息进行有效性检查
     * @date 18:21 2019/9/11
     * @Param [joinPoint]
     * @return void
     **/
    @Before("serviceUpdateResetPasswordByEmailPoint()")
    public void serviceUpdateResetPasswordByEmailLoggerBefore(JoinPoint joinPoint) {
        String targetMethodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        if (!UserUtil.isValidUserEmail((String) joinPoint.getArgs()[0])
                || !UserUtil.isValidUserPassword((String) joinPoint.getArgs()[1])) {
            logger.error("服务端" + targetMethodName + "方法收到了非法请求参数，请引起警惕");
            throw new UserDataException("根据邮箱修改密码时服务端" + targetMethodName + "方法接收的用户信息存在不合法行为！");
        }
    }

    /**
     * @author JinZhiyun
     * @description 对更新用户基本资料的入参信息进行有效性检查
     * @date 18:26 2019/9/11
     * @Param [joinPoint]
     * @return void
     **/
    @Before("serviceUpdateResetUserInfoPoint()")
    public void serviceUpdateResetUserInfoLoggerBefore(JoinPoint joinPoint) {
        String targetMethodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        if (!UserUtil.isValidUserResetInfo((User) joinPoint.getArgs()[0])){
            logger.error("服务端" + targetMethodName + "方法收到了非法请求参数，请引起警惕");
            throw new UserDataException("修改用户基本资料时服务端" + targetMethodName + "方法接收的用户信息存在不合法行为！");
        }
    }

    /**
     * @author JinZhiyun
     * @description 对登录后更新用户密码的入参信息进行有效性检查
     * @date 18:28 2019/9/11
     * @Param [joinPoint]
     * @return void
     **/
    @Before("serviceUpdateResetUserPasswordPoint()")
    public void serviceUpdateResetUserPasswordLoggerBefore(JoinPoint joinPoint) {
        String targetMethodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        if (StringUtils.isEmpty((String) joinPoint.getArgs()[0]) || StringUtils.isEmpty((String) joinPoint.getArgs()[1])) {
            logger.error("服务端" + targetMethodName + "方法收到了非法请求参数，请引起警惕");
            throw new UserDataException("修改用户密码时服务端" + targetMethodName + "方法接收的用户信息存在不合法行为！");
        }
    }

    /**
     * @author JinZhiyun
     * @description 对重置用户邮箱的入参信息进行有效性检查
     * @date 18:31 2019/9/11
     * @Param [joinPoint]
     * @return void
     **/
    @Before("serviceUpdateResetEmailByEmailPoint()")
    public void serviceUpdateResetEmailByEmailLoggerBefore(JoinPoint joinPoint) {
        String targetMethodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        if (StringUtils.isEmpty((String) joinPoint.getArgs()[0]) || StringUtils.isEmpty((String) joinPoint.getArgs()[1])) {
            logger.error("服务端" + targetMethodName + "方法收到了非法请求参数，请引起警惕");
            throw new UserDataException("修改用户基本资料时服务端" + targetMethodName + "方法接收的用户信息存在不合法行为！");
        }
    }
}
