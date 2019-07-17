package com.springmvc.dao;

import com.springmvc.entity.Student;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StudentMapperTest extends BaseMapperTest {

    @Test
    public void selectStudentByNum() {
        Student student=studentMapper.selectStudentByNum("516030910428");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(student.getStuBirthday()));
    }

    @Test
    public void deleteManyStudents() {
        List<String> list=new ArrayList<>();
        list.add("516030910428");
//        list.add("516030910429");
        list.add("516030910430");
        studentMapper.deleteManyStudents(list);
    }

    @Test
    public void selectStuNumBySex() {
        System.out.println(studentMapper.selectStuTotalBySex("","",""));
    }

    @Test
    public void selectStuTotalByCommonName() {
        System.out.println(studentMapper.selectStuTotalByCommonName("college",null,null));
    }
}