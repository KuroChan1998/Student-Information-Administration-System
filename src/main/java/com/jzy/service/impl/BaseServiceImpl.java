package com.jzy.service.impl;

import com.jzy.dao.*;
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
    protected UserMapper userMapper;

    @Autowired
    protected StudentMapper studentMapper;

    @Autowired
    protected TeacherMapper teacherMapper;

    @Autowired
    protected ClassMapper classMapper;

    @Autowired
    protected MajorMapper majorMapper;

    @Autowired
    protected CollegeMapper collegeMapper;

    @Autowired
    protected GradeMapper gradeMapper;

    @Autowired
    protected TitleMapper titleMapper;

    @Autowired
    protected RedisTemplate<String,Object> redisTemplate;

    protected final static Logger logger = Logger.getLogger(BaseServiceImpl.class);
}
