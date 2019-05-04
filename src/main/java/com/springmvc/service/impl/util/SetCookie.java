package com.springmvc.service.impl.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName SetCookie
 * @Author JinZhiyun
 * @Description 设置Cookie业务类
 * @Date 2019/4/18 13:55
 * @Version 1.0
 **/
public class SetCookie {
    /**
     * @return void
     * @Author JinZhiyun
     * @Description //设置用户登录cookie
     * @Date 13:58 2019/4/18
     * @Param [userId, userPassword, remember, request, response]
     **/
    public static void setUserLoginCookie(String userId, String userPassword, String remember
            , HttpServletRequest request, HttpServletResponse response) {
        Cookie loginAccountCookie = new Cookie(Constants.USERID_COOKIE, userId);
        Cookie loginPasswordCookie = new Cookie(Constants.USERPASSWORD_COOKIE, userPassword);
        //设置Cookie的父路经
        loginAccountCookie.setPath(request.getContextPath() + "/");
        loginPasswordCookie.setPath(request.getContextPath() + "/");
        //获取是否保存Cookie（例如：复选框的值）
        if (remember == null) {
            //不保存Cookie
            loginAccountCookie.setMaxAge(0);
            loginPasswordCookie.setMaxAge(0);
        } else {
            //保存Cookie的时间长度，单位为秒
            loginAccountCookie.setMaxAge(7 * 24 * 60 * 60);
            loginPasswordCookie.setMaxAge(7 * 24 * 60 * 60);
        }
        //加入Cookie到响应头
        response.addCookie(loginAccountCookie);
        response.addCookie(loginPasswordCookie);
    }
}
