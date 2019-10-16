package com.jzy.dao;

import com.jzy.dto.other.senior.ObjectTotalGroupByCommonName;
import com.jzy.dto.teacher.TeacherSearchDto;
import com.jzy.dto.teacher.TeacherWithTitleMajorCollegeDto;
import com.jzy.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author JinZhiyun
 * @IntefaceName TeacherMapper
 * @Description 教师业务持久层接口
 * @Date 2019/6/14 13:00
 * @Version 1.0
 **/
public interface TeacherMapper {
    /**
     * @return java.util.List<com.jzy.dto.teacher.TeacherWithTitleMajorCollegeDto>
     * @author JinZhiyun
     * @Description 查询所有符合条件的教师及其附带信息
     * @Date 8:24 2019/6/30
     * @Param [teacherSearch]
     **/
    List<TeacherWithTitleMajorCollegeDto> selectAllTeacherInfo(TeacherSearchDto teacherSearch);

    /**
     * @author JinZhiyun
     * @Description 查询用户名对应的教师及其附带信息
     * @Date 12:39 2019/7/7
     * @Param [teaNum]
     * @return com.jzy.dto.teacher.TeacherWithTitleMajorCollegeDto
     **/
    TeacherWithTitleMajorCollegeDto selectTeacherOwnInfoByNum(@Param("teaNum") String teaNum);

    /**
     * @return com.jzy.entity.Teacher
     * @author JinZhiyun
     * @Description 查询工号对应的教师
     * @Date 12:32 2019/6/30
     * @Param [teaNum]
     **/
    Teacher selectTeacherByNum(@Param("teaNum") String teaNum);

    /**
     * @return void
     * @author JinZhiyun
     * @Description 修改教师信息
     * @Date 12:57 2019/6/30
     * @Param [teaOriNum, teaWTMC]
     **/
    void updateTeacherInfo(@Param("teaOriNum") String teaOriNum, @Param("teaWTMC") TeacherWithTitleMajorCollegeDto teaWTMC);

    /**
     * @return void
     * @author JinZhiyun
     * @Description 向教师表插入一个教师
     * @Date 16:20 2019/6/30
     * @Param [teaWTMC]
     **/
    void insertTeacher(TeacherWithTitleMajorCollegeDto teaWTMC);

    /**
     * @return void
     * @author JinZhiyun
     * @Description 删除一个学生
     * @Date 16:48 2019/6/30
     * @Param [teaNum]
     **/
    void deleteOneTeacher(@Param("teaNum") String teaNum);

    /**
     * @author JinZhiyun
     * @Description 删除多个教师
     * @Date 18:28 2019/6/30
     * @Param [teaNums]
     * @return void
     **/
    void deleteManyTeachers(List<String> teaNums);

    /**
     * @author JinZhiyun
     * @Description 查找符合条件的对应类别名称的教师数的封装对象，这里用mysql存储过程实现
     * type为查询的类型：
     *      allCollegeByTeaTitle：查询某学院下的不同职称教师人数
     *      wholeSchoolByTeaTitle： 查询全校的不同职称教师人数
     *      allMajorByTeaTitle： 查询某专业下的不同职称教师人数
     * CREATE DEFINER=`root`@`localhost` PROCEDURE `count_tea_percent`(IN type_ varchar(50), IN college_name_ varchar(50), IN major_name_ varchar(50))
     * begin
     * 	case type_
     * 		when 'allCollegeByTeaTitle' then
     * 			select count(*) as total,t2.title_name as commonName from teacher t1 inner join title t2 on t1.tea_title=t2.title_id inner join major m1 on t1.tea_major=m1.major_id inner join college c1 on m1.major_college=c1.college_id and c1.college_name=college_name_ group by t1.tea_title;
     * 		when 'wholeSchoolByTeaTitle' then
     * 			select count(*) as total,t2.title_name as commonName from teacher t1 inner join title t2 on t1.tea_title=t2.title_id group by t1.tea_title;
     * 		when 'allMajorByTeaTitle' then
     * 			select count(*) as total,t2.title_name as commonName from teacher t1 inner join title t2 on t1.tea_title=t2.title_id inner join major m1 on t1.tea_major=m1.major_id and m1.major_name=major_name_ group by t1.tea_title;
     *      else
     * 			select 0 as total,'' as commonName;
     *  end case;
     * end
     * @Date 17:06 2019/7/24
     * @Param [type, collegeName, majorName]
     * @return java.util.List<com.jzy.dto.other.senior.ObjectTotalGroupByCommonName>
     **/
    List<ObjectTotalGroupByCommonName> selectTeaTotalByCommonName(@Param("type") String type, @Param("collegeName") String collegeName, @Param("majorName") String majorName);
}