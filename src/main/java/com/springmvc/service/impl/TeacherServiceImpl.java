package com.springmvc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmvc.dto.other.MyPage;
import com.springmvc.dto.teacher.TeacherSearchDto;
import com.springmvc.dto.teacher.TeacherWithTitleMajorCollegeDto;
import com.springmvc.entity.Teacher;
import com.springmvc.service.TeacherService;
import com.springmvc.util.MyTimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
public class TeacherServiceImpl extends BaseServiceImpl implements TeacherService {

    @Override
    public PageInfo<TeacherWithTitleMajorCollegeDto> selectAllTeacherInfo(MyPage myPage, TeacherSearchDto teacherSearch) {
        PageHelper.startPage(myPage.getPageNum(), myPage.getPageSize());//第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
        List<TeacherWithTitleMajorCollegeDto> teaWTMCs = teacherMapper.selectAllTeacherInfo(teacherSearch);
        for (TeacherWithTitleMajorCollegeDto teaWTMC : teaWTMCs) {
            if (teaWTMC.getTeaBirthday() != null) {
                //date字符串
                teaWTMC.setTeaBirthdayToString(MyTimeUtil.dateToStr(teaWTMC.getTeaBirthday()));
                //由生日计算年龄
                teaWTMC.setTeaAge(MyTimeUtil.getAgeByBirth(teaWTMC.getTeaBirthday()));
            }
        }
        return new PageInfo<>(teaWTMCs);
    }

    @Override
    public PageInfo<TeacherWithTitleMajorCollegeDto> selectTeacherOwnInfoByNum(MyPage myPage, String teaNum) {
        PageHelper.startPage(myPage.getPageNum(), myPage.getPageSize());//第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
        List<TeacherWithTitleMajorCollegeDto> teaWTMCs = new ArrayList<>();
        TeacherWithTitleMajorCollegeDto teaWTMC0 = teacherMapper.selectTeacherOwnInfoByNum(teaNum);
        if (teaWTMC0 != null) {
            teaWTMCs.add(teaWTMC0);
            for (TeacherWithTitleMajorCollegeDto teaWTMC : teaWTMCs) {
                if (teaWTMC.getTeaBirthday() != null) {
                    //date字符串
                    teaWTMC.setTeaBirthdayToString(MyTimeUtil.dateToStr(teaWTMC.getTeaBirthday()));
                    //由生日计算年龄
                    teaWTMC.setTeaAge(MyTimeUtil.getAgeByBirth(teaWTMC.getTeaBirthday()));
                }
            }
        }
        return new PageInfo<>(teaWTMCs);
    }

    @Override
    public Teacher selectTeacherByNum(String teaNum) {
        return teacherMapper.selectTeacherByNum(teaNum);
    }

    @Override
    public void updateTeacherInfo(String teaOriNum, TeacherWithTitleMajorCollegeDto teaWTMC) {
        if (!teaOriNum.equals(teaWTMC.getTeaNum())) {
            classMapper.updateClassTeaNum(teaOriNum, teaWTMC.getTeaNum());
            gradeMapper.updateGradeTeaNum(teaOriNum, teaWTMC.getTeaNum());
            majorMapper.updateMajorTeaNum(teaOriNum, teaWTMC.getTeaNum());
            collegeMapper.updateCollegeTeaNum(teaOriNum, teaWTMC.getTeaNum());
        }
        if (teaWTMC.getTeaBirthdayToString() != null) {
            teaWTMC.setTeaBirthday(MyTimeUtil.strToDate(teaWTMC.getTeaBirthdayToString()));
        } else {
            teaWTMC.setTeaBirthday(null);
        }
        teacherMapper.updateTeacherInfo(teaOriNum, teaWTMC);
    }

    @Override
    public void insertTeacher(TeacherWithTitleMajorCollegeDto teaWTMC) {
        if (teaWTMC.getTeaBirthdayToString() != null) {
            teaWTMC.setTeaBirthday(MyTimeUtil.strToDate(teaWTMC.getTeaBirthdayToString()));
        } else {
            teaWTMC.setTeaBirthday(null);
        }
        teacherMapper.insertTeacher(teaWTMC);
    }

    @Override
    public void deleteOneTeacher(String teaNum) {
        gradeMapper.updateGradeTeaNum(teaNum, null); //若是年级教师负责人，该班年级教师负责人工号置null
        classMapper.updateClassTeaNum(teaNum, null); //若是年级学生负责人，该年级学生负责人学号置null
        majorMapper.updateMajorTeaNum(teaNum, null);
        collegeMapper.updateCollegeTeaNum(teaNum, null);
        teacherMapper.deleteOneTeacher(teaNum);
    }

    @Override
    public void deleteManyTeachers(List<String> teaNums) {
        for (String teaNum : teaNums) {
            deleteOneTeacher(teaNum);
        }
    }

    @Override
    public TeacherWithTitleMajorCollegeDto selectTeacherInfoByNum(String teaNum) {
        TeacherWithTitleMajorCollegeDto teaWTMC = teacherMapper.selectTeacherOwnInfoByNum(teaNum);
        if (teaWTMC != null) {
            if (teaWTMC.getTeaBirthday() != null) {
                //date字符串
                teaWTMC.setTeaBirthdayToString(MyTimeUtil.dateToStr(teaWTMC.getTeaBirthday()));
                //由生日计算年龄
                teaWTMC.setTeaAge(MyTimeUtil.getAgeByBirth(teaWTMC.getTeaBirthday()));
            }
        }
        return teaWTMC;
    }
}
