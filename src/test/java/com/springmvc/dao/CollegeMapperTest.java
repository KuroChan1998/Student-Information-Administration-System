package com.springmvc.dao;

import com.springmvc.dto.CollegeDto;
import com.springmvc.dto.StudentWithMajorCollegeDto;
import com.springmvc.entity.College;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import others.UnitTestBase;

import java.util.List;

public class CollegeMapperTest extends UnitTestBase {
    @Autowired
    private CollegeMapper collegeMapper;

    @Test
    public void findAllCollege() {
        List<College> list=collegeMapper.findAllCollege();
        for (College college:list){
            System.out.println(college);
        }
    }

    @Test
    public void queryAllCollegeInfo() {
        List<CollegeDto> collegeDtos=collegeMapper.queryAllCollegeInfo("电子");
        for (CollegeDto collegeDto:collegeDtos){
            System.out.println(collegeDto);
        }
    }

    @Test
    public void findStuCollegeOwnInfoById() {
        List<CollegeDto> collegeDtos=collegeMapper.findStuCollegeOwnInfoById("516030910428");
        for (CollegeDto collegeDto:collegeDtos){
            System.out.println(collegeDto);
        }
    }

    @Test
    public void findTeaCollegeOwnInfoById() {
        List<CollegeDto> collegeDtos=collegeMapper.findTeaCollegeOwnInfoById("100000000001");
        for (CollegeDto collegeDto:collegeDtos){
            System.out.println(collegeDto);
        }
    }

    @Test
    public void updateStuNumAddOne() {
        StudentWithMajorCollegeDto stuWMCD=new StudentWithMajorCollegeDto();
        stuWMCD.setStuId("516030910000");
        stuWMCD.setStuClassName("2016级计算机1班");
        stuWMCD.setStuMajorName("计算机科学");
        stuWMCD.setStuCollegeName("电子信息与电气工程学院");
        stuWMCD.setStuName("111");
        stuWMCD.setStuAge(12);
        stuWMCD.setStuGrade("2016级");
        stuWMCD.setStuDegree("硕士");
        stuWMCD.setStuPhone("12345678901");
        System.out.println(collegeMapper.findIdByCollegeName(stuWMCD.getStuCollegeName()));
        collegeMapper.updateStuNumAddOne(collegeMapper.findIdByCollegeName(stuWMCD.getStuCollegeName()));
    }

    @Test
    public void findCollegeById() {
        collegeMapper.findCollegeById("1");
    }
}