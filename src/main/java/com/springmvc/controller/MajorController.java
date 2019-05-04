package com.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.springmvc.dto.MajorWithCollegeDto;
import com.springmvc.dto.MyPage;
import com.springmvc.dto.ResultMap;
import com.springmvc.entity.User;
import com.springmvc.service.MajorService;
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
 * @ClassName MajorCollege
 * @Author JinZhiyun
 * @Description 专业业务控制器
 * @Date 2019/4/16 18:55
 * @Version 1.0
 **/
@Controller
@RequestMapping("/major")
public class MajorController {
    @Autowired
    private MajorService majorService;

    @Autowired
    private UserService userService;

    /**
     * @return java.lang.Object
     * @Author JinZhiyun
     * @Description 联动渲染专业select栏的ajax交互
     * @Date 22:56 2019/4/18
     * @Param [collegeName]
     **/
    @RequestMapping("/getMajorNameByCollege")
    @ResponseBody
    public Object findMajorNameByCollege(@RequestParam(value = "collegeName", required = false) String collegeName) {
        return majorService.findMajorNameByCollege(collegeName);
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
     * @return com.springmvc.dto.ResultMap<java.util.List   <   com.springmvc.dto.MajorWithCollegeDto>>
     * @Author JinZhiyun
     * @Description 查询所有专业信息的ajax交互
     * @Date 22:59 2019/4/18
     * @Param [myPage, majorName, majorCollegeName]
     **/
    @RequestMapping("/showAllMajorInfo")
    @ResponseBody
    public ResultMap<List<MajorWithCollegeDto>> showAllMajorInfo(MyPage myPage
            , @RequestParam(value = "majorName", required = false) String majorName
            , @RequestParam(value = "majorCollegeName", required = false) String majorCollegeName) {
        PageInfo<MajorWithCollegeDto> pageInfo = majorService.queryAllMajorInfo(myPage, majorCollegeName, majorName);//从数据库中获取查询所需的数据，在此之前，你不需要在sql语句中编写分页语句，该插件会在查询时直接将分页语句添加到数据库后
        return new ResultMap<>(0, "", (int) pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * @return com.springmvc.dto.ResultMap<java.util.List   <   com.springmvc.dto.MajorWithCollegeDto>>
     * @Author JinZhiyun
     * @Description 查询用户专业信息的ajax交互
     * @Date 17:33 2019/4/19
     * @Param [myPage, session, request]
     **/
    @RequestMapping("/myOwnInfo")
    @ResponseBody
    public ResultMap<List<MajorWithCollegeDto>> myOwnInfo(MyPage myPage, HttpSession session, HttpServletRequest request) {
        User user = userService.updateUserSession(session, request);
        PageInfo<MajorWithCollegeDto> pageInfo = majorService.findMajorOwnInfoById(myPage, user);
        return new ResultMap<>(0, "", (int) pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 定向到专业学院信息修改页面majorAndCollegeInfoModify.jsp
     * @Date 9:51 2019/5/2
     * @Param []
     **/
    @RequestMapping("/modify")
    public String modify() {
        //是否权限判断直接交给springmvc拦截器
        return "app/modify/majorAndCollegeInfoModify";
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 重定向到编辑专业iframe子页面并返回相应model
     * @Date 10:52 2019/5/2
     * @Param [model, majorId, majorCollegeName, majorName]
     **/
    @RequestMapping("/edit")
    public String majorEdit(Model model, @RequestParam(value = "majorId", required = false) String majorId
            , @RequestParam(value = "majorCollegeName", required = false) String majorCollegeName
            , @RequestParam(value = "majorName", required = false) String majorName) {
        model.addAttribute("majorId", majorId);
        model.addAttribute("majorCollegeName", majorCollegeName);
        model.addAttribute("majorName", majorName);
        return "app/modify/majorForm";
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 更新专业信息
     * @Date 11:07 2019/5/2
     * @Param [majorOriId, majorOriName, majorWCD]
     **/
    @RequestMapping("/updateInfo")
    @ResponseBody
    public Map<String, Object> updateInfo(@RequestParam("majorOriId") String majorOriId, @RequestParam("majorOriName") String majorOriName, MajorWithCollegeDto majorWCD) {
        Map<String, Object> map = new HashedMap();

        map.put("data", majorService.updateMapDataResult(majorOriId, majorOriName, majorWCD));
        return map;
    }

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 重定向到添加专业iframe子页面并返回相应model
     * @Date 12:49 2019/5/2
     * @Param [model]
     **/
    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("majorIdRec", majorService.findRecommendedClassId());
        return "app/modify/majorFormAdd";
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 更新专业信息
     * @Date 13:01 2019/5/2
     * @Param [majorWCD]
     **/
    @RequestMapping("/insert")
    @ResponseBody
    public Map<String, Object> insertMajor(MajorWithCollegeDto majorWCD) {
        Map<String, Object> map = new HashedMap();

        System.out.println(majorWCD);
        map.put("data", majorService.insertMapDataResult(majorWCD));

        return map;
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @Author JinZhiyun
     * @Description 删除一个专业ajax交互
     * @Date 13:40 2019/5/2
     * @Param [majorWCD]
     **/
    @RequestMapping("/deleteOne")
    @ResponseBody
    public Map<String, Object> deleteOneMajor(MajorWithCollegeDto majorWCD) {
        System.out.println(majorWCD);
        Map<String, Object> map = new HashedMap();

        majorService.deleteOneMajor(majorWCD);
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
        Map<String, Object> map = new HashedMap();

        List<MajorWithCollegeDto> majorWCDs = JSON.parseArray(majors, MajorWithCollegeDto.class);

        majorService.deleteManyMajors(majorWCDs);
        map.put("data", "deleteSuccess");
        return map;
    }
}
