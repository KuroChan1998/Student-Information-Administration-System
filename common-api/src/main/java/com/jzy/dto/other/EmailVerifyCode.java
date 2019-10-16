package com.jzy.dto.other;

import com.jzy.util.other.MyTimeUtil;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author JinZhiyun
 * @ClassName EmailVerifyCode
 * @Description 邮箱验证码传输类，v1.2.0中舍弃其计时功能使用，通过redis缓存过期时间实现
 * @Date 2019/6/6 9:27
 * @Version 1.0
 **/
public class EmailVerifyCode implements Serializable {
    private static final long serialVersionUID = -7615516438892167645L;

    private String email;

    private String code;

    private long initTime;

    private static long validTimeMilliseconds = MyTimeUtil.VALID_TIME_5_MIN; //5min，单位毫秒

    private static long validTimeSeconds = validTimeMilliseconds / 1000; //单位：秒

    private static long validTimeMinutes = validTimeSeconds / 60; //单位：分钟

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getInitTime() {
        return initTime;
    }

    public void setInitTime(long initTime) {
        this.initTime = initTime;
    }

    public static long getValidTimeMilliseconds() {
        return validTimeMilliseconds;
    }


    public static long getValidTimeSeconds() {
        return validTimeSeconds;
    }

    public static long getValidTimeMinutes() {
        return validTimeMinutes;
    }

    private static void setValidTimeMilliseconds(long validTimeMilliseconds) {
        EmailVerifyCode.validTimeMilliseconds = validTimeMilliseconds;
    }

    private static void setValidTimeSeconds(long validTimeSeconds) {
        EmailVerifyCode.validTimeSeconds = validTimeSeconds;
    }

    private static void setValidTimeMinutes(long validTimeMinutes) {
        EmailVerifyCode.validTimeMinutes = validTimeMinutes;
    }

    public EmailVerifyCode(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public EmailVerifyCode(String email, String code, long initTime) {
        this.email = email;
        this.code = code;
        this.initTime = initTime;
    }

    public EmailVerifyCode() {
    }


    /**
     * @return void
     * @author JinZhiyun
     * @description 重置验证码类的有效时间
     * @date 9:26 2019/9/9
     * @Param [validTime, timeUnit]
     **/
    public static void resetValidTime(long validTime, TimeUnit timeUnit) {
        switch (timeUnit) {
            case MILLISECONDS:
                setValidTimeMilliseconds(validTime);
                setValidTimeSeconds(validTime / 1000);
                setValidTimeMinutes(validTime / (1000 * 60));
                break;
            case SECONDS:
                setValidTimeSeconds(validTime);
                setValidTimeMilliseconds(validTime * 1000);
                setValidTimeMinutes(validTime / 60);
                break;
            case MINUTES:
                setValidTimeMinutes(validTime);
                setValidTimeSeconds(validTime * 60);
                setValidTimeMilliseconds(validTime * 1000 * 60);
                break;
            default:
                setValidTimeMilliseconds(MyTimeUtil.VALID_TIME_5_MIN);
                setValidTimeSeconds(MyTimeUtil.VALID_TIME_5_MIN / 1000);
                setValidTimeMinutes(MyTimeUtil.VALID_TIME_5_MIN / (1000 * 60));

        }
    }

    /**
     * @return boolean
     * @author JinZhiyun
     * @Description 检查当前存储的验证码是否还有效，该方法在v1.2.0版本后舍弃，有效时间验证交给redis
     * @Date 9:58 2019/6/6
     * @Param []
     **/
    @Deprecated
    public boolean isValid() {
        return MyTimeUtil.cmpTime(initTime, validTimeMilliseconds);
    }

    public static void main(String[] args) {
        EmailVerifyCode.resetValidTime(60000000,TimeUnit.MINUTES);
        System.out.println(EmailVerifyCode.getValidTimeMilliseconds());
        System.out.println(EmailVerifyCode.getValidTimeMinutes());
        System.out.println(EmailVerifyCode.getValidTimeSeconds());
    }

    @Override
    public String toString() {
        return "EmailVerifyCode{" +
                "email='" + email + '\'' +
                ", code='" + code + '\'' +
                ", initTime=" + initTime +
                '}';
    }
}
