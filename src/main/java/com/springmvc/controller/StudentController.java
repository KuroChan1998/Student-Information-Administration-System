package com.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.springmvc.dto.other.MyPage;
import com.springmvc.dto.other.ResultMap;
import com.springmvc.dto.student.StudentSearchDto;
import com.springmvc.dto.student.StudentWithGradeClassMajorCollegeDto;
import com.springmvc.entity.User;
import com.springmvc.util.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName StudentController
 * @Author JinZhiyun
 * @Description 学生业务控制器
 * @Date 2019/4/13 20:58
 * @Version 1.0
 **/
@Controller
@RequestMapping("/student")
public class StudentController extends BaseController{
    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 定向到学生信息查询页面stuInfoQuery.jsp
     * @Date 23:01 2019/4/18
     * @Param []
     **/
    @RequestMapping("/query")
    public String query() {
        return "/app/query/stuInfoQuery";
    }

    /**
     * @author JinZhiyun
     * @Description 查询学生信息的ajax交互
     * @Date 14:48 2019/6/19
     * @Param [myPage, studentSearch]
     * @return com.springmvc.dto.other.ResultMap<java.util.List<com.springmvc.dto.student.StudentSearchDto>>
     **/
    @RequestMapping("/showAllStuInfo")
    @ResponseBody
    public ResultMap<List<StudentWithGradeClassMajorCollegeDto>> showAllStuInfo(MyPage myPage, StudentSearchDto studentSearch) {
        PageInfo<StudentWithGradeClassMajorCollegeDto> pageInfo = studentService.selectAllStudentInfo(myPage, studentSearch);//从数据库中获取查询所需的数据，在此之前，你不需要在sql语句中编写分页语句，该插件会在查询时直接将分页语句添加到数据库后
        return new ResultMap<>(0, "", (int) pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * @author JinZhiyun
     * @Description 查询学生个人信息的ajax交互
     * @Date 16:51 2019/7/25
     * @Param [myPage]
     * @return com.springmvc.dto.other.ResultMap<java.util.List<com.springmvc.dto.student.StudentWithGradeClassMajorCollegeDto>>
     **/
    @RequestMapping("/myOwnInfo")
    @ResponseBody
    public ResultMap<List<StudentWithGradeClassMajorCollegeDto>> myOwnInfo(MyPage myPage) {
        User user = (User) session.getAttribute(Constants.USERINFO_SESSION);
        PageInfo<StudentWithGradeClassMajorCollegeDto> pageInfo = studentService.selectStudentOwnInfoByNum(myPage, user.getUserName());
        return new ResultMap<>(0, "", (int) pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 定向到学生信息修改页面stuInfoModify.jsp
     * @Date 10:14 2019/4/20
     * @Param []
     **/
    @RequestMapping("/modify")
    public String modify() {
        //是否权限判断直接交给springmvc拦截器
        return "app/modify/stuInfoModify";
    }

    /**
     * @author JinZhiyun
     * @Description 重定向到编辑学生iframe子页面并返回相应model
     * @Date 16:11 2019/7/7
     * @Param [model, stuWGCMC]
     * @return java.lang.String
     **/
    @RequestMapping("/edit")
    public String stuEdit(Model model, StudentWithGradeClassMajorCollegeDto stuWGCMC) {
        model.addAttribute(Constants.STUDENT_ALL_INFO_MODEL, stuWGCMC);
        return "app/modify/stuForm";
    }

    /**
     * @author JinZhiyun
     * @Description 更新学生信息
     * @Date 16:52 2019/7/25
     * @Param [stuOriNum, stuWGCMC]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/updateInfo")
    @ResponseBody
    public Map<String, Object> updateInfo(@RequestParam("stuOriNum") String stuOriNum, StudentWithGradeClassMajorCollegeDto stuWGCMC) {
        Map<String, Object> map = new HashMap();
        if (!stuOriNum.equals(stuWGCMC.getStuNum())) { //如果学号修改过了
            if (studentService.selectStudentByNum(stuWGCMC.getStuNum()) != null) { //如果修改后的学生学号已存在
                map.put("data", "stuNumExist");
                return map;
            }
        }
        studentService.updateStudentInfo(stuOriNum, stuWGCMC);
        map.put("data", "updateSuccess");
        return map;
    }

    /**
     * @author JinZhiyun
     * @Description 添加学生ajax交互
     * @Date 21:18 2019/6/23
     * @Param [stuWGCMC]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/insert")
    @ResponseBody
    public Map<String, Object> insertStudent(StudentWithGradeClassMajorCollegeDto stuWGCMC) {
        Map<String, Object> map = new HashMap();
        if (studentService.selectStudentByNum(stuWGCMC.getStuNum()) != null) { //如果修改后的学生学号已存在
            map.put("data", "stuNumExist");
        } else {
            studentService.insertStudent(stuWGCMC);
            map.put("data", "insertSuccess");
        }
        return map;
    }

    /**
     * @author JinZhiyun
     * @Description 删除一个学生ajax交互
     * @Date 18:47 2019/6/24
     * @Param [stuNum]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/deleteOne")
    @ResponseBody
    public Map<String, Object> deleteOneStudent(@RequestParam("stuNum") String stuNum) {
        Map<String, Object> map = new HashMap();

        studentService.deleteOneStudent(stuNum);
        map.put("data", "deleteSuccess");
        return map;
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 删除多个学生ajax交互
     * @Date 22:02 2019/4/27
     * @Param [students]
     **/
    @RequestMapping("/deleteMany")
    @ResponseBody
    public Map<String, Object> deleteManyStudents(@RequestParam("students") String students) {
        Map<String, Object> map = new HashMap();
        List<StudentWithGradeClassMajorCollegeDto> stuWGCMCs = JSON.parseArray(students, StudentWithGradeClassMajorCollegeDto.class);
        List<String> stuNums=new ArrayList<>();
        for (StudentWithGradeClassMajorCollegeDto stuWGCMC:stuWGCMCs) {
            stuNums.add(stuWGCMC.getStuNum());
        }
        studentService.deleteManyStudents(stuNums);
        map.put("data", "deleteSuccess");
        return map;
    }

    /**
     * @author JinZhiyun
     * @Description 定向到班长信息iframe子页面classStuInfo.jsp
     * @Date 12:51 2019/7/7
     * @Param [model, stuNum]
     * @return java.lang.String
     **/
    @RequestMapping("/stuInfo")
    public String stuInfo(Model model, @RequestParam(value = "stuNum", required = false) String stuNum) {
        StudentWithGradeClassMajorCollegeDto stuWGCMC= studentService.selectStudentInfoByNum(stuNum);
        model.addAttribute(Constants.STUDENT_MODEL, stuWGCMC);
        return "app/query/classStuInfo";
    }
}
