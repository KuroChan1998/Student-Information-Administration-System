package com.springmvc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmvc.dao.*;
import com.springmvc.dto.MajorWithCollegeDto;
import com.springmvc.dto.MyPage;
import com.springmvc.entity.Class;
import com.springmvc.entity.College;
import com.springmvc.entity.Major;
import com.springmvc.entity.User;
import com.springmvc.service.MajorService;
import com.springmvc.service.impl.util.DoWithString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName MajorServiceImpl
 * @Author JinZhiyun
 * @Description 专业业务实现
 * @Date 2019/4/16 18:59
 * @Version 1.0
 **/
@Service
@Transactional
public class MajorServiceImpl implements MajorService {
    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public List<Major> findMajorNameByCollege(String collegeName) {
        return majorMapper.findMajorNameByCollege(collegeName);
    }

    @Override
    public PageInfo<MajorWithCollegeDto> queryAllMajorInfo(MyPage myPage, String majorCollegeName, String majorName) {
        PageHelper.startPage(myPage.getPageNum(), myPage.getPageSize());//第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
        List<MajorWithCollegeDto> majorWCD = majorMapper.queryAllMajorInfo(majorCollegeName, majorName);
        return new PageInfo<>(majorWCD);
    }

    @Override
    public PageInfo<MajorWithCollegeDto> findMajorOwnInfoById(MyPage myPage, User user) {
        List<MajorWithCollegeDto> majorWDs;
        if (user.getUserIdentity().equals("学生")) {
            PageHelper.startPage(myPage.getPageNum(), myPage.getPageSize());//第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
            majorWDs = majorMapper.findStuMajorOwnInfoById(user.getUserId());
        } else {
            PageHelper.startPage(myPage.getPageNum(), myPage.getPageSize());//第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
            majorWDs = majorMapper.findTeaMajorOwnInfoById(user.getUserId());
        }
        return new PageInfo<>(majorWDs);
    }

    @Override
    public void majorStuNumAddOne(String stuMajorName, String stuCollegeName) {
        collegeMapper.updateStuNumAddOne(collegeMapper.findIdByCollegeName(stuCollegeName));
        majorMapper.updateStuNumAddOne(majorMapper.findIdByMajorName(stuMajorName));
    }

    @Override
    public void majorStuNumMinusOne(String stuMajorName, String stuCollegeName) {
        collegeMapper.updateStuNumMinusOne(collegeMapper.findIdByCollegeName(stuCollegeName));
        majorMapper.updateStuNumMinusOne(majorMapper.findIdByMajorName(stuMajorName));
    }

    @Override
    public Major findMajorById(String majorId) {
        return majorMapper.findMajorById(majorId);
    }

    @Override
    public Major findMajorByName(String majorName) {
        return majorMapper.findMajorByName(majorName);
    }

    @Override
    public String findMajorIdByTeaId(String majorTeaId) {
        Major myMajor = majorMapper.findMajorByTeaId(majorTeaId);
        if (myMajor == null) {
            return null;
        } else {
            return myMajor.getMajorId();
        }
    }

    @Override
    public String updateMapDataResult(String majorOriId, String majorOriName, MajorWithCollegeDto majorWCD) {
        if (!majorOriId.equals(majorWCD.getMajorId())) { //如果专业编号修改过了
            if (findMajorById(majorWCD.getMajorId()) != null) { //如果修改后的专业编号已存在
                return "majorIdExist";
            }
        }
        if (!majorOriName.equals(majorWCD.getMajorName())) { //如果专业名称修改过了
            if (findMajorByName(majorWCD.getMajorName()) != null) { //如果修改后的专业名称已存在
                return "majorNameExist";
            }
        }

        if (!majorWCD.getMajorTeaId().equals("")) { //如果该专业有负责人
            if (teacherMapper.findTeacherById(majorWCD.getMajorTeaId()) == null) { //如果负责人id不存在
                return "majorTeaIdNotExist";
            } else {
                String majorId = findMajorIdByTeaId(majorWCD.getMajorTeaId());
                if (!(majorId == null || majorId.equals(majorOriId))) { //若(majorId==null|| majorId.equals(majorOriId))可以执行更新该负责人
                    return "majorTeaIdRepeat";
                }
            }
        }

        //通过验证则update
        updateInfo(majorOriId, majorWCD);
        return "updateSuccess";

    }

    @Override
    public void updateInfo(String majorOriId, MajorWithCollegeDto majorWCD) {
        //获取老专业学院信息
        Major oldMajor = majorMapper.findMajorById(majorOriId);
        College oldCollege = collegeMapper.findCollegeById(oldMajor.getMajorCollegeId());
        //旧学院专业数-1
        collegeMapper.updateMajorNumMinusOne(oldCollege.getCollegeId());

        //原来专业所在学院的学生人数减去原来专业的学生人数
        Integer oldCollegeStuNum = oldCollege.getCollegeStuNum() - oldMajor.getMajorStuNum();
        //设置
        collegeMapper.updateSetStuNum(oldCollegeStuNum, oldCollege.getCollegeId());

        //获取新专业学院信息
        String collegeId = collegeMapper.findIdByCollegeName(majorWCD.getMajorCollegeName());
        College newCollege = collegeMapper.findCollegeById(collegeId);
        //新学院专业数+1
        collegeMapper.updateMajorNumAddOne(collegeId);

        //新专业所在学院的学生人数加上该专业的学生人数
        Integer newCollegeStuNum = newCollege.getCollegeStuNum() + majorWCD.getMajorStuNum();
        //设置
        collegeMapper.updateSetStuNum(newCollegeStuNum, newCollege.getCollegeId());

        //更新
        majorMapper.updateInfo(majorOriId, majorWCD);
    }

    @Override
    public String findRecommendedClassId() {
        String reId = majorMapper.findMaxMajorId();
        return DoWithString.doWithReid(reId);
    }

    @Override
    public String insertMapDataResult(MajorWithCollegeDto majorWCD) {
        if (findMajorById(majorWCD.getMajorId()) != null) { //如果添加的专业编号已存在
            return "majorIdExist";
        }
        if (findMajorByName(majorWCD.getMajorName()) != null) { //如果添加的专业名称已存在
            return "majorNameExist";
        }
        if (!majorWCD.getMajorTeaId().equals("")) { //如果该专业有负责人
            if (teacherMapper.findTeacherById(majorWCD.getMajorTeaId()) == null) { //如果负责人id不存在
                return "majorTeaIdNotExist";
            } else {
                String majorId = findMajorIdByTeaId(majorWCD.getMajorTeaId());
                if (majorId != null) { //若(majorId==null)可以执行添加该负责人
                    return "majorTeaIdRepeat";
                }
            }
        }
        //通过验证则insert
        insertMajor(majorWCD);
        return "insertSuccess";
    }

    @Override
    public void insertMajor(MajorWithCollegeDto majorWCD) {
        majorMapper.insertMajor(majorWCD);
        collegeMapper.updateMajorNumAddOne(collegeMapper.findIdByCollegeName(majorWCD.getMajorCollegeName()));
        //新班级人数为专业，不用计算调整学生人数
    }

    @Override
    public void deleteOneMajor(MajorWithCollegeDto majorWCD) {
        //所在学院专业数-1
        collegeMapper.updateMajorNumMinusOne(collegeMapper.findIdByCollegeName(majorWCD.getMajorCollegeName()));

        //删除专业下的教师
        teacherMapper.deleteTeachersByMajorId(majorWCD.getMajorId());
        //删除专业下的班级及学生
        //先找出该专业下的所有班级
        List<Class> classes = classMapper.findClassByMajorId(majorWCD.getMajorId());
        for (Class myClass : classes) {
            studentMapper.deleteStudentsByClassId(myClass.getClassId());
        }
        classMapper.deleteClassByMajorId(majorWCD.getMajorId());

        //重置学院学生人数
        College college = collegeMapper.findCollegeById(collegeMapper.findIdByCollegeName(majorWCD.getMajorCollegeName()));
        Integer newCollegeStuNum = college.getCollegeStuNum() - majorWCD.getMajorStuNum();
        collegeMapper.updateSetStuNum(newCollegeStuNum, college.getCollegeId());

        //删除
        majorMapper.deleteOneMajor(majorWCD.getMajorId());
    }

    @Override
    public void deleteManyMajors(List<MajorWithCollegeDto> majorWCDs) {
        for (MajorWithCollegeDto majorWCD : majorWCDs) {
            deleteOneMajor(majorWCD);
        }
    }

}
