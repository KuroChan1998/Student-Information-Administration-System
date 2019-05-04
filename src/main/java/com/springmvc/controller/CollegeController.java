package com.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.springmvc.dto.CollegeDto;
import com.springmvc.dto.MyPage;
import com.springmvc.dto.ResultMap;
import com.springmvc.entity.College;
import com.springmvc.entity.User;
import com.springmvc.service.CollegeService;
import com.springmvc.service.UserService;
import net.sf.json.JSONArray;
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
 * @ClassName CollegeController
 * @Author JinZhiyun
 * @Description 学院业务控制器
 * @Date 2019/4/16 18:55
 * @Version 1.0
 **/
@Controller
@RequestMapping("/college")
public class CollegeController {
    @Autowired
    private CollegeService collegeService;

    @Autowired
    private UserService userService;

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
    public Object findCollegeName() {
        List<College> colleges = collegeService.findAllCollege();
        JSONArray jsonArray = new JSONArray();
        for (College college : colleges) {
            jsonArray.add(college);
        }
        return jsonArray.toString();
    }

    /**
     * @return com.springmvc.dto.ResultMap<java.util.List   <   com.springmvc.dto.CollegeDto>>
     * @Author JinZhiyun
     * @Description 查询所有学院信息的ajax交互
     * @Date 22:51 2019/4/18
     * @Param [myPage, collegeName]
     **/
    @RequestMapping("/showAllCollegeInfo")
    @ResponseBody
    public ResultMap<List<CollegeDto>> showAllCollegeInfo(MyPage myPage, @RequestParam(value = "collegeName", required = false) String collegeName) {
        PageInfo<CollegeDto> pageInfo = collegeService.queryAllCollegeInfo(myPage, collegeName);
        return new ResultMap<>(0, "", (int) pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * @return com.springmvc.dto.ResultMap<java.util.List   <   com.springmvc.dto.CollegeDto>>
     * @Author JinZhiyun
     * @Description 查询用户班级信息的ajax交互
     * @Date 13:32 2019/5/3
     * @Param [myPage, session, request]
     **/
    @RequestMapping("/myOwnInfo")
    @ResponseBody
    public ResultMap<List<CollegeDto>> myOwnInfo(MyPage myPage, HttpSession session, HttpServletRequest request) {
        User user = userService.updateUserSession(session, request);
        PageInfo<CollegeDto> pageInfo = collegeService.findCollegeOwnInfoById(myPage, user);
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
    public String collegeEdit(Model model, @RequestParam(value = "collegeId", required = false) String collegeId
            , @RequestParam(value = "collegeName", required = false) String collegeName) {
        model.addAttribute("collegeName", collegeName);
        model.addAttribute("collegeId", collegeId);
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
        Map<String, Object> map = new HashedMap();

        map.put("data", collegeService.updateMapDataResult(collegeOriId, collegeOriName, collegeDto));
        return map;
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 重定向到添加学院iframe子页面并返回相应model
     * @Date 17:20 2019/5/2
     * @Param [model]
     **/
    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("collegeIdRec", collegeService.findRecommendedMajorId());
        return "app/modify/collegeFormAdd";
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
        Map<String, Object> map = new HashedMap();

        System.out.println(collegeDto);
        map.put("data", collegeService.insertMapDataResult(collegeDto));

        return map;
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 删除一个学院ajax交互
     * @Date 19:50 2019/5/2
     * @Param [collegeDto]
     **/
    @RequestMapping("/deleteOne")
    @ResponseBody
    public Map<String, Object> deleteOneCollege(CollegeDto collegeDto) {
        System.out.println(collegeDto);
        Map<String, Object> map = new HashedMap();

        collegeService.deleteOneCollege(collegeDto);
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
        Map<String, Object> map = new HashedMap();

        List<CollegeDto> collegeDtos = JSON.parseArray(colleges, CollegeDto.class);

        collegeService.deleteManyColleges(collegeDtos);

        map.put("data", "deleteSuccess");
        return map;
    }
}
