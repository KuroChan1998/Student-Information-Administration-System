package com.jzy.controller;

import com.jzy.dto.other.EmailVerifyCode;
import com.jzy.dto.other.EmailVerifyCodeSession;
import com.jzy.dto.other.UserLogin;
import com.jzy.dto.other.senior.StudentPercentBySex;
import com.jzy.entity.User;
import com.jzy.interceptor.Token;
import com.jzy.util.other.Constants;
import com.jzy.util.other.SetCookie;
import com.jzy.util.student.StudentUtil;
import com.jzy.util.user.UserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
 * @Description 其他一些请求的控制类
 * @Date 2019/6/6 13:09
 * @Version 1.0
 **/
@Controller
public class OtherController extends BaseController {
    /**
     * @author JinZhiyun
     * @Description 定向到主页index.jsp，并返回用户model
     * @Date 16:48 2019/7/25
     * @Param []
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @RequestMapping("/index")
    public ModelAndView index() {
        User user = userService.updateUserSession(session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(UserUtil.USER_MODEL, user);
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
     * @author JinZhiyun
     * @Description 测试登录成功与否的ajax交互
     * @Date 16:49 2019/7/25
     * @Param [user, remember]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/loginTest")
    @ResponseBody //直接返回 json 数据
    public Map<String, Object> loginTest(User user, @RequestParam(value = "remember", required = false) String remember) {
        Map<String, Object> map = new HashMap();
        UserLogin userLogin=userService.userLoginTest(user);
        if (userLogin.getLoginFlag()){
            //登录成功把User对象设置到session
            session.setAttribute(UserUtil.USER_INFO_SESSION, userLogin.getUser());
            //记住密码存cookie
            SetCookie.setUserLoginCookie(user.getUserName(), user.getUserPassword(), remember, request, response);
            //设置CsrfToken
            SetCookie.setCSRFTokenCookieAndSession(session, request, response);
        }
        map.put("data",userLogin);
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
     * @return java.util.Map<java.lang.String                               ,                               java.lang.Object>
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
     * @return java.util.Map<java.lang.String       ,       java.lang.Object>
     * @author JinZhiyun
     * @Description 发送验证码的ajax交互
     * @Date 8:46 2019/6/6
     * @Param [user]
     **/
    @RequestMapping("/sendVerifyCodeToEmail")
    @ResponseBody
    public Map<String, Object> sendVerifyCodeToEmail(User user) {
        Map<String, Object> map = new HashMap();
        //清空重置邮箱验证码信息的session状态
        session.setAttribute(UserUtil.USER_EMAIL_SESSION, new EmailVerifyCodeSession(user.getUserEmail(),false));
        otherService.sendVerifyCodeToEmail(user.getUserEmail());
        map.put("msg", "sendSuccess");
        return map;
    }

    /**
     * @return java.util.Map<java.lang.String       ,       java.lang.Object>
     * @author JinZhiyun
     * @Description 检测验证码是否正确的ajax交互
     * @Date 8:58 2019/6/6
     * @Param [emailVerifyCode, user]
     **/
    @RequestMapping("/emailVerifyCodeTest")
    @ResponseBody
    public Map<String, Object> emailVerifyCodeTest(@RequestParam(value = "emailVerifyCode",required = false) String emailVerifyCode, User user) {
        Map<String, Object> map = new HashMap();
        if (userService.selectUserByEmail(user.getUserEmail()) == null) {
            map.put("data", "emailUnregistered");
        } else if (!otherService.ifValidEmailVerifyCode(new EmailVerifyCode(user.getUserEmail(), emailVerifyCode))) {
            map.put("data", "verifyCodeWrong");
        } else {
            session.setAttribute(UserUtil.USER_EMAIL_SESSION, new EmailVerifyCodeSession(user.getUserEmail(),true));
            map.put("data", "verifyCodeCorrect");
        }
        return map;
    }

    /**
     * @return java.util.Map<java.lang.String, java.lang.Object>
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
        EmailVerifyCodeSession emailVerifyCodeSession= (EmailVerifyCodeSession) session.getAttribute(UserUtil.USER_EMAIL_SESSION);
        userService.updateResetPasswordByEmail(emailVerifyCodeSession.getUserEmail(), user.getUserPassword());
        map.put("data", "resetPasswordSuccess");
        return map;
    }

    /**
     * @author JinZhiyun
     * @description 400页面
     * @date 10:34 2019/9/30
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/400")
    public String error400Page() {
        int status = response.getStatus();
        String msg="";
        if (status==404) {
            msg="400！服务器不理解请求的语法。";
            logger.error(msg);
        }
        return "tips/HTTP-400";
    }

    /**
     * @author JinZhiyun
     * @description 404页面
     * @date 10:34 2019/9/30
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/404")
    public String error404Page() {
        int status = response.getStatus();
        String msg="";
        if (status==404) {
            msg="Not Found! 未找到，服务器找不到请求的网页";
            logger.error(msg);
        }
        return "tips/HTTP-404";
    }

    /**
     * @author JinZhiyun
     * @description 500页面
     * @date 10:34 2019/9/30
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/500")
    public String error500Page() {
        int status = response.getStatus();
        String msg="";
        if (status==500) {
            msg="Internal Server Error! 服务器内部错误，服务器遇到错误，无法完成请求";
            logger.error(msg);
        }
        return "tips/HTTP-500";
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
     * @Description 定向到控制台console.jsp，并传入系统介绍信息
     * @Date 9:58 2019/5/3
     * @Param [model]
     **/
    @RequestMapping("/console")
    public String console(Model model) {
        model.addAttribute("projectWholeName",Constants.ProjectInfo.PROJECT_WHOLE_NAME);
        model.addAttribute("frontEndTech",Constants.ProjectInfo.FRONT_END_TECH);
        model.addAttribute("visualizeTech",Constants.ProjectInfo.VISUALIZE_TECH);
        model.addAttribute("backEndTechIOC",Constants.ProjectInfo.BACK_END_TECH_IOC);
        model.addAttribute("backEndTechMCV",Constants.ProjectInfo.BACK_END_TECH_MVC);
        model.addAttribute("backEndTechORM",Constants.ProjectInfo.BACK_END_TECH_ORM);
        model.addAttribute("caches",Constants.ProjectInfo.CACHES);
        model.addAttribute("databases",Constants.ProjectInfo.DATABASES);
        model.addAttribute("securityTech",Constants.ProjectInfo.SECURITY_TECH);
        model.addAttribute("logTech",Constants.ProjectInfo.LOG_TECH);
        model.addAttribute("language",Constants.ProjectInfo.LANGUAGE);
        model.addAttribute("feature",Constants.ProjectInfo.FEATURE);
        return "home/console";
    }

    /**
     * @return java.util.Map<java.lang.String               ,               java.lang.Object>
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
    @RequestMapping("/senior/stuSex")
    public String sex() {
        return "senior/student/stuSex";
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
        return "senior/student/stuNum";
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @author JinZhiyun
     * @Description 学生人数比可视化ajax
     * @Date 19:00 2019/7/21
     * @Param [collegeName, majorName, type]
     **/
    @RequestMapping("/findPersonTotalPercentByCommonName")
    @ResponseBody
    public Map<String, Object> findPersonTotalPercentByCommonName(@RequestParam(value = "college", required = false) String collegeName
            , @RequestParam(value = "major", required = false) String majorName, @RequestParam(value = "type", required = false) String type) {
        Map<String, Object> map = new HashMap<>();
        List<List<Object>> list = otherService.proSelectStuTotalByCollegeOrMajorName(type, collegeName, majorName);
        map.put("commonName", list.get(0));
        map.put("total", list.get(1));
        return map;
    }

    /**
     * @author JinZhiyun
     * @Description 本硕博比可视化ajax
     * @Date 17:40 2019/7/23
     * @Param [collegeName, type]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/findStuTotalByDegreeAndCollege")
    @ResponseBody
    public Map<String, Object> findStuTotalByDegreeAndCollege(@RequestParam(value = "college", required = false) String collegeName, @RequestParam(value = "type", required = false) String type) {
        Map<String, Object> map = new HashMap<>();
        map.put("dimensions", new ArrayList<Object>() {//这个大括号 就相当于我们  new 接口
                    {//这个大括号 就是 构造代码块 会在构造函数前 调用
                        this.add("product");
                        this.addAll(StudentUtil.STUDENT_DEGREES);
                    }
                }
        );
        List<Map<String, Object>> list = otherService.transferStuTotalToValidJSON(type,collegeName);
        map.put("source", list);
        return map;
    }

    /**
     * @author JinZhiyun
     * @Description 重定向到师资力量echarts可视化
     * @Date 17:42 2019/7/23
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/senior/teaPower")
    public String teaPower() {
        return "senior/teacher/teaPower";
    }

    /**
     * @author JinZhiyun
     * @Description 师资力量可视化ajax
     * @Date 18:45 2019/7/24
     * @Param [collegeName, majorName, type]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/findTeaTotalGroupByTitle")
    @ResponseBody
    public Map<String, Object> findTeaTotalGroupByTitle(@RequestParam(value = "college", required = false) String collegeName
            , @RequestParam(value = "major", required = false) String majorName,@RequestParam(value = "type", required = false) String type) {
        return otherService.transTeaTotalToValidJSON(type,collegeName,majorName);
    }

    /**
     * @author JinZhiyun
     * @description 跳转更新历史页面
     * @date 10:33 2019/9/30
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/updateHistory")
    public String updateHistory(){
        return "home/updateHistory";
    }
}
