<%--
  Created by IntelliJ IDEA.
  User: 13681864361
  Date: 2019/4/6
  Time: 17:52
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
    <title>登入 - StuInfoAdmin</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${ctx}/static/plugins/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${ctx}/static/plugins/layuiadmin/style/admin.css" media="all">
    <link rel="stylesheet" href="${ctx}/static/plugins/layuiadmin/style/login.css" media="all">
</head>
<body>

<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">

    <div class="layadmin-user-login-main">
        <div class="layadmin-user-login-box layadmin-user-login-header">
            <h2>StuInfoAdmin</h2>
            <p>大学生学籍信息管理系统</p>
        </div>
        <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-username"
                       for="LAY-user-login-username"></label>
                <input type="text" name="username" id="LAY-user-login-username" lay-verify="required"
                       placeholder="用户名(学号或工号)" class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-password"
                       for="LAY-user-login-password"></label>
                <input type="password" name="password" id="LAY-user-login-password" lay-verify="required"
                       placeholder="密码" class="layui-input">
            </div>
            <div class="layui-form-item">
                <div class="layui-row">
                    <div class="layui-col-xs7">
                        <label class="layadmin-user-login-icon layui-icon layui-icon-vercode"
                               for="LAY-user-login-vercode"></label>
                        <input type="text" name="vercode" id="LAY-user-login-vercode" lay-verify="required"
                               placeholder="图形验证码" class="layui-input">
                    </div>
                    <div class="layui-col-xs5">
                        <div id="v_container" style="margin-left: 10px;width: 130px;height: 36px;">
                            <%--<img src="https://www.oschina.net/action/user/captcha" class="layadmin-user-login-codeimg" id="LAY-user-get-vercode">--%>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-form-item" style="margin-bottom: 20px;">
                <input type="checkbox" name="remember" id="remember" lay-skin="primary" title="记住密码">
                <a href="${ctx}/forget" class="layadmin-user-jump-change layadmin-link"
                   style="margin-top: 7px;">忘记密码？</a>
            </div>
            <div class="layui-form-item">
                <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-login-submit" id="my_button">登
                    入
                </button>
            </div>
            <div class="layui-trans layui-form-item layadmin-user-login-other">
                <label>社交账号登入</label>
                <a href="javascript:;"><i class="layui-icon layui-icon-login-qq"></i></a>
                <a href="javascript:;"><i class="layui-icon layui-icon-login-wechat"></i></a>
                <a href="javascript:;"><i class="layui-icon layui-icon-login-weibo"></i></a>

                <a href="${ctx}/reg" class="layadmin-user-jump-change layadmin-link">注册帐号</a>
            </div>
        </div>
    </div>

    <div class="layui-trans layadmin-user-login-footer">
        <p>© 2019 <a href="http://kurochan.cn/" target="_blank">kurochan.cn</a></p>
    </div>
</div>

<script src="${ctx}/static/custom/js/external/jquery-3.3.1.min.js"></script>
<script src="${ctx}/static/custom/js/external/gVerify.js"></script>
<%--图形验证码的放置与验证--%>
<script src="${ctx}/static/custom/js/getCookie.js"></script>
<script src="${ctx}/static/plugins/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '${ctx}/static/plugins/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'user'], function () {
        var $ = layui.$
            , setter = layui.setter
            , admin = layui.admin
            , form = layui.form
            , router = layui.router()
            , search = router.search;


        //查找cookie填充到表单
        var userId_cookie = getCookie('loginAccount');
        var userPassword_cookie = getCookie('loginPassword');
        var remember_cookie = getCookie("remember");
        // console.log(userId_cookie);
        // console.log(userPassword_cookie);
        $("#LAY-user-login-username").val(userId_cookie);
        $("#LAY-user-login-password").val(userPassword_cookie);
        if (remember_cookie == 'true'){$("#remember").prop("checked",true);}



        //初始化图形验证码
        var verifyCode = new GVerify("v_container");


        form.render();

        //提交
        form.on('submit(LAY-user-login-submit)', function (obj) {
            var field = obj.field;
            console.log(field.remember);//测试是否获取到表单数据，调试模式下在页面控制台查看

            var res = verifyCode.validate(field.vercode);
            // 图形码验证
            if (!res) {
                return layer.msg("验证码错误！");
            }


            //请求登入接口
            $.ajax({
                type: 'post'
                , url: '${ctx}/loginTest' //实际使用请改成服务端真实接口
                , data: {'userName': field.username, 'userPassword': field.password, 'remember': field.remember}
                , success: function (res) {
                    // //请求成功后，写入 access_token
                    // layui.data(setter.tableName, {
                    //     key: setter.request.tokenName
                    //     ,value: res.data.access_token
                    // });

                    if (res.data == "resultSuccess") {
                        //登入成功的提示与跳转
                        layer.msg('登入成功', {
                            offset: '15px'
                            , icon: 1
                            , time: 1000
                        }, function () {
                            location.href = '${ctx}/index'; //后台主页
                        });
                    } else if (res.data == "resultFail") {
                        verifyCode = new GVerify("v_container");
                        layer.msg('用户名或密码错误', {
                            offset: '15px'
                            , icon: 2
                            , time: 1000
                        });
                    } else {
                        layer.msg('未知错误', {
                            offset: '15px'
                            , icon: 2
                            , time: 1000
                        });
                    }
                }
            });

        });


        // //实际使用时记得删除该代码
        // layer.msg('为了方便演示，用户名密码可随意输入', {
        //     offset: '15px'
        //     ,icon: 1
        // });

    });
</script>
</body>
</html>

