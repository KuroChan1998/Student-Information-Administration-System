package com.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.springmvc.dto.other.MyPage;
import com.springmvc.dto.other.ResultMap;
import com.springmvc.dto.teacher.TeacherSearchDto;
import com.springmvc.dto.teacher.TeacherWithTitleMajorCollegeDto;
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
 * @ClassName TeacherController
 * @Author JinZhiyun
 * @Description 教师业务控制器
 * @Date 2019/4/16 13:07
 * @Version 1.0
 **/
@Controller
@RequestMapping("/teacher")
public class TeacherController extends BaseController{
    /**
     * @author JinZhiyun
     * @Description 定向到教师信息查询页面teaInfoQuery.jsp
     * @Date 8:02 2019/6/30
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/query")
    public String query(){
        return "/app/query/teaInfoQuery";
    }

    /**
     * @author JinZhiyun
     * @Description 查询教师信息的ajax交互
     * @Date 8:07 2019/6/30
     * @Param [myPage, teacherSearch]
     * @return com.springmvc.dto.other.ResultMap<java.util.List<com.springmvc.dto.teacher.TeacherWithTitleMajorCollegeDto>>
     **/
    @RequestMapping("/showAllTeaInfo")
    @ResponseBody
    public ResultMap<List<TeacherWithTitleMajorCollegeDto>> showAllTeaInfo(MyPage myPage, TeacherSearchDto teacherSearch) {
        PageInfo<TeacherWithTitleMajorCollegeDto> pageInfo = teacherService.selectAllTeacherInfo(myPage, teacherSearch);//从数据库中获取查询所需的数据，在此之前，你不需要在sql语句中编写分页语句，该插件会在查询时直接将分页语句添加到数据库后
        return new ResultMap<>(0, "", (int) pageInfo.getTotal(), pageInfo.getList());
    }


    /**
     * @return com.springmvc.dto.other.ResultMap<java.util.List   <   com.springmvc.dto.TeacherWithMajorCollegeDto>>
     * @Author JinZhiyun
     * @Description 查询教师个人信息的ajax交互
     * @Date 11:09 2019/4/19
     * @Param [myPage, session, request]
     **/
    @RequestMapping("/myOwnInfo")
    @ResponseBody
    public ResultMap<List<TeacherWithTitleMajorCollegeDto>> myOwnInfo(MyPage myPage) {
        User user = (User) session.getAttribute(Constants.USERINFO_SESSION);
        PageInfo<TeacherWithTitleMajorCollegeDto> pageInfo = teacherService.selectTeacherOwnInfoByNum(myPage, user.getUserName());//从数据库中获取查询所需的数据，在此之前，你不需要在sql语句中编写分页语句，该插件会在查询时直接将分页语句添加到数据库后
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
    public String teaEdit(Model model, TeacherWithTitleMajorCollegeDto teaWTMC) {
        System.out.println(teaWTMC);
        model.addAttribute(Constants.TEACHER_ALL_INFO_MODEL, teaWTMC);
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
    public Map<String, Object> updateInfo(@RequestParam("teaOriNum") String teaOriNum, TeacherWithTitleMajorCollegeDto teaWTMC) {
        Map<String, Object> map = new HashMap();
        if (!teaOriNum.equals(teaWTMC.getTeaNum())) { //如果工学号修改过了
            if (teacherService.selectTeacherByNum(teaWTMC.getTeaNum()) != null) { //如果修改后的学生学号已存在
                map.put("data", "teaNumExist");
                return map;
            }
        }
        teacherService.updateTeacherInfo(teaOriNum, teaWTMC);
        map.put("data", "updateSuccess");
        return map;
    }

    /**
     * @author JinZhiyun
     * @Description 添加教师ajax交互
     * @Date 16:12 2019/6/30
     * @Param [teaWTMC]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/insert")
    @ResponseBody
    public Map<String, Object> insertTeacher(TeacherWithTitleMajorCollegeDto teaWTMC) {
        System.out.println(teaWTMC);
        Map<String, Object> map = new HashMap();
        if (teacherService.selectTeacherByNum(teaWTMC.getTeaNum()) != null) {
            map.put("data", "teaNumExist");
        } else {
            teacherService.insertTeacher(teaWTMC);
            map.put("data", "insertSuccess");
        }
        return map;
    }

    /**
     * @author JinZhiyun
     * @Description 删除一个教师ajax交互
     * @Date 16:42 2019/6/30
     * @Param [teaNum]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/deleteOne")
    @ResponseBody
    public Map<String, Object> deleteOneTeacher(@RequestParam("teaNum") String teaNum) {
        System.out.println(teaNum);
        Map<String, Object> map = new HashMap();

        teacherService.deleteOneTeacher(teaNum);
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
        Map<String, Object> map = new HashMap();

        List<TeacherWithTitleMajorCollegeDto> teaWMCDs = JSON.parseArray(teachers, TeacherWithTitleMajorCollegeDto.class);
        List<String> teaNums=new ArrayList<>();
        for (TeacherWithTitleMajorCollegeDto teaWMCD:teaWMCDs) {
            teaNums.add(teaWMCD.getTeaNum());
        }
        teacherService.deleteManyTeachers(teaNums);

        map.put("data", "deleteSuccess");
        return map;
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 定向到班主任信息iframe子页面classStuInfo.jsp
     * @Date 9:20 2019/4/19
     * @Param [model, teaNum]
     **/
    @RequestMapping("/teaInfo")
    public String teaInfo(Model model, @RequestParam(value = "teaNum", required = false) String teaNum) {
        TeacherWithTitleMajorCollegeDto teaWTMC = teacherService.selectTeacherInfoByNum(teaNum);
        model.addAttribute(Constants.TEACHER_MODEL, teaWTMC);
        return "app/query/classTeaInfo";
    }
}
