package com.jzy.service;

import com.github.pagehelper.PageInfo;
import com.jzy.dto.other.MyPage;
import com.jzy.dto.other.senior.ObjectTotalGroupByCommonName;
import com.jzy.dto.teacher.TeacherSearchDto;
import com.jzy.dto.teacher.TeacherWithTitleMajorCollegeDto;
import com.jzy.entity.Teacher;

import java.util.List;

/**
 * @author JinZhiyun
 * @IntefaceName TitleService
 * @Description 教师业务接口
 * @Date 2019/6/14 12:52
 * @Version 1.0
 **/
public interface TeacherService {
    /**
     * @author JinZhiyun
     * @Description 返回所有符合条件教师信息的分页结果
     * @Date 8:39 2019/6/30
     * @Param [myPage, teacherSearch]
     * @return com.github.pagehelper.PageInfo<com.jzy.dto.teacher.TeacherWithTitleMajorCollegeDto>
     **/
    PageInfo<TeacherWithTitleMajorCollegeDto> selectAllTeacherInfo(MyPage myPage, TeacherSearchDto teacherSearch);

    /**
     * @author JinZhiyun
     * @Description 返回用户名对应的教师及其附带信息分页结果
     * @Date 9:08 2019/6/30
     * @Param [myPage, teaNum]
     * @return com.github.pagehelper.PageInfo<com.jzy.dto.teacher.TeacherWithTitleMajorCollegeDto>
     **/
    PageInfo<TeacherWithTitleMajorCollegeDto> selectTeacherOwnInfoByNum(MyPage myPage, String teaNum);

    /**
     * @author JinZhiyun
     * @Description 返回工号对应的教师
     * @Date 12:31 2019/6/30
     * @Param [teaNum]
     * @return com.jzy.entity.Teacher
     **/
    Teacher selectTeacherByNum(String teaNum);

    /**
     * @author JinZhiyun
     * @Description 修改教师信息
     * @Date 12:36 2019/6/30
     * @Param [teaOriNum, teaWTMC]
     * @return void
     **/
    void updateTeacherInfo(String teaOriNum, TeacherWithTitleMajorCollegeDto teaWTMC);

    /**
     * @author JinZhiyun
     * @Description 添加教师业务
     * @Date 16:16 2019/6/30
     * @Param [teaWTMC]
     * @return void
     **/
    void insertTeacher(TeacherWithTitleMajorCollegeDto teaWTMC);

    /**
     * @author JinZhiyun
     * @Description 删除一个教师业务
     * @Date 16:42 2019/6/30
     * @Param [teaNum]
     * @return void
     **/
    void deleteOneTeacher(String teaNum);

    /**
     * @author JinZhiyun
     * @Description 删除多个教师业务
     * @Date 17:15 2019/6/30
     * @Param [teaNums]
     * @return void
     **/
    void deleteManyTeachers(List<String> teaNums);

    /**
     * @author JinZhiyun
     * @Description 由工号返回相应教师信息
     * @Date 12:49 2019/7/7
     * @Param [teaNum]
     * @return com.jzy.dto.teacher.TeacherWithTitleMajorCollegeDto
     **/
    TeacherWithTitleMajorCollegeDto selectTeacherInfoByNum(String teaNum);

    /**
     * @author JinZhiyun
     * @description 查找符合条件的对应类别名称的教师数的封装对象，这里直接调用dao，其用mysql存储过程实现
     * type为查询的类型：
     *      allCollegeByTeaTitle：查询某学院下的不同职称教师人数
     *      wholeSchoolByTeaTitle： 查询全校的不同职称教师人数
     *      allMajorByTeaTitle： 查询某专业下的不同职称教师人数
     * @date 18:25 2019/10/14
     * @Param [type, collegeName, majorName]
     * @return java.util.List<com.jzy.dto.other.senior.ObjectTotalGroupByCommonName>
     **/
    List<ObjectTotalGroupByCommonName> selectTeaTotalByCommonName(String type, String collegeName, String majorName);

}
