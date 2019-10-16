package com.jzy.exception;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName StudentDataException
 * @description 用户数据异常
 * @date 2019/9/10 14:52
 **/
public class StudentDataException extends DataException {
    public StudentDataException() {
    }

    public StudentDataException(String message) {
        super(message);
    }
}
