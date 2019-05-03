package com.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.springmvc.dto.ClassWithMajorCollegeDto;
import com.springmvc.dto.MyPage;
import com.springmvc.dto.ResultMap;
import com.springmvc.entity.User;
import com.springmvc.service.ClassService;
import com.springmvc.service.UserService;
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
 * @ClassName ClassController
 * @Author JinZhiyun
 * @Description 班级业务控制器
 * @Date 2019/4/16 15:26
 * @Version 1.0
 **/
@Controller
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private ClassService classService;

    @Autowired
    private UserService userService;

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
     * @return com.springmvc.dto.ResultMap<java.util.List       <       com.springmvc.dto.ClassWithMajorCollegeDto>>
     * @Author JinZhiyun
     * @Description 班级信息查询的ajax交互
     * @Date 22:49 2019/4/18
     * @Param [myPage, className, classMajorName, classCollegeName]
     **/
    @RequestMapping("/showAllClassInfo")
    @ResponseBody
    public ResultMap<List<ClassWithMajorCollegeDto>> showAllStuInfo(MyPage myPage
            , @RequestParam(value = "className", required = false) String className
            , @RequestParam(value = "classMajorName", required = false) String classMajorName
            , @RequestParam(value = "classCollegeName", required = false) String classCollegeName) {
        PageInfo<ClassWithMajorCollegeDto> pageInfo = classService.queryAllClassInfo(myPage, className, classMajorName, classCollegeName);//从数据库中获取查询所需的数据，在此之前，你不需要在sql语句中编写分页语句，该插件会在查询时直接将分页语句添加到数据库后
        return new ResultMap<>(0, "", (int) pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * @return com.springmvc.dto.ResultMap<java.util.List       <       com.springmvc.dto.ClassWithMajorCollegeDto>>
     * @Author JinZhiyun
     * @Description 查询用户班级信息的ajax交互
     * @Date 17:33 2019/4/19
     * @Param [myPage, session, request]
     **/
    @RequestMapping("/myOwnInfo")
    @ResponseBody
    public ResultMap<List<ClassWithMajorCollegeDto>> myOwnInfo(MyPage myPage, HttpSession session, HttpServletRequest request) {
        User user = userService.updateUserSession(session, request);
        PageInfo<ClassWithMajorCollegeDto> pageInfo = classService.findClassOwnInfoById(myPage, user);
        return new ResultMap<>(0, "", (int) pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * @return java.lang.Object
     * @Author JinZhiyun
     * @Description 联动渲染班级select栏的ajax交互
     * @Date 22:30 2019/4/23
     * @Param [majorName]
     **/
    @RequestMapping("/getClassNameByMajor")
    @ResponseBody
    public Object findClassNameByMajor(@RequestParam(value = "majorName", required = false) String majorName) {
        return classService.findClassNameByMajor(majorName);
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
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 重定向到编辑班级iframe子页面并返回相应model
     * @Date 18:30 2019/4/28
     * @Param [model, classId, classCollegeName, classMajorName]
     **/
    @RequestMapping("/edit")
    public String classEdit(Model model, @RequestParam(value = "classId", required = false) String classId
            , @RequestParam(value = "className", required = false) String className
            , @RequestParam(value = "classCollegeName", required = false) String classCollegeName
            , @RequestParam(value = "classMajorName", required = false) String classMajorName) {
        model.addAttribute("classId", classId);
        model.addAttribute("className", className);
        model.addAttribute("classCollegeName", classCollegeName);
        model.addAttribute("classMajorName", classMajorName);
        return "app/modify/classForm";
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 更新班级信息
     * @Date 10:10 2019/4/29
     * @Param [classOriId, classOriName, classWMCD]
     **/
    @RequestMapping("/updateInfo")
    @ResponseBody
    public Map<String, Object> updateInfo(@RequestParam("classOriId") String classOriId, @RequestParam("classOriName") String classOriName, ClassWithMajorCollegeDto classWMCD) {
        Map<String, Object> map = new HashedMap();

        map.put("data", classService.updateMapDataResult(classOriId, classOriName, classWMCD));
        return map;
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 重定向到添加班级iframe子页面并返回相应model
     * @Date 10:11 2019/4/29
     * @Param [model]
     **/
    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("classIdRec", classService.findRecommendedClassId());
        return "app/modify/classFormAdd";
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 更新教师信息
     * @Date 10:55 2019/4/29
     * @Param [classWMCD]
     **/
    @RequestMapping("/insert")
    @ResponseBody
    public Map<String, Object> insertClass(ClassWithMajorCollegeDto classWMCD) {
        Map<String, Object> map = new HashedMap();

        map.put("data", classService.insertMapDataResult(classWMCD));

        return map;
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 删除一个班级ajax交互
     * @Date 15:08 2019/4/29
     * @Param [classWMCD]
     **/
    @RequestMapping("/deleteOne")
    @ResponseBody
    public Map<String, Object> deleteOneClass(ClassWithMajorCollegeDto classWMCD) {
        System.out.println(classWMCD);
        Map<String, Object> map = new HashedMap();

        classService.deleteOneClass(classWMCD);
        map.put("data", "deleteSuccess");
        return map;
    }

    /**
     * @return
     * @Author JinZhiyun
     * @Description 删除多个班级ajax交互
     * @Date 9:36 2019/5/2
     * @Param
     **/
    @RequestMapping("/deleteMany")
    @ResponseBody
    public Map<String, Object> deleteManyClasses(@RequestParam("classes") String classes) {
        Map<String, Object> map = new HashedMap();

        List<ClassWithMajorCollegeDto> classWMCDs = JSON.parseArray(classes, ClassWithMajorCollegeDto.class);

        classService.deleteManyClasses(classWMCDs);

        map.put("data", "deleteSuccess");
        return map;
    }
}
