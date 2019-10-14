package com.jzy.service;

import com.jzy.entity.Grade;

import java.util.List;

/**
 * @author JinZhiyun
 * @IntefaceName GradeService
 * @Description 年级业务接口
 * @Date 2019/6/14 12:52
 * @Version 1.0
 **/
public interface GradeService {
    /**
     * @author JinZhiyun
     * @Description 查询所有的年级信息
     * @Date 22:45 2019/6/18
     * @Param []
     * @return java.util.List<com.jzy.entity.Grade>
     **/
    List<Grade> selectAllGrade();

    /**
     * @author JinZhiyun
     * @description 更新年级学生负责人学号
     * @date 18:08 2019/10/14
     * @Param [stuOriNum, stuNum]
     * @return void
     **/
    void updateGradeStuNum(String stuOriNum, String stuNum);

    /**
     * @author JinZhiyun
     * @description 更新年级教师负责人学号
     * @date 18:11 2019/10/14
     * @Param [teaOriNum, teaNum]
     * @return void
     **/
    void updateGradeTeaNum(String teaOriNum, String teaNum);
}
