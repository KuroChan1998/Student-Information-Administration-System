package com.springmvc.service.impl;

import others.UnitTestBase;
import com.springmvc.dto.ClassWithMajorCollegeDto;
import com.springmvc.service.ClassService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ClassServiceImplTest extends UnitTestBase {
    @Autowired
    private ClassService classService;
    @Test
    public void findClassIdByMoniId() {
        System.out.println();
        System.out.println(classService.findClassIdByMoniId("514030910425as"));
        System.out.println();
    }

    @Test
    public void findClassIdByTeaId() {
        System.out.println();
        System.out.println(classService.findClassIdByTeaId("100000000001"));
        System.out.println();
    }

    @Test
    public void updateInfo() {
        ClassWithMajorCollegeDto c=new ClassWithMajorCollegeDto();
        c.setClassId("2");
        c.setClassName("2014计算机1班");
        classService.updateInfo("1",c);
    }
}