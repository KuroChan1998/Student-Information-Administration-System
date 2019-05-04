package com.springmvc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmvc.dao.*;
import com.springmvc.dto.ClassWithMajorCollegeDto;
import com.springmvc.dto.MyPage;
import com.springmvc.entity.Class;
import com.springmvc.entity.College;
import com.springmvc.entity.Major;
import com.springmvc.entity.User;
import com.springmvc.service.ClassService;
import com.springmvc.service.MajorService;
import com.springmvc.service.impl.util.DoWithString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName ClassServiceImpl
 * @Author JinZhiyun
 * @Description 班级业务实现
 * @Date 2019/4/16 18:07
 * @Version 1.0
 **/
@Service
@Transactional
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private MajorService majorService;

    @Override
    public PageInfo<ClassWithMajorCollegeDto> queryAllClassInfo(MyPage myPage, String className, String classMajorName, String classCollegeName) {
        PageHelper.startPage(myPage.getPageNum(), myPage.getPageSize());//第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
        List<ClassWithMajorCollegeDto> classWMCDs = classMapper.queryAllClassInfo(className, classMajorName, classCollegeName);
        return new PageInfo<>(classWMCDs);
    }

    @Override
    public PageInfo<ClassWithMajorCollegeDto> findClassOwnInfoById(MyPage myPage, User user) {
        List<ClassWithMajorCollegeDto> classWMCDs;
        if (user.getUserIdentity().equals("学生")) {
            PageHelper.startPage(myPage.getPageNum(), myPage.getPageSize());//第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
            classWMCDs = classMapper.findStuClassOwnInfoById(user.getUserId());
        } else {
            PageHelper.startPage(myPage.getPageNum(), myPage.getPageSize());//第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
            classWMCDs = classMapper.findTeaClassOwnInfoById(user.getUserId());
        }
        return new PageInfo<>(classWMCDs);
    }

    @Override
    public List<Class> findClassNameByMajor(String majorName) {
        return classMapper.findClassNameByMajor(majorName);
    }

    @Override
    public Class findClassById(String classId) {
        return classMapper.findClassById(classId);
    }

    @Override
    public Class findClassByName(String className) {
        return classMapper.findClassByName(className);
    }

    @Override
    public String updateMapDataResult(String classOriId, String classOriName, ClassWithMajorCollegeDto classWMCD) {
        if (!classOriId.equals(classWMCD.getClassId())) { //如果班级编号修改过了
            if (findClassById(classWMCD.getClassId()) != null) { //如果修改后的班级编号已存在
                return "classIdExist";
            }
        }
        if (!classOriName.equals(classWMCD.getClassName())) { //如果班级名称修改过了
            if (findClassByName(classWMCD.getClassName()) != null) { //如果修改后的班级名称已存在
                return "classNameExist";
            }
        }

        if (!classWMCD.getClassMoniId().equals("")) { //如果该班级有班长
            if (studentMapper.findStudentById(classWMCD.getClassMoniId()) == null) { //如果班长id不存在
                return "classMoniIdNotExist";
            } else {
                String classId = findClassIdByMoniId(classWMCD.getClassMoniId());
                if (!(classId == null || classId.equals(classOriId))) { //若(classId==null|| classId.equals(classOriId)可以执行更新该班长
                    return "classMoniIdRepeat";
                }
            }

        }

        if (!classWMCD.getClassTeaId().equals("")) { //如果该班级有班主任
            if (teacherMapper.findTeacherById(classWMCD.getClassTeaId()) == null) { //如果班主任长id不存在
                return "classTeaIdNotExist";
            } else {
                String classId = findClassIdByTeaId(classWMCD.getClassTeaId());
                if (!(classId == null || classId.equals(classOriId))) { //若(classId==null|| classId.equals(classOriId)可以执行更新该班主任
                    return "classTeaIdRepeat";
                }
            }
        }

        //通过验证则update
        updateInfo(classOriId, classWMCD);
        return "updateSuccess";
    }

    @Override
    public String findClassIdByMoniId(String classMoniId) {
        Class myClass = classMapper.findClassByMoniId(classMoniId);
        if (myClass == null) {
            return null;
        } else {
            return myClass.getClassId();
        }
    }

    @Override
    public String findClassIdByTeaId(String classTeaId) {
        Class myClass = classMapper.findClassByTeaId(classTeaId);
        if (myClass == null) {
            return null;
        } else {
            return myClass.getClassId();
        }
    }

    @Override
    public void updateInfo(String classOriId, ClassWithMajorCollegeDto classWMCD) {
        //获取老班级专业学院信息
        Class oldClass = classMapper.findClassById(classOriId);
        Major oldMajor = majorMapper.findMajorById(oldClass.getClassMajorId());
        College oldCollege = collegeMapper.findCollegeById(oldMajor.getMajorCollegeId());
        //旧专业班级数-1
        majorMapper.updateClassNumMinusOne(oldClass.getClassMajorId());

        //原来班级所在专业学院的学生人数减去原来班级的学生人数
        Integer oldMajorStuNum = oldMajor.getMajorStuNum() - oldClass.getClassStuNum();
        Integer oldCollegeStuNum = oldCollege.getCollegeStuNum() - oldClass.getClassStuNum();
        //设置
        majorMapper.updateSetStuNum(oldMajorStuNum, oldMajor.getMajorId());
        collegeMapper.updateSetStuNum(oldCollegeStuNum, oldCollege.getCollegeId());

        //获取新班级专业学院信息
        String majorId = majorMapper.findIdByMajorName(classWMCD.getClassMajorName());
        Major newMajor = majorMapper.findMajorById(majorId);
        College newCollege = collegeMapper.findCollegeById(newMajor.getMajorCollegeId());
        //新专业班级数+1
        majorMapper.updateClassNumAddOne(majorId);

        //新班级所在专业学院的学生人数加上该班级班级的学生人数
        Integer newMajorStuNum = newMajor.getMajorStuNum() + classWMCD.getClassStuNum();
        Integer newCollegeStuNum = newCollege.getCollegeStuNum() + classWMCD.getClassStuNum();
        //设置
        majorMapper.updateSetStuNum(newMajorStuNum, newMajor.getMajorId());
        collegeMapper.updateSetStuNum(newCollegeStuNum, newCollege.getCollegeId());

        //更新班级所有信息
        classMapper.updateInfo(classOriId, classWMCD);
    }

    @Override
    public void insertClass(ClassWithMajorCollegeDto classWMCD) {
        classMapper.insertClass(classWMCD);
        majorMapper.updateClassNumAddOne(majorMapper.findIdByMajorName(classWMCD.getClassMajorName()));
        //新班级人数为0，不用计算调整学生人数
    }

    @Override
    public String insertMapDataResult(ClassWithMajorCollegeDto classWMCD) {
        if (findClassById(classWMCD.getClassId()) != null) { //如果添加班级编号已存在
            return "classIdExist";
        }
        if (findClassByName(classWMCD.getClassName()) != null) { //如果修改后的班级名称已存在
            return "classNameExist";
        }
        if (!classWMCD.getClassMoniId().equals("")) { //如果该班级有班长
            if (studentMapper.findStudentById(classWMCD.getClassMoniId()) == null) { //如果班长id不存在
                return "classMoniIdNotExist";
            } else {
                String classId = findClassIdByMoniId(classWMCD.getClassMoniId());
                if (classId != null) { //若该学生还未担任其他班班长，可以执行添加该班长
                    return "classMoniIdRepeat";
                }
            }
        }

        if (!classWMCD.getClassTeaId().equals("")) { //如果该班级有班主任
            if (teacherMapper.findTeacherById(classWMCD.getClassTeaId()) == null) { //如果班主任长id不存在
                return "classTeaIdNotExist";
            } else {
                String classId = findClassIdByTeaId(classWMCD.getClassTeaId());
                if (classId != null) { //若该教师还未担任其他班班主任，可以执行添加该教师长
                    return "classTeaIdRepeat";
                }
            }
        }
        //通过验证则insert
        insertClass(classWMCD);
        return "insertSuccess";
    }

    @Override
    public String findRecommendedClassId() {
        String reId = classMapper.findMaxClassId();
        return DoWithString.doWithReid(reId);
    }

    @Override
    public void classStuNumAddOne(String stuClassName, String stuMajorName, String stuCollegeName) {
        classMapper.updateStuNumAddOne(classMapper.findIdByClassName(stuClassName));
        majorService.majorStuNumAddOne(stuMajorName, stuCollegeName);
    }

    @Override
    public void classStuNumMinusOne(String stuClassName, String stuMajorName, String stuCollegeName) {
        classMapper.updateStuNumMinusOne(classMapper.findIdByClassName(stuClassName));
        majorService.majorStuNumMinusOne(stuMajorName, stuCollegeName);
    }

    @Override
    public void deleteOneClass(ClassWithMajorCollegeDto classWMCD) {
        //所在专业班级数-1
        majorMapper.updateClassNumMinusOne(majorMapper.findIdByMajorName(classWMCD.getClassMajorName()));
        //删除班级下的学生
        studentMapper.deleteStudentsByClassId(classWMCD.getClassId());
        //重置专业学院学生人数
        Major major = majorMapper.findMajorById(majorMapper.findIdByMajorName(classWMCD.getClassMajorName()));
        College college = collegeMapper.findCollegeById(collegeMapper.findIdByCollegeName(classWMCD.getClassCollegeName()));
        Integer newMajorStuNum = major.getMajorStuNum() - classWMCD.getClassStuNum();
        Integer newCollegeStuNum = college.getCollegeStuNum() - classWMCD.getClassStuNum();
        majorMapper.updateSetStuNum(newMajorStuNum, major.getMajorId());
        collegeMapper.updateSetStuNum(newCollegeStuNum, college.getCollegeId());
        //删除该班级
        classMapper.deleteOneClass(classWMCD.getClassId());
    }

    @Override
    public void deleteManyClasses(List<ClassWithMajorCollegeDto> classWMCDs) {
        for (ClassWithMajorCollegeDto classWMCD : classWMCDs) {
            deleteOneClass(classWMCD);
        }
    }
}
