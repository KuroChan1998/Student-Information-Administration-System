package com.jzy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author JinZhiyun
 * @ClassName TitleController
 * @Description 教师职称业务控制器
 * @Date 2019/6/14 12:43
 * @Version 1.0
 **/
@Controller
@RequestMapping("/title")
public class TitleController extends BaseController {
    /**
     * @author JinZhiyun
     * @Description 用于ajax渲染年级select表单栏，找出数据库中所有的年级名称
     * @Date 20:55 2019/6/25
     * @Param []
     * @return java.lang.Object
     **/
    @RequestMapping("/getTitleName")
    @ResponseBody
    public Object getTitleName() {
        return titleService.selectAllTitle();
    }
}
