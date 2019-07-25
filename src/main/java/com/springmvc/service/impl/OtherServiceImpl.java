package com.springmvc.service.impl;

import com.springmvc.dto.other.EmailVerifyCode;
import com.springmvc.dto.other.senior.ObjectTotalGroupByCommonName;
import com.springmvc.dto.other.senior.StudentPercentBySex;
import com.springmvc.dto.other.senior.StudentTotalGroupBySex;
import com.springmvc.entity.College;
import com.springmvc.entity.Major;
import com.springmvc.entity.User;
import com.springmvc.service.OtherService;
import com.springmvc.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author JinZhiyun
 * @ClassName OtherServiceImpl
 * @Description 其他业务接口方法实现类
 * @Date 2019/6/6 13:12
 * @Version 1.0
 **/
@Service
@Transactional
public class OtherServiceImpl extends BaseServiceImpl implements OtherService {
    @Override
    public String uploadUserIcon(MultipartFile file, HttpServletRequest request, String userId) {
        String prefix = "";

        String uploadDir = Constants.USER_DEFAULT_ICON_DIR;
        String absoluFilePath = "";
        String relaFilePath = "";

        DateFormat format1 = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
        String dateStr = format1.format(new Date());

        //保存上传
        OutputStream out = null;
        InputStream fileInput = null;
        try {
            if (file != null) {
                String originalName = file.getOriginalFilename();
                prefix = originalName.substring(originalName.lastIndexOf(".") + 1);
                String filepath = request.getServletContext().getRealPath(uploadDir); //文件存放文件夹路径名
                filepath = filepath.replace("\\", "/");
                String fileName = userId + "_icon_" + dateStr + "." + prefix; //文件名
                absoluFilePath = filepath + "/" + fileName;//绝对路径
                relaFilePath = uploadDir + "/" + fileName;
                System.out.println(relaFilePath);

                File files = new File(absoluFilePath);
                //打印查看上传路径
                System.out.println(filepath);
                if (!files.getParentFile().exists()) {
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (fileInput != null) {
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }

        return relaFilePath;
    }

    @Override
    public EmailVerifyCode sendVerifyCodeToEmail(String userEmail) {
        // 获取前端传入的参数
        String emailAddress = userEmail;
        String verifyCode = VerifyCode.randomCode();
        String emailMsg = "收到来自StuInfoAdmin--大学生学籍信息管理系统的验证码：\n" + verifyCode + "\n有效时间: " + EmailVerifyCode.validTime / (1000 * 60) + "分钟";

        try {

            // 邮件发送处理
            SendEmailUtil.sendMail(emailAddress, emailMsg);

            return new EmailVerifyCode(userEmail, verifyCode, MyTimeUtil.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean ifValidEmailVerifyCode(String code, HttpSession session) {
        EmailVerifyCode emailVerifyCode = (EmailVerifyCode) session.getAttribute(Constants.EMAILVERIFYCODE_SESSION);
        if (code == null || !code.equals(emailVerifyCode.getCode()) || !emailVerifyCode.isValid()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean ifUserIsAdmin(HttpSession session) {
        User user = (User) session.getAttribute(Constants.USERINFO_SESSION);
        if (user.getUserIdentity().equals("管理员")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public StudentPercentBySex selectStuTotalBySex(String stuCollegeName, String stuMajorName, String stuClassName) {
        StudentPercentBySex studentPercentBySex = new StudentPercentBySex();
        List<StudentTotalGroupBySex> studentTotalGroupBySexes = studentMapper.selectStuTotalBySex(stuCollegeName, stuMajorName, stuClassName);
        studentPercentBySex.setStuCollegeName(stuCollegeName);
        studentPercentBySex.setStuMajorName(stuMajorName);
        studentPercentBySex.setStuClassName(stuClassName);
        if (studentTotalGroupBySexes.size() != 0) {
            for (StudentTotalGroupBySex studentTotalGroupBySex : studentTotalGroupBySexes) {
                if (studentTotalGroupBySex.getSex().equals("男")) {
                    studentPercentBySex.setMale(studentTotalGroupBySex.getTotal());
                } else if (studentTotalGroupBySex.getSex().equals("女")) {
                    studentPercentBySex.setFemale(studentTotalGroupBySex.getTotal());
                }
            }
        } else {
            studentPercentBySex.setMale(0);
            studentPercentBySex.setFemale(0);
        }
        studentPercentBySex.setTotal();
        return studentPercentBySex;
    }

    @Override
    public List<List<Object>> proSelectStuTotalByCollegeOrMajorName(String type, String collegeName, String majorName) {
        List<List<Object>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        result.add(new ArrayList<>());
        List<ObjectTotalGroupByCommonName> objectTotals = new ArrayList<>();
        if (type.equals("allCollege") || type.equals("allMajor") || type.equals("allClass") || type.equals("grade")) {
            objectTotals = studentMapper.selectStuTotalByCommonName(type, null, null);
        } else if (type.equals("byMajorOrCollege")) {
            String tmpType;
            if (majorName == null || majorName.equals("")) {
                if (collegeName == null || collegeName.equals("")) {
                    tmpType = "allCollege";
                } else {
                    tmpType = "majorUnderCollege";
                }
            } else {
                tmpType = "classUnderMajor";
            }
            objectTotals = studentMapper.selectStuTotalByCommonName(tmpType, collegeName, majorName);
        }
        for (ObjectTotalGroupByCommonName objectTotal : objectTotals) {
            result.get(0).add(objectTotal.getCommonName());
            result.get(1).add(objectTotal.getTotal());
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> transferStuTotalToValidJSON(String type, String collegeName) {
        List<Map<String, Object>> result = new ArrayList<>();
        if (type.equals("wholeSchoolByStuDegree")) {
            Map<String, Object> tmpMap = new HashMap<>();
            tmpMap.put("product", "全校");
            List<ObjectTotalGroupByCommonName> objectTotals = studentMapper.selectStuTotalByCommonName(type, null, null);
            tmpMap.putAll(MySimpleUtil.transferStuTotalToTmpMap(objectTotals));
            result.add(tmpMap);
            return result;
        }
        if (type.equals("allCollegeByStuDegree") || type.equals("majorUnderCollegeByStuDegree")) {
            if (collegeName == null || collegeName.equals("")) {
                List<College> colleges = collegeMapper.selectAllCollege();
                for (College college : colleges) {
                    Map<String, Object> tmpMap = new HashMap<>();
                    tmpMap.put("product", college.getCollegeName());
                    List<ObjectTotalGroupByCommonName> objectTotals = studentMapper.selectStuTotalByCommonName("allCollegeByStuDegree", college.getCollegeName(), null);
                    tmpMap.putAll(MySimpleUtil.transferStuTotalToTmpMap(objectTotals));
                    result.add(tmpMap);
                }
                return result;
            } //collegeName非空即表示查询该学院下的专业本硕博人数，此时type="majorUnderCollegeByStuDegree"，在下一个if语句块中执行
        }
        if (type.equals("allMajorByStuDegree") || type.equals("majorUnderCollegeByStuDegree")) {
/*            type="allMajorByStuDegree"
                    或者type="majorUnderCollegeByStuDegree"，collegeName非空*/
            System.out.println(collegeName);
            List<Major> majors = majorMapper.selectMajorByCollegeName(collegeName);
            System.out.println("xxx" + majors);
            for (Major major : majors) {
                Map<String, Object> tmpMap = new HashMap<>();
                tmpMap.put("product", major.getMajorName());
                List<ObjectTotalGroupByCommonName> objectTotals = studentMapper.selectStuTotalByCommonName(type.equals("allMajorByStuDegree") ? "majorUnderCollegeByStuDegree" : type, null, major.getMajorName());
                tmpMap.putAll(MySimpleUtil.transferStuTotalToTmpMap(objectTotals));
                result.add(tmpMap);
            }
            return result;
        }
        return result;
    }

    @Override
    public Map<String, Object> transTeaTotalToValidJSON(String type, String collegeName, String majorName) {
        Map<String, Object> map = new HashMap<>();
        List<List<Object>> source = new ArrayList<List<Object>>();
        List<Object> series = new ArrayList<>();
        final List<String> titleNames = titleMapper.selectAllTitleName();
        source.add(new ArrayList<Object>() {//这个大括号 就相当于我们  new 接口
            {//这个大括号 就是 构造代码块 会在构造函数前 调用
                this.add("product");
                this.addAll(titleNames);
            }
        });

        if (type.equals("allCollegeByTeaTitle")) {
            List<College> colleges = collegeMapper.selectAllCollege();
            for (College college : colleges) {
                List<Object> sourceTmp = new ArrayList<>();
                sourceTmp.add(college.getCollegeName());
                List<ObjectTotalGroupByCommonName> objectTotals = teacherMapper.selectTeaTotalByCommonName(type, college.getCollegeName(), null);
                System.out.println(objectTotals);
                System.out.println(titleNames);
                sourceTmp.addAll(MySimpleUtil.transferTeaTotalToTmpMap(objectTotals, titleNames));
                System.out.println(sourceTmp);
                source.add(sourceTmp);
            }
            series.addAll(EchartsFactory.getSeriesInFirstGrids(colleges.size())); //设置第二张表格的bar
            series.addAll(EchartsFactory.getSeriesInSecondGrids(titleNames.size())); //设置第一张表格的bar
        } else if (type.equals("byMajorOrCollege")) {
            String tmpType;
            if (majorName == null || majorName.equals("")) {
                if (collegeName == null || collegeName.equals("")) {
                    tmpType = "allCollegeByTeaTitle";
                    return transTeaTotalToValidJSON(tmpType, collegeName, majorName);
                } else {
                    tmpType = "allCollegeByTeaTitle"; //直接调用allCollegeByTeaTitle对应的sql
                    List<Object> sourceTmp = new ArrayList<>();
                    sourceTmp.add(collegeName);
                    List<ObjectTotalGroupByCommonName> objectTotals = teacherMapper.selectTeaTotalByCommonName(tmpType, collegeName, null);
                    sourceTmp.addAll(MySimpleUtil.transferTeaTotalToTmpMap(objectTotals, titleNames));
                    source.add(sourceTmp);
                    series.add(EchartsFactory.getSeriesInFirstGrid()); //设置第二张表格的bar
                    series.addAll(EchartsFactory.getSeriesInSecondGrids(titleNames.size())); //设置第一张表格的bar
                }
            } else {
                tmpType = "allMajorByTeaTitle"; //allMajorByTeaTitle
                List<Object> sourceTmp = new ArrayList<>();
                sourceTmp.add(majorName);
                List<ObjectTotalGroupByCommonName> objectTotals = teacherMapper.selectTeaTotalByCommonName(tmpType, null, majorName);
                sourceTmp.addAll(MySimpleUtil.transferTeaTotalToTmpMap(objectTotals, titleNames));
                source.add(sourceTmp);
                series.add(EchartsFactory.getSeriesInFirstGrid()); //设置第二张表格的bar
                series.addAll(EchartsFactory.getSeriesInSecondGrids(titleNames.size())); //设置第一张表格的bar
            }
        } else if (type.equals("wholeSchoolByTeaTitle")) {
            List<Object> sourceTmp = new ArrayList<>();
            sourceTmp.add("全校");
            List<ObjectTotalGroupByCommonName> objectTotals = teacherMapper.selectTeaTotalByCommonName(type, null, null);
            sourceTmp.addAll(MySimpleUtil.transferTeaTotalToTmpMap(objectTotals, titleNames));
            source.add(sourceTmp);
            series.add(EchartsFactory.getSeriesInFirstGrid()); //设置第二张表格的bar
            series.addAll(EchartsFactory.getSeriesInSecondGrids(titleNames.size())); //设置第一张表格的bar
        } else if (type.equals("allMajorByTeaTitle")) {
            List<Major> majors = majorMapper.selectMajorByCollegeName(null);
            for (Major major : majors) {
                List<Object> sourceTmp = new ArrayList<>();
                sourceTmp.add(major.getMajorName());
                List<ObjectTotalGroupByCommonName> objectTotals = teacherMapper.selectTeaTotalByCommonName(type, null, major.getMajorName());
                sourceTmp.addAll(MySimpleUtil.transferTeaTotalToTmpMap(objectTotals, titleNames));
                source.add(sourceTmp);
            }
            series.addAll(EchartsFactory.getSeriesInFirstGrids(majors.size())); //设置第二张表格的bar
            series.addAll(EchartsFactory.getSeriesInSecondGrids(titleNames.size())); //设置第一张表格的bar
        }

        map.put("source", source);
        map.put("series", series);
        return map;
    }
}
