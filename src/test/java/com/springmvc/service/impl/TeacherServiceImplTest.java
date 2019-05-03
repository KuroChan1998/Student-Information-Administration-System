package com.springmvc.service.impl;

import com.github.pagehelper.PageInfo;
import com.springmvc.dao.UnitTestBase;
import com.springmvc.dto.MyPage;
import com.springmvc.dto.TeacherInfoSearchDto;
import com.springmvc.dto.TeacherWithMajorCollegeDto;
import com.springmvc.entity.Teacher;
import com.springmvc.service.TeacherService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class TeacherServiceImplTest extends UnitTestBase {
    @Autowired
    private TeacherService teacherService;

    @Test
    public void queryAllTeaInfo() {
        TeacherInfoSearchDto teaISD=new TeacherInfoSearchDto();

        MyPage myPage=new MyPage(1,5);
        PageInfo<TeacherWithMajorCollegeDto> pageInfo=teacherService.queryAllTeaInfo(myPage,teaISD);
        for (TeacherWithMajorCollegeDto teacherWithMajorCollegeDto:pageInfo.getList()) {
            System.out.println(teacherWithMajorCollegeDto);
        }
        System.out.println(pageInfo.getTotal()+" "+pageInfo.getStartRow());
    }

    @Test
    public void deleteOneTeacher() {
    }
}