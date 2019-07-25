package com.springmvc.service;

import com.springmvc.dto.other.EmailVerifyCode;
import com.springmvc.dto.other.senior.StudentPercentBySex;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author JinZhiyun
 * @IntefaceName OtherService
 * @Description 其他业务接口
 * @Date 2019/6/14 12:52
 * @Version 1.0
 **/
public interface OtherService {
    /**
     * @return java.lang.String 返回上传的图片存储的相对路径
     * @Author JinZhiyun
     * @Description 头像上传业务
     * @Date 15:33 2019/4/10
     * @Param [file, request, UserId]
     **/
    String uploadUserIcon(MultipartFile file, HttpServletRequest request, String userId);


    /**
     * @author JinZhiyun
     * @Description 发送邮箱验证码业务
     * @Date 9:53 2019/6/6
     * @Param [userEmail]
     * @return com.springmvc.dto.other.EmailVerifyCode
     **/
    EmailVerifyCode sendVerifyCodeToEmail(String userEmail);

    /**
     * @author JinZhiyun
     * @Description 验证邮箱验证码正确与否，是否失效
     * @Date 9:23 2019/6/6
     * @Param [code, session]
     * @return boolean
     **/
    boolean ifValidEmailVerifyCode(String code, HttpSession session);

    /**
     * @author JinZhiyun
     * @Description 判断当前登录的用户是否为管理员
     * @Date 20:51 2019/6/20
     * @Param [session]
     * @return boolean
     **/
    boolean ifUserIsAdmin(HttpSession session);

    /**
     * @author JinZhiyun
     * @Description 查找符合条件的对应性别的学生数的封装对象
     * @Date 16:49 2019/7/14
     * @Param [stuCollegeName, stuMajorName, stuClassName]
     * @return com.springmvc.dto.other.senior.StudentPercentBySex
     **/
    StudentPercentBySex selectStuTotalBySex(String stuCollegeName, String stuMajorName, String stuClassName);

    /**
     * @author JinZhiyun
     * @Description
     *  返回一个长度为2的List，第0项是类别名称组成的List，第1项是对应类别名称下的人数
     *  type为查询的类型：
     *      allCollege：表示全部学院的学生人数比
     *      allMajor: 表示全部专业的学生人数比
     *      allClass：表示全部班级的学生人数比
     *      byMajorOrCollege：表示特定学院或专业的学生人数比，根据这两个值判断调用dao中对应方法的type输入值
     *      grade：表示全部全部的学生人数比
     * @Date 18:32 2019/7/16
     * @Param [collegeName, majorName]
     * @return java.util.List<java.util.List<java.lang.Object>>
     **/
    List<List<Object>> proSelectStuTotalByCollegeOrMajorName(String type, String collegeName, String majorName);
    
    /**
     * @author JinZhiyun
     * @Description 查询本硕博人数比业务
     * type为查询的类型：
     *      wholeSchoolByStuDegree：表示全校的本硕博人数比，返回的List为1，即仅含一个Map
     *      allCollegeByStuDegree: 表示全部学院的本硕博人数比
     *      majorUnderCollegeByStuDegree：表示特定学院下专业的本硕博人数比，若输入collegeName变量为空，则等效于allCollegeByStuDegree类型查询
     *      allMajorByStuDegree：表示全部专业的本硕博人数比
     * @Date 17:20 2019/7/23
     * @Param [type, collegeName]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> transferStuTotalToValidJSON(String type, String collegeName);

    /**
     * @author JinZhiyun
     * @Description 查找符合条件的对应类别名称的教师数的封装对象，这里用mysql存储过程实现
     * type为查询的类型：
     *      allCollegeByTeaTitle：表示某学院下的不同职称教师人数
     *      byMajorOrCollege：表示特定学院或专业的学生人数比，根据这两个值判断调用dao中对应方法的type输入值
     *      wholeSchoolByTeaTitle： 表示全校的不同职称教师人数
     *      allMajorByTeaTitle： 表示某专业下的不同职称教师人数
     * @Date 18:30 2019/7/24
     * @Param [type, collegeName, majorName]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    Map<String,Object> transTeaTotalToValidJSON(String type, String collegeName, String majorName);
}

