package com.springmvc.entity;

import java.io.Serializable;

/**
 * @ClassName User
 * @Author JinZhiyun
 * @Description 用户实体类
 * @Date 2019/1/25 9:56
 * @Version 1.0
 **/
public class User implements Serializable {
    private static final long serialVersionUID = 6193927156543042624L;

    //用户表代理主键uuid
    private String userId;

    //用户名，唯一
    private String userName;

    //用户昵称
    private String userNickname;

    //用户密码
    private String userPassword;

    //用户身份
    private String userIdentity;

    //用户头像存储相对路径
    private String userIcon;

    //用户邮箱，唯一
    private String userEmail;

    //用户手机
    private String userPhone;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userIdentity='" + userIdentity + '\'' +
                ", userIcon='" + userIcon + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                '}';
    }
}
