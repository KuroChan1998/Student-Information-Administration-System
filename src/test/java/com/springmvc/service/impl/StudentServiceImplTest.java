package com.springmvc.service.impl;

import com.github.pagehelper.PageInfo;
import com.springmvc.dao.UnitTestBase;
import com.springmvc.dto.MyPage;
import com.springmvc.dto.StudentInfoSearchDto;
import com.springmvc.dto.StudentWithMajorCollegeDto;
import com.springmvc.service.StudentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentServiceImplTest extends UnitTestBase {
    @Autowired
    private StudentService studentService;

    @Test
    public void queryAllStuInfo() {
        StudentInfoSearchDto stuISD=new StudentInfoSearchDto();
        stuISD.setStuCollege("电子");
        MyPage myPage=new MyPage(1,10);
        PageInfo<StudentWithMajorCollegeDto> pageInfo=studentService.queryAllStuInfo(myPage,stuISD);
        for (StudentWithMajorCollegeDto studentWithMajorCollegeDto:pageInfo.getList()) {
            System.out.println(studentWithMajorCollegeDto);
        }
        System.out.println(pageInfo.getTotal()+" "+pageInfo.getStartRow());
    }


}