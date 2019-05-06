package com.springmvc.service;

import com.github.pagehelper.PageInfo;
import com.springmvc.dto.CollegeDto;
import com.springmvc.dto.MyPage;
import com.springmvc.entity.College;
import com.springmvc.entity.User;

import java.util.List;

public interface CollegeService {
    /**
     * @return java.util.List<com.springmvc.entity.College>
     * @Author JinZhiyun
     * @Description 找出所有的学院
     * @Date 22:53 2019/4/18
     * @Param []
     **/
    List<College> findAllCollege();

    /**
     * @return com.github.pagehelper.PageInfo<com.springmvc.dto.CollegeDto>
     * @Author JinZhiyun
     * @Description 返回符合的学院信息的分页结果
     * @Date 22:55 2019/4/18
     * @Param [myPage, collegeName]
     **/
    PageInfo<CollegeDto> queryAllCollegeInfo(MyPage myPage, String collegeName);

    /**
     * @return com.github.pagehelper.PageInfo<com.springmvc.dto.CollegeDto>
     * @Author JinZhiyun
     * @Description 返回用户Id对应的学院及其附带信息分页结果
     * @Date 17:31 2019/4/19
     * @Param [myPage, user]
     **/
    PageInfo<CollegeDto> findCollegeOwnInfoById(MyPage myPage, User user);

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 根据业务逻辑返回更新学院ajax的map键data值
     * @Date 16:49 2019/5/2
     * @Param [collegeOriId, collegeOriName, collegeDto]
     **/
    String updateMapDataResult(String collegeOriId, String collegeOriName, CollegeDto collegeDto);

    /**
     * @return com.springmvc.entity.College
     * @Author JinZhiyun
     * @Description 根据学院id找到学院
     * @Date 16:53 2019/5/2
     * @Param [collegeId]
     **/
    College findCollegeById(String collegeId);

    /**
     * @return com.springmvc.entity.College
     * @Author JinZhiyun
     * @Description 根据学院名称找到学院
     * @Date 16:56 2019/5/2
     * @Param [collegeName]
     **/
    College findCollegeByName(String collegeName);

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 返回负责人所在的学院id
     * @Date 17:01 2019/5/2
     * @Param [CollegeTeaId]
     **/
    String findCollegeIdByTeaId(String collegeTeaId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 更新学院信息业务
     * @Date 17:09 2019/5/2
     * @Param [collegeOriId, collegeDto]
     **/
    void updateInfo(String collegeOriId, CollegeDto collegeDto);

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 返回添加学院级时的建议学院编号
     * @Date 17:23 2019/5/2
     * @Param []
     **/
    String findRecommendedMajorId();

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 根据业务逻辑返回更新学院ajax的map键data值
     * @Date 17:29 2019/5/2
     * @Param [collegeDto]
     **/
    String insertMapDataResult(CollegeDto collegeDto);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 添加学院业务
     * @Date 19:34 2019/5/2
     * @Param [collegeDto]
     **/
    void insertCollege(CollegeDto collegeDto);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 删除学院业务
     * @Date 21:04 2019/5/2
     * @Param [collegeDto]
     **/
    void deleteOneCollege(CollegeDto collegeDto);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 删除多个学院
     * @Date 21:59 2019/5/2
     * @Param [collegeDtos]
     **/
    void deleteManyColleges(List<CollegeDto> collegeDtos);

    /**
     * @Author JinZhiyun
     * @Description 返回学院名字及其对应的人数
     * @Date 21:36 2019/5/5
     * @Param []
     * @return java.util.List<java.lang.Object>
     **/
    List<Object> findCollegeStuNumPercent();
}
