package com.jzy.interceptor;

import com.jzy.entity.User;
import com.jzy.util.other.MySimpleUtil;
import com.jzy.util.user.UserUtil;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginInterceptor
 * @Author JinZhiyun
 * @Description 登录Session拦截器
 * @Date 2019/4/14 12:32
 * @Version 1.0
 **/
public class LoginInterceptor implements HandlerInterceptor {
    private final static Logger logger = Logger.getLogger(LoginInterceptor.class);

    /**
     * @return boolean
     * @Author JinZhiyun
     * @Description 判断是否登录，即session是否建立，否则重定向至登录界面
     * @Date 22:47 2019/4/18
     * @Param [httpServletRequest, httpServletResponse, o]
     **/
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        User user = (User) httpServletRequest.getSession().getAttribute(UserUtil.USER_INFO_SESSION);
        if (user != null ) {
            logger.info(user.getUserName() + "访问系统！");
            return true;
        }
        logger.warn("ip:"+MySimpleUtil.getIpAddress(httpServletRequest) + " 未登录下企图进入系统！");
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
