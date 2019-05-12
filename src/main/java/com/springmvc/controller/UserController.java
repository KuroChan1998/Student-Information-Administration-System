package com.springmvc.controller;

import com.springmvc.controller.interceptor.Token;
import com.springmvc.dto.Cookies;
import com.springmvc.entity.User;
import com.springmvc.service.CollegeService;
import com.springmvc.service.MajorService;
import com.springmvc.service.StudentService;
import com.springmvc.service.UserService;
import com.springmvc.service.impl.util.Constants;
import com.springmvc.service.impl.util.MySecurity;
import com.springmvc.service.impl.util.SetCookie;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Author JinZhiyun
 * @Description 用户业务控制器
 * @Date 2019/1/25 9:52
 * @Version 1.0
 **/

@Controller
//@SessionAttributes("userInfo")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private MajorService majorService;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Author JinZhiyun
     * @Description 定向到主页index.jsp，并返回用户model
     * @Date 9:23 2019/4/19
     * @Param [session, request]
     **/
    @RequestMapping("/index")
    public ModelAndView index(HttpSession session, HttpServletRequest request) {
        User user = userService.updateUserSession(session, request);
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
        return "/user/login";
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 注销并定向到登录页面login.jsp
     * @Date 9:36 2019/4/19
     * @Param [session, request]
     **/
    @RequestMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request) {
        userService.destroyUserSession(session, request);
        return "user/login";
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
     * @Author JinZhiyun
     * @Description /测试登录成功与否的ajax交互
     * @Date 9:38 2019/4/19
     * @Param [userId, userPassword, remember, session, request, response]
     **/
    @RequestMapping("/loginTest")
    @ResponseBody //直接返回 json 数据
    public Map<String, Object> loginTest(@RequestParam("userId") String userId, @RequestParam("userPassword") String userPassword, @RequestParam(value = "remember", required = false) String remember
            , HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashedMap();
        map.put("code", 0);
        map.put("msg", "");
        int loginUserNum = userService.findUserByIdAndPassword(userId, userPassword);
        if (loginUserNum == 0) {
            map.put("data", "resultFail");
        } else if (loginUserNum == 1) {
            map.put("data", "resultSuccess");
            //登录成功设置session
            session = request.getSession(true);
            session.setAttribute(Constants.USERID_SESSION, userId);
            //记住密码存cookie
            SetCookie.setUserLoginCookie(userId, userPassword, remember, request, response);


        } else {
            map.put("data", "resultError");
        }
        return map;
    }

    /**
     * @return java.lang.Object
     * @Author JinZhiyun
     * @Description //TODO
     * @Date 13:41 2019/5/3
     * @Param [request]
     **/
    @RequestMapping("/user/getCookie")
    @ResponseBody
    public Object getCookie(HttpServletRequest request) {

        String loginAccount = "";
        String loginPassword = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            //遍历Cookie
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                System.out.println(cookie);
                System.out.println(cookie.getName());
                //此处类似与Map有
                if ("loginAccount".equals(cookie.getName())) {
                    loginAccount = cookie.getValue();
                }
                if ("loginPassword".equals(cookie.getName())) {
                    loginPassword = cookie.getValue();
                }
            }
        }
        //自己定义的javabean Cookies
        Cookies co = new Cookies();
        co.setLoginAccount(loginAccount);
        co.setLoginPassword(loginPassword);

        return co;
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 判断注册成功与否的ajax交互
     * @Date 9:39 2019/4/19
     * @Param [user]
     **/
    @RequestMapping("/regTest")
    @ResponseBody
    public Map<String, Object> regTest(User user) {
        Map<String, Object> map = new HashedMap();
        map.put("code", 0);
        map.put("msg", "");

        int i = userService.insertUserRegInfo(user);

        if (i == -1) {
            map.put("data", "regIdExist");
        } else if (i == -2) {
            map.put("data", "regNicknameExist");
        } else if (i == -3) {
            map.put("data", "regEmailExist");
        } else {
            map.put("data", "regSuccess");
        }
        return map;
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 发送验证码的ajax交互
     * @Date 9:44 2019/4/19
     * @Param [userEmail, session, request]
     **/
    @RequestMapping("/sendVerifyCodeToEmail")
    @ResponseBody
    public Map<String, Object> sendVerifyCodeToEmail(@RequestParam("userEmail") String userEmail, HttpSession session, HttpServletRequest request) {
        Map<String, Object> map = new HashedMap();

        String code = userService.sendVerifyCodeToEmail(userEmail);

        session = request.getSession(true);
        session.setAttribute(Constants.EMAIL_VERIFYCODE_SESSION, code);
        map.put("msg", "sendSuccess");

        return map;
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 检测验证码是否正确的ajax交互
     * @Date 9:45 2019/4/19
     * @Param [emailVerifyCode, userEmail, session, request]
     **/
    @RequestMapping("/emailVerifyCodeTest")
    @ResponseBody
    public Map<String, Object> emailVerifyCodeTest(@RequestParam("emailVerifyCode") String emailVerifyCode, @RequestParam("userEmail") String userEmail, HttpSession session, HttpServletRequest request) {
        Map<String, Object> map = new HashedMap();
        map.put("code", 0);
        map.put("msg", "");

        session = request.getSession();
        String code = (String) session.getAttribute(Constants.EMAIL_VERIFYCODE_SESSION);
        System.out.println(userEmail);
        if (code == null || !code.equals(emailVerifyCode)) {
            map.put("data", "verifyCodeWrong");
        } else if (userService.findUserByEmail(userEmail) != 1) {
            map.put("data", "emailUnregistered");
        } else {
            session.setAttribute(Constants.USEREMAIL_SESSION, userEmail);
            map.put("data", "verifyCodeCorrect");
        }

        return map;
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 重置密码的ajax交互
     * @Date 9:46 2019/4/19
     * @Param [userPassword, session, request]
     **/
    @RequestMapping("/resetPassword")
    @Token(remove = true)
    @ResponseBody
    public Map<String, Object> resetPassword(@RequestParam("userPassword") String userPassword, HttpSession session, HttpServletRequest request) {
        Map<String, Object> map = new HashedMap();
        map.put("code", 0);
        map.put("msg", "");
        session = request.getSession();
        String userEmail = (String) session.getAttribute(Constants.USEREMAIL_SESSION);

        if (userEmail != null && userEmail != "") {
            userService.updateResetPasswordByEmail(userEmail, userPassword);
        }

        return map;
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 定向到用户基本信息页面，并返回相应用户model
     * @Date 9:47 2019/4/19
     * @Param [model, session, request]
     **/
    @RequestMapping("/info")
    public String info(Model model, HttpSession session, HttpServletRequest request) {
        User user = userService.updateUserSession(session, request);
        model.addAttribute(Constants.USER_MODEL, user);
        model.addAttribute(Constants.USER_ELSEINDENTITY_MODEL, userService.elseIdentityList(user.getUserIdentity()));
        return "set/user/info";
    }


    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 上传头像
     * @Date 15:37 2019/4/10
     * @Param [file, request, session]
     **/
    @RequestMapping("/upload/userIcon")
    @ResponseBody
    public Map<String, Object> uploadUserIcon(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpSession session) {
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> map = new HashMap<>();

        User user = userService.updateUserSession(session, request);

        String relaFilePath = userService.uploadUserIcon(file, request, user.getUserId());
        System.out.println(relaFilePath);
        //返回layui规定的文件上传模块JSON格式
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", map2);
        map2.put("src", relaFilePath);
        return map;
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 重置用户基本信息的ajax交互
     * @Date 9:48 2019/4/19
     * @Param [user]
     **/
    @RequestMapping("/userInfoReset")
    @ResponseBody
    public Map<String, Object> userInfoReset(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();

        userService.updateResetUserInfo(user);

        map.put("code", 0);
        map.put("msg", "");
        map.put("data", "");
        return map;
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 定向到修改密码页面password.jsp
     * @Date 9:49 2019/4/19
     * @Param [model, session, request]
     **/
    @RequestMapping("/password")
    public String password(Model model, HttpSession session, HttpServletRequest request) {
        User user = userService.updateUserSession(session, request);
        model.addAttribute(Constants.USER_MODEL, user);
        return "set/user/password";
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 已登录主页后在password.jsp页面修改密码的ajax交互
     * @Date 9:49 2019/4/19
     * @Param [userOldPassword, userNewPassword, request, session]
     **/
    @RequestMapping("/resetPasswordAfterLogin")
    @ResponseBody
    public Map<String, Object> resetPasswordAfterLogin(@RequestParam("oldPassword") String userOldPassword, @RequestParam("newPassword") String userNewPassword, HttpServletRequest request, HttpSession session) {
        Map<String, Object> map = new HashMap<>();

        map.put("code", 0);
        map.put("msg", "");
        session = request.getSession();
        String userId = (String) session.getAttribute(Constants.USERID_SESSION);

        String userPassword = userService.selectUserPasswordById(userId);
        if (!(MySecurity.encryptUserPassword(userOldPassword,userId)).equals(userPassword)) {
            map.put("data", "oldPasswordWrong");
        } else {
            userService.updateResetPasswordByUserId(userNewPassword, userId);
            map.put("data", "resetPasswordAfterLoginSuccess");
        }

        return map;
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 定向到修改邮箱页面email.jsp
     * @Date 9:52 2019/4/19
     * @Param [model, session, request]
     **/
    @RequestMapping("/email")
    public String email(Model model, HttpSession session, HttpServletRequest request) {
        User user = userService.updateUserSession(session, request);
        model.addAttribute(Constants.USER_MODEL, user);
        return "set/user/email";
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
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

        if (userService.findUserByEmail(userNewEmail) != 0) {
            map.put("data", "newEmailRegistered");
        } else {
            userService.updateResetEmailByEmail(userOldEmail, userNewEmail);
            map.put("data", "resetEmailSuccess");
        }

        return map;
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
     * @Author JinZhiyun
     * @Description 定向到因表单重复提交而跳转的空白页面
     * @Date 21:44 2019/5/11
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/formRepeatSubmit")
    public String formRepeatSubmit(){
        return "tips/formRepeatSubmit";
    }

    /**
     * @Author JinZhiyun
     * @Description 重定向到echarts可视化性别比饼图
     * @Date 12:18 2019/5/5
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/senior/sex")
    public String sex(){
        return "senior/sex";
    }

    /**
     * @Author JinZhiyun
     * @Description 性别比可视化ajax
     * @Date 18:54 2019/5/5
     * @Param [stuCollegeName, stuMajorName, stuClassName]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/findSexPercent")
    @ResponseBody
    public Map<String, Object> findSexPercent(@RequestParam(value = "college", required = false) String stuCollegeName
            , @RequestParam(value = "major", required = false) String stuMajorName
            , @RequestParam(value = "class", required = false) String stuClassName) {
        Map<String, Object> map = new HashMap<>();

        List<Integer> list=studentService.findStuNumBySex(stuCollegeName,stuMajorName,stuClassName);

        map.put("total",list.get(0));

        map.put("male", list.get(1));

        map.put("female", list.get(2));

        return map;
    }

    /**
     * @Author JinZhiyun
     * @Description 重定向到echarts可视化学生数柱状图
     * @Date 19:37 2019/5/5
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/senior/stuNum")
    public String stuNum(){
        return "senior/stuNum";
    }

    /**
     * @Author JinZhiyun
     * @Description 学院人数比可视化ajax
     * @Date 21:39 2019/5/5
     * @Param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/findCollegeStuNumPercent")
    @ResponseBody
    public Map<String, Object> findCollegeStuNumPercent() {
        Map<String, Object> map = new HashMap<>();

        List<Object> list=collegeService.findCollegeStuNumPercent();


        map.put("collegeName",list.get(0));
        map.put("collegeStuNum",list.get(1));

        return map;
    }

    /**
     * @Author JinZhiyun
     * @Description 专业人数比可视化ajax
     * @Date 22:23 2019/5/5
     * @Param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/findMajorStuNumPercent")
    @ResponseBody
    public Map<String, Object> findMajorStuNumPercent(@RequestParam(value = "college", required = false) String collegeName) {
        Map<String, Object> map = new HashMap<>();

        List<Object> list=majorService.findMajorStuNumPercent(collegeName);

        map.put("majorName",list.get(0));
        map.put("majorStuNum",list.get(1));

        return map;
    }
}
