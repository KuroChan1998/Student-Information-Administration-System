package com.springmvc.service;

import com.github.pagehelper.PageInfo;
import com.springmvc.dto.classP.ClassSearchDto;
import com.springmvc.dto.classP.ClassWithGradeMajorCollegeDto;
import com.springmvc.dto.other.MyPage;
import com.springmvc.entity.Class;
import com.springmvc.entity.User;

import java.util.List;

/**
 * @author JinZhiyun
 * @IntefaceName ClassService
 * @Description 班级业务接口
 * @Date 2019/6/14 12:52
 * @Version 1.0
 **/
public interface ClassService {
    /**
     * @return java.util.List<com.springmvc.entity.Class>
     * @Author JinZhiyun
     * @Description 返回符合条件的班级信息
     * @Date 22:50 2019/4/23
     * @Param [majorName]
     **/
    List<Class> selectClassByMajorName(String majorName);

    /**
     * @author JinZhiyun
     * @Description 返回符合条件的班级信息分页结果
     * @Date 8:46 2019/7/7
     * @Param [myPage, classSearch]
     * @return com.github.pagehelper.PageInfo<com.springmvc.dto.classP.ClassWithGradeMajorCollegeDto>
     **/
    PageInfo<ClassWithGradeMajorCollegeDto> selectAllClassInfo(MyPage myPage, ClassSearchDto classSearch);

    /**
     * @author JinZhiyun
     * @Description 返回用户名对应的班级及其附带信息分页结果
     * @Date 11:44 2019/7/7
     * @Param [myPage, user]
     * @return com.github.pagehelper.PageInfo<com.springmvc.dto.classP.ClassWithGradeMajorCollegeDto>
     **/
    PageInfo<ClassWithGradeMajorCollegeDto> selectClassOwnInfoByNum(MyPage myPage, User user);

    /**
     * @author JinZhiyun
     * @Description 根据业务逻辑返回更新班级ajax的map键data值
     * @Date 16:46 2019/7/7
     * @Param [classOriName, classWGMC]
     * @return java.lang.String
     **/
    String updateClassInfo(String classOriId, String classOriName, ClassWithGradeMajorCollegeDto classWGMC);

    /**
     * @author JinZhiyun
     * @Description 根据业务逻辑返回更新班级ajax的map键data值
     * @Date 18:30 2019/7/7
     * @Param [classWGMC]
     * @return java.lang.String
     **/
    String insertClass(ClassWithGradeMajorCollegeDto classWGMC);

    /**
     * @author JinZhiyun
     * @Description 删除一个班级业务
     * @Date 9:32 2019/7/8
     * @Param [className]
     * @return void
     **/
    void deleteOneClass(String className);

    /**
     * @author JinZhiyun
     * @Description 删除多个班级业务
     * @Date 9:45 2019/7/8
     * @Param [classNames]
     * @return void
     **/
    void deleteManyClasses(List<String> classNames);
}
