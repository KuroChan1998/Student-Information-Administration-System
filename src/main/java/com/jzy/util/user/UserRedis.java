package com.jzy.util.user;

import com.jzy.util.MyRedis;

/**
 * @author JinZhiyun
 * @version 1.0
 * @IntefaceName UserRedis
 * @description 用户类的redis模块接口，提供一些基本方法和常量
 * @date 2019/9/10 13:15
 **/
public interface UserRedis extends MyRedis {
    String KEY_USER="user";

    String KEY_USER_LOGIN=KEY_USER+":login";

    String KEY_USER_LOGIN_FAIL=KEY_USER_LOGIN+":fail";

    String KEY_USER_LOGIN_NAMEANDPASSWORD =KEY_USER_LOGIN+":nameAndPassword";

    String KEY_USER_VERIFYCODE=KEY_USER+":verifyCode";

    String KEY_USER_VERIFYCODE_EMAIL=KEY_USER_VERIFYCODE+":email";

}
