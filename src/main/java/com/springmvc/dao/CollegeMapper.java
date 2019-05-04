package com.springmvc.dao;

import com.springmvc.dto.CollegeDto;
import com.springmvc.entity.College;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollegeMapper {
    /**
     * @return java.util.List<com.springmvc.entity.College>
     * @Author JinZhiyun
     * @Description 找出数据库中所有的学院
     * @Date 22:54 2019/4/18
     * @Param []
     **/
    List<College> findAllCollege();

    /**
     * @return java.util.List<com.springmvc.dto.CollegeDto>
     * @Author JinZhiyun
     * @Description 查询符合条件的所有学院信息
     * @Date 22:55 2019/4/18
     * @Param [collegeName]
     **/
    List<CollegeDto> queryAllCollegeInfo(@Param("collegeName") String collegeName);

    /**
     * @return java.util.List<com.springmvc.dto.CollegeDto>
     * @Author JinZhiyun
     * @Description 查询学生用户Id对应的学院及其附带信息
     * @Date 17:13 2019/4/19
     * @Param [stuId]
     **/
    List<CollegeDto> findStuCollegeOwnInfoById(@Param("stuId") String stuId);

    /**
     * @return java.util.List<com.springmvc.dto.CollegeDto>
     * @Author JinZhiyun
     * @Description 查询教师用户Id对应的学院及其附带信息
     * @Date 17:28 2019/4/19
     * @Param [teaId]
     **/
    List<CollegeDto> findTeaCollegeOwnInfoById(@Param("teaId") String teaId);

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 由学院名称寻找学院id
     * @Date 19:30 2019/4/26
     * @Param [majorName]
     **/
    String findIdByCollegeName(@Param("collegeName") String collegeName);

    /**
     * @return com.springmvc.entity.College
     * @Author JinZhiyun
     * @Description 由学院id查询学院信息
     * @Date 19:24 2019/5/1
     * @Param [colegeId]
     **/
    College findCollegeById(@Param("collegeId") String collegeId);

    /**
     * @return com.springmvc.entity.College
     * @Author JinZhiyun
     * @Description 查询学院负责人所在的学院信息
     * @Date 16:59 2019/5/2
     * @Param [teaId]
     **/
    College findCollegeByTeaId(@Param("teaId") String teaId);

    /**
     * @return com.springmvc.entity.College
     * @Author JinZhiyun
     * @Description 由学院名称查询学院信息
     * @Date 16:55 2019/5/2
     * @Param [collegeName]
     **/
    College findCollegeByName(@Param("collegeName") String collegeName);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 指定学院学生人数+1
     * @Date 19:30 2019/4/26
     * @Param [collegeId]
     **/
    void updateStuNumAddOne(@Param("collegeId") String collegeId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 指定学院学生人数-1
     * @Date 22:03 2019/4/26
     * @Param [collegeId]
     **/
    void updateStuNumMinusOne(@Param("collegeId") String collegeId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 指定学院专业个数+1
     * @Date 12:23 2019/5/2
     * @Param [collegeId]
     **/
    void updateMajorNumAddOne(@Param("collegeId") String collegeId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 指定学院专业个数-1
     * @Date 12:24 2019/5/2
     * @Param [collegeId]
     **/
    void updateMajorNumMinusOne(@Param("collegeId") String collegeId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 更新学院负责人id
     * @Date 22:24 2019/4/27
     * @Param [collegesOriTeaId, collegeNewTeaId]
     **/
    void updateTeaId(@Param("collegeOriTeaId") String collegesOriTeaId, @Param("collegeNewTeaId") String collegeNewTeaId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 删除教师id时若该教师是学院负责人任将此学院学院负责人id置null？
     * @Date 23:06 2019/4/27
     * @Param [collegerTeaId]
     **/
    void updateDeleteTea(@Param("collegeTeaId") String collegerTeaId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 设定学院学生个数为collegeStuNum
     * @Date 22:47 2019/5/1
     * @Param [collegeStuNum, collegeId]
     **/
    void updateSetStuNum(@Param("collegeStuNum") Integer collegeStuNum, @Param("collegeId") String collegeId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 修改学院信息
     * @Date 17:10 2019/5/2
     * @Param [collegeOriId, collegeDto]
     **/
    void updateInfo(@Param("collegeOriId") String collegeOriId, @Param("collegeDto") CollegeDto collegeDto);

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 找到最大的学院编号
     * @Date 17:22 2019/5/2
     * @Param []
     **/
    String findMaxCollegeId();

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 向学院表插入一个学院
     * @Date 17:34 2019/5/2
     * @Param [collegeDto]
     **/
    void insertCollege(CollegeDto collegeDto);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 删除一个学院
     * @Date 21:29 2019/5/2
     * @Param [collegeId]
     **/
    void deleteOneCollege(@Param("collegeId") String collegeId);
}
