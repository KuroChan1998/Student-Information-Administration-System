package com.jzy.dao;

import com.jzy.entity.Title;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JinZhiyun
 * @IntefaceName TitleMapper.xml
 * @Description 教师职称业务持久层接口
 * @Date 2019/6/14 13:00
 * @Version 1.0
 **/
public interface TitleMapper {
    /**
     * @author JinZhiyun
     * @Description 查询所有的教师职称信息
     * @Date 20:57 2019/6/25
     * @Param []
     * @return java.util.List<com.jzy.entity.Title>
     **/
    List<Title> selectAllTitle();


    /**
     * @author JinZhiyun
     * @Description 查询所有的教师职称名称
     * @Date 21:14 2019/7/23
     * @Param []
     * @return java.util.List<java.lang.String>
     **/
    List<String> selectAllTitleName();
}
