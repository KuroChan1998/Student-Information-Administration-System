package com.springmvc.dao;

import com.springmvc.dto.major.MajorSearchDto;
import com.springmvc.dto.major.MajorWithCollegeDto;
import com.springmvc.entity.Major;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JinZhiyun
 * @IntefaceName MajorMapper
 * @Description 专业业务持久层接口
 * @Date 2019/6/14 13:00
 * @Version 1.0
 **/
@Repository
public interface MajorMapper {
    /**
     * @return java.util.List<com.springmvc.entity.Major>
     * @Author JinZhiyun
     * @Description 查询符合条件的专业信息
     * @Date 22:58 2019/4/18
     * @Param [collegeName]
     **/
    List<Major> selectMajorByCollegeName(@Param("collegeName") String collegeName);

    /**
     * @author JinZhiyun
     * @Description 由专业名称查询专业信息
     * @Date 9:15 2019/7/12
     * @Param [collegeName]
     * @return com.springmvc.entity.Major
     **/
    Major selectMajorByName(@Param("majorName") String majorName);

    /**
     * @author JinZhiyun
     * @Description 由专业教师负责人查询专业信息
     * @Date 9:39 2019/7/12
     * @Param [teaNum]
     * @return com.springmvc.entity.Major
     **/
    Major selectMajorByTeaNum(@Param("teaNum") String teaNum);

    /**
     * @author JinZhiyun
     * @Description 更新专业教师负责人学号
     * @Date 12:51 2019/6/30
     * @Param [teaOriNum, teaNum]
     * @return void
     **/
    void updateMajorTeaNum(@Param("teaOriNum") String teaOriNum, @Param("teaNum") String teaNum);

    /**
     * @author JinZhiyun
     * @Description 查询所有符合条件的专业信息
     * @Date 16:39 2019/7/8
     * @Param [majorSearch]
     * @return java.util.List<com.springmvc.dto.major.MajorWithCollegeDto>
     **/
    List<MajorWithCollegeDto> selectAllMajorInfo(MajorSearchDto majorSearch);

    /**
     * @author JinZhiyun
     * @Description 查询学生用户名对应的专业及其附带信息
     * @Date 17:33 2019/7/8
     * @Param [stuNum]
     * @return com.springmvc.dto.major.MajorWithCollegeDto
     **/
    MajorWithCollegeDto selectStuMajorOwnInfoByNum(@Param("stuNum") String stuNum);

    /**
     * @author JinZhiyun
     * @Description 查询教师用户名对应的专业及其附带信息
     * @Date 17:33 2019/7/8
     * @Param [teaNum]
     * @return com.springmvc.dto.major.MajorWithCollegeDto
     **/
    MajorWithCollegeDto selectTeaMajorOwnInfoByNum(@Param("teaNum") String teaNum);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 修改专业信息
     * @Date 12:33 2019/5/2
     * @Param [majorOriId, majorWCD]
     **/
    void updateMajorInfo(@Param("majorOriId") String majorOriId, @Param("majorWC") MajorWithCollegeDto majorWC);

    /**
     * @author JinZhiyun
     * @Description 向专业表插入一个专业
     * @Date 8:41 2019/7/14
     * @Param [majorWC]
     * @return void
     **/
    void insertMajor(MajorWithCollegeDto majorWC);

    /**
     * @author JinZhiyun
     * @Description
     * @Date 8:57 2019/7/14
     * @Param [majorName]
     * @return void
     **/
    void deleteOneMajor(@Param("majorName") String majorName);
}
