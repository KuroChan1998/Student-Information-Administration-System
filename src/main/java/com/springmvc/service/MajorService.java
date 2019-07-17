package com.springmvc.service;

import com.github.pagehelper.PageInfo;
import com.springmvc.dto.major.MajorSearchDto;
import com.springmvc.dto.major.MajorWithCollegeDto;
import com.springmvc.dto.other.MyPage;
import com.springmvc.entity.Major;
import com.springmvc.entity.User;

import java.util.List;

/**
 * @author JinZhiyun
 * @IntefaceName MajorService
 * @Description 专业业务接口
 * @Date 2019/6/14 12:52
 * @Version 1.0
 **/
public interface MajorService {
    /**
     * @return java.util.List<com.springmvc.entity.Major>
     * @Author JinZhiyun
     * @Description 返回符合条件的专业信息
     * @Date 22:57 2019/4/18
     * @Param [collegeName]
     **/
    List<Major> selectMajorByCollegeName(String collegeName);

    /**
     * @author JinZhiyun
     * @Description 返回符合条件的专业信息的分页结果
     * @Date 16:38 2019/7/8
     * @Param [myPage, majorSearch]
     * @return com.github.pagehelper.PageInfo<com.springmvc.dto.major.MajorWithCollegeDto>
     **/
    PageInfo<MajorWithCollegeDto> selectAllMajorInfo(MyPage myPage, MajorSearchDto majorSearch);

    /**
     * @author JinZhiyun
     * @Description 返回用户名对应的专业及其附带信息分页结果
     * @Date 17:19 2019/7/8
     * @Param [myPage, user]
     * @return com.github.pagehelper.PageInfo<com.springmvc.dto.major.MajorWithCollegeDto>
     **/
    PageInfo<MajorWithCollegeDto> selectMajorOwnInfoByNum(MyPage myPage, User user);

    /**
     * @author JinZhiyun
     * @Description 根据业务逻辑返回更新专业ajax的map键data值
     * @Date 8:58 2019/7/12
     * @Param [majorOriId, majorOriName, majorWC]
     * @return java.lang.String
     **/
    String updateMajorInfo(String majorOriId, String majorOriName, MajorWithCollegeDto majorWC);

    /**
     * @author JinZhiyun
     * @Description 根据业务逻辑返回更新专业ajax的map键data值
     * @Date 8:37 2019/7/14
     * @Param [majorWC]
     * @return java.lang.String
     **/
    String insertMajor(MajorWithCollegeDto majorWC);

    /**
     * @author JinZhiyun
     * @Description 删除专业业务
     * @Date 8:57 2019/7/14
     * @Param [majorName]
     * @return void
     **/
    void deleteOneMajor(String majorName);

    /**
     * @author JinZhiyun
     * @Description 删除多个专业业务
     * @Date 9:17 2019/7/14
     * @Param [majorNames]
     * @return void
     **/
    void deleteManyMajors(List<String> majorNames);
}
