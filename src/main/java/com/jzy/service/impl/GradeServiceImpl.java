package com.jzy.service.impl;

import com.jzy.entity.Grade;
import com.jzy.service.GradeService;
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
@Service("gradeService")
@Transactional
public class GradeServiceImpl extends BaseServiceImpl implements GradeService {
    @Override
    public List<Grade> selectAllGrade() {
        return gradeMapper.selectAllGrade();
    }
}
