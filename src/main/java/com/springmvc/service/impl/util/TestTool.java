package com.springmvc.service.impl.util;

/**
 * @ClassName TestTool
 * @Author JinZhiyun
 * @Description //TODO
 * @Date 2019/4/29 10:28
 * @Version 1.0
 **/
public class TestTool {
    public static boolean isDigit(String strNum) {
        return strNum.matches("[0-9]{1,}");
    }
}
