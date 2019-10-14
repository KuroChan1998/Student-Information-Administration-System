package com.jzy.interceptor;

import com.jzy.dto.other.EmailVerifyCodeSession;
import com.jzy.util.user.UserUtil;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName EmailVerifyCodeInterceptor
 * @description 邮箱验证码校验拦截器
 * @date 2019/10/11 12:45
 **/
public class EmailVerifyCodeInterceptor implements HandlerInterceptor {
    private final static Logger logger = Logger.getLogger(EmailVerifyCodeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        EmailVerifyCodeSession emailVerifyCodeSession= (EmailVerifyCodeSession) request.getSession().getAttribute(UserUtil.USER_EMAIL_SESSION);
        if (!emailVerifyCodeSession.getFlag()) {
            logger.warn("邮箱验证码校验出错，疑似绕过验证码攻击");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
