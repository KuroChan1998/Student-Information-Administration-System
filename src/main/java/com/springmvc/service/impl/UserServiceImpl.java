package com.springmvc.service.impl;

import com.springmvc.entity.User;
import com.springmvc.service.UserService;
import com.springmvc.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName UserServiceImpl
 * @Author JinZhiyun
 * @Description 用户业务实现
 * @Date 2019/1/25 9:58
 * @Version 1.0
 **/
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl implements UserService {
    @Override
    public User selectUserByNameAndPassword(String userName, String userPassword) {
        User userTmp=userMapper.selectUserByName(userName);
        if (userTmp == null){
            return null;
        } else {
            if (userTmp.getUserPassword().equals(MySecurity.encryptUserPassword(userPassword, userTmp.getUserId()))){
                return userTmp;
            } else {
                return null;
            }
        }
    }

    @Override
    public User updateUserSession(HttpSession session) {
        //获得代理主键，更新当前用户信息至session
        String userId = ((User) session.getAttribute(Constants.USERINFO_SESSION)).getUserId();
        User user = this.selectUserById(userId);
        session.setAttribute(Constants.USERINFO_SESSION, user);
        return user;
    }

    @Override
    public User selectUserById(String userId) {
        return userMapper.selectUserById(userId);
    }

    @Override
    public User selectUserByName(String userName) {
        return userMapper.selectUserByName(userName);
    }

    @Override
    public User selectUserByEmail(String userEmail) {
        return userMapper.selectUserByEmail(userEmail);
    }

    @Override
    public String insertUserRegInfo(User user) {
        if (userMapper.selectUserByName(user.getUserName()) != null) {
            return "regNameExist"; //regNameExist表示用户名已被注册
        }
        if (userMapper.selectUserByEmail(user.getUserEmail()) != null) {
            return "regEmailExist"; //regEmailExist表示邮箱已被注册
        }
        //服务端设置用户uuid
        user.setUserId(UUID.randomUUID().toString().replaceAll("-",""));
        //用户密码md5加密
        user.setUserPassword(MySecurity.encryptUserPassword(user.getUserPassword(),user.getUserId()));
        //设置默认头像
        user.setUserIcon(Constants.USER_DEFAULT_ICON_PATH);
        userMapper.insertUserRegInfo(user);
        return "regSuccess";//return "regSuccess"表示注册成功
    }

    @Override
    public void deleteUserSession(HttpSession session) {
        session.removeAttribute(Constants.USERINFO_SESSION);
    }

    @Override
    public void updateResetPasswordByEmail(String userEmail, String userPassword) {
        userPassword=MySecurity.encryptUserPassword(userPassword,userMapper.selectUserByEmail(userEmail).getUserId());
        userMapper.updateResetPasswordByEmail(userEmail, userPassword);
    }

    @Override
    public List<String> findElseIdentityList(String userIdentity) {
        List<String> list = new ArrayList<>();
        if (userIdentity.equals("学生")) {
            list.add("教师");
            list.add("管理员");
        } else if (userIdentity.equals("教师")) {
            list.add("学生");
            list.add("管理员");
        } else {
            list.add("学生");
            list.add("教师");
        }
        return list;
    }

    @Override
    public String updateResetUserInfo(User user) {
        User tmpUser = userMapper.selectUserByName(user.getUserName());
        if (tmpUser == null || tmpUser.getUserId().equals(user.getUserId())) { //如果用户名为被修改过或者新的用户名未被注册，执行update
            userMapper.updateResetUserInfo(user);
            return "updateSuccess"; //updateSuccess表示昵注册成功
        } else {
            return "updateNameExist";
        }
    }

    @Override
    public void updateResetPasswordByUserId(String userPassword, String userId) {
        userPassword=MySecurity.encryptUserPassword(userPassword,userId);
        userMapper.updateResetPasswordByUserId(userPassword, userId);
    }

    @Override
    public void updateResetEmailByEmail(String userOldEmail, String userNewEmail) {
        userMapper.updateResetEmailByEmail(userOldEmail, userNewEmail);
    }
}
