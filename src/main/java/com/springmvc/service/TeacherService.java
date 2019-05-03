package com.springmvc.service;

import com.github.pagehelper.PageInfo;
import com.springmvc.dto.MyPage;
import com.springmvc.dto.TeacherInfoSearchDto;
import com.springmvc.dto.TeacherWithMajorCollegeDto;
import com.springmvc.entity.Teacher;

import java.util.List;

public interface TeacherService {
    /**
     * @return com.github.pagehelper.PageInfo<com.springmvc.dto.TeacherWithMajorCollegeDto>
     * @Author JinZhiyun
     * @Description 返回所有符合条件教师信息的分页结果
     * @Date 9:18 2019/4/19
     * @Param [myPage, teaISD]
     **/
    PageInfo<TeacherWithMajorCollegeDto> queryAllTeaInfo(MyPage myPage, TeacherInfoSearchDto teaISD);

    /**
     * @return com.springmvc.entity.Teacher
     * @Author JinZhiyun
     * @Description 返回工号对应的教师
     * @Date 9:23 2019/4/19
     * @Param [teaId]
     **/
    Teacher findTeacherById(String teaId);

    /**
     * @return com.github.pagehelper.PageInfo<com.springmvc.dto.TeacherWithMajorCollegeDto>
     * @Author JinZhiyun
     * @Description 返回用户Id对应的教师及其附带信息分页结果
     * @Date 11:06 2019/4/19
     * @Param [myPage, teaId]
     **/
    PageInfo<TeacherWithMajorCollegeDto> findTeacherOwnInfoById(MyPage myPage, String teaId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 修改学生信息
     * @Date 22:26 2019/4/27
     * @Param [teaOriId, teaWMCD]
     **/
    void updateInfo(String teaOriId, TeacherWithMajorCollegeDto teaWMCD);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 添加教师业务
     * @Date 22:50 2019/4/27
     * @Param [teaWmCD]
     **/
    void insertTeacher(TeacherWithMajorCollegeDto teaWMCD);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 删除一个教师业务
     * @Date 23:08 2019/4/27
     * @Param [teaWMCD]
     **/
    void deleteOneTeacher(TeacherWithMajorCollegeDto teaWMCD);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 删除多个教师业务
     * @Date 8:18 2019/4/28
     * @Param [teaWMCD]
     **/
    void deleteManyTeachers(List<TeacherWithMajorCollegeDto> teaWMCDs);
}
