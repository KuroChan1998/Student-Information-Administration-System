package com.jzy.dao;

import com.jzy.util.teacher.TeacherUtil;
import org.junit.Test;

public class TeacherMapperTest extends BaseMapperTest{

    @Test
    public void selectTeacherByNum() {
        System.out.println(TeacherUtil.isValidTeacherAllInfo(teacherMapper.selectTeacherByNum("100000000060")));
    }
}