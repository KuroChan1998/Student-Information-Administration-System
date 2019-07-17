package com.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import others.UnitTestBase;

/**
 * @author JinZhiyun
 * @ClassName BaseServiceTest
 * @Description //TODO
 * @Date 2019/6/5 16:35
 * @Version 1.0
 **/
public class BaseServiceTest extends UnitTestBase {
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
    protected OtherService otherService;
}
