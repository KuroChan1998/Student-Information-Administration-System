package com.springmvc.dao;

import com.springmvc.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author JinZhiyun
 * @IntefaceName UserMapper
 * @Description 用户业务持久层接口
 * @Date 2019/6/14 13:00
 * @Version 1.0
 **/
@Repository
public interface UserMapper {
    /**
     * @return int
     * @Author JinZhiyun
     * @Description 根据用户名密码查询符合条件的用户数量
     * @Date 9:40 2019/4/19
     * @Param [userId, userPassword]
     **/
    User selectUserByNameAndPassword(@Param("userName") String userName, @Param("userPassword") String userPassword);

    /**
     * @return com.springmvc.entity.User
     * @Author JinZhiyun
     * @Description 根据用户UUId查询用户信息
     * @Date 9:53 2019/4/19
     * @Param [userId]
     **/
    User selectUserById(@Param("userId") String userId);

    /**
     * @author JinZhiyun
     * @Description 根据用户名查询用户信息
     * @Date 8:09 2019/6/6
     * @Param [userName]
     * @return com.springmvc.entity.User
     **/
    User selectUserByName(@Param("userName") String userName);

    /**
     * @Author JinZhiyun
     * @Description 根据用户邮箱查询用户信息
     * @Date 11:21 2019/5/12
     * @Param [userEmail]
     * @return com.springmvc.entity.User
     **/
    User selectUserByEmail(@Param("userEmail") String userEmail);

    /**
     * @return int
     * @Author JinZhiyun
     * @Description 向用户表插入注册用户信息，成功返回1
     * @Date 9:43 2019/4/19
     * @Param [user]
     **/
    int insertUserRegInfo(User user);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 更新邮箱对应的用户密码
     * @Date 9:47 2019/4/19
     * @Param [userEmail, userPassword]
     **/
    void updateResetPasswordByEmail(@Param("userEmail") String userEmail, @Param("userPassword") String userPassword);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 更新用户信息
     * @Date 9:48 2019/4/19
     * @Param [user]
     **/
    void updateResetUserInfo(User user);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 更新当前用户密码
     * @Date 9:51 2019/4/19
     * @Param [userPassword, userId]
     **/
    void updateResetPasswordByUserId(@Param("userPassword") String userPassword, @Param("userId") String userId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 更新用户邮箱
     * @Date 9:53 2019/4/19
     * @Param [userOldEmail, userNewEmail]
     **/
    void updateResetEmailByEmail(@Param("userOldEmail") String userOldEmail, @Param("userNewEmail") String userNewEmail);
}