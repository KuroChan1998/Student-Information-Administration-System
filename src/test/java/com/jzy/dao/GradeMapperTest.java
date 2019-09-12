package com.jzy.dao;

import com.jzy.util.grade.GradeUtil;
import org.junit.Test;

public class GradeMapperTest extends BaseMapperTest{

    @Test
    public void selectAllGrade() {
        System.out.println(GradeUtil.isValidGradeAllInfo(gradeMapper.selectAllGrade().get(0)));
    }
}