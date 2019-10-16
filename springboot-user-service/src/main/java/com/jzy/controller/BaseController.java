package com.jzy.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jzy.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author JinZhiyun
 * @ClassName BaseController
 * @Description 基础控制器，用来继承。
 * 有如下内容：
 * 1、自动注入所有所需服务层接口
 * 2、设置request、response、session对象
 * 之后其他控制类只需继承此类，无需自行注入和设置
 * @Date 2019/6/4 22:39
 * @Version 1.0
 **/
@Controller
public abstract class BaseController {
    protected HttpServletRequest request;

    protected HttpServletResponse response;

    protected HttpSession session;

    @Autowired
    protected UserService userService;

    @Reference
    protected StudentService studentService;

    @Reference
    protected TeacherService teacherService;

    @Reference
    protected ClassService classService;

    @Reference
    protected MajorService majorService;

    @Reference
    protected CollegeService collegeService;

    @Reference
    protected GradeService gradeService;

    @Reference
    protected TitleService titleService;

    @Autowired
    protected OtherService otherService;

    @Autowired
    protected RedisTemplate<String,Object> redisTemplate;

    protected final static Logger logger = Logger.getLogger(BaseController.class);

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession(true);
    }
}
