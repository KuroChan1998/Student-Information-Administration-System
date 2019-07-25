package com.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.springmvc.dto.classP.ClassSearchDto;
import com.springmvc.dto.classP.ClassWithGradeMajorCollegeDto;
import com.springmvc.dto.other.MyPage;
import com.springmvc.dto.other.ResultMap;
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
 * @ClassName ClassController
 * @Author JinZhiyun
 * @Description 班级业务控制器
 * 处理班级相关的页面跳转、增删改查ajax请求。
 * 该控制类都有根请求路径/class
 * @Date 2019/4/16 15:26
 * @Version 1.0
 **/
@Controller
@RequestMapping("/class")
public class ClassController extends BaseController {
    /**
     * @return java.lang.Object
     * @Author JinZhiyun
     * @Description 联动渲染班级select栏的ajax交互，接收majorName参数，返回该majorName下的所有班级构成的list作为JSON
     * @Date 22:30 2019/4/23
     * @Param [majorName]
     **/
    @RequestMapping("/getClassNameByMajor")
    @ResponseBody
    public Object findClassNameByMajor(@RequestParam(value = "majorName", required = false) String majorName) {
        return classService.selectClassByMajorName(majorName);
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 定向到班级信息查询页面classInfoQuery.jsp
     * @Date 22:48 2019/4/18
     * @Param []
     **/
    @RequestMapping("/query")
    public String query() {
        return "app/query/classInfoQuery";
    }

    /**
     * @return com.springmvc.dto.other.ResultMap<java.util.List   <   com.springmvc.dto.classP.ClassWithGradeMajorCollegeDto>>
     * @author JinZhiyun
     * @Description 班级信息查询的ajax交互，返回符合layui表格要求的JSON
     * @Date 9:33 2019/7/7
     * @Param [myPage, classSearch]
     **/
    @RequestMapping("/showAllClassInfo")
    @ResponseBody
    public ResultMap<List<ClassWithGradeMajorCollegeDto>> showAllStuInfo(MyPage myPage, ClassSearchDto classSearch) {
        PageInfo<ClassWithGradeMajorCollegeDto> pageInfo = classService.selectAllClassInfo(myPage, classSearch);//从数据库中获取查询所需的数据，在此之前，你不需要在sql语句中编写分页语句，该插件会在查询时直接将分页语句添加到数据库后
        return new ResultMap<>(0, "", (int) pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * @return com.springmvc.dto.other.ResultMap<java.util.List   <   com.springmvc.dto.classP.ClassWithGradeMajorCollegeDto>>
     * @author JinZhiyun
     * @Description 查询用户个人的班级信息的ajax交互，返回符合layui表格要求的JSON
     * @Date 11:43 2019/7/7
     * @Param [myPage]
     **/
    @RequestMapping("/myOwnInfo")
    @ResponseBody
    public ResultMap<List<ClassWithGradeMajorCollegeDto>> myOwnInfo(MyPage myPage) {
        User user = (User) session.getAttribute(Constants.USERINFO_SESSION);
        PageInfo<ClassWithGradeMajorCollegeDto> pageInfo = classService.selectClassOwnInfoByNum(myPage, user);
        return new ResultMap<>(0, "", (int) pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 定向到班级信息修改页面classInfoModify.jsp
     * @Date 17:44 2019/4/28
     * @Param []
     **/
    @RequestMapping("/modify")
    public String modify() {
        //是否权限判断直接交给springmvc拦截器
        return "app/modify/classInfoModify";
    }

    /**
     * @author JinZhiyun
     * @Description 重定向到编辑班级iframe子页面并返回相应model
     * @Date 16:38 2019/7/25
     * @Param [model, classWGMC]
     * @return java.lang.String
     **/
    @RequestMapping("/edit")
    public String classEdit(Model model, ClassWithGradeMajorCollegeDto classWGMC) {
        model.addAttribute(Constants.CLASS_ALL_INFO_MODEL, classWGMC);
        return "app/modify/classForm";
    }

    /**
     * @return java.util.Map<java.lang.String       ,       java.lang.Object>
     * @Author JinZhiyun
     * @Description 更新班级信息的ajax交互，返回字符串表示更新成功与否结果
     * @Date 10:10 2019/4/29
     * @Param [classOriId, classOriName, classWMCD]
     **/
    @RequestMapping("/updateInfo")
    @ResponseBody
    public Map<String, Object> updateInfo(@RequestParam("classOriName") String classOriName, @RequestParam("classOriId") String classOriId, ClassWithGradeMajorCollegeDto classWGMC) {
        Map<String, Object> map = new HashMap();
        map.put("data", classService.updateClassInfo(classOriId, classOriName, classWGMC));
        return map;
    }

    /**
     * @return java.lang.String
     * @author JinZhiyun
     * @Description 重定向到添加班级iframe子页面并返回相应model
     * @Date 18:24 2019/7/7
     * @Param []
     **/
    @RequestMapping("/add")
    public String add() {
        return "app/modify/classForm";
    }

    /**
     * @return java.util.Map<java.lang.String       ,       java.lang.Object>
     * @Author JinZhiyun
     * @Description 更新教师信息
     * @Date 10:55 2019/4/29
     * @Param [classWMCD]
     **/
    @RequestMapping("/insert")
    @ResponseBody
    public Map<String, Object> insertClass(ClassWithGradeMajorCollegeDto classWGMC) {
        Map<String, Object> map = new HashMap();

        map.put("data", classService.insertClass(classWGMC));

        return map;
    }

    /**
     * @return java.util.Map<java.lang.String       ,       java.lang.Object>
     * @Author JinZhiyun
     * @Description 删除一个班级ajax交互
     * @Date 15:08 2019/4/29
     * @Param [classWMCD]
     **/
    @RequestMapping("/deleteOne")
    @ResponseBody
    public Map<String, Object> deleteOneClass(@RequestParam("className") String className) {
        System.out.println(className);
        Map<String, Object> map = new HashMap();

        classService.deleteOneClass(className);
        map.put("data", "deleteSuccess");
        return map;
    }

    /**
     * @author JinZhiyun
     * @Description 删除多个班级ajax交互
     * @Date 16:43 2019/7/25
     * @Param [classes]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/deleteMany")
    @ResponseBody
    public Map<String, Object> deleteManyClasses(@RequestParam("classes") String classes) {
        Map<String, Object> map = new HashMap();

        List<ClassWithGradeMajorCollegeDto> classWMCDs = JSON.parseArray(classes, ClassWithGradeMajorCollegeDto.class);

        List<String> classNames = new ArrayList<>();
        for (ClassWithGradeMajorCollegeDto classWGMC : classWMCDs) {
            classNames.add(classWGMC.getClassName());
        }
        classService.deleteManyClasses(classNames);

        map.put("data", "deleteSuccess");
        return map;
    }
}
