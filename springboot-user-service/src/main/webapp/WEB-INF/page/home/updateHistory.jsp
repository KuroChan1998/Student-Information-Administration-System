D<%--
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
            <h3 class="layui-timeline-title">v1.0.0-2019.10.16</h3>
            <div class="layui-timeline-title">
                <p><em>大学生信息管理系统</em> StuInfoAdmin-v1.0.0版本上线啦！该版本功能上与主支SSM项目的v1.3.0版本对应：</p>
                <ul>
                    <li>项目根据不同的功能模块分为学生、教师、班级、专业、学院、年级、职称、用户8个服务模块</li>
                    <li>学生、教师、班级、专业、学院、年级、职称都是作为对外暴露的7个服务模块（分别暴露服务接口StudentService, TeacherService, ClassService, MajorService, CollegeService, GradeService, TitleService），基于dubbo暴露于不同的端口下，他们既是提供者也是消费者；用户服务模块，其中OtherService作为提供验证码发送等其他业务也隶属于用户服务的子服务，因此归于用户服务范畴，对外只暴露UserService接口。用户服务同时也是一个web项目，启动该服务的同时会启动springboot内置tomcat。</li>
                    <li>common-api模块作为所有服务模块的公共api，提供entity对象、dto对象、工具类、自定义异常类等等。</li>
                    <li>该项目为多模块项目，根（父）模块中没有代码；common-api是其子项目，其他服务子模块StudentService等都引用common-api，因此也是根（父）模块的子模块。</li>
                </ul>
                <p><a href="https://github.com/KuroChan1998/Student-Information-Administration-System/tree/v1.0.0-Sb_M_D_Z"
                   target="_blank" class="layui-btn">立即下载</a></p>
            </div>
        </div>
    </li>
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
