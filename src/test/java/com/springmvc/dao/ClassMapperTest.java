package com.springmvc.dao;

import com.springmvc.dto.ClassWithMajorCollegeDto;
import com.springmvc.entity.Class;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class ClassMapperTest extends UnitTestBase {
    @Autowired
    private ClassMapper classMapper;
    @Test
    public void queryAllClassInfo() {
        List<ClassWithMajorCollegeDto> classWMCDs=classMapper.queryAllClassInfo("","","");
        for (ClassWithMajorCollegeDto classWMCD:classWMCDs){
            System.out.println(classWMCD);
        }
    }

    @Test
    public void findClassOwnInfoById() {
        List<ClassWithMajorCollegeDto> classWMCDs=classMapper.findStuClassOwnInfoById("516030910429");
        for (ClassWithMajorCollegeDto classWMCD:classWMCDs){
            System.out.println(classWMCD);
        }
    }

    @Test
    public void findTeaClassOwnInfoById() {
        List<ClassWithMajorCollegeDto> classWMCDs=classMapper.findTeaClassOwnInfoById("100000000001");
        for (ClassWithMajorCollegeDto classWMCD:classWMCDs){
            System.out.println(classWMCD);
        }
    }

    @Test
    public void findClassNameByMajor() {
        List<Class> classes=classMapper.findClassNameByMajor("计算机");
        System.out.println(classes);
    }

    @Test
    public void findIdByClassName() {
        System.out.println(classMapper.findIdByClassName("2016级计算机1班"));
    }

    @Test
    public void updateStuNumAddOne() {
        classMapper.updateStuNumAddOne("1");
    }

    @Test
    public void insertClass() {
        ClassWithMajorCollegeDto classWithMajorCollegeDto=new ClassWithMajorCollegeDto();
        classWithMajorCollegeDto.setClassId("100");
        classWithMajorCollegeDto.setClassName("nnn");
        classWithMajorCollegeDto.setClassStuNum(0);
        classMapper.insertClass(classWithMajorCollegeDto);
    }

    @Test
    public void updateStuNumAddN() {
        classMapper.updateStuNumAddN(2,"1");
    }
}