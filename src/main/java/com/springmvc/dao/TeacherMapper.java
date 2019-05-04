package com.springmvc.dao;

import com.springmvc.dto.TeacherInfoSearchDto;
import com.springmvc.dto.TeacherWithMajorCollegeDto;
import com.springmvc.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper {
    /**
     * @Author JinZhiyun
     * @Description 查询所有符合条件的教师及其附带信息
     * @Date 9:20 2019/4/19
     * @Param [teaISD]
     * @return java.util.List<com.springmvc.dto.TeacherWithMajorCollegeDto>
     **/
    List<TeacherWithMajorCollegeDto> queryAllTeaInfo(TeacherInfoSearchDto teaISD);

    /**
     * @Author JinZhiyun
     * @Description 查询工号对应的教师
     * @Date 9:23 2019/4/19
     * @Param [teaId]
     * @return com.springmvc.entity.Teacher
     **/
    Teacher findTeacherById(@Param("teaId") String teaId);

    /**
     * @Author JinZhiyun
     * @Description 查询用户Id对应的教师及其附带信息
     * @Date 11:00 2019/4/19
     * @Param [teaId]
     * @return java.util.List<com.springmvc.dto.TeacherWithMajorCollegeDto>
     **/
    List<TeacherWithMajorCollegeDto> findTeacherOwnInfoById(@Param("teaId")String teaId);

    /**
     * @Author JinZhiyun
     * @Description 修改教师信息
     * @Date 22:11 2019/4/27
     * @Param [stuOriId, stuWMCD]
     * @return void
     **/
    void updateInfo(@Param("teaOriId") String teaOriId, @Param("teaWMCD") TeacherWithMajorCollegeDto teaWMCD);

    /**
     * @Author JinZhiyun
     * @Description 向教师表插入一个教师
     * @Date 22:41 2019/4/27
     * @Param [teaWmCD]
     * @return void
     **/
    void insertTeacher(TeacherWithMajorCollegeDto teaWMCD);

    /**
     * @Author JinZhiyun
     * @Description 删除一个教师
     * @Date 22:59 2019/4/27
     * @Param [teaId]
     * @return void
     **/
    void deleteOneTeacher(@Param("teaId")String teaId);

    /**
     * @Author JinZhiyun
     * @Description 根据专业id删除教师
     * @Date 13:46 2019/5/2
     * @Param [majorId]
     * @return void
     **/
    void deleteTeachersByMajorId(@Param("majorId") String majorId);
}
