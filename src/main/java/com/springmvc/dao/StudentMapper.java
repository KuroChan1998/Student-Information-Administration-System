package com.springmvc.dao;

import com.springmvc.dto.other.senior.ObjectTotalGroupByCommonName;
import com.springmvc.dto.other.senior.StudentTotalGroupBySex;
import com.springmvc.dto.student.StudentSearchDto;
import com.springmvc.dto.student.StudentWithGradeClassMajorCollegeDto;
import com.springmvc.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JinZhiyun
 * @IntefaceName StudentMapper
 * @Description 学生业务持久层接口
 * @Date 2019/6/14 13:00
 * @Version 1.0
 **/
@Repository
public interface StudentMapper {
    /**
     * @return com.springmvc.entity.Student
     * @author JinZhiyun
     * @Description 查询学号对应的学生
     * @Date 14:57 2019/6/19
     * @Param [stuNum]
     **/
    Student selectStudentByNum(@Param("stuNum") String stuNum);

    /**
     * @return java.util.List<com.springmvc.dto.student.StudentWithGradeClassMajorCollegeDto>
     * @author JinZhiyun
     * @Description 查询所有符合条件的学生及其附带信息
     * @Date 14:57 2019/6/19
     * @Param [studentSearch]
     **/
    List<StudentWithGradeClassMajorCollegeDto> selectAllStudentInfo(StudentSearchDto studentSearch);

    /**
     * @author JinZhiyun
     * @Description 查询用户名对应的学生及其附带信息
     * @Date 12:40 2019/7/7
     * @Param [stuNum]
     * @return com.springmvc.dto.student.StudentWithGradeClassMajorCollegeDto
     **/
    StudentWithGradeClassMajorCollegeDto selectStudentOwnInfoByNum(@Param("stuNum") String stuNum);

    /**
     * @return void
     * @author JinZhiyun
     * @Description 修改学生信息
     * @Date 16:50 2019/6/23
     * @Param [stuOriNum, stuWGCMC]
     **/
    void updateStudentInfo(@Param("stuOriNum") String stuOriNum, @Param("stuWGCMC") StudentWithGradeClassMajorCollegeDto stuWGCMC);

    /**
     * @return void
     * @author JinZhiyun
     * @Description 向学生表插入一个学生
     * @Date 21:21 2019/6/23
     * @Param [stuWGCMC]
     **/
    void insertStudent(StudentWithGradeClassMajorCollegeDto stuWGCMC);

    /**
     * @return void
     * @author JinZhiyun
     * @Description 删除一个学生
     * @Date 8:36 2019/6/24
     * @Param [stuNum]
     **/
    void deleteOneStudent(@Param("stuNum") String stuNum);

    /**
     * @return void
     * @author JinZhiyun
     * @Description 删除多个学生
     * @Date 19:17 2019/6/24
     * @Param [stuNums]
     **/
    void deleteManyStudents(List<String> stuNums);

    /**
     * @author JinZhiyun
     * @Description 查找符合条件的对应性别的学生数的封装对象
     * @Date 17:59 2019/7/14
     * @Param [stuCollegeName, stuMajorName, stuClassName]
     * @return java.util.List<com.springmvc.dto.other.senior.StudentTotalGroupBySex>
     **/
    List<StudentTotalGroupBySex> selectStuTotalBySex(@Param("stuCollegeName") String stuCollegeName, @Param("stuMajorName") String stuMajorName, @Param("stuClassName") String stuClassName);

    /**
     * @author JinZhiyun
     * @Description 查找符合条件的对应类别名称的学生数的封装对象，这里用mysql存储过程实现
     * CREATE DEFINER=`root`@`%` PROCEDURE `count_stu_percent`(IN type_ varchar(20), IN college_name_ varchar(50), IN major_name_ varchar(50))
     * BEGIN
     * 	if (type_ = 'allCollege') then
     * 		select count(*) as total,c2.college_name as commonName from student s1 inner join class c1 on s1.stu_class=c1.class_id inner join major m1 on c1.class_major=m1.major_id inner join college c2 on c2.college_id=m1.major_college group by c2.college_id;
     * 	elseif (type_ = 'majorUnderCollege') then
     * 		select count(*) as total,m1.major_name as commonName from student s1 inner join class c1 on s1.stu_class=c1.class_id inner join major m1 on c1.class_major=m1.major_id inner join college c2 on c2.college_id=m1.major_college and c2.college_name=college_name_ group by m1.major_id;
     * 	elseif (type_ = 'allMajor') then
     * 		select count(*) as total,m1.major_name as commonName from student s1 inner join class c1 on s1.stu_class=c1.class_id inner join major m1 on c1.class_major=m1.major_id group by m1.major_id;
     * 	elseif (type_ = 'classUnderMajor') then
     * 		select count(*) as total,c1.class_name as commonName from student s1 inner join class c1 on s1.stu_class=c1.class_id inner join major m1 on c1.class_major=m1.major_id and m1.major_name=major_name_ group by c1.class_id;
     * 	elseif (type_ = 'allClass') then
     * 		select count(*) as total,c1.class_name as commonName from student s1 inner join class c1 on s1.stu_class=c1.class_id group by c1.class_id;
     * 	elseif (type_ = 'grade') then
     * 		select count(*) as total,g1.grade_name as commonName from student s1 inner join grade g1 on s1.stu_grade=g1.grade_id group by g1.grade_id;
     * 	end if;
     * END
     * @Date 9:05 2019/7/16
     * @Param [type, collegeName, majorName]
     * @return java.util.List<com.springmvc.dto.other.senior.ObjectTotalGroupByCommonName>
     **/
    List<ObjectTotalGroupByCommonName> selectStuTotalByCommonName(@Param("type") String type, @Param("collegeName") String collegeName, @Param("majorName") String majorName);
}
