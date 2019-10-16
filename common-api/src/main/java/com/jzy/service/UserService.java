package com.jzy.service;

import com.jzy.dto.other.UserLogin;
import com.jzy.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author JinZhiyun
 * @IntefaceName UserService
 * @Description 用户业务接口
 * @Date 2019/6/14 12:52
 * @Version 1.0
 **/
public interface UserService {
    /**
     * @author JinZhiyun
     * @description 对输入的用户名密码，检查redis缓存和mysql数据库，返回相应的封装结果UserLogin
     * @date 21:21 2019/9/7
     * @Param [user]
     * @return com.jzy.dto.other.UserLogin
     **/
    UserLogin userLoginTest(User user);

    /**
     * @author JinZhiyun
     * @Description 用户登录验证服务
     *             注意，根据前台的输入法则，这里输入的userName不限于用户的用户名userName字段，也可以是用户邮箱等唯一标识
     * @Date 16:54 2019/6/5
     * @Param [userName, userPassword]
     * @return com.jzy.entity.User
     **/
    User selectUserByNameAndPassword(String userName, String userPassword);

    /**
     * @author JinZhiyun
     * @Description 更新当前用户的Session
     * @Date 16:54 2019/6/5
     * @Param [session]
     * @return com.jzy.entity.User
     **/
    User updateUserSession(HttpSession session);

    /**
     * @return com.jzy.entity.User
     * @Author JinZhiyun
     * @Description 根据用户id查询用户的全部信息
     * @Date 23:18 2019/4/10
     * @Param [userId]
     **/
    User selectUserById(String userId);

    /**
     * @author JinZhiyun
     * @Description 根据用户名查询用户信息
     * @Date 8:09 2019/6/6
     * @Param [userName]
     * @return com.jzy.entity.User
     **/
    User selectUserByName(String userName);

    /**
     * @Author JinZhiyun
     * @Description 根据用户邮箱查询用户信息
     * @Date 11:21 2019/5/12
     * @Param [userEmail]
     * @return com.jzy.entity.User
     **/
    User selectUserByEmail(String userEmail);

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 用户注册验证服务
     * @Date 10:47 2019/4/8
     * @Param [user]
     **/
    String insertUserRegInfo(User user);

    /**
     * @author JinZhiyun
     * @Description 注销登录用户session
     * @Date 8:30 2019/6/6
     * @Param [session]
     * @return void
     **/
    void deleteUserSession(HttpSession session);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 忘记密码时由邮箱重置密码服务
     * @Date 23:16 2019/4/10
     * @Param [userEmail, userPassword]
     **/
    void updateResetPasswordByEmail(String userEmail, String userPassword);

    /**
     * @author JinZhiyun
     * @Description 返回当前身份相对所有身份的补集
     * @Date 13:43 2019/6/6
     * @Param [userIdentity]
     * @return java.util.List<java.lang.String>
     **/
    List<String> findElseIdentityList(String userIdentity);

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 修改用户基本资料
     * @Date 23:20 2019/4/10
     * @Param [user]
     **/
    String updateResetUserInfo(User user);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 重置当前用户密码
     * @Date 23:20 2019/4/10
     * @Param [userPassword, userId]
     **/
    void updateResetPasswordByUserId(String userPassword, String userId);

    /**
     * @author JinZhiyun
     * @description 修改用户密码，返回控制层需要的字符串
     * @date 13:09 2019/9/10
     * @Param [userOldPassword, userNewPasswordUser, user]
     * @return java.lang.String
     **/
    String updateResetUserPassword(String userOldPassword, String userNewPassword, User user);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 更改绑定邮箱
     * @Date 10:27 2019/4/11
     * @Param [userOldEmail, userNewEmail]
     **/
    void updateResetEmailByEmail(String userOldEmail, String userNewEmail);
}