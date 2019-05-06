package com.springmvc.dao;

import com.springmvc.dto.StudentInfoSearchDto;
import com.springmvc.dto.StudentWithMajorCollegeDto;
import com.springmvc.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    /**
     * @return java.util.List<com.springmvc.dto.StudentWithMajorCollegeDto>
     * @Author JinZhiyun
     * @Description 查询所有符合条件的学生及其附带信息
     * @Date 23:02 2019/4/18
     * @Param [stuISD]
     **/
    List<StudentWithMajorCollegeDto> queryAllStuInfo(StudentInfoSearchDto stuISD);

    /**
     * @return com.springmvc.entity.Student
     * @Author JinZhiyun
     * @Description 查询学号对应的学生
     * @Date 9:22 2019/4/19
     * @Param [stuId]
     **/
    Student findStudentById(@Param("stuId") String stuId);

    /**
     * @return java.util.List<com.springmvc.dto.StudentWithMajorCollegeDto>
     * @Author JinZhiyun
     * @Description 查询用户Id对应的学生及其附带信息
     * @Date 10:21 2019/4/19
     * @Param [stuId]
     **/
    List<StudentWithMajorCollegeDto> findStudentOwnInfoById(@Param("stuId") String stuId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 修改学生信息
     * @Date 22:15 2019/4/25
     * @Param [stuWMCD]
     **/
    void updateInfo(@Param("stuOriId") String stuOriId, @Param("stuWMCD") StudentWithMajorCollegeDto stuWMCD);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 向学生表插入一个学生
     * @Date 18:50 2019/4/26
     * @Param [stuWMCD]
     **/
    void insertStudent(StudentWithMajorCollegeDto stuWMCD);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 删除一个学生
     * @Date 22:07 2019/4/26
     * @Param [stuId]
     **/
    void deleteOneStudent(@Param("stuId") String stuId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 根据班级id删除学生
     * @Date 8:21 2019/5/1
     * @Param [classId]
     **/
    void deleteStudentsByClassId(@Param("classId") String classId);

    /**
     * @Author JinZhiyun
     * @Description 查找符合条件的对应性别的学生数
     * @Date 9:55 2019/5/5
     * @Param [stuSex, stuCollegeName, stuMajorName, stuClassName]
     * @return java.lang.Integer
     **/
    Integer findStuNumBySex(@Param("stuSex") String stuSex, @Param("stuCollegeName") String stuCollegeName
            , @Param("stuMajorName") String stuMajorName,@Param("stuClassName") String stuClassName);
}
