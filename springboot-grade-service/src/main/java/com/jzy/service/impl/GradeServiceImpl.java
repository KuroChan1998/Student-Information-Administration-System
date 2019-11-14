package com.jzy.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jzy.dao.GradeMapper;
import com.jzy.entity.Grade;
import com.jzy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author JinZhiyun
 * @ClassName GradeServiceImpl
 * @Description 年级业务实现
 * @Date 2019/6/14 12:56
 * @Version 1.0
 **/
@Service("gradeService")
@com.alibaba.dubbo.config.annotation.Service
public class GradeServiceImpl implements GradeService {
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
    private TitleService titleService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public List<Grade> selectAllGrade() {
        return gradeMapper.selectAllGrade();
    }

    @Override
    public void updateGradeStuNum(String stuOriNum, String stuNum) {
        gradeMapper.updateGradeStuNum(stuOriNum, stuNum);
    }

    @Override
    public void updateGradeTeaNum(String teaOriNum, String teaNum) {
        gradeMapper.updateGradeTeaNum(teaOriNum, teaNum);
    }
}
