package com.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.springmvc.dto.major.MajorSearchDto;
import com.springmvc.dto.major.MajorWithCollegeDto;
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
 * @ClassName MajorCollege
 * @Author JinZhiyun
 * @Description 专业业务控制器
 * @Date 2019/4/16 18:55
 * @Version 1.0
 **/
@Controller
@RequestMapping("/major")
public class MajorController extends BaseController {
    /**
     * @return java.lang.Object
     * @Author JinZhiyun
     * @Description 联动渲染专业select栏的ajax交互
     * @Date 22:56 2019/4/18
     * @Param [collegeName]
     **/
    @RequestMapping("/getMajorNameByCollege")
    @ResponseBody
    public Object getMajorNameByCollege(@RequestParam(value = "collegeName", required = false) String collegeName) {
        return majorService.selectMajorByCollegeName(collegeName);
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 定向到专业和学员信息查询页面majorAndCollegeInfoQuery.jsp
     * @Date 22:58 2019/4/18
     * @Param []
     **/
    @RequestMapping("/query")
    public String query() {
        return "app/query/majorAndCollegeInfoQuery";
    }

    /**
     * @return com.springmvc.dto.other.ResultMap<java.util.List   <   com.springmvc.dto.major.MajorWithCollegeDto>>
     * @author JinZhiyun
     * @Description 查询所有专业信息的ajax交互
     * @Date 9:22 2019/7/11
     * @Param [myPage, majorSearch]
     **/
    @RequestMapping("/showAllMajorInfo")
    @ResponseBody
    public ResultMap<List<MajorWithCollegeDto>> showAllMajorInfo(MyPage myPage, MajorSearchDto majorSearch) {
        PageInfo<MajorWithCollegeDto> pageInfo = majorService.selectAllMajorInfo(myPage, majorSearch);//从数据库中获取查询所需的数据，在此之前，你不需要在sql语句中编写分页语句，该插件会在查询时直接将分页语句添加到数据库后
        return new ResultMap<>(0, "", (int) pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * @return com.springmvc.dto.other.ResultMap<java.util.List   <   com.springmvc.dto.major.MajorWithCollegeDto>>
     * @author JinZhiyun
     * @Description 查询用户专业信息的ajax交互
     * @Date 9:22 2019/7/11
     * @Param [myPage]
     **/
    @RequestMapping("/myOwnInfo")
    @ResponseBody
    public ResultMap<List<MajorWithCollegeDto>> myOwnInfo(MyPage myPage) {
        User user = (User) session.getAttribute(Constants.USERINFO_SESSION);
        PageInfo<MajorWithCollegeDto> pageInfo = majorService.selectMajorOwnInfoByNum(myPage, user);
        return new ResultMap<>(0, "", (int) pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * @return java.lang.String
     * @author JinZhiyun
     * @Description 定向到专业学院信息修改页面majorAndCollegeInfoModify.jsp
     * @Date 9:22 2019/7/11
     * @Param []
     **/
    @RequestMapping("/modify")
    public String modify() {
        //是否权限判断直接交给springmvc拦截器
        return "app/modify/majorAndCollegeInfoModify";
    }

    /**
     * @author JinZhiyun
     * @Description 重定向到编辑专业iframe子页面并返回相应model
     * @Date 16:46 2019/7/25
     * @Param [model, majorWC]
     * @return java.lang.String
     **/
    @RequestMapping("/edit")
    public String majorEdit(Model model, MajorWithCollegeDto majorWC) {
        model.addAttribute(Constants.MAJOR_ALL_INFO_MODEL, majorWC);
        return "app/modify/majorForm";
    }

    /**
     * @return java.util.Map<java.lang.String       ,       java.lang.Object>
     * @Author JinZhiyun
     * @Description 更新专业信息
     * @Date 11:07 2019/5/2
     * @Param [majorOriId, majorOriName, majorWC]
     **/
    @RequestMapping("/updateInfo")
    @ResponseBody
    public Map<String, Object> updateInfo(@RequestParam("majorOriId") String majorOriId, @RequestParam("majorOriName") String majorOriName, MajorWithCollegeDto majorWC) {
        Map<String, Object> map = new HashMap();
        map.put("data", majorService.updateMajorInfo(majorOriId, majorOriName, majorWC));
        return map;
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 重定向到添加专业iframe子页面并返回相应model
     * @Date 12:49 2019/5/2
     * @Param []
     **/
    @RequestMapping("/add")
    public String add() {
        return "app/modify/majorForm";
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 更新专业信息
     * @Date 13:01 2019/5/2
     * @Param [majorWC]
     **/
    @RequestMapping("/insert")
    @ResponseBody
    public Map<String, Object> insertMajor(MajorWithCollegeDto majorWC) {
        Map<String, Object> map = new HashMap();
        map.put("data", majorService.insertMajor(majorWC));

        return map;
    }

    /**
     * @author JinZhiyun
     * @Description 删除一个专业ajax交互
     * @Date 9:16 2019/7/14
     * @Param [majorName]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/deleteOne")
    @ResponseBody
    public Map<String, Object> deleteOneMajor(@RequestParam("majorName") String majorName) {
        Map<String, Object> map = new HashMap();
        majorService.deleteOneMajor(majorName);
        map.put("data", "deleteSuccess");
        return map;
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 删除多个专业ajax交互
     * @Date 16:26 2019/5/2
     * @Param [majors]
     **/
    @RequestMapping("/deleteMany")
    @ResponseBody
    public Map<String, Object> deleteManyMajors(@RequestParam("majors") String majors) {
        Map<String, Object> map = new HashMap();

        List<MajorWithCollegeDto> majorWCDs = JSON.parseArray(majors, MajorWithCollegeDto.class);
        List<String> majorNames=new ArrayList<>();
        for (MajorWithCollegeDto majorWCD:majorWCDs){
            majorNames.add(majorWCD.getMajorName());
        }
        majorService.deleteManyMajors(majorNames);
        map.put("data", "deleteSuccess");
        return map;
    }
}
