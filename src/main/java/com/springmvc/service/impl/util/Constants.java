package com.springmvc.service.impl.util;

/**
 * @ClassName Constants
 * @Author JinZhiyun
 * @Description 存放一些常量
 * @Date 2019/4/14 8:30
 * @Version 1.0
 **/
public class Constants {
    public static String USER_DEFAULT_ICON_DIR="/static/uploadUserIcon";

    public static String USER_DEFAULT_ICON_NAME="user_default_icon.jpg";

    public static String USER_DEFAULT_ICON_PATH=USER_DEFAULT_ICON_DIR+"/"+USER_DEFAULT_ICON_NAME;

    public static String USERID_SESSION="userId";

    public static String EMAIL_VERIFYCODE_SESSION="emailVerifyCode";

    public static String USEREMAIL_SESSION="userEmail";

    public static String USER_MODEL="userInfo";

    public static String USERINFO_SESSION ="userSessionInfo";

    public static String USER_ELSEINDENTITY_MODEL="elseIdentity";

    public static String TEACHER_MODEL="teaInfo";

    public static String STUDENT_MODEL="stuInfo";

    public static String USERID_COOKIE="loginAccount";

    public static String USERPASSWORD_COOKIE="loginPassword";
}
