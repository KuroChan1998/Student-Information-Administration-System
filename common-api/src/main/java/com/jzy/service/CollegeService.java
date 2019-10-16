package com.jzy.service;

import com.github.pagehelper.PageInfo;
import com.jzy.dto.college.CollegeDto;
import com.jzy.dto.college.CollegeSearchDto;
import com.jzy.dto.other.MyPage;
import com.jzy.entity.College;
import com.jzy.entity.User;

import java.util.List;

/**
 * @author JinZhiyun
 * @IntefaceName CollegeService
 * @Description 学院业务接口
 * @Date 2019/6/14 12:52
 * @Version 1.0
 **/
public interface CollegeService {
    /**
     * @author JinZhiyun
     * @Description 找出所有的学院
     * @Date 13:34 2019/6/14
     * @Param []
     * @return java.util.List<com.jzy.entity.College>
     **/
    List<College> selectAllCollege();

    /**
     * @author JinZhiyun
     * @Description 返回符合的学院信息的分页结果
     * @Date 13:21 2019/7/25
     * @Param [myPage, collegeSearchDto]
     * @return com.github.pagehelper.PageInfo<com.jzy.dto.college.CollegeDto>
     **/
    PageInfo<CollegeDto> selectAllCollegeInfo(MyPage myPage, CollegeSearchDto collegeSearchDto);

    /**
     * @author JinZhiyun
     * @Description 返回用户名对应的学院及其附带信息分页结果
     * @Date 19:40 2019/7/10
     * @Param [myPage, user]
     * @return com.github.pagehelper.PageInfo<com.jzy.dto.college.CollegeDto>
     **/
    PageInfo<CollegeDto> selectCollegeOwnInfoByNum(MyPage myPage, User user);

    /**
     * @author JinZhiyun
     * @Description 根据业务逻辑返回更新学院ajax的map键data值
     * @Date 9:51 2019/7/14
     * @Param [collegeOriId, collegeOriName, collegeDto]
     * @return java.lang.String
     **/
    String updateCollegeInfo(String collegeOriId, String collegeOriName, CollegeDto collegeDto);

    /**
     * @author JinZhiyun
     * @description 更新学院教师负责人工号
     * @date 18:14 2019/10/14
     * @Param [teaOriNum, teaNum]
     * @return void
     **/
    void updateCollegeTeaNum(String teaOriNum, String teaNum);

    /**
     * @author JinZhiyun
     * @Description 根据业务逻辑返回更新学院ajax的map键data值
     * @Date 15:05 2019/7/14
     * @Param [collegeDto]
     * @return java.lang.String
     **/
    String insertCollege(CollegeDto collegeDto);

    /**
     * @author JinZhiyun
     * @Description 删除学院业务
     * @Date 15:58 2019/7/14
     * @Param [collegeName]
     * @return void
     **/
    void deleteOneCollege(String collegeName);

    /**
     * @author JinZhiyun
     * @Description 删除多个学院
     * @Date 16:06 2019/7/14
     * @Param [collegeNames]
     * @return void
     **/
    void deleteManyColleges(List<String> collegeNames);
}
