<%--
  Created by IntelliJ IDEA.
  User: 13681864361
  Date: 2019/4/9
  Time: 16:19
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
    <title>设置我的资料</title>
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
                <div class="layui-card-header">设置我的资料</div>
                <div class="layui-card-body" pad15>

                    <div class="layui-form" lay-filter="">
                        <div class="layui-form-item">
                            <label class="layui-form-label">我的角色</label>
                            <div class="layui-input-inline">
                                <select name="role" lay-verify="myidentity">
                                    <option value="${userInfo.userIdentity}" selected>${userInfo.userIdentity}</option>
                                    <option value="${elseIdentity.get(0)}" disabled>${elseIdentity.get(0)}</option>
                                    <option value="${elseIdentity.get(0)}" disabled>${elseIdentity.get(1)}</option>
                                </select>
                            </div>
                            <div class="layui-form-mid layui-word-aux">当前身份不可更改为其它身份</div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">用户名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="username" value="${userInfo.userId}" readonly
                                       class="layui-input" lay-verify="id">
                            </div>
                            <div class="layui-form-mid layui-word-aux">此项不可修改。(学号或工号）一般用于后台登入名</div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">昵称</label>
                            <div class="layui-input-inline">
                                <input type="text" name="nickname" value="${userInfo.userNickname}"
                                       lay-verify="nickname" autocomplete="off" placeholder="请输入昵称" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">修改头像</label>
                            <div class="layui-input-inline uploadHeadImage">
                                <div class="layui-upload-drag" id="headImg">
                                    <i class="layui-icon"></i>
                                    <p>点击上传图片，或将图片拖拽到此处</p>
                                </div>
                            </div>
                            <div class="layui-input-inline">
                                <div class="layui-upload-list">
                                    <img class="layui-upload-img headImage" src="${userInfo.userIcon}" id="demo1"
                                         height="110">
                                    <p id="demoText">当前头像↑</p>
                                </div>
                            </div>
                            <div><input type="text" id="hiddenIconUrl" style="display: none" name="hiddenIconUrl"
                                        value="${userInfo.userIcon}"/></div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">手机</label>
                            <div class="layui-input-inline">
                                <input type="text" name="cellphone" value="${userInfo.userPhone}" lay-verify="phone"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">验证码</label>
                            <div class="layui-input-inline">
                                <div class="layui-row">
                                    <input type="text" name="emailcode" lay-verify="required" placeholder="请输入邮箱验证码"
                                           id="LAY-user-login-smscode" class="layui-input">
                                    <%--<input type="text" name="vercode" id="LAY-user-login-smscode" lay-verify="required" placeholder="短信验证码" class="layui-input">--%>
                                </div>
                            </div>
                            <div><input type="text" style="display:none" id="hiddenUserEmail" name="email"
                                        value="${userInfo.userEmail}"/></div>
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
                                <button class="layui-btn" lay-submit lay-filter="setmyinfo">确认修改</button>
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
    }).use(['index', 'user', 'upload', 'form', 'layer', 'element'], function () {
        var $ = layui.$
            , element = layui.element
            , setter = layui.setter
            , admin = layui.admin
            , form = layui.form
            , router = layui.router()
            , search = router.search
            , upload = layui.upload;


        /***************************************************/
        //拖拽上传
        var uploadInst = upload.render({
            elem: '#headImg'
            , url: '/upload/userIcon'
            , size: 2048 //KB
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            }
            , done: function (res) {
                //如果上传失败
                if (res.code > 0) {
                    return layer.msg('上传失败');
                }
                //上传成功
                //打印后台传回的地址: 把地址放入一个隐藏的input中, 和表单一起提交到后台,
                // console.log(res.data.src);
                $("#hiddenIconUrl").val(res.data.src);
                var demoText = $('#demoText');
                demoText.html('<span style="color: #8f8f8f;">上传成功!!!请点击确认修改即可!</span>');
            }
            , error: function () {
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
            }
        });
        element.init();
        /*************************************************************/

        /*************************************************************/
        var InterValObj; //timer变量，控制时间
        var count = 60; //间隔函数，1秒执行
        var curCount;//当前剩余秒数
        //向邮箱发送验证码
        $('#send-email-code').click(function () {
            //校验邮箱
            var email = $("#hiddenUserEmail").val();
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
            } else {
                curCount--;
                $("#send-email-code").html(curCount + "秒后重获");
            }
        }

        /*************************************************************/


        form.render();

        //提交
        form.on('submit(setmyinfo)', function (obj) {
            var field = obj.field;

            // console.log(field);
            //请求接口
            admin.req({
                url: '${ctx}/emailVerifyCodeTest' //实际使用请改成服务端真实接口
                , data: {"emailVerifyCode": field.emailcode, "userEmail": field.email}
                , success: function (res) {
                    if (res.data == "verifyCodeCorrect") {
                        layer.msg('修改已完成，请刷新页面', {
                            icon: 1
                            , time: 1000
                        }, function () {

                            /*****************************************************/
                            //验证码正确后重置用户基本资料
                            var allData = {
                                "userId": field.username,
                                "userNickname": field.nickname,
                                "userIdentity": field.role,
                                "userIcon": field.hiddenIconUrl,
                                "userPhone": field.cellphone
                            };
                            //请求接口
                            admin.req({
                                type: 'post',
                                contentType: 'application/json;charset=utf-8',
                                data: JSON.stringify(allData),
                                url: '${ctx}/userInfoReset' //实际使用请改成服务端真实接口
                                , success: function (res2) {
                                    location.href = "${ctx}/info"

                                }
                            });
                            /*****************************************************/
                        });
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
