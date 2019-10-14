package com.jzy.service.impl;

import com.jzy.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author JinZhiyun
 * @ClassName BaseServiceImpl
 * @Description 基础业务类，用来继承
 * @Date 2019/6/5 9:16
 * @Version 1.0
 **/
public abstract class BaseServiceImpl {
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

    @Autowired
    protected RedisTemplate<String,Object> redisTemplate;

    protected final static Logger logger = Logger.getLogger(BaseServiceImpl.class);
}
