package com.jzy.util.user;

import com.jzy.util.MyModel;

/**
 * @author JinZhiyun
 * @version 1.0
 * @IntefaceName UserModel
 * @description 用户类的model模块接口，提供一些基本方法和常量
 * @date 2019/9/10 13:19
 **/
public interface UserModel extends MyModel {
    String USER_MODEL = "userInfo";

    String USER_ELSE_IDENTITY_MODEL = "elseIdentity";
}
