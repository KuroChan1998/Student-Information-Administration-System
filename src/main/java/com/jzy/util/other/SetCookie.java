package com.jzy.util.other;

import com.jzy.interceptor.CsrfInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;


/**
 * @ClassName SetCookie
 * @Author JinZhiyun
 * @Description 设置Cookie业务类
 * @Date 2019/4/18 13:55
 * @Version 1.0
 **/
public class SetCookie {
    private static final String USERNAME_COOKIE ="loginAccount";

    private static final String USERPASSWORD_COOKIE="loginPassword";

    private static final String REMEMBER="remember";

    private SetCookie(){}

    /**
     * @return void
     * @Author JinZhiyun
     * @Description //设置用户登录cookie
     * @Date 13:58 2019/4/18
     * @Param [userId, userPassword, remember, request, response]
     **/
    public static void setUserLoginCookie(String userName, String userPassword, String remember
            , HttpServletRequest request, HttpServletResponse response) {
        Cookie loginAccountCookie = new Cookie(USERNAME_COOKIE, userName);
        Cookie loginPasswordCookie = new Cookie(USERPASSWORD_COOKIE, userPassword);
        Cookie rememberCookie = new Cookie(REMEMBER, "true");
        //设置Cookie的父路经
        loginAccountCookie.setPath(request.getContextPath() + "/");
        loginPasswordCookie.setPath(request.getContextPath() + "/");
        rememberCookie.setPath(request.getContextPath() + "/");
        //获取是否保存Cookie（例如：复选框的值）
        if (remember == null) {
            //不保存Cookie
            loginAccountCookie.setMaxAge(0);
            loginPasswordCookie.setMaxAge(0);
            rememberCookie.setMaxAge(0);
        } else {
            //保存Cookie的时间长度，单位为秒
            loginAccountCookie.setMaxAge(7 * 24 * 60 * 60);
            loginPasswordCookie.setMaxAge(7 * 24 * 60 * 60);
            rememberCookie.setMaxAge(7 * 24 * 60 * 60);
        }
        //加入Cookie到响应头
        response.addCookie(loginAccountCookie);
        response.addCookie(loginPasswordCookie);
        response.addCookie(rememberCookie);
    }

    /**
     * @author JinZhiyun
     * @description 设置CSRFToken到session和cookie
     * @date 10:26 2019/10/11
     * @Param [session, request, response]
     * @return void
     **/
    public static void setCSRFTokenCookieAndSession(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        String authorization = UUID.randomUUID().toString();
        session.setAttribute(Constants.CSRF_NUMBER,authorization);
        Cookie cookie = new Cookie(Constants.CSRF_NUMBER, authorization);
        cookie.setPath(request.getContextPath()+"/");
        response.addCookie(cookie);
    }
}
