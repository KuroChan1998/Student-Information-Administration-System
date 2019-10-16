package com.jzy.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jzy.dao.UserMapper;
import com.jzy.dto.other.UserLogin;
import com.jzy.entity.User;
import com.jzy.service.*;
import com.jzy.util.other.MySecurity;
import com.jzy.util.user.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName UserServiceImpl
 * @Author JinZhiyun
 * @Description 用户业务实现
 * @Date 2019/1/25 9:58
 * @Version 1.0
 **/
@Service("userService")
@Transactional
@com.alibaba.dubbo.config.annotation.Service
public class UserServiceImpl implements UserService {
    @Reference
    private StudentService studentService;

    @Reference
    private TeacherService teacherService;

    @Reference
    private ClassService classService;

    @Reference
    private MajorService majorService;

    @Reference
    private CollegeService collegeService;

    @Reference
    private GradeService gradeService;

    @Reference
    private TitleService titleService;

    @Autowired
    private OtherService otherService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserLogin userLoginTest(User user) {
        UserLogin userLogin = new UserLogin();
        ValueOperations<String, Object> vOps = redisTemplate.opsForValue();

        String key = UserLogin.getUserLoginFailKey(user.getUserName());
        if (redisTemplate.hasKey(key) && redisTemplate.getExpire(key) != -1) { //检查当前用户名是否处于冻结状态
            userLogin.setLoginFlag(false);
            userLogin.setLockedFlag(true);
            userLogin.setWrongTimes(UserLogin.DEFAULT_WRONG_TIMES);
            userLogin.setTimeRemaining(redisTemplate.getExpire(key, TimeUnit.MINUTES) + 1); //getExpire下取整，这里所以取+1
            return userLogin;
        }

        //没有冻结
        User userSessionInfo = selectUserByNameAndPassword(user.getUserName(), user.getUserPassword());
        if (userSessionInfo == null) {//登录失败
            userLogin.setLoginFlag(false);
            if (!redisTemplate.hasKey(key)) {
                vOps.set(key, "1"); //如果当前用户名没有登录失败次数的缓存，设为第一次登录失败
            } else {
                /*
                 * 登录失败次数加一，这里没有使用increment方法
                 * 因为redisTemplate配置中value序列化使用了GenericJackson2JsonRedisSerializer，这会导致该方法报String转换错误
                 */
                int wrongTimes = Integer.parseInt((String) vOps.get(key));
                vOps.set(key, wrongTimes + 1 + "");
            }
            int wrongTimes = Integer.parseInt((String) vOps.get(key));
            userLogin.setWrongTimes(wrongTimes);
            if (userLogin.getWrongTimes() == UserLogin.DEFAULT_WRONG_TIMES) { //如果当前用户名连续五次输错密码
                redisTemplate.expire(key, UserLogin.DEFAULT_BASE_DELAY_TIME, TimeUnit.MINUTES);//设置当前用户冻结冻结15分钟
            }
        } else { //登录成功
            userLogin.setLoginFlag(true);
            userLogin.setUser(userSessionInfo);
            vOps.set(key, "0");//重置当前用户登录失败次数缓存值为0
        }
        return userLogin;
    }

    @Override
    public User selectUserByNameAndPassword(String userName, String userPassword) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(userPassword)) { //服务端输入校验
            return null;
        }
        //先检查redis缓存有无该用户登录信息，有则不必置数据库查询
        HashOperations<String, String, Object> hOps = redisTemplate.opsForHash();
        final String baseKey = UserUtil.KEY_USER_LOGIN_NAMEANDPASSWORD;
        if (!hOps.hasKey(baseKey, userName)) { //没有缓存，去mysql查询
            System.out.println("走数据库");
            User userTmp;
            if (UserUtil.isValidUserEmail(userName)) { //先根据输入userName格式猜测输入是否为用户邮箱，若是通过邮箱验证登录
                userTmp = userMapper.selectUserByEmail(userName);
            } else {
                userTmp = userMapper.selectUserByName(userName);
            }
            if (userTmp == null) {
                return null;
            } else {
                String encryptedPasswordInput = MySecurity.encryptUserPassword(userPassword, userTmp.getUserId());
                if (userTmp.getUserPassword().equals(encryptedPasswordInput)) {
                    //登录成功，同时把当前User对象设置redis缓存
                    hOps.put(baseKey, userName, userTmp);
                    return userTmp;
                } else {
                    return null;
                }
            }
        } else {//从缓存中校验
            System.out.println("从缓存中查");
            User userTmp = (User) hOps.get(baseKey, userName);
            if (userTmp == null) {
                return null;
            } else {
                String encryptedPasswordInput = MySecurity.encryptUserPassword(userPassword, userTmp.getUserId());
                if (userTmp.getUserPassword().equals(encryptedPasswordInput)) {
                    return userTmp;
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public User updateUserSession(HttpSession session) {
        //获得代理主键，更新当前用户信息至session
        String userId = ((User) session.getAttribute(UserUtil.USER_INFO_SESSION)).getUserId();
        User user = this.selectUserById(userId);
        session.setAttribute(UserUtil.USER_INFO_SESSION, user);
        return user;
    }

    @Override
    public User selectUserById(String userId) {
        return !StringUtils.isEmpty(userId) ? userMapper.selectUserById(userId) : null;
    }

    @Override
    public User selectUserByName(String userName) {
        return !StringUtils.isEmpty(userName) ? userMapper.selectUserByName(userName) : null;
    }

    @Override
    public User selectUserByEmail(String userEmail) {
        return !StringUtils.isEmpty(userEmail) ? userMapper.selectUserByEmail(userEmail) : null;
    }

    @Override
    public String insertUserRegInfo(User user) {
        if (userMapper.selectUserByName(user.getUserName()) != null) {
            return "regNameExist"; //regNameExist表示用户名已被注册
        }
        if (userMapper.selectUserByEmail(user.getUserEmail()) != null) {
            return "regEmailExist"; //regEmailExist表示邮箱已被注册
        }
        //服务端设置用户uuid
        user.setUserId(UUID.randomUUID().toString().replaceAll("-", ""));
        //用户密码md5加密
        user.setUserPassword(MySecurity.encryptUserPassword(user.getUserPassword(), user.getUserId()));
        //设置默认头像
        user.setUserIcon(UserUtil.USER_DEFAULT_ICON_PATH);
        userMapper.insertUserRegInfo(user);
        return "regSuccess";//return "regSuccess"表示注册成功
    }

    @Override
    public void deleteUserSession(HttpSession session) {
        session.removeAttribute(UserUtil.USER_INFO_SESSION);
    }

    @Override
    public void updateResetPasswordByEmail(String userEmail, String userPassword) {
        userPassword = MySecurity.encryptUserPassword(userPassword, userMapper.selectUserByEmail(userEmail).getUserId());
        userMapper.updateResetPasswordByEmail(userEmail, userPassword);
    }

    @Override
    public List<String> findElseIdentityList(String userIdentity) {
        List<String> list = new ArrayList<>();
        if (userIdentity.equals("学生")) {
            list.add("教师");
            list.add("管理员");
        } else if (userIdentity.equals("教师")) {
            list.add("学生");
            list.add("管理员");
        } else {
            list.add("学生");
            list.add("教师");
        }
        return list;
    }

    @Override
    public String updateResetUserInfo(User user) {
        User tmpUser = userMapper.selectUserByName(user.getUserName());
        if (tmpUser == null || tmpUser.getUserId().equals(user.getUserId())) { //如果用户名为被修改过或者新的用户名未被注册，执行update
            userMapper.updateResetUserInfo(user);
            return "updateSuccess"; //updateSuccess表示昵注册成功
        } else {
            return "updateNameExist";
        }
    }

    @Override
    public void updateResetPasswordByUserId(String userPassword, String userId) {
        userPassword = MySecurity.encryptUserPassword(userPassword, userId);
        userMapper.updateResetPasswordByUserId(userPassword, userId);
    }

    @Override
    public String updateResetUserPassword(String userOldPassword, String userNewPassword, User user) {
        if (!(MySecurity.encryptUserPassword(userOldPassword, user.getUserId())).equals(user.getUserPassword())) {
            return "oldPasswordWrong";
        } else {
            updateResetPasswordByUserId(userNewPassword, user.getUserId());
            return "resetPasswordAfterLoginSuccess";
        }
    }

    @Override
    public void updateResetEmailByEmail(String userOldEmail, String userNewEmail) {
        userMapper.updateResetEmailByEmail(userOldEmail, userNewEmail);
    }
}
