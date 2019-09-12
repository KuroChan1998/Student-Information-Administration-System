package com.jzy.dao;

import com.jzy.dto.college.CollegeDto;
import com.jzy.dto.college.CollegeSearchDto;
import com.jzy.entity.College;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JinZhiyun
 * @IntefaceName CollegeMapper
 * @Description 学院业务持久层接口
 * @Date 2019/6/14 13:00
 * @Version 1.0
 **/
@Repository
public interface CollegeMapper {
    /**
     * @author JinZhiyun
     * @Description 找出数据库中所有的学院
     * @Date 13:36 2019/6/14
     * @Param []
     * @return java.util.List<com.jzy.entity.College>
     **/
    List<College> selectAllCollege();

    /**
     * @author JinZhiyun
     * @Description 由学院名称查询学院信息
     * @Date 9:53 2019/7/14
     * @Param [collegeName]
     * @return com.jzy.entity.College
     **/
    College selectCollegeByName(@Param("collegeName") String collegeName);

    /**
     * @author JinZhiyun
     * @Description 查询学院负责人所在的学院信息
     * @Date 9:56 2019/7/14
     * @Param [teaNum]
     * @return com.jzy.entity.College
     **/
    College selectCollegeByTeaNum(@Param("teaNum") String teaNum);

    /**
     * @author JinZhiyun
     * @Description 更新学院教师负责人工号
     * @Date 12:53 2019/6/30
     * @Param [teaOriNum, teaNum]
     * @return void
     **/
    void updateCollegeTeaNum(@Param("teaOriNum") String teaOriNum, @Param("teaNum") String teaNum);

    /**
     * @author JinZhiyun
     * @Description 查询符合条件的所有学院信息
     * @Date 13:21 2019/7/25
     * @Param [collegeSearchDto]
     * @return java.util.List<com.jzy.dto.college.CollegeDto>
     **/
    List<CollegeDto> selectAllCollegeInfo(CollegeSearchDto collegeSearchDto);

    /**
     * @author JinZhiyun
     * @Description 查询学生用户名对应的学院及其附带信息
     * @Date 19:44 2019/7/10
     * @Param [userName]
     * @return com.jzy.dto.college.CollegeDto
     **/
    CollegeDto selectStuCollegeOwnInfoByNum(@Param("stuNum") String stuNum);

    /**
     * @author JinZhiyun
     * @Description 查询教师用户名对应的学院及其附带信息
     * @Date 19:54 2019/7/10
     * @Param [userName]
     * @return com.jzy.dto.college.CollegeDto
     **/
    CollegeDto selectTeaCollegeOwnInfoByNum(@Param("teaNum") String teaNum);

    /**
     * @author JinZhiyun
     * @Description 修改学院信息
     * @Date 13:40 2019/7/14
     * @Param [collegeOriId, collegeDto]
     * @return void
     **/
    void updateCollegeInfo(@Param("collegeOriId") String collegeOriId, @Param("collegeDto") CollegeDto collegeDto);

    /**
     * @author JinZhiyun
     * @Description 向学院表插入一个学院
     * @Date 15:12 2019/7/14
     * @Param [collegeDto]
     * @return void
     **/
    void insertCollege(CollegeDto collegeDto);

    /**
     * @author JinZhiyun
     * @Description 删除一个学院
     * @Date 15:59 2019/7/14
     * @Param [collegeName]
     * @return void
     **/
    void deleteOneCollege(@Param("collegeName") String collegeName);
}
