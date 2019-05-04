package com.springmvc.service.impl.util;

import java.math.BigInteger;

/**
 * @ClassName DoWithString
 * @Author JinZhiyun
 * @Description 处理从数据库中查到的建议编号reId
 * @Date 2019/5/2 12:54
 * @Version 1.0
 **/
public class DoWithString {
    public static String doWithReid(String reId) {
        if (reId != null) {
            BigInteger b = new BigInteger(reId);
            b = b.add(new BigInteger("1"));
            return b.toString();
        } else {
            reId = reId + "error";
            return reId;
        }
    }
}
