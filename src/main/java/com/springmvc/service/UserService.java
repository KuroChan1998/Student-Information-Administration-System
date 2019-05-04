package com.springmvc.service;

import com.springmvc.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author JinZhiyun
 * @Description //TODO
 * @Date 10:43 2019/4/8
 * @Param
 * @return
 **/
public interface UserService {
    /**
     * @return int
     * @Author JinZhiyun
     * @Description 用户登录验证服务
     * @Date 10:10 2019/4/8
     * @Param [userId, userPassword]
     **/
    int findUserByIdAndPassword(String userId, String userPassword);

    /**
     * @return int
     * @Author JinZhiyun
     * @Description 用户注册验证服务
     * @Date 10:47 2019/4/8
     * @Param [user]
     **/
    int insertUserRegInfo(User user);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 忘记密码时由邮箱重置密码服务
     * @Date 23:16 2019/4/10
     * @Param [userEmail, userPassword]
     **/
    void updateResetPasswordByEmail(String userEmail, String userPassword);

    /**
     * @return
     * @Author JinZhiyun
     * @Description 返回邮箱符合的用户个数
     * @Date 23:17 2019/4/10
     * @Param
     **/
    int findUserByEmail(String userEmail);

    /**
     * @return com.springmvc.entity.User
     * @Author JinZhiyun
     * @Description 根据用户名查询用户的全部信息
     * @Date 23:18 2019/4/10
     * @Param [userId]
     **/
    User selectUserById(String userId);

    /**
     * @return java.lang.String 返回验证码
     * @Author JinZhiyun
     * @Description 发送邮箱验证码业务
     * @Date 14:44 2019/4/8
     * @Param [userEmail]
     **/
    String sendVerifyCodeToEmail(String userEmail);

    /**
     * @return java.util.List<java.lang.String>
     * @Author JinZhiyun
     * @Description 返回当前身份相对所有身份的补集
     * @Date 23:19 2019/4/10
     * @Param [identity]
     **/
    List<String> elseIdentityList(String identity);

    /**
     * @return java.lang.String 返回上传的图片存储的相对路径
     * @Author JinZhiyun
     * @Description 头像上传业务
     * @Date 15:33 2019/4/10
     * @Param [file, request, UserId]
     **/
    String uploadUserIcon(MultipartFile file, HttpServletRequest request, String UserId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 修改用户基本资料
     * @Date 23:20 2019/4/10
     * @Param [user]
     **/
    void updateResetUserInfo(User user);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 重置当前用户密码
     * @Date 23:20 2019/4/10
     * @Param [userPassword, userId]
     **/
    void updateResetPasswordByUserId(String userPassword, String userId);

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 获取当前用户的密码
     * @Date 23:21 2019/4/10
     * @Param [userId]
     **/
    String selectUserPasswordById(String userId);

    /**
     * @return com.springmvc.entity.User
     * @Author JinZhiyun
     * @Description 更新当前用户的Session
     * @Date 23:21 2019/4/10
     * @Param [session, request]
     **/
    User updateUserSession(HttpSession session, HttpServletRequest request);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 更改绑定邮箱
     * @Date 10:27 2019/4/11
     * @Param [userOldEmail, userNewEmail]
     **/
    void updateResetEmailByEmail(String userOldEmail, String userNewEmail);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 注销登录用户session
     * @Date 9:24 2019/4/19
     * @Param [session, request]
     **/
    void destroyUserSession(HttpSession session, HttpServletRequest request);

    /**
     * @return boolean
     * @Author JinZhiyun
     * @Description 判断当前登录的用户是否为管理员
     * @Date 10:15 2019/4/20
     * @Param [session, request]
     **/
    boolean ifAdmin(HttpSession session, HttpServletRequest request);
}