package com.jzy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jzy.dao.StudentMapper;
import com.jzy.dto.other.MyPage;
import com.jzy.dto.other.senior.ObjectTotalGroupByCommonName;
import com.jzy.dto.other.senior.StudentTotalGroupBySex;
import com.jzy.dto.student.StudentSearchDto;
import com.jzy.dto.student.StudentWithGradeClassMajorCollegeDto;
import com.jzy.entity.Student;
import com.jzy.service.StudentService;
import com.jzy.util.other.MyTimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName StudentServiceImpl
 * @Author JinZhiyun
 * @Description 学生业务实现
 * @Date 2019/4/15 19:23
 * @Version 1.0
 **/
@Service("studentService")
public class StudentServiceImpl extends BaseServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;


    @Override
    public Student selectStudentByNum(String stuNum) {
        return !StringUtils.isEmpty(stuNum) ? studentMapper.selectStudentByNum(stuNum) : null;
    }

    @Override
    public PageInfo<StudentWithGradeClassMajorCollegeDto> selectAllStudentInfo(MyPage myPage, StudentSearchDto studentSearch) {
        PageHelper.startPage(myPage.getPageNum(), myPage.getPageSize());//第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
        List<StudentWithGradeClassMajorCollegeDto> stuWGCMCs = studentMapper.selectAllStudentInfo(studentSearch);
        for (StudentWithGradeClassMajorCollegeDto stuWGCMC : stuWGCMCs) {
            if (stuWGCMC.getStuBirthday() != null) {
                //date字符串
                stuWGCMC.setStuBirthdayToString(MyTimeUtil.dateToStr(stuWGCMC.getStuBirthday()));
                //由生日计算年龄
                stuWGCMC.setStuAge(MyTimeUtil.getAgeByBirth(stuWGCMC.getStuBirthday()));
            }
        }
        return new PageInfo<>(stuWGCMCs);
    }

    @Override
    public PageInfo<StudentWithGradeClassMajorCollegeDto> selectStudentOwnInfoByNum(MyPage myPage, String stuNum) {
        PageHelper.startPage(myPage.getPageNum(), myPage.getPageSize());//第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
        List<StudentWithGradeClassMajorCollegeDto> stuWGCMCs = new ArrayList<>();
        StudentWithGradeClassMajorCollegeDto stuWGCMC0 = studentMapper.selectStudentOwnInfoByNum(stuNum);
        if (stuWGCMC0 != null) {
            stuWGCMCs.add(stuWGCMC0);
            for (StudentWithGradeClassMajorCollegeDto stuWGCMC : stuWGCMCs) {
                if (stuWGCMC.getStuBirthday() != null) {
                    //date字符串
                    stuWGCMC.setStuBirthdayToString(MyTimeUtil.dateToStr(stuWGCMC.getStuBirthday()));
                    //由生日计算年龄
                    stuWGCMC.setStuAge(MyTimeUtil.getAgeByBirth(stuWGCMC.getStuBirthday()));
                }
            }
        }
        return new PageInfo<>(stuWGCMCs);
    }

    @Override
    public void updateStudentInfo(String stuOriNum, StudentWithGradeClassMajorCollegeDto stuWGCMC) {
        if (!stuOriNum.equals(stuWGCMC.getStuNum())) {
            //更新班长学号
            classService.updateClassStuNum(stuOriNum, stuWGCMC.getStuNum());
            //更新年级学生负责人
            gradeService.updateGradeStuNum(stuOriNum, stuWGCMC.getStuNum());
        }

        if (stuWGCMC.getStuBirthdayToString() != null) {
            stuWGCMC.setStuBirthday(MyTimeUtil.strToDate(stuWGCMC.getStuBirthdayToString()));
        } else {
            stuWGCMC.setStuBirthday(null);
        }
        studentMapper.updateStudentInfo(stuOriNum, stuWGCMC);
    }

    @Override
    public void insertStudent(StudentWithGradeClassMajorCollegeDto stuWGCMC) {
        if (stuWGCMC.getStuBirthdayToString() != null) {
            stuWGCMC.setStuBirthday(MyTimeUtil.strToDate(stuWGCMC.getStuBirthdayToString()));
        } else {
            stuWGCMC.setStuBirthday(null);
        }
        studentMapper.insertStudent(stuWGCMC);
    }

    @Override
    public void deleteOneStudent(String stuNum) {
        gradeService.updateGradeStuNum(stuNum, null); //若是年级学生负责人，该年级学生负责人学号置null
        classService.updateClassStuNum(stuNum, null); //若是班长，该班班长学号置null
        studentMapper.deleteOneStudent(stuNum);
    }

    @Override
    public void deleteManyStudents(List<String> stuNums) {
        for (String stuNum : stuNums) {
            deleteOneStudent(stuNum);
        }
    }

    @Override
    public StudentWithGradeClassMajorCollegeDto selectStudentInfoByNum(String stuNum) {
        StudentWithGradeClassMajorCollegeDto stuWGCMC = studentMapper.selectStudentOwnInfoByNum(stuNum);
        if (stuWGCMC != null) {
            if (stuWGCMC.getStuBirthday() != null) {
                //date字符串
                stuWGCMC.setStuBirthdayToString(MyTimeUtil.dateToStr(stuWGCMC.getStuBirthday()));
                //由生日计算年龄
                stuWGCMC.setStuAge(MyTimeUtil.getAgeByBirth(stuWGCMC.getStuBirthday()));
            }
        }
        return stuWGCMC;
    }

    @Override
    public List<StudentTotalGroupBySex> selectStuTotalBySex(String stuCollegeName, String stuMajorName, String stuClassName) {
        return studentMapper.selectStuTotalBySex(stuCollegeName, stuMajorName, stuClassName);
    }

    @Override
    public List<ObjectTotalGroupByCommonName> selectStuTotalByCommonName(String type, String collegeName, String majorName) {
        return studentMapper.selectStuTotalByCommonName(type, collegeName, majorName);
    }
}
