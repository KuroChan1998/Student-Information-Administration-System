package com.jzy.log;

import com.jzy.exception.UserDataException;
import com.jzy.util.other.MySimpleUtil;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName OtherServiceLogger
 * @description 对其他业务的日志管理
 * @date 2019/9/11 18:34
 **/
@Aspect
@Component
public class OtherServiceLogger {
    private final static Logger logger = Logger.getLogger(OtherServiceLogger.class);

    @Pointcut("execution(* com.jzy.service.impl.OtherServiceImpl.sendVerifyCodeToEmail(..))")
    public void serviceSendVerifyCodeToEmailPoint(){
    }

    @Pointcut("execution(* com.jzy.service.impl.OtherServiceImpl.ifValidEmailVerifyCode(..))")
    public void serviceIfValidEmailVerifyCodePoint(){}

    /**
     * @author JinZhiyun
     * @description 对发送邮箱验证码业务的入参信息进行有效性检查
     * @date 18:45 2019/9/11
     * @Param [joinPoint]
     * @return void
     **/
    @Before("serviceSendVerifyCodeToEmailPoint()")
    public void serviceSendVerifyCodeToEmailLoggerBefore(JoinPoint joinPoint) {
        String targetMethodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        if (!MySimpleUtil.isEmail((String) joinPoint.getArgs()[0])) {
            logger.error("服务端" + targetMethodName + "方法收到了非法请求参数，请引起警惕");
            throw new UserDataException("发送邮箱验证码时服务端" + targetMethodName + "方法接收的数据存在不合法行为！");
        }
    }
}
