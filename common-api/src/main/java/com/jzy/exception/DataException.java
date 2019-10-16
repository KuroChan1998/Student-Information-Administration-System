package com.jzy.exception;

/**
 * @author JinZhiyun
 * @description 数据异常
 * @date 14:50 2019/9/10
 * @Param
 * @return
 **/
public class DataException extends RuntimeException {
    public DataException() {
        super();
    }

    public DataException(String message) {
        super(message);
    }
}
