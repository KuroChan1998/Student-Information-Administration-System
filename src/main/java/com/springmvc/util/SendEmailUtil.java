package com.springmvc.util;

/**
 * @ClassName SendEmailUtil
 * @Author JinZhiyun
 * @Description 发送邮件工具类
 * @Date 2019/4/8 14:39
 * @Version 1.0
 **/

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmailUtil {
    /**
     * @return boolean
     * @Author JinZhiyun
     * @Description 想指定邮箱发送邮件服务
     * @Date 23:22 2019/4/10
     * @Param [emailAddress, emailMsg]
     **/
    public static boolean sendMail(String emailAddress, String emailMsg) {
        // 谁发送
        String from = "929703621@qq.com";

        // 发给谁
        String to = emailAddress;


        // 发送者的用户名和密码(邮箱登录用)
        final String username = "929703621"; // 此处填写发送的邮箱名
        final String password = "bpofvasqxhjlbchg"; // 此处填写登录的邮箱密码（授权码！非密码）

        // 定义properties对象，设置环境信息
        Properties properties = new Properties();

        /*
         * mail.smtp.host ：指定连接的邮件服务器的主机名。如：163邮箱就填写smtp.163.com
         * 若在本地测试的话，需要在本地安装smtp服务器
         */
        String host = "smtp.qq.com";
        properties.setProperty("mail.smtp.host", host);
        // mail.smtp.auth：指定客户端是否要向邮件服务器提交验证
        properties.setProperty("mail.smtp.auth", "true");

        /*
         * mail.transport.protocol：指定邮件发送协议：smtp。smtp：发邮件；pop3：收邮件
         * mail.store.protocol:指定邮件接收协议
         */
        properties.setProperty("mail.transport.protocol", "smtp");

        // 获取session对象
        Session session = Session.getInstance(properties);

        // 当设置为true，JavaMail AP就会将其运行过程和邮件服务器的交互命令信息输出到console中，用于JavaMail的调试
        session.setDebug(true);

        try {
            // 创建邮件对象
            MimeMessage message = new MimeMessage(session);

            // 设置邮件发送方
            message.setFrom(new InternetAddress(from));


            // 设置邮件发送的主题<邮件标题>
            message.setSubject("StuInfoAdmin验证码");


            // 设置邮件发送的内容
            message.setContent(emailMsg, "text/html;charset=utf-8");

            Transport transport = session.getTransport();


            // 连接邮件服务器，“”中填写邮件服务器主机名
            transport.connect(host, 25, username, password);
            transport.sendMessage(message, new Address[]{new InternetAddress(to)});
            transport.close();

            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}