package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author JinZhiyun
 * @ClassName GradeController
 * @Description 年级业务控制器
 * @Date 2019/6/14 12:41
 * @Version 1.0
 **/
@Controller
@RequestMapping("/grade")
public class GradeController extends BaseController{
    /**
     * @author JinZhiyun
     * @Description 用于ajax渲染年级select表单栏，找出数据库中所有的年级名称
     * @Date 22:53 2019/6/18
     * @Param []
     * @return java.lang.Object
     **/
    @RequestMapping("/getGradeName")
    @ResponseBody
    public Object getGradeName() {
        return gradeService.selectAllGrade();
    }
}
