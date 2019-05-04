package com.springmvc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmvc.dao.ClassMapper;
import com.springmvc.dao.CollegeMapper;
import com.springmvc.dao.MajorMapper;
import com.springmvc.dao.StudentMapper;
import com.springmvc.dto.MyPage;
import com.springmvc.dto.StudentInfoSearchDto;
import com.springmvc.dto.StudentWithMajorCollegeDto;
import com.springmvc.entity.Student;
import com.springmvc.service.ClassService;
import com.springmvc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName StudentServiceImpl
 * @Author JinZhiyun
 * @Description 学生业务实现
 * @Date 2019/4/15 19:23
 * @Version 1.0
 **/
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private ClassService classService;

    @Override
    public PageInfo<StudentWithMajorCollegeDto> queryAllStuInfo(MyPage myPage, StudentInfoSearchDto stuISD) {
        PageHelper.startPage(myPage.getPageNum(), myPage.getPageSize());//第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
        List<StudentWithMajorCollegeDto> stuWMCDs = studentMapper.queryAllStuInfo(stuISD);
        return new PageInfo<>(stuWMCDs);
    }

    @Override
    public Student findStudentById(String stuId) {
        return studentMapper.findStudentById(stuId);
    }

    @Override
    public PageInfo<StudentWithMajorCollegeDto> findStudentOwnInfoById(MyPage myPage, String stuId) {
        PageHelper.startPage(myPage.getPageNum(), myPage.getPageSize());//第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
        List<StudentWithMajorCollegeDto> stuWMCDs = studentMapper.findStudentOwnInfoById(stuId);
        return new PageInfo<>(stuWMCDs);
    }

    @Override
    public void updateInfo(String stuOriId, StudentWithMajorCollegeDto stuWMCD) {
        if (!stuOriId.equals(stuWMCD.getStuId())) {
            classMapper.updateMoniId(stuOriId, stuWMCD.getStuId());
        }

        StudentWithMajorCollegeDto oldStu = findStuClassMajorCollegeName(stuOriId);
        //该学生原来的班级专业学院人数-1
        classService.classStuNumMinusOne(oldStu.getStuClassName(), oldStu.getStuMajorName(), oldStu.getStuCollegeName());
        //该学生修改后的班级专业学院人数+1
        classService.classStuNumAddOne(stuWMCD.getStuClassName(), stuWMCD.getStuMajorName(), stuWMCD.getStuCollegeName());

        studentMapper.updateInfo(stuOriId, stuWMCD);
    }

    @Override
    public void insertStudent(StudentWithMajorCollegeDto stuWMCD) {
        studentMapper.insertStudent(stuWMCD);
        classService.classStuNumAddOne(stuWMCD.getStuClassName(), stuWMCD.getStuMajorName(), stuWMCD.getStuCollegeName());
    }

    @Override
    public void deleteOneStudent(StudentWithMajorCollegeDto stuWMCD) {
        //似乎ClassId直接在分装的dto里了，我tm也不知道为什么，可能是layui前端框架对上次传到table的obj施了魔法
        classService.classStuNumMinusOne(stuWMCD.getStuClassName(), stuWMCD.getStuMajorName(), stuWMCD.getStuCollegeName());
        classMapper.updateDeleteMoni(stuWMCD.getStuId());
        studentMapper.deleteOneStudent(stuWMCD.getStuId());
    }

    @Override
    public void deleteManyStudents(List<StudentWithMajorCollegeDto> stuWMCDs) {
        for (StudentWithMajorCollegeDto stuWMCD : stuWMCDs) {
            deleteOneStudent(stuWMCD);
        }
    }

    @Override
    public StudentWithMajorCollegeDto findStuClassMajorCollegeName(String stuId) {
        List<StudentWithMajorCollegeDto> stuWMCDs = studentMapper.findStudentOwnInfoById(stuId);
        return stuWMCDs.get(0);
    }
}
