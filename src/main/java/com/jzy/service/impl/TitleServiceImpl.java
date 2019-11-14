package com.jzy.service.impl;

import com.jzy.dao.TitleMapper;
import com.jzy.entity.Title;
import com.jzy.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JinZhiyun
 * @ClassName TitleServiceImpl
 * @Description 教师职称业务实现
 * @Date 2019/6/14 12:44
 * @Version 1.0
 **/
@Service("titleService")
public class TitleServiceImpl extends BaseServiceImpl implements TitleService {
    @Autowired
    private TitleMapper titleMapper;

    @Override
    public List<Title> selectAllTitle() {
        return titleMapper.selectAllTitle();
    }

    @Override
    public List<String> selectAllTitleName() {
        return titleMapper.selectAllTitleName();
    }
}
