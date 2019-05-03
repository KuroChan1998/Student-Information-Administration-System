package com.springmvc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmvc.dao.ClassMapper;
import com.springmvc.dao.CollegeMapper;
import com.springmvc.dao.MajorMapper;
import com.springmvc.dao.TeacherMapper;
import com.springmvc.dto.MyPage;
import com.springmvc.dto.TeacherInfoSearchDto;
import com.springmvc.dto.TeacherWithMajorCollegeDto;
import com.springmvc.entity.Teacher;
import com.springmvc.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName TeacherServiceImpl
 * @Author JinZhiyun
 * @Description 教师业务实现
 * @Date 2019/4/16 13:08
 * @Version 1.0
 **/
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public PageInfo<TeacherWithMajorCollegeDto> queryAllTeaInfo(MyPage myPage, TeacherInfoSearchDto teaISD) {
        PageHelper.startPage(myPage.getPageNum(), myPage.getPageSize());//第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
        List<TeacherWithMajorCollegeDto> teaWMCD = teacherMapper.queryAllTeaInfo(teaISD);
        return new PageInfo<>(teaWMCD);
    }

    @Override
    public Teacher findTeacherById(String teaId) {
        return teacherMapper.findTeacherById(teaId);
    }

    @Override
    public PageInfo<TeacherWithMajorCollegeDto> findTeacherOwnInfoById(MyPage myPage, String teaId) {
        PageHelper.startPage(myPage.getPageNum(), myPage.getPageSize());//第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
        List<TeacherWithMajorCollegeDto> teaWMCD = teacherMapper.findTeacherOwnInfoById(teaId);
        return new PageInfo<>(teaWMCD);
    }

    @Override
    public void updateInfo(String teaOriId, TeacherWithMajorCollegeDto teaWMCD) {
        if (!teaOriId.equals(teaWMCD.getTeaId())) {
            classMapper.updateTeaId(teaOriId, teaWMCD.getTeaId());
            majorMapper.updateTeaId(teaOriId, teaWMCD.getTeaId());
            collegeMapper.updateTeaId(teaOriId, teaWMCD.getTeaId());
        }
        teacherMapper.updateInfo(teaOriId, teaWMCD);
    }

    @Override
    public void insertTeacher(TeacherWithMajorCollegeDto teaWMCD) {
        teacherMapper.insertTeacher(teaWMCD);
    }

    @Override
    public void deleteOneTeacher(TeacherWithMajorCollegeDto teaWMCD) {
        classMapper.updateDeleteTea(teaWMCD.getTeaId());
        majorMapper.updateDeleteTea(teaWMCD.getTeaId());
        collegeMapper.updateDeleteTea(teaWMCD.getTeaId());
        teacherMapper.deleteOneTeacher(teaWMCD.getTeaId());
    }

    @Override
    public void deleteManyTeachers(List<TeacherWithMajorCollegeDto> teaWMCDs) {
        for (TeacherWithMajorCollegeDto teaWMCD : teaWMCDs) {
            deleteOneTeacher(teaWMCD);
        }
    }
}
