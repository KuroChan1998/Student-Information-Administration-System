package com.springmvc.dao;

import com.springmvc.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import others.UnitTestBase;

/**
 * @ClassName UserMapperTest
 * @Author JinZhiyun
 * @Description //TODO
 * @Date 2019/4/8 9:27
 * @Version 1.0
 **/
public class UserMapperTest extends UnitTestBase {
    @Autowired
    private UserMapper userMapper;//注入需要测试的Service或Mapper，直接调用方法测试即可


    @Test
    public void findIfIdExist() {
        String userId="516030910428";
//        String userId="xxx";
        int i=userMapper.findIfIdExist(userId);
        System.out.println(i);
    }

    @Test
    public void findIfNicknameExist() {
        String nickName="超级酷乐酱";
        int i=userMapper.findIfNicknameExist(nickName);
        System.out.println(i);
    }

    @Test
    public void updateResetPasswordByEmail(){
        String email="929703621@qq.com";
        String password="12345678";
        userMapper.updateResetPasswordByEmail(email,password);

    }

    @Test
    public void selectUserById(){
        String userId="516030910428";
        System.out.println(userMapper.selectUserById(userId));
    }

    @Test
    public void updateResetUserInfo(){
        User user=new User();
        user.setUserId("51630910428");
        user.setUserNickname("超级酷乐酱2");
        user.setUserIdentity("学生");
        userMapper.updateResetUserInfo(user);
    }

    @Test
    public void updateResetPasswordByUserId(){
        String userId="516030910428";
        String userPassword=">?<<<<";
        userMapper.updateResetPasswordByUserId(userPassword,userId);
    }

    @Test
    public void updateResetEmailByEmail(){
        String userOldEmail="929703621@qq.com";
        String userNewEmail="2419759134@qq.com";
        userMapper.updateResetEmailByEmail(userOldEmail,userNewEmail);
    }
}