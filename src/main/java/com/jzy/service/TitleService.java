package com.jzy.service;

import com.jzy.entity.Title;

import java.util.List;

/**
 * @author JinZhiyun
 * @IntefaceName TitleService
 * @Description 教师职称业务接口
 * @Date 2019/6/14 12:52
 * @Version 1.0
 **/
public interface TitleService {
    /**
     * @author JinZhiyun
     * @Description 查询所有的教师职称信息
     * @Date 20:59 2019/6/25
     * @Param []
     * @return java.util.List<com.jzy.entity.Title>
     **/
    List<Title> selectAllTitle();
}

