package com.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.springmvc.dto.MyPage;
import com.springmvc.dto.ResultMap;
import com.springmvc.dto.TeacherInfoSearchDto;
import com.springmvc.dto.TeacherWithMajorCollegeDto;
import com.springmvc.entity.Teacher;
import com.springmvc.entity.User;
import com.springmvc.service.TeacherService;
import com.springmvc.service.UserService;
import com.springmvc.service.impl.util.Constants;
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
 * @ClassName TeacherController
 * @Author JinZhiyun
 * @Description 教师业务控制器
 * @Date 2019/4/16 13:07
 * @Version 1.0
 **/
@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private UserService userService;

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 定向到教师信息查询页面teaInfoQuery.jsp
     * @Date 23:03 2019/4/18
     * @Param []
     **/
    @RequestMapping("/query")
    public String query() {
        return "app/query/teaInfoQuery";
    }

    /**
     * @return com.springmvc.dto.ResultMap<java.util.List   <   com.springmvc.dto.TeacherWithMajorCollegeDto>>
     * @Author JinZhiyun
     * @Description 查询教师信息的ajax交互
     * @Date 23:04 2019/4/18
     * @Param [myPage, teaISD]
     **/
    @RequestMapping("/showAllTeaInfo")
    @ResponseBody
    public ResultMap<List<TeacherWithMajorCollegeDto>> showAllTeaInfo(MyPage myPage, TeacherInfoSearchDto teaISD) {
        PageInfo<TeacherWithMajorCollegeDto> pageInfo = teacherService.queryAllTeaInfo(myPage, teaISD);//从数据库中获取查询所需的数据，在此之前，你不需要在sql语句中编写分页语句，该插件会在查询时直接将分页语句添加到数据库后
        return new ResultMap<>(0, "", (int) pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 定向到班主任信息iframe子页面classStuInfo.jsp
     * @Date 9:20 2019/4/19
     * @Param [model, teaId]
     **/
    @RequestMapping("/teaInfo")
    public String teaInfo(Model model, @RequestParam(value = "teaId", required = false) String teaId) {
        Teacher teacher = teacherService.findTeacherById(teaId);
        model.addAttribute(Constants.TEACHER_MODEL, teacher);
        return "app/query/classTeaInfo";
    }

    /**
     * @return com.springmvc.dto.ResultMap<java.util.List   <   com.springmvc.dto.TeacherWithMajorCollegeDto>>
     * @Author JinZhiyun
     * @Description 查询教师个人信息的ajax交互
     * @Date 11:09 2019/4/19
     * @Param [myPage, session, request]
     **/
    @RequestMapping("/myOwnInfo")
    @ResponseBody
    public ResultMap<List<TeacherWithMajorCollegeDto>> myOwnInfo(MyPage myPage, HttpSession session, HttpServletRequest request) {
        User user = userService.updateUserSession(session, request);
        PageInfo<TeacherWithMajorCollegeDto> pageInfo = teacherService.findTeacherOwnInfoById(myPage, user.getUserId());//从数据库中获取查询所需的数据，在此之前，你不需要在sql语句中编写分页语句，该插件会在查询时直接将分页语句添加到数据库后
        return new ResultMap<>(0, "", (int) pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 定向到教师信息修改页面teaInfoModify.jsp
     * @Date 12:35 2019/4/27
     * @Param []
     **/
    @RequestMapping("/modify")
    public String modify() {
        //是否权限判断直接交给springmvc拦截器
        return "app/modify/teaInfoModify";
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 重定向到编辑教师iframe子页面并返回相应model
     * @Date 22:04 2019/4/27
     * @Param [model, teaId, teaCollegeName, teaMajorName]
     **/
    @RequestMapping("/edit")
    public String teaEdit(Model model, @RequestParam(value = "teaId", required = false) String teaId
            , @RequestParam(value = "teaCollegeName", required = false) String teaCollegeName
            , @RequestParam(value = "teaMajorName", required = false) String teaMajorName) {
        model.addAttribute("teaId", teaId);
        model.addAttribute("teaCollegeName", teaCollegeName);
        model.addAttribute("teaMajorName", teaMajorName);
        return "app/modify/teaForm";
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 更新教师信息
     * @Date 22:03 2019/4/27
     * @Param [teaOriId, teaWMCD]
     **/
    @RequestMapping("/updateInfo")
    @ResponseBody
    public Map<String, Object> updateInfo(@RequestParam("teaOriId") String teaOriId, TeacherWithMajorCollegeDto teaWMCD) {
        Map<String, Object> map = new HashedMap();
        if (!teaOriId.equals(teaWMCD.getTeaId())) { //如果工学号修改过了
            if (teacherService.findTeacherById(teaWMCD.getTeaId()) != null) { //如果修改后的学生学号已存在
                map.put("data", "teaIdExist");
                return map;
            }
        }
        teacherService.updateInfo(teaOriId, teaWMCD);
        map.put("data", "updateSuccess");
        return map;
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 添加教师ajax交互
     * @Date 22:37 2019/4/27
     * @Param [teaWMCD]
     **/
    @RequestMapping("/insert")
    @ResponseBody
    public Map<String, Object> insertTeacher(TeacherWithMajorCollegeDto teaWMCD) {
        System.out.println(teaWMCD);
        Map<String, Object> map = new HashedMap();
        if (teacherService.findTeacherById(teaWMCD.getTeaId()) != null) {
            map.put("data", "teaIdExist");
        } else {
            teacherService.insertTeacher(teaWMCD);
            map.put("data", "insertSuccess");
        }
        return map;
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 删除一个教师ajax交互
     * @Date 22:57 2019/4/27
     * @Param [stuWCD]
     **/
    @RequestMapping("/deleteOne")
    @ResponseBody
    public Map<String, Object> deleteOneTeacher(TeacherWithMajorCollegeDto teaWMCD) {
        System.out.println(teaWMCD);
        Map<String, Object> map = new HashedMap();

        teacherService.deleteOneTeacher(teaWMCD);
        map.put("data", "deleteSuccess");
        return map;
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 删除多个教师ajax交互
     * @Date 9:32 2019/5/2
     * @Param [teachers]
     **/
    @RequestMapping("/deleteMany")
    @ResponseBody
    public Map<String, Object> deleteManyTeachers(@RequestParam("teachers") String teachers) {
        Map<String, Object> map = new HashedMap();

        List<TeacherWithMajorCollegeDto> teaWMCDs = JSON.parseArray(teachers, TeacherWithMajorCollegeDto.class);

        teacherService.deleteManyTeachers(teaWMCDs);

        map.put("data", "deleteSuccess");
        return map;
    }
}
