package com.springmvc.dto;

/**
 * @ClassName Cookies
 * @Author JinZhiyun
 * @Description 自定义Cookies bean
 * @Date 2019/4/18 10:31
 * @Version 1.0
 **/
public class Cookies {
    private String loginAccount;

    private String loginPassword;

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
}
