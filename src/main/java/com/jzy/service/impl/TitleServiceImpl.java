package com.jzy.service.impl;

import com.jzy.entity.Title;
import com.jzy.service.TitleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author JinZhiyun
 * @ClassName TitleServiceImpl
 * @Description 教师职称业务实现
 * @Date 2019/6/14 12:44
 * @Version 1.0
 **/
@Service("titleService")
@Transactional
public class TitleServiceImpl extends BaseServiceImpl implements TitleService {
    @Override
    public List<Title> selectAllTitle() {
        return titleMapper.selectAllTitle();
    }
}
