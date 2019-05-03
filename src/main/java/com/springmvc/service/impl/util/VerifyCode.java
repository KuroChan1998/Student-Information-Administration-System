package com.springmvc.service.impl.util;

import java.util.Random;

/**
 * @ClassName VerifyCode
 * @Author JinZhiyun
 * @Description 验证码相关业务类
 * @Date 2019/4/8 15:51
 * @Version 1.0
 **/
public class VerifyCode {
    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description //生成六位随机数
     * @Date 15:54 2019/4/8
     * @Param []
     **/
    public static String randomCode() {
        String code = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {

            int r = random.nextInt(10); //每次随机出一个数字（0-9）

            code = code + r;  //把每次随机出的数字拼在一起

        }
        return code;
    }
}
