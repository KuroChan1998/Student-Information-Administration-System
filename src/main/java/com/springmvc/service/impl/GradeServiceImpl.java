package com.springmvc.service.impl;

import com.springmvc.entity.Grade;
import com.springmvc.service.GradeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author JinZhiyun
 * @ClassName GradeServiceImpl
 * @Description 年级业务实现
 * @Date 2019/6/14 12:56
 * @Version 1.0
 **/
@Service
@Transactional
public class GradeServiceImpl extends BaseServiceImpl implements GradeService {
    @Override
    public List<Grade> selectAllGrade() {
        return gradeMapper.selectAllGrade();
    }
}
