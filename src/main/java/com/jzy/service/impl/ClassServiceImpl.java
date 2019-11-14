package com.jzy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jzy.dao.ClassMapper;
import com.jzy.dto.clazz.ClassSearchDto;
import com.jzy.dto.clazz.ClassWithGradeMajorCollegeDto;
import com.jzy.dto.other.MyPage;
import com.jzy.entity.Class;
import com.jzy.entity.User;
import com.jzy.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ClassServiceImpl
 * @Author JinZhiyun
 * @Description 班级业务实现
 * @Date 2019/4/16 18:07
 * @Version 1.0
 **/
@Service("classService")
public class ClassServiceImpl extends BaseServiceImpl implements ClassService {
    @Autowired
    private ClassMapper classMapper;

    @Override
    public List<Class> selectClassByMajorName(String majorName) {
        return classMapper.selectClassByMajorName(majorName);
    }

    @Override
    public PageInfo<ClassWithGradeMajorCollegeDto> selectAllClassInfo(MyPage myPage, ClassSearchDto classSearch) {
        PageHelper.startPage(myPage.getPageNum(), myPage.getPageSize());//第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
        List<ClassWithGradeMajorCollegeDto> classWGMCs = classMapper.selectAllClassInfo(classSearch);
        return new PageInfo<>(classWGMCs);
    }

    @Override
    public PageInfo<ClassWithGradeMajorCollegeDto> selectClassOwnInfoByNum(MyPage myPage, User user) {
        List<ClassWithGradeMajorCollegeDto> classWGMCs = new ArrayList<>();
        if (user.getUserIdentity().equals("学生")) {
            PageHelper.startPage(myPage.getPageNum(), myPage.getPageSize());//第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
            classWGMCs.add(classMapper.selectStuClassOwnInfoByNum(user.getUserName()));
        } else if (user.getUserIdentity().equals("教师")) {
            PageHelper.startPage(myPage.getPageNum(), myPage.getPageSize());//第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
            classWGMCs.add(classMapper.selectTeaClassOwnInfoByNum(user.getUserName()));
        }
        return new PageInfo<>(classWGMCs);
    }

    @Override
    public String updateClassInfo(String classOriId, String classOriName, ClassWithGradeMajorCollegeDto classWGMC) {
        if (!classOriName.equals(classWGMC.getClassName())) { //如果班级名称修改过了
            if (classMapper.selectClassByName(classWGMC.getClassName()) != null) { //如果修改后的班级编号已存在
                return "classNameExist";
            }
        }
        if (!classWGMC.getClassStuNum().equals("")) { //如果该班级有班长
            if (studentService.selectStudentByNum(classWGMC.getClassStuNum()) == null) { //如果班长学号不存在
                return "classMoniNumNotExist";
            } else {
                Class mClass = classMapper.selectClassByStuNum(classWGMC.getClassStuNum());
                if (!(mClass == null || mClass.getClassId().equals(classOriId))) { //若(classId==null|| classId.equals(classOriId)可以执行更新该班长
                    return "classMoniNumRepeat";
                }
            }

        }
        if (!classWGMC.getClassTeaNum().equals("")) { //如果该班级有班主任
            if (teacherService.selectTeacherByNum(classWGMC.getClassTeaNum()) == null) { //如果班主任长id不存在
                return "classTeaNumNotExist";
            } else {
                Class mClass = classMapper.selectClassByTeaNum(classWGMC.getClassTeaNum());
                if (!(mClass == null || mClass.getClassId().equals(classOriId))) { //若(classId==null|| classId.equals(classOriId)可以执行更新该班主任
                    return "classTeaNumRepeat";
                }
            }
        }

        //通过验证则update
        classMapper.updateClassInfo(classOriId, classWGMC);
        return "updateSuccess";
    }

    @Override
    public void updateClassStuNum(String stuOriNum, String stuNum) {
        classMapper.updateClassStuNum(stuOriNum, stuNum);
    }

    @Override
    public void updateClassTeaNum(String teaOriNum, String teaNum) {
        classMapper.updateClassTeaNum(teaOriNum, teaNum);
    }

    @Override
    public String insertClass(ClassWithGradeMajorCollegeDto classWGMC) {
        if (classMapper.selectClassByName(classWGMC.getClassName()) != null) { //如果添加的班级名称已存在
            return "classNameExist";
        }
        if (!classWGMC.getClassStuNum().equals("")) { //如果该班级有班长
            if (studentService.selectStudentByNum(classWGMC.getClassStuNum()) == null) { //如果班长学号不存在
                return "classMoniNumNotExist";
            } else {
                Class mClass = classMapper.selectClassByStuNum(classWGMC.getClassStuNum());
                if (mClass != null) {
                    return "classMoniNumRepeat";
                }
            }

        }
        if (!classWGMC.getClassTeaNum().equals("")) { //如果该班级有班主任
            if (teacherService.selectTeacherByNum(classWGMC.getClassTeaNum()) == null) { //如果班主任长id不存在
                return "classTeaNumNotExist";
            } else {
                Class mClass = classMapper.selectClassByTeaNum(classWGMC.getClassTeaNum());
                if (mClass != null) {
                    return "classTeaNumRepeat";
                }
            }
        }

        classMapper.insertClass(classWGMC);
        return "insertSuccess";
    }

    @Override
    public void deleteOneClass(String className) {
        //删除该班级
        classMapper.deleteOneClass(className);
    }

    @Override
    public void deleteManyClasses(List<String> classNames) {
        for (String className : classNames) {
            deleteOneClass(className);
        }
    }
}
