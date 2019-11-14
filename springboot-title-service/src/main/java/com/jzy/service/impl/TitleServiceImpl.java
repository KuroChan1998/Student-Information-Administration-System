package com.jzy.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jzy.dao.TitleMapper;
import com.jzy.entity.Title;
import com.jzy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author JinZhiyun
 * @ClassName TitleServiceImpl
 * @Description 教师职称业务实现
 * @Date 2019/6/14 12:44
 * @Version 1.0
 **/
@Service("titleService")
@com.alibaba.dubbo.config.annotation.Service
public class TitleServiceImpl implements TitleService {
    @Reference
    private UserService userService;

    @Reference
    private StudentService studentService;

    @Reference
    private TeacherService teacherService;

    @Reference
    private ClassService classService;

    @Reference
    private MajorService majorService;

    @Reference
    private CollegeService collegeService;

    @Reference
    private GradeService gradeService;


    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private TitleMapper titleMapper;

    @Override
    public List<Title> selectAllTitle() {
        return titleMapper.selectAllTitle();
    }

    @Override
    public List<String> selectAllTitleName() {
        return titleMapper.selectAllTitleName();
    }
}
