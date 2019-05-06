package com.springmvc.service;

import com.github.pagehelper.PageInfo;
import com.springmvc.dto.MyPage;
import com.springmvc.dto.StudentInfoSearchDto;
import com.springmvc.dto.StudentWithMajorCollegeDto;
import com.springmvc.entity.Student;

import java.util.List;

public interface StudentService {
    /**
     * @return com.github.pagehelper.PageInfo<com.springmvc.dto.StudentWithMajorCollegeDto>
     * @Author JinZhiyun
     * @Description 返回符合条件的学生信息分页结果
     * @Date 23:02 2019/4/18
     * @Param [myPage, stuISD]
     **/
    PageInfo<StudentWithMajorCollegeDto> queryAllStuInfo(MyPage myPage, StudentInfoSearchDto stuISD);

    /**
     * @return com.springmvc.entity.Student
     * @Author JinZhiyun
     * @Description 由学号返回相应学生
     * @Date 9:22 2019/4/19
     * @Param [stuId]
     **/
    Student findStudentById(String stuId);

    /**
     * @return com.github.pagehelper.PageInfo<com.springmvc.dto.StudentWithMajorCollegeDto>
     * @Author JinZhiyun
     * @Description 返回用户Id对应的学生及其附带信息分页结果
     * @Date 10:44 2019/4/19
     * @Param [myPage, stuId]
     **/
    PageInfo<StudentWithMajorCollegeDto> findStudentOwnInfoById(MyPage myPage, String stuId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 修改学生信息
     * @Date 22:15 2019/4/25
     * @Param [stuWMCD]
     **/
    void updateInfo(String stuOriId, StudentWithMajorCollegeDto stuWMCD);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 添加学生业务
     * @Date 19:32 2019/4/26
     * @Param [stuWMCD]
     **/
    void insertStudent(StudentWithMajorCollegeDto stuWMCD);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 删除一个学生业务
     * @Date 22:08 2019/4/26
     * @Param [stuWMCD]
     **/
    void deleteOneStudent(StudentWithMajorCollegeDto stuWMCD);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 批量删除学生业务
     * @Date 23:34 2019/4/26
     * @Param [stuWMCDs]
     **/
    void deleteManyStudents(List<StudentWithMajorCollegeDto> stuWMCDs);

    /**
     * @return com.springmvc.dto.StudentWithMajorCollegeDto
     * @Author JinZhiyun
     * @Description 找到学生所在的班级专业学院名称
     * @Date 16:25 2019/4/29
     * @Param [stuId]
     **/
    StudentWithMajorCollegeDto findStuClassMajorCollegeName(String stuId);

    /**
     * @Author JinZhiyun
     * @Description 查找符合条件的对应性别的学生数，返回长度为3的list，第一项为总人数，第二项为男生个数，第三项为女生个数
     * @Date 18:48 2019/5/5
     * @Param [stuCollegeName, stuMajorName, stuClassName]
     * @return java.util.List<java.lang.Integer>
     **/
    List<Integer> findStuNumBySex(String stuCollegeName, String stuMajorName, String stuClassName);
}
