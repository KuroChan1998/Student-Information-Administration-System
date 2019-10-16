package com.jzy.util.user;

import com.jzy.util.MySession;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName UserSession
 * @description 用户类的session模块接口，提供一些基本方法和常量
 * @date 2019/9/10 13:19
 **/
public interface UserSession extends MySession {
    String USER_EMAIL_SESSION = "userEmail";

    String USER_INFO_SESSION = "userSessionInfo";
}
