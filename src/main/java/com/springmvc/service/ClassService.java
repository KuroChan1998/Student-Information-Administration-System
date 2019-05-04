package com.springmvc.service;

import com.github.pagehelper.PageInfo;
import com.springmvc.dto.ClassWithMajorCollegeDto;
import com.springmvc.dto.MyPage;
import com.springmvc.entity.Class;
import com.springmvc.entity.User;

import java.util.List;

public interface ClassService {
    /**
     * @return com.github.pagehelper.PageInfo<com.springmvc.dto.ClassWithMajorCollegeDto>
     * @Author JinZhiyun
     * @Description 返回符合条件的班级信息分页结果
     * @Date 15:46 2019/4/19
     * @Param [myPage, className, classMajorName, classCollegeName]
     **/
    PageInfo<ClassWithMajorCollegeDto> queryAllClassInfo(MyPage myPage, String className, String classMajorName, String classCollegeName);

    /**
     * @return com.github.pagehelper.PageInfo<com.springmvc.dto.ClassWithMajorCollegeDto>
     * @Author JinZhiyun
     * @Description 返回用户Id对应的班级及其附带信息分页结果
     * @Date 15:47 2019/4/19
     * @Param [myPage, user]
     **/
    PageInfo<ClassWithMajorCollegeDto> findClassOwnInfoById(MyPage myPage, User user);

    /**
     * @return java.util.List<com.springmvc.entity.Class>
     * @Author JinZhiyun
     * @Description 返回符合条件的班级信息
     * @Date 22:50 2019/4/23
     * @Param [majorName]
     **/
    List<Class> findClassNameByMajor(String majorName);

    /**
     * @return com.springmvc.entity.Class
     * @Author JinZhiyun
     * @Description 由班级编号查询班级信息业务
     * @Date 20:31 2019/4/28
     * @Param [classId]
     **/
    Class findClassById(String classId);

    /**
     * @return com.springmvc.entity.Class
     * @Author JinZhiyun
     * @Description 由班级名称查询班级信息业务
     * @Date 20:59 2019/4/28
     * @Param [className]
     **/
    Class findClassByName(String className);

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 根据业务逻辑返回更新班级ajax的map键data值
     * @Date 21:12 2019/4/28
     * @Param [classOriId, classOriName, classWMCD]
     **/
    String updateMapDataResult(String classOriId, String classOriName, ClassWithMajorCollegeDto classWMCD);

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 返回班长所在的班级id
     * @Date 21:28 2019/4/28
     * @Param [classMoniId]
     **/
    String findClassIdByMoniId(String classMoniId);

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 返回班主任所在的班级id
     * @Date 21:45 2019/4/28
     * @Param [classTeaId]
     **/
    String findClassIdByTeaId(String classTeaId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 更新班级业务
     * @Date 22:23 2019/4/28
     * @Param [classOriId, classWMCD]
     **/
    void updateInfo(String classOriId, ClassWithMajorCollegeDto classWMCD);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 添加班级业务
     * @Date 22:14 2019/4/28
     * @Param [classWMCD]
     **/
    void insertClass(ClassWithMajorCollegeDto classWMCD);

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 根据业务逻辑返回更新班级ajax的map键data值
     * @Date 12:45 2019/4/29
     * @Param [classWMCD]
     **/
    String insertMapDataResult(ClassWithMajorCollegeDto classWMCD);

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 返回添加班级时的建议班级编号
     * @Date 10:14 2019/4/29
     * @Param []
     **/
    String findRecommendedClassId();

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 班级学生数+1，同时其所属专业、学院学生数+1
     * @Date 15:59 2019/4/29
     * @Param [stUClassName, stuMajorName, stuCollegeName]
     **/
    void classStuNumAddOne(String stuClassName, String stuMajorName, String stuCollegeName);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 班级学生数-1，同时其所属专业、学院学生数-1
     * @Date 16:11 2019/4/29
     * @Param [stUClassName, stuMajorName, stuCollegeName]
     **/
    void classStuNumMinusOne(String stuClassName, String stuMajorName, String stuCollegeName);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 删除班级业务
     * @Date 8:32 2019/5/1
     * @Param [classWMCD]
     **/
    void deleteOneClass(ClassWithMajorCollegeDto classWMCD);

    /**
     * @return
     * @Author JinZhiyun
     * @Description 删除多个班级
     * @Date 9:34 2019/5/2
     * @Param
     **/
    void deleteManyClasses(List<ClassWithMajorCollegeDto> classWMCDs);
}
