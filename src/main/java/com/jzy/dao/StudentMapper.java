package com.jzy.dao;

import com.jzy.dto.other.senior.ObjectTotalGroupByCommonName;
import com.jzy.dto.other.senior.StudentTotalGroupBySex;
import com.jzy.dto.student.StudentSearchDto;
import com.jzy.dto.student.StudentWithGradeClassMajorCollegeDto;
import com.jzy.entity.Student;
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
public interface StudentMapper {
    /**
     * @return com.jzy.entity.Student
     * @author JinZhiyun
     * @Description 查询学号对应的学生
     * @Date 14:57 2019/6/19
     * @Param [stuNum]
     **/
    Student selectStudentByNum(@Param("stuNum") String stuNum);

    /**
     * @return java.util.List<com.jzy.dto.student.StudentWithGradeClassMajorCollegeDto>
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
     * @return com.jzy.dto.student.StudentWithGradeClassMajorCollegeDto
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
     * @return java.util.List<com.jzy.dto.other.senior.StudentTotalGroupBySex>
     **/
    List<StudentTotalGroupBySex> selectStuTotalBySex(@Param("stuCollegeName") String stuCollegeName, @Param("stuMajorName") String stuMajorName, @Param("stuClassName") String stuClassName);

    /**
     * @author JinZhiyun
     * @Description 查找符合条件的对应类别名称的学生数的封装对象，这里用mysql存储过程实现
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
     * CREATE DEFINER=`root`@`%` PROCEDURE `count_stu_percent`(IN type_ varchar(50), IN college_name_ varchar(50), IN major_name_ varchar(50))
     * BEGIN
     * 	case type_
     * 		when 'allCollege' then
     * 			select count(*) as total,c2.college_name as commonName from student s1 inner join class c1 on s1.stu_class=c1.class_id inner join major m1 on c1.class_major=m1.major_id inner join college c2 on c2.college_id=m1.major_college group by c2.college_id;
     * 		when 'majorUnderCollege' then
     * 			select count(*) as total,m1.major_name as commonName from student s1 inner join class c1 on s1.stu_class=c1.class_id inner join major m1 on c1.class_major=m1.major_id inner join college c2 on c2.college_id=m1.major_college and c2.college_name=college_name_ group by m1.major_id;
     * 		when 'allMajor' then
     * 			select count(*) as total,m1.major_name as commonName from student s1 inner join class c1 on s1.stu_class=c1.class_id inner join major m1 on c1.class_major=m1.major_id group by m1.major_id;
     * 		when 'classUnderMajor' then
     * 			select count(*) as total,c1.class_name as commonName from student s1 inner join class c1 on s1.stu_class=c1.class_id inner join major m1 on c1.class_major=m1.major_id and m1.major_name=major_name_ group by c1.class_id;
     * 		when 'allClass' then
     * 			select count(*) as total,c1.class_name as commonName from student s1 inner join class c1 on s1.stu_class=c1.class_id group by c1.class_id;
     * 		when 'grade' then
     * 			select count(*) as total,g1.grade_name as commonName from student s1 inner join grade g1 on s1.stu_grade=g1.grade_id group by g1.grade_id;
     * 		when 'allCollegeByStuDegree' then
     * 			select count(*) as total,s1.stu_degree as commonName from student s1 inner join class c1 on s1.stu_class=c1.class_id inner join major m1 on c1.class_major=m1.major_id inner join college c2 on c2.college_id=m1.major_college and c2.college_name=college_name_ group by s1.stu_degree;
     * 		when 'majorUnderCollegeByStuDegree' then
     * 			select count(*) as total,s1.stu_degree as commonName from student s1 inner join class c1 on s1.stu_class=c1.class_id inner join major m1 on c1.class_major=m1.major_id and m1.major_name=major_name_ group by s1.stu_degree;
     *      when 'wholeSchoolByStuDegree' then
     * 			select count(*) as total,s1.stu_degree as commonName from student s1 group by s1.stu_degree;
     *      else
     * 			select 0 as total,'' as commonName;
     *   end case;
     * END
     * @Date 9:05 2019/7/16
     * @Param [type, collegeName, majorName]
     * @return java.util.List<com.jzy.dto.other.senior.ObjectTotalGroupByCommonName>
     **/
    List<ObjectTotalGroupByCommonName> selectStuTotalByCommonName(@Param("type") String type, @Param("collegeName") String collegeName, @Param("majorName") String majorName);
}
