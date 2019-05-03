package com.springmvc.dao;

import com.springmvc.dto.TeacherInfoSearchDto;
import com.springmvc.dto.TeacherWithMajorCollegeDto;
import com.springmvc.entity.Teacher;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class TeacherMapperTest extends UnitTestBase {
    @Autowired
    private TeacherMapper teacherMapper;

    @Test
    public void queryAllTeaInfo() {
        TeacherInfoSearchDto teaISD=new TeacherInfoSearchDto();
        teaISD.setTeaName("张");
//        teaISD.setTeaCollege("电子");
        List<TeacherWithMajorCollegeDto> teaWMCDs=teacherMapper.queryAllTeaInfo(teaISD);

        for (TeacherWithMajorCollegeDto t:teaWMCDs){
            System.out.println(t);
        }
    }


    @Test
    public void findTeacherById() {
        Teacher teacher=teacherMapper.findTeacherById(null);
        System.out.println(teacher);
    }

    @Test
    public void updateInfo() {
        TeacherWithMajorCollegeDto teaWMCD=new TeacherWithMajorCollegeDto();
        teaWMCD.setTeaId("100000000000");
        teaWMCD.setTeaName("sb");
        teacherMapper.updateInfo("100000000001",teaWMCD);
    }
}