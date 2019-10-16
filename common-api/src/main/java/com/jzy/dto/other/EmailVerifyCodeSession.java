package com.jzy.dto.other;

import java.io.Serializable;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName EmailVerifyCodeSession
 * @description 验证邮箱验证码后，存入session的对象，若服务端校验成功flag置true，
 *      防止攻击者通过将json数据改成verifyCodeCorrect来绕过验证码
 * @date 2019/10/11 12:13
 **/
public class EmailVerifyCodeSession implements Serializable {
    private static final long serialVersionUID = 5352564703274820500L;

    private String userEmail;

    private boolean flag;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public EmailVerifyCodeSession(String userEmail, boolean flag) {
        this.userEmail = userEmail;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "EmailVerifyCodeSession{" +
                "userEmail='" + userEmail + '\'' +
                ", flag=" + flag +
                '}';
    }
}
