package com.jzy.util;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName MyDatabaseUtil
 * @description 实体类工具类的基类，提供基本方法和参数
 * @date 2019/9/10 15:26
 **/
public abstract class MyDatabaseUtil {
    /**
     * 列出所有表的主键的长度要求信息等
     **/
    protected static final int UUID_LENGTH=32;


    /**
     * @return boolean
     * @author JinZhiyun
     * @description x是否非空且为正，即满足mysql自增主键的特性
     * @date 10:13 2019/9/11
     * @Param [x]
     **/
    protected static boolean isNotNullAndPositive(Integer x) {
        return x != null && x > 0;
    }

    protected static boolean isNull(Object o) {
        return o == null;
    }

    /**
     * @author JinZhiyun
     * @description 输入串是否满足uuid格式
     * @date 10:25 2019/9/11
     * @Param [id]
     * @return boolean
     **/
    protected static boolean isValidUUID(String id){
        return id != null && id.length() == UUID_LENGTH;
    }
}
