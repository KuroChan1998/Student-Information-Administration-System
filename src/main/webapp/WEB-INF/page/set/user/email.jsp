<%--
  Created by IntelliJ IDEA.
  User: 13681864361
  Date: 2019/4/11
  Time: 9:23
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
    <title>设置我的密码</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${ctx}/static/plugins/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${ctx}/static/plugins/layuiadmin/style/admin.css" media="all">
</head>
<body>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">修改密码</div>
                <div class="layui-card-body" pad15>

                    <div class="layui-form" lay-filter="">
                        <div class="layui-form-item">
                            <label class="layui-form-label">当前邮箱</label>
                            <div class="layui-input-inline">
                                <input type="text" name="oldEmail" id="oldEmail" value="${userInfo.userEmail}" readonly
                                       class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">此项为已绑定邮箱，下面验证码发送至此邮箱</div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">新邮箱</label>
                            <div class="layui-input-inline">
                                <input type="text" name="newEmail" id="newEmail"
                                       lay-verify="email" autocomplete="off" placeholder="请输入新邮箱" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">邮箱验证码</label>
                            <div class="layui-input-inline">
                                <div class="layui-row">
                                    <input type="text" name="emailcode" lay-verify="required" placeholder="请输入邮箱验证码"
                                           id="LAY-user-login-smscode" class="layui-input">
                                    <%--<input type="text" name="vercode" id="LAY-user-login-smscode" lay-verify="required" placeholder="短信验证码" class="layui-input">--%>
                                </div>

                            </div>
                            <div class="layui-input-inline">
                                <div style="margin-left: 10px">
                                    <%--<input id="send-email-code" style="width: 120px;height: 39px;text-align: center;background-color: white;border: 1px solid #E2E2E2" name="send-email-code" type="button"   value="获取验证码"  />--%>
                                    <button type="button" class="layui-btn layui-btn-primary layui-btn-fluid"
                                            id="send-email-code" name="send-email-code" style="width: auto">获取验证码
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="setmyEmail">确认修改</button>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<script src="${ctx}/static/plugins/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '${ctx}/static/plugins/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'set'], function () {
        var $ = layui.$
            , setter = layui.setter
            , admin = layui.admin
            , form = layui.form
            , router = layui.router();


        /*************************************************************/
        var InterValObj; //timer变量，控制时间
        var count = 60; //间隔函数，1秒执行
        var curCount;//当前剩余秒数
        //向邮箱发送验证码
        $('#send-email-code').click(function () {
            //校验邮箱
            var email = $("#oldEmail").val();
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
        /*************************************************************/


        form.render();

        //提交
        form.on('submit(setmyEmail)', function (obj) {
            var field = obj.field;

            // console.log(field);

            //请求接口
            admin.req({
                url: '${ctx}/emailVerifyCodeTest' //实际使用请改成服务端真实接口
                , data: {"emailVerifyCode": field.emailcode, "userEmail": field.oldEmail}
                , success: function (res) {
                    if (res.data == "verifyCodeCorrect") {
                        /*****************************************************/
                        //验证码正确后修改绑定邮箱ajax
                        $.ajax({
                            url: '${ctx}/resetEmail' //实际使用请改成服务端真实接口
                            ,
                            data: {
                                "oldEmail": field.oldEmail,
                                "newEmail": field.newEmail
                            }
                            ,
                            success: function (res) {
                                if (res.data == "newEmailRegistered") {
                                    return layer.msg('此新邮箱已被注册', {
                                        icon: 5,
                                        anim: 6
                                    });
                                } else if (res.data == "resetEmailSuccess") {
                                    layer.msg('修改已完成，请刷新页面', {
                                        icon: 1
                                        , time: 1000
                                    }, function () {
                                        location.href = '${ctx}/email';
                                    });
                                } else {
                                    return layer.msg('未知错误', {
                                        icon: 5,
                                        anim: 6
                                    });
                                }

                            }
                        });
                        /*****************************************************/
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
    });
</script>
</body>
</html>
