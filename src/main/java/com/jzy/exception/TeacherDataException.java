package com.jzy.exception;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName TeacherDataException
 * @description 教师数据异常
 * @date 2019/9/11 18:39
 **/
public class TeacherDataException extends DataException {
    public TeacherDataException() {
    }

    public TeacherDataException(String message) {
        super(message);
    }
}
