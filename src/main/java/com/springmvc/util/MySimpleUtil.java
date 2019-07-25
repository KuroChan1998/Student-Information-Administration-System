package com.springmvc.util;

import com.springmvc.dto.other.senior.ObjectTotalGroupByCommonName;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JinZhiyun
 * @ClassName MySimpleUtil
 * @Description 自定义的简易工具类
 * @Date 2019/6/19 22:46
 * @Version 1.0
 **/
public class MySimpleUtil {
    /**
     * @return java.lang.String
     * @author JinZhiyun
     * @Description 获取用户ip地址
     * @Date 22:53 2019/6/19
     * @Param [request]
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

    /**
     * @author JinZhiyun
     * @Description
     * 输入形如[ObjectTotalGroupByCommonName{commonName='硕士'} ObjectTotal{total=30},
     *           ObjectTotalGroupByCommonName{commonName='博士'} ObjectTotal{total=10},
     *              ObjectTotalGroupByCommonName{commonName='本科'} ObjectTotal{total=31}]
     * 输出Map形如['硕士':30, '博士':10, '本科':31]
     * 若输入缺少一项commonName，形如
     *    [ObjectTotalGroupByCommonName{commonName='硕士'} ObjectTotal{total=30},
     *       ObjectTotalGroupByCommonName{commonName='博士'} ObjectTotal{total=10}]
     * 输出Map形如['硕士':30, '博士':10]
     * @Date 16:27 2019/7/23
     * @Param [objectTotals]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    public static Map<String, Object> transferStuTotalToTmpMap(List<ObjectTotalGroupByCommonName> objectTotals) {
        Map<String, Object> tmpMap = new HashMap<>();
        for (String stuDegree : Constants.stuDegrees) {
            boolean found=false; //表示在结果集中是否找到当前stuDegree
            for (ObjectTotalGroupByCommonName objectTotal:objectTotals){
                if (stuDegree.equals(objectTotal.getCommonName())){
                    tmpMap.put(stuDegree, objectTotal.getTotal());
                    found=true;
                    break;
                }
            }
            if (!found){
                tmpMap.put(stuDegree, 0);
            }
        }
        return tmpMap;
    }

    /**
     * @author JinZhiyun
     * @Description
     * 输入形如objectTotals:
     *          [ObjectTotalGroupByCommonName{commonName='副教授'} ObjectTotal{total=2},
     *              ObjectTotalGroupByCommonName{commonName='研究员'} ObjectTotal{total=2},
     *                  ObjectTotalGroupByCommonName{commonName='副研究员'} ObjectTotal{total=2},
     *                      ObjectTotalGroupByCommonName{commonName='高级工程师'} ObjectTotal{total=2},
     *                          ObjectTotalGroupByCommonName{commonName='教授'} ObjectTotal{total=2}]
     *        titleNames: [副教授, 副研究员, 教授, 研究员, 高级工程师]
     * 输出List形如[海洋船舶与建筑工程学院, 2, 2, 2, 2, 2]
     * 若输入缺少一项commonName，
     * 输出形如 [数学科学学院, 2, 1, 5, 2, 0]
     * @Date 17:36 2019/7/24
     * @Param [objectTotals, titleNames]
     * @return java.util.List<java.lang.Object>
     **/
    public static List<Object> transferTeaTotalToTmpMap(List<ObjectTotalGroupByCommonName> objectTotals, List<String> titleNames) {
        List<Object> sourceTmp = new ArrayList<>();
        for (String titleName : titleNames) {
            boolean found = false; //表示在结果集中是否找到当前stuDegree
            for (ObjectTotalGroupByCommonName objectTotal : objectTotals) {
                if (titleName.equals(objectTotal.getCommonName())) {
                    sourceTmp.add(objectTotal.getTotal());
                    found = true;
                    break;
                }
            }
            if (!found) {
                sourceTmp.add(0);
            }
        }
        return sourceTmp;
    }

}
