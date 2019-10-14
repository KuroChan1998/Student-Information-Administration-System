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
    <title>忘记密码 - StuInfoAdmin</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${ctx}/static/plugins/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${ctx}/static/plugins/layuiadmin/style/admin.css" media="all">
    <link rel="stylesheet" href="${ctx}/static/plugins/layuiadmin/style/login.css" media="all">
</head>

<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">
    <div class="layadmin-user-login-main">
        <div class="layadmin-user-login-box layadmin-user-login-header">
            <h2>StuInfoAdmin</h2>
            <p>大学生学籍信息管理系统</p>
        </div>
        <div class="layadmin-user-login-box layadmin-user-login-body layui-form">

            <script type="text/html" template>
                {{# if(layui.router().search.type === 'resetpass'){ }}
                <div class="layui-form-item">
                    <label class="layadmin-user-login-icon layui-icon layui-icon-password"
                           for="LAY-user-login-password"></label>
                    <input type="password" name="password" id="LAY-user-login-password" lay-verify="pass"
                           placeholder="新密码" class="layui-input">
                </div>
                <div class="layui-form-item">
                    <label class="layadmin-user-login-icon layui-icon layui-icon-password"
                           for="LAY-user-login-repass"></label>
                    <input type="password" name="repass" id="LAY-user-login-repass" lay-verify="required"
                           placeholder="确认密码" class="layui-input">
                </div>
                <input name="token" type="hidden" value="${token}"/>
                <div class="layui-form-item">
                    <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-forget-resetpass">重置新密码
                    </button>
                </div>
                {{# } else { }}
                <div class="layui-form-item">
                    <label class="layadmin-user-login-icon layui-icon layui-icon-list"
                           for="LAY-user-login-email"></label>
                    <input type="text" name="email" id="LAY-user-login-email" lay-verify="email" placeholder="请输入注册时的邮箱"
                           class="layui-input">
                </div>
                <div class="layui-form-item">
                    <div class="layui-row">
                        <div class="layui-col-xs7">
                            <label class="layadmin-user-login-icon layui-icon layui-icon-vercode"
                                   for="LAY-user-login-smscode"></label>
                            <input type="text" name="emailcode" lay-verify="required" placeholder="请输入邮箱验证码"
                                   id="LAY-user-login-smscode" class="layui-input">
                            <%--<input type="text" name="vercode" id="LAY-user-login-smscode" lay-verify="required" placeholder="短信验证码" class="layui-input">--%>
                        </div>
                        <div class="layui-col-xs5">
                            <div style="margin-left: 10px;">
                                <%--<input id="send-email-code" style="width: 120px;height: 39px;text-align: center;background-color: white;border: 1px solid #E2E2E2" name="send-email-code" type="button"   value="获取验证码"  />--%>
                                <button type="button" class="layui-btn layui-btn-primary layui-btn-fluid"
                                        id="send-email-code" name="send-email-code">获取验证码
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-forget-submit">找回密码
                    </button>
                </div>
                {{# } }}
            </script>

        </div>
    </div>

    <div class="layui-trans layadmin-user-login-footer">
        <p>© 2019 <a href="http://kurochan1998.github.io/" target="_blank">kurochan.cn</a></p>
    </div>
</div>

<script src="${ctx}/static/custom/js/external/jquery-3.3.1.min.js"></script>
<%--发送邮箱验证码的js脚本--%>
<%--<script src="${ctx}/static/custom/js/external/gVerify.js"></script>--%>
<%--图形验证码的放置与验证--%>
<script src="${ctx}/static/plugins/layuiadmin/layui/layui.js"></script>
<script>
    //全局变量存储验证邮箱值便于重置时与后端交互

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

        // //初始化图形验证码
        // var verifyCode = new GVerify("v_container");

        /*****************************************************/
        //发送邮件验证码模块
        var InterValObj; //timer变量，控制时间
        var count = 60; //间隔函数，1秒执行
        var curCount;//当前剩余秒数
        //向邮箱发送验证码
        $('#send-email-code').click(function () {
            //校验邮箱
            var email = $("#LAY-user-login-email").val();
            if (!email.match(/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/)) {
                return layer.msg('请输入正确的邮箱格式');
            }

            // 设置button效果，开始计时
            curCount = count;
            $("#send-email-code").attr("disabled", true);  //按钮禁止点击
            $("#send-email-code").html(curCount + "秒后重获");
            InterValObj = window.setInterval(SetRemainTime, 1000); // 启动计时器timer处理函数，1秒执行一次

            layer.msg('验证码已发送至你的邮箱，请注意查收', {
                icon: 1
                , shade: 0
            });
            //请求发送验证码
            $.ajax({
                type: "get",
                url: "${ctx}/sendVerifyCodeToEmail",
                async: true,
                data: {"userEmail": email},
                success: function (obj) {
                },
                dataType: "json"
            });
        });

        //timer处理函数
        function SetRemainTime() {
            if (curCount == 0) {//超时重新获取验证码
                window.clearInterval(InterValObj);// 停止计时器
                $("#send-email-code").attr("disabled", false);
                $("#send-email-code").html("重获验证码");
                // document.getElementById("send-email-code").removeAttribute("disabled");//移除禁用状态改为可用
                // document.getElementById("send-email-code").value="重获验证码";
            } else {
                curCount--;
                $("#send-email-code").html(curCount + "秒后重获");
            }
        }

        /*****************************************************/


        form.render();
        //找回密码下一步
        form.on('submit(LAY-user-forget-submit)', function (obj) {
            var field = obj.field;

            //请求接口
            $.ajax({
                url: '${ctx}/emailVerifyCodeTest' //实际使用请改成服务端真实接口
                , data: {"emailVerifyCode": field.emailcode, "userEmail": field.email}
                , success: function (res) {
                    if (res.data == "verifyCodeCorrect") {
                        location.hash = '/type=resetpass';
                        location.reload();
                    } else if (res.data == "emailUnregistered") {
                        layer.msg('该邮箱尚未注册', {
                            icon: 5,
                            anim: 6
                        });
                    } else if (res.data == "verifyCodeWrong") {
                        layer.msg('验证码错误', {
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


        //重置密码
        form.on('submit(LAY-user-forget-resetpass)', function (obj) {
            var field = obj.field;

            //确认密码
            if (field.password !== field.repass) {
                return layer.msg('两次密码输入不一致');
            }

            //请求接口
            $.ajax({
                url: '${ctx}/resetPassword?token=' + field.token //实际使用请改成服务端真实接口
                ,
                type: 'post'
                , data: {"userPassword": field.password}
                , success: function (res) {
                    if (res.data == "resetPasswordSuccess") {
                        layer.msg('密码已成功重置', {
                            offset: '15px'
                            , icon: 1
                            , time: 1000
                        }, function () {
                            location.href = '${ctx}/login'; //跳转到登入页
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