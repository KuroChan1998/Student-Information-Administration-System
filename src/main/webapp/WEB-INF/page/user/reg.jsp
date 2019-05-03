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
    <title>注册 - StuInfoAdmin</title>
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
                <label class="layadmin-user-login-icon layui-icon layui-icon-user" for="LAY-user-login-id"></label>
                <input type="text" name="id" id="LAY-user-login-id" lay-verify="id" placeholder="用户名(学号或工号)"
                       class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-user" for="LAY-user-login-identity"></label>
                    <select name="identity" lay-verify="myidentity" id="LAY-user-login-identity">
                        <option value="">请选择你的身份</option>
                        <option value="学生">学生</option>
                        <option value="教师">教师</option>
                        <option value="管理员" disabled>管理员</option>
                    </select>
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-password"
                       for="LAY-user-login-password"></label>
                <input type="password" name="password" id="LAY-user-login-password" lay-verify="pass" placeholder="密码"
                       class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-password"
                       for="LAY-user-login-repass"></label>
                <input type="password" name="repass" id="LAY-user-login-repass" lay-verify="required" placeholder="确认密码"
                       class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-username"
                       for="LAY-user-login-nickname"></label>
                <input type="text" name="nickname" id="LAY-user-login-nickname" lay-verify="nickname" placeholder="昵称"
                       class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-cellphone"
                       for="LAY-user-login-cellphone"></label>
                <input type="text" name="cellphone" id="LAY-user-login-cellphone" lay-verify="phone" placeholder="手机"
                       class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-list" for="LAY-user-login-email"></label>
                <input type="text" name="email" id="LAY-user-login-email" lay-verify="email" placeholder="邮箱"
                       class="layui-input">
            </div>
            <%--<div class="layui-form-item">--%>
                <%--<div class="layui-row">--%>
                    <%--<div class="layui-col-xs7">--%>
                        <%--<label class="layadmin-user-login-icon layui-icon layui-icon-vercode"--%>
                               <%--for="LAY-user-login-vercode"></label>--%>
                        <%--<input type="text" name="vercode" id="LAY-user-login-vercode" lay-verify="required"--%>
                               <%--placeholder="验证码" class="layui-input">--%>
                    <%--</div>--%>
                    <%--<div class="layui-col-xs5">--%>
                        <%--<div style="margin-left: 10px;">--%>
                            <%--<button type="button" class="layui-btn layui-btn-primary layui-btn-fluid"--%>
                                    <%--id="LAY-user-getsmscode">获取验证码--%>
                            <%--</button>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <div class="layui-form-item">
                <input type="checkbox" name="agreement" lay-skin="primary" title="同意用户协议" checked>
            </div>
            <div class="layui-form-item">
                <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-reg-submit">注 册</button>
            </div>
            <div class="layui-trans layui-form-item layadmin-user-login-other">
                <label>社交账号注册</label>
                <a href="javascript:;"><i class="layui-icon layui-icon-login-qq"></i></a>
                <a href="javascript:;"><i class="layui-icon layui-icon-login-wechat"></i></a>
                <a href="javascript:;"><i class="layui-icon layui-icon-login-weibo"></i></a>

                <a href="${ctx}/login" class="layadmin-user-jump-change layadmin-link layui-hide-xs">用已有帐号登入</a>
                <a href="${ctx}/login"
                   class="layadmin-user-jump-change layadmin-link layui-hide-sm layui-show-xs-inline-block">登入</a>
            </div>
        </div>
    </div>

    <div class="layui-trans layadmin-user-login-footer">

        <p>© 2018 <a href="http://kurochan.cn/" target="_blank">kurochan.cn</a></p>
        <%--<p>--%>
        <%--<span><a href="http://www.layui.com/admin/#get" target="_blank">获取授权</a></span>--%>
        <%--<span><a href="http://www.layui.com/admin/pro/" target="_blank">在线演示</a></span>--%>
        <%--<span><a href="http://www.layui.com/admin/" target="_blank">前往官网</a></span>--%>
        <%--</p>--%>
    </div>

</div>

<script src="${ctx}/static/plugins/layuiadmin/layui/layui.js"></script>
<script src="${ctx}/static/custom/js/myLayVerify.js"></script>
<script src="${ctx}/static/custom/js/verifyCode.js"></script>
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
            , router = layui.router();

        /*****************************************************/
        //用户名栏失去焦点提示
        $(function () {
            $("#LAY-user-login-identity").blur(function () {
                //小tips

                layer.msg('请务必以自己真实的学号或工号注册，不然用该账号快捷查询自己的学籍信息模块会不起作用', {
                    offset: '100px',
                    icon: 2,
                    time: 3000
                });
            });
        });
        /*****************************************************/

        form.render();

        //提交
        form.on('submit(LAY-user-reg-submit)', function (obj) {
            var field = obj.field;


            //确认密码
            if (field.password !== field.repass) {
                return layer.msg('两次密码输入不一致');
            }


            //是否同意用户协议
            if (!field.agreement) {
                return layer.msg('你必须同意用户协议才能注册');
            }

            console.log(idWithIdentityValid(field.id,field.identity))
            if (!idWithIdentityValid(field.id,field.identity)) {
                return layer.msg('注意：若以学生注册用户名（学号）第一位必须为5; 若以教师注册用户名（工号）第一位必须为1', {
                    offset: '100px',
                    icon: 2,
                    time: 3000
                });
            }


            //请求接口
            admin.req({
                url: '${ctx}/regTest' //实际使用请改成服务端真实接口
                ,
                data: {
                    "userId": field.id,
                    "userNickname": field.nickname,
                    "userPassword": field.password,
                    "userIdentity": field.identity,
                    "userEmail":field.email,
                    "userPhone":field.cellphone
                }
                ,
                success: function (res) {
                    if (res.data == "regSuccess") {
                        layer.msg('注册成功', {
                            offset: '15px'
                            , icon: 1
                            , time: 1000
                        }, function () {
                            location.href = '${ctx}/login'; //跳转到登入页
                        });
                    } else if (res.data == "regIdExist"){
                        layer.msg('用户名已被注册', {
                            icon: 5,
                            anim: 6
                        });
                    } else if (res.data == "regNicknameExist"){
                        layer.msg('昵称已被注册', {
                            icon: 5,
                            anim: 6
                        });
                    } else if (res.data == "regEmailExist"){
                        layer.msg('邮箱已被注册', {
                            icon: 5,
                            anim: 6
                        });
                    } else {
                        layer.msg('未知错误', {
                            icon: 5,
                            anim: 6
                        });
                    }
                }
            });

            return false;
        });
    });
</script>
</body>
</html>