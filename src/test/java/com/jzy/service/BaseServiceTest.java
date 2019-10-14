package com.jzy.service;

import org.springframework.beans.factory.annotation.Autowired;
import others.UnitTestBase;

/**
 * @author JinZhiyun
 * @ClassName BaseServiceTest
 * @Description 服务层接口测试类的基类，用来继承
 * @Date 2019/6/5 16:35
 * @Version 1.0
 **/
public abstract class BaseServiceTest extends UnitTestBase {
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
