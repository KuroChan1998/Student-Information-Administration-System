package com.springmvc.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @ClassName MySecurity
 * @Author JinZhiyun
 * @Description shiro加密等工具类
 * @Date 2019/5/12 11:02
 * @Version 1.0
 **/
public class MySecurity {
    private static int hashIterations=5;

    /**
     * @Author JinZhiyun
     * @Description md5加密，加密内容source,带盐加密salt，指定加密次数：hashIterations
     * @Date 11:06 2019/5/12
     * @Param [source, salt]
     * @return java.lang.String
     **/
    public static String encryptUserPassword(String source, String salt){
        return new Md5Hash(source, salt, hashIterations).toBase64();
    }
}
