package com.springmvc.service.impl;

import com.springmvc.dto.other.EmailVerifyCode;
import com.springmvc.dto.other.senior.ObjectTotalGroupByCommonName;
import com.springmvc.dto.other.senior.StudentPercentBySex;
import com.springmvc.dto.other.senior.StudentTotalGroupBySex;
import com.springmvc.entity.User;
import com.springmvc.service.OtherService;
import com.springmvc.util.Constants;
import com.springmvc.util.SendEmailUtil;
import com.springmvc.util.MyTimeUtil;
import com.springmvc.util.VerifyCode;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author JinZhiyun
 * @ClassName OtherServiceImpl
 * @Description //TODO
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
        StudentPercentBySex studentPercentBySex=new StudentPercentBySex();
        List<StudentTotalGroupBySex> studentTotalGroupBySexes =studentMapper.selectStuTotalBySex(stuCollegeName,stuMajorName,stuClassName);
        studentPercentBySex.setStuCollegeName(stuCollegeName);
        studentPercentBySex.setStuMajorName(stuMajorName);
        studentPercentBySex.setStuClassName(stuClassName);
        if (studentTotalGroupBySexes.size() !=0) {
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
        List<List<Object>> result=new ArrayList<>();
        result.add(new ArrayList<>());
        result.add(new ArrayList<>());
        List<ObjectTotalGroupByCommonName> objectTotals=new ArrayList<>();
        if (type.equals("allCollege") || type.equals("allMajor") || type.equals("allClass") || type.equals("grade")){
            objectTotals=studentMapper.selectStuTotalByCommonName(type,null,null);
        } else if (type.equals("byMajorOrCollege")){
            String tmpType;
            if (majorName == null || majorName.equals("")) {
                if (collegeName == null || collegeName.equals("")) {
                    tmpType="allCollege";
                } else {
                    tmpType="majorUnderCollege";
                }
            } else {
                tmpType="classUnderMajor";
            }
            objectTotals=studentMapper.selectStuTotalByCommonName(tmpType,collegeName,majorName);
        }
        for (ObjectTotalGroupByCommonName objectTotal : objectTotals) {
            result.get(0).add(objectTotal.getCommonName());
            result.get(1).add(objectTotal.getTotal());
        }
        return result;
    }
}
