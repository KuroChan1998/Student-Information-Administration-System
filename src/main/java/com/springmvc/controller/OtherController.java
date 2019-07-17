package com.springmvc.controller;

import com.springmvc.dto.other.EmailVerifyCode;
import com.springmvc.dto.other.senior.StudentPercentBySex;
import com.springmvc.entity.User;
import com.springmvc.interceptor.Token;
import com.springmvc.util.Constants;
import com.springmvc.util.SetCookie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JinZhiyun
 * @ClassName OtherController
 * @Description //TODO
 * @Date 2019/6/6 13:09
 * @Version 1.0
 **/
@Controller
public class OtherController extends BaseController {
    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Author JinZhiyun
     * @Description 定向到主页index.jsp，并返回用户model
     * @Date 9:23 2019/4/19
     * @Param [session, request]
     **/
    @RequestMapping("/index")
    public ModelAndView index() {
        User user = userService.updateUserSession(session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(Constants.USER_MODEL, user);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 定向到登录页面login.jsp
     * @Date 9:25 2019/4/19
     * @Param []
     **/
    @RequestMapping("/login")
    public String login() {
        return "user/login";
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 注销并定向到登录页面login.jsp
     * @Date 9:36 2019/4/19
     * @Param []
     **/
    @RequestMapping("/logout")
    public String logout() {
        userService.deleteUserSession(session);
        return "user/login";
    }

    /**
     * @return java.util.Map<java.lang.String               ,               java.lang.Object>
     * @Author JinZhiyun
     * @Description 测试登录成功与否的ajax交互
     * @Date 9:38 2019/4/19
     * @Param [userId, userPassword, remember, session, request, response]
     **/
    @RequestMapping("/loginTest")
    @ResponseBody //直接返回 json 数据
    public Map<String, Object> loginTest(User user, @RequestParam(value = "remember", required = false) String remember) {
        Map<String, Object> map = new HashMap();
        User userSessionInfo = userService.selectUserByNameAndPassword(user.getUserName(), user.getUserPassword());
        if (userSessionInfo == null) {
            map.put("data", "resultFail");
        } else {
            map.put("data", "resultSuccess");
            //登录成功设置session
            session.setAttribute(Constants.USERINFO_SESSION, userSessionInfo);
            System.out.println(remember);
            //记住密码存cookie
            SetCookie.setUserLoginCookie(user.getUserName(), user.getUserPassword(), remember, request, response);
        }
        return map;
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 定向到注册页面reg.jsp
     * @Date 9:37 2019/4/19
     * @Param []
     **/
    @RequestMapping("/reg")
    public String reg() {
        return "user/reg";
    }

    /**
     * @return java.util.Map<java.lang.String               ,               java.lang.Object>
     * @Author JinZhiyun
     * @Description 判断注册成功与否的ajax交互
     * @Date 9:39 2019/4/19
     * @Param [user]
     **/
    @RequestMapping("/regTest")
    @ResponseBody
    public Map<String, Object> regTest(User user) {
        Map<String, Object> map = new HashMap();
        map.put("data", userService.insertUserRegInfo(user));
        return map;
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 定向到忘记密码页面forget.jsp
     * @Date 9:37 2019/4/19
     * @Param []
     **/
    @RequestMapping("/forget")
    @Token(save = true)
    public String forget() {
        return "user/forget";
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @author JinZhiyun
     * @Description 发送验证码的ajax交互
     * @Date 8:46 2019/6/6
     * @Param [user]
     **/
    @RequestMapping("/sendVerifyCodeToEmail")
    @ResponseBody
    public Map<String, Object> sendVerifyCodeToEmail(User user) {
        Map<String, Object> map = new HashMap();
        EmailVerifyCode emailVerifyCode = otherService.sendVerifyCodeToEmail(user.getUserEmail());
        session.setAttribute(Constants.EMAILVERIFYCODE_SESSION, emailVerifyCode);
        map.put("msg", "sendSuccess");
        return map;
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @author JinZhiyun
     * @Description 检测验证码是否正确的ajax交互
     * @Date 8:58 2019/6/6
     * @Param [emailVerifyCode, user]
     **/
    @RequestMapping("/emailVerifyCodeTest")
    @ResponseBody
    public Map<String, Object> emailVerifyCodeTest(@RequestParam("emailVerifyCode") String emailVerifyCode, User user) {
        Map<String, Object> map = new HashMap();

        if (userService.selectUserByEmail(user.getUserEmail()) == null) {
            map.put("data", "emailUnregistered");
        } else if (!otherService.ifValidEmailVerifyCode(emailVerifyCode, session)) {
            map.put("data", "verifyCodeWrong");
        } else {
            session.setAttribute(Constants.USEREMAIL_SESSION, user.getUserEmail());
            map.put("data", "verifyCodeCorrect");
        }

        return map;
    }

    /**
     * @return java.util.Map<java.lang.String               ,               java.lang.Object>
     * @Author JinZhiyun
     * @Description 重置密码的ajax交互
     * @Date 9:46 2019/4/19
     * @Param [user]
     **/
    @RequestMapping("/resetPassword")
    @Token(remove = true)
    @ResponseBody
    public Map<String, Object> resetPassword(User user) {
        Map<String, Object> map = new HashMap();

        String userEmail = (String) session.getAttribute(Constants.USEREMAIL_SESSION);
        if (userEmail != null && userEmail != "") {
            userService.updateResetPasswordByEmail(userEmail, user.getUserPassword());
            map.put("data", "resetPasswordSuccess");
        }

        return map;
    }

    /*=================以上为非登录下的请求，在LoginInterceptor中应该排除==================*/

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 定向到无修改权限提示页面error.jsp
     * @Date 13:17 2019/5/3
     * @Param []
     **/
    @RequestMapping("/error")
    public String error() {
        return "tips/error";
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 定向到控制台console.jsp
     * @Date 9:58 2019/5/3
     * @Param []
     **/
    @RequestMapping("/console")
    public String console() {
        return "home/console";
    }

    /**
     * @return java.util.Map<java.lang.String       ,       java.lang.Object>
     * @Author JinZhiyun
     * @Description 性别比可视化ajax
     * @Date 18:54 2019/5/5
     * @Param [stuCollegeName, stuMajorName, stuClassName]
     **/
    @RequestMapping("/findSexPercent")
    @ResponseBody
    public Map<String, Object> findSexPercent(@RequestParam(value = "college", required = false) String stuCollegeName
            , @RequestParam(value = "major", required = false) String stuMajorName
            , @RequestParam(value = "class", required = false) String stuClassName) {
        Map<String, Object> map = new HashMap<>();
        StudentPercentBySex studentPercent = otherService.selectStuTotalBySex(stuCollegeName, stuMajorName, stuClassName);
        map.put(Constants.STUDENT_PERCENT_BY_SEX, studentPercent);
        return map;
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 重定向到echarts可视化性别比饼图
     * @Date 12:18 2019/5/5
     * @Param []
     **/
    @RequestMapping("/senior/sex")
    public String sex() {
        return "senior/sex";
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 重定向到echarts可视化学生数柱状图
     * @Date 19:37 2019/5/5
     * @Param []
     **/
    @RequestMapping("/senior/stuNum")
    public String stuNum() {
        return "senior/stuNum";
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 学院人数比可视化ajax
     * @Date 21:39 2019/5/5
     * @Param []
     **/
    @RequestMapping("/findPersonTotalPercentByCommonName")
    @ResponseBody
    public Map<String, Object> findCollegeStuNumPercent(@RequestParam(value = "college", required = false) String collegeName
            , @RequestParam(value = "major", required = false) String majorName, @RequestParam(value = "type", required = false) String type) {
        Map<String, Object> map = new HashMap<>();
        List<List<Object>> list=otherService.proSelectStuTotalByCollegeOrMajorName(type, collegeName,majorName);
        map.put("commonName",list.get(0));
        map.put("total",list.get(1));
        return map;
    }
}
