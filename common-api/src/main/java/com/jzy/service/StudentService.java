package com.jzy.service;

import com.github.pagehelper.PageInfo;
import com.jzy.dto.other.MyPage;
import com.jzy.dto.other.senior.ObjectTotalGroupByCommonName;
import com.jzy.dto.other.senior.StudentTotalGroupBySex;
import com.jzy.dto.student.StudentSearchDto;
import com.jzy.dto.student.StudentWithGradeClassMajorCollegeDto;
import com.jzy.entity.Student;

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
     * @return com.jzy.entity.Student
     **/
    Student selectStudentByNum(String stuNum);

    /**
     * @author JinZhiyun
     * @Description 返回符合条件的学生信息分页结果
     * @Date 15:14 2019/6/19
     * @Param [myPage, studentSearch]
     * @return com.github.pagehelper.PageInfo<com.jzy.dto.student.StudentWithGradeClassMajorCollegeDto>
     **/
    PageInfo<StudentWithGradeClassMajorCollegeDto> selectAllStudentInfo(MyPage myPage, StudentSearchDto studentSearch);

    /**
     * @author JinZhiyun
     * @Description 返回用户名对应的学生及其附带信息分页结果
     * @Date 23:19 2019/6/19
     * @Param [myPage, stuNum]
     * @return com.github.pagehelper.PageInfo<com.jzy.dto.student.StudentWithGradeClassMajorCollegeDto>
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
     * @return com.jzy.dto.student.StudentWithGradeClassMajorCollegeDto
     **/
    StudentWithGradeClassMajorCollegeDto selectStudentInfoByNum(String stuNum);

    /**
     * @author JinZhiyun
     * @description 查找符合条件的对应性别的学生数的封装对象
     * @date 18:18 2019/10/14
     * @Param [stuCollegeName, stuMajorName, stuClassName]
     * @return java.util.List<com.jzy.dto.other.senior.StudentTotalGroupBySex>
     **/
    List<StudentTotalGroupBySex> selectStuTotalBySex(String stuCollegeName, String stuMajorName, String stuClassName);

    /**
     * @author JinZhiyun
     * @description  查找符合条件的对应类别名称的学生数的封装对象，这里调用dao层方法，其用mysql存储过程实现
     * type为查询的类型：
     *      allCollege：查询全部学院的学生人数比
     *      allMajor: 查询全部专业的学生人数比
     *      allClass：查询全部班级的学生人数比
     *      majorUnderCollege：查询特定学院下专业的学生人数比
     *      classUnderMajor：查询特定专业下班级的学生人数比
     *      grade：查询全部全部的学生人数比
     *      wholeSchoolByStuDegree：查询全校的本硕博人数比
     *      allCollegeByStuDegree: 查询全部学院的本硕博人数比
     *      majorUnderCollegeByStuDegree：查询特定学院下专业的本硕博人数比
     * @date 18:21 2019/10/14
     * @Param [type, collegeName, majorName]
     * @return java.util.List<com.jzy.dto.other.senior.ObjectTotalGroupByCommonName>
     **/
    List<ObjectTotalGroupByCommonName> selectStuTotalByCommonName(String type, String collegeName, String majorName);

}
