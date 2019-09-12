package com.jzy.controller;

import com.jzy.entity.User;
import com.jzy.util.user.UserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserController
 * @Author JinZhiyun
 * @Description 用户业务控制器
 * 处理班级相关的页面跳转、增删改查ajax请求。
 * 该控制类都有根请求路径/class
 * @Date 2019/1/25 9:52
 * @Version 1.0
 **/

@Controller
@RequestMapping("/user")
//@SessionAttributes("userInfo")
public class UserController extends BaseController {
    /**
     * @return java.lang.String
     * @author JinZhiyun
     * @Description 定向到用户基本信息页面，并返回相应用户model
     * @Date 16:54 2019/7/25
     * @Param [model]
     **/
    @RequestMapping("/info")
    public String info(Model model) {
        User user = userService.updateUserSession(session);
        model.addAttribute(UserUtil.USER_MODEL, user);
        model.addAttribute(UserUtil.USER_ELSE_IDENTITY_MODEL, userService.findElseIdentityList(user.getUserIdentity()));
        return "set/user/info";
    }


    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @author JinZhiyun
     * @Description 上传头像
     * @Date 16:54 2019/7/25
     * @Param [file]
     **/
    @RequestMapping("/uploadUserIcon")
    @ResponseBody
    public Map<String, Object> uploadUserIcon(@RequestParam(value = "file", required = false) MultipartFile file) {
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> map = new HashMap<>();

        User user = userService.updateUserSession(session);

        String relaFilePath = otherService.uploadUserIcon(file, request, user.getUserId());
        //返回layui规定的文件上传模块JSON格式
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", map2);
        map2.put("src", relaFilePath);
        return map;
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @author JinZhiyun
     * @Description 重置用户基本信息的ajax交互
     * @Date 16:55 2019/7/25
     * @Param [user]
     **/
    @RequestMapping("/userInfoReset")
    @ResponseBody
    public Map<String, Object> userInfoReset(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", userService.updateResetUserInfo(user));
        return map;
    }

    /**
     * @return java.lang.String
     * @author JinZhiyun
     * @Description 定向到修改密码页面password.jsp
     * @Date 10:50 2019/6/13
     * @Param [model]
     **/
    @RequestMapping("/password")
    public String password(Model model) {
        User user = userService.updateUserSession(session);
        model.addAttribute(UserUtil.USER_MODEL, user);
        return "set/user/password";
    }

    /**
     * @return java.util.Map<java.lang.String       ,       java.lang.Object>
     * @author JinZhiyun
     * @Description 已登录主页后在password.jsp页面修改密码的ajax交互
     * @Date 10:51 2019/6/13
     * @Param [userOldPassword, userNewPassword]
     **/
    @RequestMapping("/resetPasswordAfterLogin")
    @ResponseBody
    public Map<String, Object> resetPasswordAfterLogin(@RequestParam("oldPassword") String userOldPassword, @RequestParam("newPassword") String userNewPassword) {
        Map<String, Object> map = new HashMap<>();
        User user = (User) session.getAttribute(UserUtil.USER_INFO_SESSION);
        map.put("data",userService.updateResetUserPassword(userOldPassword,userNewPassword,user));
        return map;
    }

    /**
     * @return java.lang.String
     * @author JinZhiyun
     * @Description 定向到修改邮箱页面email.jsp
     * @Date 11:06 2019/6/13
     * @Param [model]
     **/
    @RequestMapping("/email")
    public String email(Model model) {
        User user = userService.updateUserSession(session);
        model.addAttribute(UserUtil.USER_MODEL, user);
        return "set/user/email";
    }

    /**
     * @return java.util.Map<java.lang.String ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 修改邮箱的ajax交互
     * @Date 9:52 2019/4/19
     * @Param [userOldEmail, userNewEmail, request, session]
     **/
    @RequestMapping("/resetEmail")
    @ResponseBody
    public Map<String, Object> resetEmail(@RequestParam("oldEmail") String userOldEmail, @RequestParam("newEmail") String userNewEmail, HttpServletRequest request, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");

        if (userService.selectUserByEmail(userNewEmail) != null) {
            map.put("data", "newEmailRegistered");
        } else {
            userService.updateResetEmailByEmail(userOldEmail, userNewEmail);
            map.put("data", "resetEmailSuccess");
        }
        return map;
    }
}
