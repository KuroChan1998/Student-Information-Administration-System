package com.springmvc.service.impl;

import com.springmvc.dao.UserMapper;
import com.springmvc.entity.User;
import com.springmvc.service.UserService;
import com.springmvc.service.impl.util.Constants;
import com.springmvc.service.impl.util.MySecurity;
import com.springmvc.service.impl.util.SendEmailUtil;
import com.springmvc.service.impl.util.VerifyCode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @ClassName UserServiceImpl
 * @Author JinZhiyun
 * @Description 用户业务实现
 * @Date 2019/1/25 9:58
 * @Version 1.0
 **/
@Service
@Transactional
public class UserServiceImpl implements UserService {

//    @Resource(name = "111")
//    private UserMapper userMapper;

    @Autowired
    private UserMapper userMapper;

    private final static Logger logger = Logger.getLogger(UserServiceImpl.class);


    @Override
    public int findUserByIdAndPassword(String userId, String userPassword) {
        userPassword=MySecurity.encryptUserPassword(userPassword,userId);
        return userMapper.findByIdAndPassword(userId, userPassword);
    }

    @Override
    public String insertUserRegInfo(User user) {
        if (userMapper.findIfIdExist(user.getUserId()) != 0) {
            return "regIdExist"; //regIdExist表示用户名已被注册
        }
//        if (userMapper.findIfNicknameExist(user.getUserNickname()) != 0) {
//            return "regNicknameExist"; //regNicknameExist表示昵称已被注册
//        }
        if (userMapper.findIfEmailExist(user.getUserEmail()) != 0) {
            return "regEmailExist"; //regEmailExist表示邮箱已被注册
        }
        user.setUserPassword(MySecurity.encryptUserPassword(user.getUserPassword(),user.getUserId()));
        user.setUserIcon(Constants.USER_DEFAULT_ICON_PATH);
        userMapper.insertUserRegInfo(user);
        return "regSuccess";//return "regSuccess"表示注册成功
    }

    @Override
    public String sendVerifyCodeToEmail(String userEmail) {
        // 获取前端传入的参数
        String emailAddress = userEmail;
        String verifyCode = VerifyCode.randomCode();
        String emailMsg = "收到来自StuInfoAdmin--大学生学籍信息管理系统的验证码：\n" + verifyCode;

        try {

            // 邮件发送处理
            SendEmailUtil.sendMail(emailAddress, emailMsg);

            return verifyCode;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<String> elseIdentityList(String identity) {
        List<String> list = new ArrayList<>();
        if (identity.equals("学生")) {
            list.add("教师");
            list.add("管理员");
        } else if (identity.equals("教师")) {
            list.add("学生");
            list.add("管理员");
        } else {
            list.add("学生");
            list.add("教师");
        }
        return list;
    }

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
    public String updateResetUserInfo(User user) {
        userMapper.updateResetUserInfo(user);
        return "updateSuccess"; //updateSuccess表示昵注册成功
    }

    @Override
    public void updateResetPasswordByUserId(String userPassword, String userId) {
        userPassword=MySecurity.encryptUserPassword(userPassword,userId);
        userMapper.updateResetPasswordByUserId(userPassword, userId);
    }

    @Override
    public User updateUserSession(HttpSession session, HttpServletRequest request) {
        session = request.getSession();
        String userId = (String) session.getAttribute(Constants.USERID_SESSION);
        User user = this.selectUserById(userId);
        session.setAttribute(Constants.USERINFO_SESSION, user);
        return user;
    }

    @Override
    public void updateResetEmailByEmail(String userOldEmail, String userNewEmail) {
        userMapper.updateResetEmailByEmail(userOldEmail, userNewEmail);
    }

    @Override
    public void destroyUserSession(HttpSession session, HttpServletRequest request) {
        session = request.getSession();
        session.removeAttribute(Constants.USERID_SESSION);
    }

    @Override
    public boolean ifAdmin(HttpSession session, HttpServletRequest request) {
        User user = updateUserSession(session, request);
        if (user.getUserIdentity().equals("管理员")) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void updateResetPasswordByEmail(String userEmail, String userPassword) {
        String userId=userMapper.selectUserByUserEmail(userEmail).getUserId();
        userPassword=MySecurity.encryptUserPassword(userPassword,userId);
        userMapper.updateResetPasswordByEmail(userEmail, userPassword);
    }

    @Override
    public int findUserByEmail(String userEmail) {
        return userMapper.findIfEmailExist(userEmail);
    }

    @Override
    public User selectUserById(String userId) {
        return userMapper.selectUserById(userId);
    }


}
