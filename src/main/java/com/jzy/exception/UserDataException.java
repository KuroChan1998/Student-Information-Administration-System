package com.jzy.exception;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName UserDataException
 * @description 用户数据异常
 * @date 2019/9/9 15:37
 **/
public class UserDataException extends DataException {
    public UserDataException() {
    }

    public UserDataException(String message) {
        super(message);
    }
}
