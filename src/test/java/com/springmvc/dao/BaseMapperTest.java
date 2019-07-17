package com.springmvc.dao;

import com.springmvc.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import others.UnitTestBase;

/**
 * @author JinZhiyun
 * @ClassName BaseMapperTest
 * @Description //TODO
 * @Date 2019/6/5 16:39
 * @Version 1.0
 **/
public class BaseMapperTest extends UnitTestBase {
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
    protected TitleMapper titleMapper;

    @Autowired
    protected GradeMapper gradeMapper;
}
