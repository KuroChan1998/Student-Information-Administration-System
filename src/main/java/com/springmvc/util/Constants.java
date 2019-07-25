package com.springmvc.util;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Constants
 * @Author JinZhiyun
 * @Description 存放一些常量
 * @Date 2019/4/14 8:30
 * @Version 1.0
 **/
public class Constants {
    public static final String USER_DEFAULT_ICON_DIR = "/static/custom/img/uploadUserIcon";

    public static final String USER_DEFAULT_ICON_NAME = "user_default_icon.jpg";

    public static final String USER_DEFAULT_ICON_PATH = USER_DEFAULT_ICON_DIR + "/" + USER_DEFAULT_ICON_NAME;

    public static final String EMAILVERIFYCODE_SESSION = "emailVerifyCode";

    public static final String USEREMAIL_SESSION = "userEmail";

    public static final String USER_MODEL = "userInfo";

    public static final String USERINFO_SESSION = "userSessionInfo";

    public static final String USER_ELSEINDENTITY_MODEL = "elseIdentity";

    public static final String TEACHER_ALL_INFO_MODEL = "teaAllInfo";

    public static final String TEACHER_MODEL = "teaInfo";

    public static final String STUDENT_ALL_INFO_MODEL = "stuAllInfo";

    public static final String STUDENT_MODEL = "stuInfo";

    public static final String CLASS_ALL_INFO_MODEL = "classAllInfo";

    public static final String MAJOR_ALL_INFO_MODEL = "majorAllInfo";

    public static final String COLLEGE_ALL_INFO_MODEL = "collegeAllInfo";

    public static final String STUDENT_PERCENT_BY_SEX = "studentPercent";

    public static final List<String> stuDegrees= Arrays.asList("本科","硕士","博士");
}
