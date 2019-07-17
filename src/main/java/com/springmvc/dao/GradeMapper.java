package com.springmvc.dao;

import com.springmvc.entity.Grade;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JinZhiyun
 * @IntefaceName GradeMapper.xml
 * @Description 年级业务持久层接口
 * @Date 2019/6/14 13:00
 * @Version 1.0
 **/
@Repository
public interface GradeMapper {
    /**
     * @author JinZhiyun
     * @Description 查询所有的年级信息
     * @Date 22:42 2019/6/18
     * @Param []
     * @return java.util.List<com.springmvc.entity.Grade>
     **/
    List<Grade> selectAllGrade();

    /**
     * @author JinZhiyun
     * @Description 更新年级学生负责人学号
     * @Date 20:33 2019/6/23
     * @Param [stuOriNum, stuNum]
     * @return void
     **/
    void updateGradeStuNum(@Param("stuOriNum") String stuOriNum, @Param("stuNum") String stuNum);

    /**
     * @author JinZhiyun
     * @Description 更新年级教师负责人学号
     * @Date 12:49 2019/6/30
     * @Param [teaOriNum, teaNum]
     * @return void
     **/
    void updateGradeTeaNum(@Param("teaOriNum") String teaOriNum, @Param("teaNum") String teaNum);
}
