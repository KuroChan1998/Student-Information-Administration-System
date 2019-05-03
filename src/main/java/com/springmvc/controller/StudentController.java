package com.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.springmvc.dto.MyPage;
import com.springmvc.dto.ResultMap;
import com.springmvc.dto.StudentInfoSearchDto;
import com.springmvc.dto.StudentWithMajorCollegeDto;
import com.springmvc.entity.Student;
import com.springmvc.entity.User;
import com.springmvc.service.StudentService;
import com.springmvc.service.UserService;
import com.springmvc.service.impl.util.Constants;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

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
     * @return com.springmvc.dto.ResultMap<java.util.List   <   com.springmvc.dto.StudentWithMajorCollegeDto>>
     * @Author JinZhiyun
     * @Description 查询学生信息的ajax交互
     * @Date 23:01 2019/4/18
     * @Param [myPage, stuISD]
     **/
    @RequestMapping("/showAllStuInfo")
    @ResponseBody
    public ResultMap<List<StudentWithMajorCollegeDto>> showAllStuInfo(MyPage myPage, StudentInfoSearchDto stuISD) {
        PageInfo<StudentWithMajorCollegeDto> pageInfo = studentService.queryAllStuInfo(myPage, stuISD);//从数据库中获取查询所需的数据，在此之前，你不需要在sql语句中编写分页语句，该插件会在查询时直接将分页语句添加到数据库后
        return new ResultMap<>(0, "", (int) pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 定向到班长信息iframe子页面classStuInfo.jsp
     * @Date 23:03 2019/4/18
     * @Param [model, stuId]
     **/
    @RequestMapping("/stuInfo")
    public String stuInfo(Model model, @RequestParam(value = "stuId", required = false) String stuId) {
        Student student = studentService.findStudentById(stuId);
        model.addAttribute(Constants.STUDENT_MODEL, student);
        return "app/query/classStuInfo";
    }

    /**
     * @return com.springmvc.dto.ResultMap<java.util.List   <   com.springmvc.dto.StudentWithMajorCollegeDto>>
     * @Author JinZhiyun
     * @Description 查询学生个人信息的ajax交互
     * @Date 11:10 2019/4/19
     * @Param [myPage, session, request]
     **/
    @RequestMapping("/myOwnInfo")
    @ResponseBody
    public ResultMap<List<StudentWithMajorCollegeDto>> myOwnInfo(MyPage myPage, HttpSession session, HttpServletRequest request) {
        User user = userService.updateUserSession(session, request);
        PageInfo<StudentWithMajorCollegeDto> pageInfo = studentService.findStudentOwnInfoById(myPage, user.getUserId());
        return new ResultMap<>(0, "", (int) pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 重定向到编辑学生iframe子页面并返回相应model
     * @Date 8:55 2019/4/26
     * @Param [model, stuId, stuCollegeName, stuMajorName, stuClassName]
     **/
    @RequestMapping("/edit")
    public String stuEdit(Model model, @RequestParam(value = "stuId", required = false) String stuId
            , @RequestParam(value = "stuCollegeName", required = false) String stuCollegeName
            , @RequestParam(value = "stuMajorName", required = false) String stuMajorName
            , @RequestParam(value = "stuClassName", required = false) String stuClassName) {
        model.addAttribute("stuId", stuId);
        model.addAttribute("stuCollegeName", stuCollegeName);
        model.addAttribute("stuMajorName", stuMajorName);
        model.addAttribute("stuClassName", stuClassName);
        return "app/modify/stuForm";
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 更新学生信息
     * @Date 22:14 2019/4/25
     * @Param [stuOriId, stuWCD]
     **/
    @RequestMapping("/updateInfo")
    @ResponseBody
    public Map<String, Object> updateInfo(@RequestParam("stuOriId") String stuOriId, StudentWithMajorCollegeDto stuWCD) {
        Map<String, Object> map = new HashedMap();
//        map.put("code", 0);
//        map.put("msg", "");
        if (!stuOriId.equals(stuWCD.getStuId())) { //如果学号修改过了
            if (studentService.findStudentById(stuWCD.getStuId()) != null) { //如果修改后的学生学号已存在
                map.put("data", "stuIdExist");
                return map;
            }
        }
        studentService.updateInfo(stuOriId, stuWCD);
        map.put("data", "updateSuccess");
        return map;
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 添加学生ajax交互
     * @Date 21:37 2019/4/26
     * @Param [stuWCD]
     **/
    @RequestMapping("/insert")
    @ResponseBody
    public Map<String, Object> insertStudent(StudentWithMajorCollegeDto stuWCD) {
        System.out.println(stuWCD);
        Map<String, Object> map = new HashedMap();

        if (studentService.findStudentById(stuWCD.getStuId()) != null) {
            map.put("data", "stuIdExist");
        } else {
            studentService.insertStudent(stuWCD);
            map.put("data", "insertSuccess");
        }
        return map;
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 删除一个学生ajax交互
     * @Date 21:56 2019/4/26
     * @Param [stuWCD]
     **/
    @RequestMapping("/deleteOne")
    @ResponseBody
    public Map<String, Object> deleteOneStudent(StudentWithMajorCollegeDto stuWCD) {
        System.out.println(stuWCD);
        Map<String, Object> map = new HashedMap();

        studentService.deleteOneStudent(stuWCD);
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
        Map<String, Object> map = new HashedMap();

        List<StudentWithMajorCollegeDto> stuWMCDs = JSON.parseArray(students, StudentWithMajorCollegeDto.class);

        studentService.deleteManyStudents(stuWMCDs);
        map.put("data", "deleteSuccess");
        return map;
    }
}
