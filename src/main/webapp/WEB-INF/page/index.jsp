<%--
  Created by IntelliJ IDEA.
  User: 13681864361
  Date: 2019/4/6
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>StuInfoAdmin - 大学生学籍信息管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="icon" href="${ctx}/static/custom/img/others/favicon-20190714032159399.ico" type="image/x-icon">
    <link rel="stylesheet" href="${ctx}/static/plugins/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${ctx}/static/plugins/layuiadmin/style/admin.css" media="all">

    <script>
        /^http(s*):\/\//.test(location.href) || alert('请先部署到 localhost 下再访问');
    </script>
</head>
<body class="layui-layout-body">

<div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <!-- 头部区域 -->
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item layadmin-flexible" lay-unselect>
                    <a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
                        <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
                    </a>
                </li>
                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;" layadmin-event="refresh" title="刷新">
                        <i class="layui-icon layui-icon-refresh-3"></i>
                    </a>
                </li>
            </ul>
            <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="theme">
                        <i class="layui-icon layui-icon-theme"></i>
                    </a>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="fullscreen">
                        <i class="layui-icon layui-icon-screen-full"></i>
                    </a>
                </li>
                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;">
                        <img src="${ctx}${userInfo.userIcon}" class="layui-nav-img">
                        ${userInfo.userName}
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a lay-href="${ctx}/user/info">基本资料</a></dd>
                        <dd><a lay-href="${ctx}/user/password">修改密码</a></dd>
                        <dd><a lay-href="${ctx}/user/email">修改绑定邮箱</a></dd>
                        <hr>
                        <dd style="text-align: center;"><a href="${ctx}/logout">退出</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="about"><i
                            class="layui-icon layui-icon-more-vertical"></i></a>
                </li>
            </ul>
        </div>

        <!-- 侧边菜单 -->
        <div class="layui-side layui-side-menu">
            <div class="layui-side-scroll">
                <div class="layui-logo" lay-href="${ctx}/console">
                    <span>StuInfoAdmin</span>
                </div>

                <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu"
                    lay-filter="layadmin-system-side-menu">
                    <li data-name="home" class="layui-nav-item layui-nav-itemed">
                        <a href="javascript:;" lay-tips="主页" lay-direction="2">
                            <i class="layui-icon layui-icon-home"></i>
                            <cite>主页</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd data-name="console" class="layui-this">
                                <a lay-href="${ctx}/console">控制台</a>
                            </dd>
                            <%--<dd data-name="console">--%>
                            <%--<a href="${ctx}/static/plugins/myPersonalProject/index.html" target="_blank">酷乐的个人主页</a>--%>
                            <%--</dd>--%>
                            <%--<dd data-name="console">--%>
                            <%--<a lay-href="home/homepage2.html">主页二</a>--%>
                            <%--</dd>--%>
                        </dl>
                    </li>
                    <li data-name="app" class="layui-nav-item">
                        <a href="javascript:;" lay-tips="应用" lay-direction="2">
                            <i class="layui-icon layui-icon-app"></i>
                            <cite>应用</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd data-name="content">
                                <a href="javascript:;">信息查询</a>
                                <dl class="layui-nav-child">
                                    <dd data-name="list"><a lay-href="${ctx}/student/query">学生信息查询</a></dd>
                                    <dd data-name="tags"><a lay-href="${ctx}/teacher/query">教师信息查询</a></dd>
                                    <dd data-name="comment"><a lay-href="${ctx}/class/query">班级信息查询</a></dd>
                                    <dd data-name="comment"><a lay-href="${ctx}/major/query">专业&学院信息查询</a></dd>
                                </dl>
                            </dd>
                            <dd data-name="forum">
                                <a href="javascript:;">信息修改</a>
                                <dl class="layui-nav-child">
                                    <dd data-name="list"><a lay-href="${ctx}/student/modify">学生信息修改</a></dd>
                                    <dd data-name="tags"><a lay-href="${ctx}/teacher/modify">教师信息修改</a></dd>
                                    <dd data-name="comment"><a lay-href="${ctx}/class/modify">班级信息修改</a></dd>
                                    <dd data-name="comment"><a lay-href="${ctx}/major/modify">专业&学院信息修改</a></dd>
                                </dl>
                            </dd>
                        </dl>
                    </li>
                    <li data-name="senior" class="layui-nav-item">
                        <a href="javascript:;" lay-tips="高级" lay-direction="2">
                            <i class="layui-icon layui-icon-senior"></i>
                            <cite>高级</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd data-name="echarts">
                                <a href="javascript:;">学生高级查询</a>
                                <dl class="layui-nav-child">
                                    <dd><a lay-href="${ctx}/senior/stuSex">学生男女比</a></dd>
                                    <dd><a lay-href="${ctx}/senior/stuNum">学生人数比</a></dd>
                                </dl>
                            </dd>
                            <dd data-name="echarts">
                                <a href="javascript:;">教师高级查询</a>
                                <dl class="layui-nav-child">
                                    <dd><a lay-href="${ctx}/senior/teaPower">师资力量</a></dd>
                                </dl>
                            </dd>
                        </dl>
                    </li>
                    <li data-name="set" class="layui-nav-item">
                        <a href="javascript:;" lay-tips="设置" lay-direction="2">
                            <i class="layui-icon layui-icon-set"></i>
                            <cite>设置</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd class="layui-nav-itemed">
                                <a href="javascript:;">我的设置</a>
                                <dl class="layui-nav-child">
                                    <dd><a lay-href="${ctx}/user/info">基本资料</a></dd>
                                    <dd><a lay-href="${ctx}/user/password">修改密码</a></dd>
                                    <dd><a lay-href="${ctx}/user/email">修改绑定邮箱</a></dd>
                                </dl>
                            </dd>
                        </dl>
                    </li>
                </ul>
            </div>
        </div>

        <!-- 页面标签 -->
        <div class="layadmin-pagetabs" id="LAY_app_tabs">
            <div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-down">
                <ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
                    <li class="layui-nav-item" lay-unselect>
                        <a href="javascript:;"></a>
                        <dl class="layui-nav-child layui-anim-fadein">
                            <dd layadmin-event="closeThisTabs"><a href="javascript:;">关闭当前标签页</a></dd>
                            <dd layadmin-event="closeOtherTabs"><a href="javascript:;">关闭其它标签页</a></dd>
                            <dd layadmin-event="closeAllTabs"><a href="javascript:;">关闭全部标签页</a></dd>
                        </dl>
                    </li>
                </ul>
            </div>
            <div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
                <ul class="layui-tab-title" id="LAY_app_tabsheader">
                    <li lay-id="home/console.html" lay-attr="home/console.html" class="layui-this"><i
                            class="layui-icon layui-icon-home"></i></li>
                </ul>
            </div>
        </div>


        <!-- 主体内容 -->
        <div class="layui-body" id="LAY_app_body">
            <div class="layadmin-tabsbody-item layui-show">
                <iframe src="${ctx}/console" frameborder="0" class="layadmin-iframe"></iframe>
            </div>
        </div>

        <!-- 辅助元素，一般用于移动设备下遮罩 -->
        <div class="layadmin-body-shade" layadmin-event="shade"></div>
    </div>
</div>

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






