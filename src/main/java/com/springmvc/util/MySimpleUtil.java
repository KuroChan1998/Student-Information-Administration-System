package com.springmvc.util;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author JinZhiyun
 * @ClassName MySimpleUtil
 * @Description //TODO
 * @Date 2019/6/19 22:46
 * @Version 1.0
 **/
public class MySimpleUtil {
    /**
     * @author JinZhiyun
     * @Description 获取用户ip地址
     * @Date 22:53 2019/6/19
     * @Param [request]
     * @return java.lang.String
     **/
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
