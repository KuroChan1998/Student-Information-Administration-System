package com.springmvc.dao;

import com.springmvc.dto.StudentInfoSearchDto;
import com.springmvc.dto.StudentWithMajorCollegeDto;
import com.springmvc.entity.Student;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import others.UnitTestBase;

import java.util.List;

public class StudentMapperTest extends UnitTestBase {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void queryAllStuInfo() {
        StudentInfoSearchDto stuISD=new StudentInfoSearchDto();
//        stuISD.setStuId("516");
//        stuISD.setStuGrade("2016级");
//        stuISD.setStuCollege("电子");
        List<StudentWithMajorCollegeDto> studentWithMajorCollegeDtos=studentMapper.queryAllStuInfo(stuISD);
        for (StudentWithMajorCollegeDto s:studentWithMajorCollegeDtos){
            System.out.println(s);
        }
    }

    @Test
    public void findStudentById() {
        Student student=studentMapper.findStudentById(null);
        System.out.println(student);
    }

    @Test
    public void findStudentOwnInfoById() {
        List<StudentWithMajorCollegeDto>  studentWithMajorCollegeDtos=studentMapper.findStudentOwnInfoById("516030910428");
        System.out.println(studentWithMajorCollegeDtos);
    }

    @Test
    public void updateInfo() {
        StudentWithMajorCollegeDto studentWithMajorCollegeDto=new StudentWithMajorCollegeDto();
        studentWithMajorCollegeDto.setStuId("555555555555");
        studentWithMajorCollegeDto.setStuName("555555555555");
        studentMapper.updateInfo("516030910428",studentWithMajorCollegeDto);
    }

    @Test
    public void insertStudent() {
        StudentWithMajorCollegeDto stuWMCD=new StudentWithMajorCollegeDto();
        stuWMCD.setStuId("516030910000");
        stuWMCD.setStuClassName("2016级计算机1班");
        stuWMCD.setStuMajorName("计算机科学");
        stuWMCD.setStuName("111");
        stuWMCD.setStuAge(12);
        stuWMCD.setStuGrade("2016级");
        stuWMCD.setStuDegree("硕士");
        stuWMCD.setStuPhone("12345678901");
        studentMapper.insertStudent(stuWMCD);
    }

    @Test
    public void findStuNumBySex() {
        String collegeName="";
        String majorName="计算机科学";
        String className="";
        System.out.println(studentMapper.findStuNumBySex("男",collegeName,majorName,className));
    }
}