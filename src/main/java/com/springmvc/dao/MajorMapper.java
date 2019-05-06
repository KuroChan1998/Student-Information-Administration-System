package com.springmvc.dao;

import com.springmvc.dto.MajorWithCollegeDto;
import com.springmvc.entity.Major;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorMapper {
    /**
     * @return java.util.List<com.springmvc.entity.Major>
     * @Author JinZhiyun
     * @Description 查询符合条件的专业信息
     * @Date 22:58 2019/4/18
     * @Param [collegeName]
     **/
    List<Major> findMajorByCollegeName(@Param("collegeName") String collegeName);

    /**
     * @return java.util.List<com.springmvc.dto.MajorWithCollegeDto>
     * @Author JinZhiyun
     * @Description 查询所有符合条件的专业信息
     * @Date 23:00 2019/4/18
     * @Param [majorCollegeName, majorName]
     **/
    List<MajorWithCollegeDto> queryAllMajorInfo(@Param("majorCollegeName") String majorCollegeName, @Param("majorName") String majorName);

    /**
     * @return java.util.List<com.springmvc.dto.MajorWithCollegeDto>
     * @Author JinZhiyun
     * @Description 查询学生用户Id对应的专业及其附带信息
     * @Date 16:12 2019/4/19
     * @Param [stuId]
     **/
    List<MajorWithCollegeDto> findStuMajorOwnInfoById(@Param("stuId") String stuId);

    /**
     * @return java.util.List<com.springmvc.dto.MajorWithCollegeDto>
     * @Author JinZhiyun
     * @Description 查询教师用户Id对应的专业及其附带信息
     * @Date 16:20 2019/4/19
     * @Param [stuId]
     **/
    List<MajorWithCollegeDto> findTeaMajorOwnInfoById(@Param("teaId") String teaId);

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 由专业名称寻找专业id
     * @Date 19:26 2019/4/26
     * @Param [majorName]
     **/
    String findIdByMajorName(@Param("majorName") String majorName);

    /**
     * @return com.springmvc.entity.Major
     * @Author JinZhiyun
     * @Description 由专业id查询专业信息
     * @Date 19:23 2019/5/1
     * @Param [majorId]
     **/
    Major findMajorById(@Param("majorId") String majorId);

    /**
     * @return com.springmvc.entity.Major
     * @Author JinZhiyun
     * @Description 由专业名称查询专业信息
     * @Date 11:22 2019/5/2
     * @Param [majorName]
     **/
    Major findMajorByName(@Param("majorName") String majorName);

    /**
     * @return java.util.List<com.springmvc.entity.Major>
     * @Author JinZhiyun
     * @Description 找到指定学院id下的所有专业
     * @Date 20:17 2019/5/2
     * @Param [collegeId]
     **/
    List<Major> findMajorByCollegeId(@Param("collegeId") String collegeId);

    /**
     * @return com.springmvc.entity.Major
     * @Author JinZhiyun
     * @Description 查询专业负责人所在的专业信息
     * @Date 11:28 2019/5/2
     * @Param [teaId]
     **/
    Major findMajorByTeaId(@Param("teaId") String teaId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 指定专业学生人数+1
     * @Date 19:28 2019/4/26
     * @Param [majorId]
     **/
    void updateStuNumAddOne(@Param("majorId") String majorId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 指定专业学生人数-1
     * @Date 22:02 2019/4/26
     * @Param [majorId]
     **/
    void updateStuNumMinusOne(@Param("majorId") String majorId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 更新专业负责人id
     * @Date 22:22 2019/4/27
     * @Param [majorsOriTeaId, majorNewTeaId]
     **/
    void updateTeaId(@Param("majorsOriTeaId") String majorsOriTeaId, @Param("majorNewTeaId") String majorNewTeaId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 删除教师id时若该教师是专业负责人任将此专业专业负责人id置null？
     * @Date 23:04 2019/4/27
     * @Param [majorTeaId]
     **/
    void updateDeleteTea(@Param("majorTeaId") String majorTeaId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 指定专业班级个数+1
     * @Date 15:13 2019/4/29
     * @Param [majorId]
     **/
    void updateClassNumAddOne(@Param("majorId") String majorId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 指定专业班级个数-1
     * @Date 15:13 2019/4/29
     * @Param [majorId]
     **/
    void updateClassNumMinusOne(@Param("majorId") String majorId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 设定专业学生个数为majorStuNum
     * @Date 22:47 2019/5/1
     * @Param [majorStuNum, majorId]
     **/
    void updateSetStuNum(@Param("majorStuNum") Integer majorStuNum, @Param("majorId") String majorId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 修改专业信息
     * @Date 12:33 2019/5/2
     * @Param [majorOriId, majorWCD]
     **/
    void updateInfo(@Param("majorOriId") String majorOriId, @Param("majorWCD") MajorWithCollegeDto majorWCD);

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 找到最大的专业编号
     * @Date 12:50 2019/5/2
     * @Param []
     **/
    String findMaxMajorId();

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 向专业表插入一个专业
     * @Date 13:16 2019/5/2
     * @Param [majorWCD]
     **/
    void insertMajor(MajorWithCollegeDto majorWCD);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 删除一个专业
     * @Date 14:25 2019/5/2
     * @Param [majorId]
     **/
    void deleteOneMajor(@Param("majorId") String majorId);
}
