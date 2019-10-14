package com.jzy.util.user;

import com.jzy.entity.User;
import com.jzy.util.MyDatabaseUtil;
import com.jzy.util.other.MySimpleUtil;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName UserUtil
 * @description 验证服务端接收到的用户dto类型是否合法，与数据库保持一致（必须），与前端输入要求保持一致
 * @date 2019/9/9 14:59
 **/
public class UserUtil extends MyDatabaseUtil implements UserRedis, UserModel, UserSession {
    private static final int USER_NAME_MAX_LENGTH = 12;

    private static final int USER_NICKNAME_MAX_LENGTH = 12;

    private static final int USER_PASSWORD_MIN_LENGTH = 6;
    private static final int USER_PASSWORD_MAX_LENGTH = 50;

    public static final List<String> VALID_USER_IDENTITIES = Arrays.asList("学生", "教师");

    public static final List<String> USER_IDENTITIES = Arrays.asList("管理员", "学生", "教师");

    private static final int USER_ICON_MAX_LENGTH = 500;

    private static final int USER_EMAIL_MAX_LENGTH = 100;

    private static final int USER_PHONE_MAX_LENGTH = 11;

    /**
     * 向外界提供一些用户服务所需的常量
     **/
    public static final String USER_DEFAULT_ICON_DIR = "/static/custom/img/uploadUserIcon";

    public static final String USER_DEFAULT_ICON_NAME = "user_default_icon.jpg";

    public static final String USER_DEFAULT_ICON_PATH = USER_DEFAULT_ICON_DIR + "/" + USER_DEFAULT_ICON_NAME;

    private UserUtil() {
    }


//    public static boolean isNull(User user) {
        /*|| (user.getUserId() == null && user.getUserName() == null && user.getUserNickname() == null
                && user.getUserPassword() == null && user.getUserIdentity() == null && user.getUserEmail() == null
                && user.getUserIcon() == null && user.getUserPhone() == null)*/
//        return user == null;
//    }

    /**
     * 保证User的每个属性是否与数据库一直，符合规范
     **/
    public static boolean isValidUserId(String userId) {
        return isValidUUID(userId);
    }

    public static boolean isValidUserName(String userName) {
        return !StringUtils.isEmpty(userName) && userName.length() <= USER_NAME_MAX_LENGTH;
    }

    public static boolean isValidUserNickname(String userNickname) {
        return !StringUtils.isEmpty(userNickname) && userNickname.length() <= USER_NICKNAME_MAX_LENGTH;
    }

    public static boolean isValidUserPassword(String userPassword) {
        return userPassword != null && userPassword.length() >= USER_PASSWORD_MIN_LENGTH && userPassword.length() <= USER_PASSWORD_MAX_LENGTH;
    }

    public static boolean isValidUserIdentity(String userIdentity) {
        return USER_IDENTITIES.contains(userIdentity);
    }


    public static boolean isValidUserIcon(String userIcon) {
        return userIcon == null || userIcon.length() <= USER_ICON_MAX_LENGTH;
    }

    public static boolean isValidUserEmail(String userEmail) {
        return MySimpleUtil.isEmail(userEmail) && userEmail.length() <= USER_EMAIL_MAX_LENGTH;
    }

    public static boolean isValidUserPhone(String userPhone) {
        return userPhone == null || userPhone.length() <= USER_PHONE_MAX_LENGTH;
    }

    /**
     * @return boolean
     * @author JinZhiyun
     * @description 用户user对象的全部字段都合法
     * @date 12:47 2019/9/10
     * @Param [user]
     **/
    public static boolean isValidUserAllInfo(User user) {
        return !isNull(user) && isValidUserId(user.getUserId()) && isValidUserName(user.getUserName()) && isValidUserNickname(user.getUserNickname())
                && isValidUserPassword(user.getUserPassword()) && isValidUserIdentity(user.getUserIdentity()) && isValidUserNameByIdentity(user)
                && isValidUserIcon(user.getUserIcon()) && isValidUserEmail(user.getUserEmail()) && isValidUserPhone(user.getUserPhone());
    }

    /**
     * @return boolean
     * @author JinZhiyun
     * @description 判断用户登录时服务端接收到的user参数是否合法
     * @date 18:21 2019/9/9
     * @Param [user]
     **/
    public static boolean isValidUserLoginNameAndPassword(User user) {
        return !isNull(user) && !StringUtils.isEmpty(user.getUserName()) && !StringUtils.isEmpty(user.getUserPassword());
    }

    /**
     * @return boolean
     * @author JinZhiyun
     * @description 根据用户名和用户身份的关系，判断用户名是否合法
     * @date 22:36 2019/9/9
     * @Param [user]
     **/
    private static boolean isValidUserNameByIdentity(User user) {
        if (isNull(user) || StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getUserIdentity())) {
            return false;
        }
        if (user.getUserIdentity().equals(USER_IDENTITIES.get(1))) { //学生身份、用户名首字符为5
            return user.getUserName().charAt(0) == '5';
        } else if (user.getUserIdentity().equals(USER_IDENTITIES.get(2))) { //教师身份、用户名首字符为1
            return user.getUserName().charAt(0) == '1';
        } else if (user.getUserIdentity().equals(USER_IDENTITIES.get(0))) { //default：管理员
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return boolean
     * @author JinZhiyun
     * @description 判断用户注册时服务端接收到的user参数是否合法
     * @date 23:22 2019/9/9
     * @Param [user]
     **/
    public static boolean isValidUserRegInfo(User user) {
        //userId为代理主键不用校验，不在前端生成，无须担心其在服务端的安全问题
        return user != null && isValidUserName(user.getUserName()) && isValidUserNickname(user.getUserNickname()) && isValidUserPassword(user.getUserPassword())
                && VALID_USER_IDENTITIES.contains(user.getUserIdentity()) && isValidUserNameByIdentity(user) && isValidUserIcon(user.getUserIcon())
                && isValidUserEmail(user.getUserEmail()) && isValidUserPhone(user.getUserPhone());
    }

    /**
     * @return boolean
     * @author JinZhiyun
     * @description 判断用户修改用户信息时服务端接收到的user参数是否合法，邮箱和密码字段不用判断
     * @date 12:49 2019/9/10
     * @Param [user]
     **/
    public static boolean isValidUserResetInfo(User user) {
        //userId为代理主键不用校验，不在前端生成，无须担心其在服务端的安全问题
        return !isNull(user) && isValidUserId(user.getUserId()) && isValidUserName(user.getUserName()) && isValidUserNickname(user.getUserNickname())
                && isValidUserIdentity(user.getUserIdentity()) && isValidUserNameByIdentity(user) && isValidUserIcon(user.getUserIcon())
                && isValidUserPhone(user.getUserPhone());
    }
}
