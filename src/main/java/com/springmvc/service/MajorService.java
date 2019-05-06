package com.springmvc.service;

import com.github.pagehelper.PageInfo;
import com.springmvc.dto.MajorWithCollegeDto;
import com.springmvc.dto.MyPage;
import com.springmvc.entity.Major;
import com.springmvc.entity.User;

import java.util.List;

public interface MajorService {
    /**
     * @return java.util.List<com.springmvc.entity.Major>
     * @Author JinZhiyun
     * @Description 返回符合条件的专业信息
     * @Date 22:57 2019/4/18
     * @Param [collegeName]
     **/
    List<Major> findMajorByCollegeName(String collegeName);

    /**
     * @return com.github.pagehelper.PageInfo<com.springmvc.dto.MajorWithCollegeDto>
     * @Author JinZhiyun
     * @Description 返回符合条件的专业信息的分页结果
     * @Date 23:00 2019/4/18
     * @Param [myPage, majorCollegeName, majorName]
     **/
    PageInfo<MajorWithCollegeDto> queryAllMajorInfo(MyPage myPage, String majorCollegeName, String majorName);

    /**
     * @return com.github.pagehelper.PageInfo<com.springmvc.dto.MajorWithCollegeDto>
     * @Author JinZhiyun
     * @Description 返回用户Id对应的专业及其附带信息分页结果
     * @Date 16:25 2019/4/19
     * @Param [myPage, user]
     **/
    PageInfo<MajorWithCollegeDto> findMajorOwnInfoById(MyPage myPage, User user);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 专业学生数+1，同时其所属学院学生数+1
     * @Date 15:57 2019/4/29
     * @Param [stuMajorName, stuCollegeName]
     **/
    void majorStuNumAddOne(String stuMajorName, String stuCollegeName);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 专业学生数-1，同时其所属学院学生数-1
     * @Date 16:09 2019/4/29
     * @Param [stuMajorName, stuCollegeName]
     **/
    void majorStuNumMinusOne(String stuMajorName, String stuCollegeName);

    /**
     * @return com.springmvc.entity.Major
     * @Author JinZhiyun
     * @Description 根据专业id找到专业
     * @Date 11:18 2019/5/2
     * @Param [majorId]
     **/
    Major findMajorById(String majorId);

    /**
     * @return com.springmvc.entity.Major
     * @Author JinZhiyun
     * @Description 根据专业名称找到专业
     * @Date 11:23 2019/5/2
     * @Param [majorName]
     **/
    Major findMajorByName(String majorName);

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 返回负责人所在的专业id
     * @Date 11:30 2019/5/2
     * @Param [majorTeaId]
     **/
    String findMajorIdByTeaId(String majorTeaId);

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 根据业务逻辑返回更新专业ajax的map键data值
     * @Date 11:15 2019/5/2
     * @Param [majorOriId, majorOriName, majorWCD]
     **/
    String updateMapDataResult(String majorOriId, String majorOriName, MajorWithCollegeDto majorWCD);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 更新专业信息业务
     * @Date 12:21 2019/5/2
     * @Param [majorOriId, majorWCD]
     **/
    void updateInfo(String majorOriId, MajorWithCollegeDto majorWCD);

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 返回添加专业级时的建议专业级编号
     * @Date 12:51 2019/5/2
     * @Param []
     **/
    String findRecommendedClassId();

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 根据业务逻辑返回更新专业ajax的map键data值
     * @Date 13:04 2019/5/2
     * @Param [majorWCD]
     **/
    String insertMapDataResult(MajorWithCollegeDto majorWCD);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 添加专业业务
     * @Date 13:23 2019/5/2
     * @Param [majorWCD]
     **/
    void insertMajor(MajorWithCollegeDto majorWCD);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 删除专业业务
     * @Date 13:43 2019/5/2
     * @Param [majorWCD]
     **/
    void deleteOneMajor(MajorWithCollegeDto majorWCD);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 删除多个专业
     * @Date 16:17 2019/5/2
     * @Param [majorWCDs]
     **/
    void deleteManyMajors(List<MajorWithCollegeDto> majorWCDs);

    /**
     * @Author JinZhiyun
     * @Description 返回相应专业名字及其对应的人数
     * @Date 22:19 2019/5/5
     * @Param [collegeName]
     * @return java.util.List<java.lang.Object>
     **/
    List<Object> findMajorStuNumPercent(String collegeName);
}
