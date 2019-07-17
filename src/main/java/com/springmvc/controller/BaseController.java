package com.springmvc.controller;

import com.springmvc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author JinZhiyun
 * @ClassName BaseController
 * @Description 基础控制器，用来继承
 * @Date 2019/6/4 22:39
 * @Version 1.0
 **/
@Controller
public class BaseController {
    protected HttpServletRequest request;

    protected HttpServletResponse response;

    protected HttpSession session;

    @Autowired
    protected UserService userService;

    @Autowired
    protected StudentService studentService;

    @Autowired
    protected TeacherService teacherService;

    @Autowired
    protected ClassService classService;

    @Autowired
    protected MajorService majorService;

    @Autowired
    protected CollegeService collegeService;

    @Autowired
    protected GradeService gradeService;

    @Autowired
    protected TitleService titleService;

    @Autowired
    protected OtherService otherService;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession(true);
    }
}
