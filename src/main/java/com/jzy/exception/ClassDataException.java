package com.jzy.exception;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName ClassDataException
 * @description 班级数据异常
 * @date 2019/9/11 18:39
 **/
public class ClassDataException extends DataException {
    public ClassDataException() {
    }

    public ClassDataException(String message) {
        super(message);
    }
}
