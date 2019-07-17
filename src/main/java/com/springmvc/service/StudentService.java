package com.springmvc.service;

import com.github.pagehelper.PageInfo;
import com.springmvc.dto.other.MyPage;
import com.springmvc.dto.other.senior.StudentPercentBySex;
import com.springmvc.dto.student.StudentSearchDto;
import com.springmvc.dto.student.StudentWithGradeClassMajorCollegeDto;
import com.springmvc.entity.Student;

import java.util.List;

/**
 * @author JinZhiyun
 * @IntefaceName StudentService
 * @Description 学生业务接口
 * @Date 2019/6/14 12:52
 * @Version 1.0
 **/
public interface StudentService {
    /**
     * @author JinZhiyun
     * @Description 返回学号对应的学生
     * @Date 10:06 2019/6/23
     * @Param [stuNum]
     * @return com.springmvc.entity.Student
     **/
    Student selectStudentByNum(String stuNum);

    /**
     * @author JinZhiyun
     * @Description 返回符合条件的学生信息分页结果
     * @Date 15:14 2019/6/19
     * @Param [myPage, studentSearch]
     * @return com.github.pagehelper.PageInfo<com.springmvc.dto.student.StudentWithGradeClassMajorCollegeDto>
     **/
    PageInfo<StudentWithGradeClassMajorCollegeDto> selectAllStudentInfo(MyPage myPage, StudentSearchDto studentSearch);

    /**
     * @author JinZhiyun
     * @Description 返回用户名对应的学生及其附带信息分页结果
     * @Date 23:19 2019/6/19
     * @Param [myPage, stuNum]
     * @return com.github.pagehelper.PageInfo<com.springmvc.dto.student.StudentWithGradeClassMajorCollegeDto>
     **/
    PageInfo<StudentWithGradeClassMajorCollegeDto> selectStudentOwnInfoByNum(MyPage myPage, String stuNum);

    /**
     * @author JinZhiyun
     * @Description 修改学生信息
     * @Date 16:49 2019/6/23
     * @Param [stuOriNum, stuWGCMC]
     * @return void
     **/
    void updateStudentInfo(String stuOriNum, StudentWithGradeClassMajorCollegeDto stuWGCMC);

    /**
     * @author JinZhiyun
     * @Description 添加学生业务
     * @Date 21:19 2019/6/23
     * @Param [stuWGCMC]
     * @return void
     **/
    void insertStudent(StudentWithGradeClassMajorCollegeDto stuWGCMC);

    /**
     * @author JinZhiyun
     * @Description 删除一个学生业务
     * @Date 18:43 2019/6/24
     * @Param [stuNum]
     * @return void
     **/
    void deleteOneStudent(String stuNum);

    /**
     * @author JinZhiyun
     * @Description 批量删除学生业务
     * @Date 19:11 2019/6/24
     * @Param [stuNums]
     * @return void
     **/
    void deleteManyStudents(List<String> stuNums);

    /**
     * @author JinZhiyun
     * @Description 由学号返回相应学生信息
     * @Date 12:45 2019/7/7
     * @Param [stuNum]
     * @return com.springmvc.dto.student.StudentWithGradeClassMajorCollegeDto
     **/
    StudentWithGradeClassMajorCollegeDto selectStudentInfoByNum(String stuNum);

}
