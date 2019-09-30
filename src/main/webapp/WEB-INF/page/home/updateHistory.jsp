<%--
  Created by IntelliJ IDEA.
  User: 13681864361
  Date: 2019/9/11
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>更新历史</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${ctx}/static/plugins/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${ctx}/static/plugins/layuiadmin/style/admin.css" media="all">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>更新时间线</legend>
</fieldset>
<ul class="layui-timeline">
    <li class="layui-timeline-item">
        <i class="layui-icon layui-timeline-axis"></i>
        <div class="layui-timeline-content layui-text">
            <h3 class="layui-timeline-title">v1.0.0-2019.9.30</h3>
            <div class="layui-timeline-title">
                <p><em>大学生信息管理系统</em> StuInfoAdmin-v1.0.0版本上线啦！</p>
                <ul>
                    <li>这里前端我没有进行修改，还是使用jsp，接口与SSM项目相同，所以前端资源在webapp目录下；需要注意的是：springboot官方强调不推荐使用jsp，可以使用freemaker、thymeleaf代替，这些模板文件应该放在resources的static和templates目录下。</li>
                    <li>layui和springboot整合时会出现layui一些图标无法显示问题，这里通过在pom.xml文件修改maven编译时对layui资源的拦截解决。</li>
                    <li>springmvc拦截器的定义有原来的spring-mvc.xml中改为在config包下的配置类SpringmvcConfig完成；redis的配置等也同样改成springboot风格即配置类完成。</li>
                    <li>在原先的com.jzy.controller.OtherController中"/error"请求与springboot内部定义的出错请求相同，产生冲突，因而改成"/myError"</li>
                </ul>
                <p><a href="https://github.com/KuroChan1998/Student-Information-Administration-System/tree/v1.0.0-Sb_M"
                   target="_blank" class="layui-btn">立即下载</a></p>
            </div>
        </div>
    </li>
    <%--<li class="layui-timeline-item">--%>
        <%--<i class="layui-icon layui-timeline-axis"></i>--%>
        <%--<div class="layui-timeline-content layui-text">--%>
            <%--<h3 class="layui-timeline-title">v1.1.0-2019.7.27</h3>--%>
            <%--<p><em>大学生信息管理系统</em> StuInfoAdmin-v1.1.0版本上线啦！</p>--%>
            <%--<ul>--%>
                <%--<li>优化了数据表结构，对原有的表的部分字段进行了修改，并增加了title和grade两个表</li>--%>
                <%--<li>优化了sql语句效率</li>--%>
                <%--<li>优化了前端查询界面及查询方式，使其更加全面，对用户友好</li>--%>
                <%--<li>更新了登录界面记住密码的cookie设置</li>--%>
                <%--<li>更新了邮箱验证码服务，增加了验证码有效时间</li>--%>
                <%--<li>优化了源代码结构，增强了规范性和可拓展性</li>--%>
            <%--</ul>--%>
            <%--<p><a href="https://pan.baidu.com/s/1yHjrk7gAycHRFapU_Waf4g"--%>
                  <%--target="_blank" class="layui-btn">立即下载</a></p>--%>
        <%--</div>--%>
    <%--</li>--%>
    <%--<li class="layui-timeline-item">--%>
        <%--<i class="layui-icon layui-timeline-axis"></i>--%>
        <%--<div class="layui-timeline-content layui-text">--%>
            <%--<h3 class="layui-timeline-title">v1.0.0-2019.9.12</h3>--%>
            <%--<p>--%>
                <%--<em>大学生信息管理系统</em>StuInfoAdmin-v1.0.0的一切准备工作似乎都已到位。发布之弦，一触即发。--%>
                <%--<br>不枉近百个日日夜夜与之为伴。因小而大，因弱而强。--%>
                <%--<br>无论它能走多远，抑或如何支撑？至少我曾倾注全心，无怨无悔 <i class="layui-icon"></i>--%>
                <%--<br><a href="https://pan.baidu.com/s/1piVQnIFdz_BIoszIEzAJwQ"--%>
                  <%--target="_blank" class="layui-btn">立即下载</a>--%>
            <%--</p>--%>
        <%--</div>--%>
    <%--</li>--%>
    <li class="layui-timeline-item">
        <i class="layui-icon layui-anim layui-anim-rotate layui-anim-loop layui-timeline-axis"></i>
        <div class="layui-timeline-content layui-text">
            <div class="layui-timeline-title">孵化中...</div>
        </div>
    </li>
</ul>

<script src="${ctx}/static/plugins/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '${ctx}/static/plugins/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use('index');
</script>

</body>
</html>
