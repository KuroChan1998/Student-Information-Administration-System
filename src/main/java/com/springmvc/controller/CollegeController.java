package com.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.springmvc.dto.college.CollegeDto;
import com.springmvc.dto.other.MyPage;
import com.springmvc.dto.other.ResultMap;
import com.springmvc.entity.College;
import com.springmvc.entity.User;
import com.springmvc.util.Constants;
import net.sf.json.JSONArray;
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
 * @ClassName CollegeController
 * @Author JinZhiyun
 * @Description 学院业务控制器
 * @Date 2019/4/16 18:55
 * @Version 1.0
 **/
@Controller
@RequestMapping("/college")
public class CollegeController extends BaseController{
    /**
     * @return java.lang.Object
     * @Author JinZhiyun
     * @Description 用于ajax渲染学院select表单栏，找出数据库中所有的学院名称
     * @Date 22:49 2019/4/18
     * @Param []
     **/
    @RequestMapping(value = "/getCollegeName", produces = "application/json; charset=utf-8")
    //注意这里只要加了ResponseBody这个注解就可以直接返回 List<College>，springmvc会自动将其解析成JSON数据格式，
    //但若用jsonArray.toString()会中文乱码，需要在@RequestMapping中设定编码
    @ResponseBody
    public Object getCollegeName() {
        List<College> colleges = collegeService.selectAllCollege();
        JSONArray jsonArray = new JSONArray();
        for (College college : colleges) {
            jsonArray.add(college);
        }
        return jsonArray.toString();
    }

    /**
     * @return com.springmvc.dto.other.ResultMap<java.util.List   <   com.springmvc.dto.college.CollegeDto>>
     * @Author JinZhiyun
     * @Description 查询所有学院信息的ajax交互
     * @Date 22:51 2019/4/18
     * @Param [myPage, collegeName]
     **/
    @RequestMapping("/showAllCollegeInfo")
    @ResponseBody
    public ResultMap<List<CollegeDto>> showAllCollegeInfo(MyPage myPage, CollegeDto collegeDto) {
        System.out.println(collegeDto);
        PageInfo<CollegeDto> pageInfo = collegeService.selectAllCollegeInfo(myPage, collegeDto);
        return new ResultMap<>(0, "", (int) pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * @return com.springmvc.dto.other.ResultMap<java.util.List   <   com.springmvc.dto.college.CollegeDto>>
     * @Author JinZhiyun
     * @Description 查询用户班级信息的ajax交互
     * @Date 13:32 2019/5/3
     * @Param [myPage, session, request]
     **/
    @RequestMapping("/myOwnInfo")
    @ResponseBody
    public ResultMap<List<CollegeDto>> myOwnInfo(MyPage myPage) {
        User user = (User) session.getAttribute(Constants.USERINFO_SESSION);
        PageInfo<CollegeDto> pageInfo = collegeService.selectCollegeOwnInfoByNum(myPage, user);
        return new ResultMap<>(0, "", (int) pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 重定向到编辑学院iframe子页面并返回相应model
     * @Date 16:44 2019/5/2
     * @Param [model, collegeId, collegeName]
     **/
    @RequestMapping("/edit")
    public String collegeEdit(Model model, CollegeDto collegeDto) {
        model.addAttribute(Constants.COLLEGE_ALL_INFO_MODEL, collegeDto);
        return "app/modify/collegeForm";
    }


    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 更新学院信息
     * @Date 16:46 2019/5/2
     * @Param [collegeOriId, collegeOriName, collegeDto]
     **/
    @RequestMapping("/updateInfo")
    @ResponseBody
    public Map<String, Object> updateInfo(@RequestParam("collegeOriId") String collegeOriId, @RequestParam("collegeOriName") String collegeOriName, CollegeDto collegeDto) {
        Map<String, Object> map = new HashMap();

        map.put("data", collegeService.updateCollegeInfo(collegeOriId, collegeOriName, collegeDto));
        return map;
    }

    /**
     * @author JinZhiyun
     * @Description 重定向到添加学院iframe子页面并返回相应model
     * @Date 15:04 2019/7/14
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/add")
    public String add() {
        return "app/modify/collegeForm";
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 更新学院信息
     * @Date 19:49 2019/5/2
     * @Param [collegeDto]
     **/
    @RequestMapping("/insert")
    @ResponseBody
    public Map<String, Object> insertCollege(CollegeDto collegeDto) {
        Map<String, Object> map = new HashMap();
        map.put("data", collegeService.insertCollege(collegeDto));
        return map;
    }

    /**
     * @author JinZhiyun
     * @Description 删除一个学院ajax交互
     * @Date 15:57 2019/7/14
     * @Param [collegeName]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/deleteOne")
    @ResponseBody
    public Map<String, Object> deleteOneCollege(@RequestParam("collegeName") String collegeName) {
        Map<String, Object> map = new HashMap();
        collegeService.deleteOneCollege(collegeName);
        map.put("data", "deleteSuccess");
        return map;
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 删除多个学院ajax交互
     * @Date 21:58 2019/5/2
     * @Param [colleges]
     **/
    @RequestMapping("/deleteMany")
    @ResponseBody
    public Map<String, Object> deleteManyMajors(@RequestParam("colleges") String colleges) {
        Map<String, Object> map = new HashMap();

        List<CollegeDto> collegeDtos = JSON.parseArray(colleges, CollegeDto.class);
        List<String> collegeNames=new ArrayList<>();
        for (CollegeDto collegeDto:collegeDtos){
            collegeNames.add(collegeDto.getCollegeName());
        }
        collegeService.deleteManyColleges(collegeNames);

        map.put("data", "deleteSuccess");
        return map;
    }
}
