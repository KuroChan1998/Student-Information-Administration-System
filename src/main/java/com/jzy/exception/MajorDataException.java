package com.jzy.exception;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName MajorDataException
 * @description 专业数据异常
 * @date 2019/9/11 18:40
 **/
public class MajorDataException extends DataException {
    public MajorDataException() {
    }

    public MajorDataException(String message) {
        super(message);
    }
}
