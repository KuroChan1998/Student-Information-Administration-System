<%--
  Created by IntelliJ IDEA.
  User: 13681864361
  Date: 2019/5/2
  Time: 10:49
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
    <title>layuiAdmin 文章管理 iframe 框</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${ctx}/static/plugins/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${ctx}/static/plugins/layuiadmin/style/admin.css" media="all">

</head>
<body>

<div class="layui-form" lay-filter="layuiadmin-app-form-list" id="layuiadmin-app-form-list" style="padding: 20px 30px 0 0;">
    <div class="layui-form-item">
        <label class="layui-form-label">专业编号</label>
        <div class="layui-input-inline">
            <input type="text" name="id"  value="" class="layui-input" placeholder="建议为：${majorIdRec}" lay-verify="CMCid">
        </div>
        <div class="layui-form-mid layui-word-aux">此项添加时确定后不可修改，为数据库中编号</div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">专业名称</label>
        <div class="layui-input-inline">
            <input type="text" name="name" value="" class="layui-input" lay-verify="name">
        </div>
    </div>
    <%--<div class="layui-form-item">--%>
    <%--<label class="layui-form-label">性别</label>--%>
    <%--<div class="layui-input-inline">--%>
    <%--<input type="text" name="sex" value="" class="layui-input">--%>
    <%--</div>--%>
    <%--</div>--%>
    <div class="layui-form-item">
        <label class="layui-form-label">专业人数</label>
        <div class="layui-input-inline">
            <input type="text" name="stuNum" value="0" class="layui-input" lay-verify="required" disabled>
        </div>
        <div class="layui-form-mid layui-word-aux">此项初值为0，且不可修改，因为参与计算</div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">专业班级数</label>
        <div class="layui-input-inline">
            <input type="text" name="classNum" value="0" class="layui-input" lay-verify="required" disabled>
        </div>
        <div class="layui-form-mid layui-word-aux">此项初值为0，且不可修改，因为参与计算</div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">学院</label>
        <div class="layui-input-inline">
            <select id="college" name="college" lay-search lay-filter="college" lay-verify="required">
                <option value="">请选择学院</option>
                <%--<option value="电子信息与电气工程学院">电子信息与电气工程学院</option>--%>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">专业负责人工号</label>
        <div class="layui-input-inline">
            <input type="text" name="teaId" id="teaId" value="" class="layui-input">
        </div>
        <button class="layui-btn layuiadmin-btn-comm" data-type="batchdel" id="query-tea-info">查看该教师信息</button>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-inline">
            <textarea name="remark" style="width: 400px; height: 150px;" class="layui-textarea" lay-verify="remark"></textarea>
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <input type="button" lay-submit lay-filter="layuiadmin-app-form-submit" id="layuiadmin-app-form-submit" value="确认添加">
        <input type="button" lay-submit lay-filter="layuiadmin-app-form-edit" id="layuiadmin-app-form-edit" value="确认编辑">
    </div>
</div>

<script src="${ctx}/static/plugins/layuiadmin/layui/layui.js"></script>
<script src="${ctx}/static/custom/js/myLayVerify.js"></script>
<script>
    layui.config({
        base: '${ctx}/static/plugins/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'form'], function(){
        var $ = layui.$
            ,form = layui.form;

        // form.verify({
        //     //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
        //     age: function(value) {
        //         if (value.length > 3) {
        //             return 'sb';
        //         }
        //     }
        // });


        //从数据库异步获取学院数据填充到学院select框中
        $.ajax({
            async:false,
            type : "get",
            url : "${ctx}/college/getCollegeName",
            success : function(data) {
                for (var i = 0; i < data.length; i++) {
                    var json = data[i];
                    $("#college").append('<option value="'+json.collegeName+'">'+json.collegeName+'</option>');
                }
                form.render('select');
            }
        });



        $("#query-tea-info").click(function () {
            layer.open({
                type: 2
                , title: '查看教师'
                , content: '/teacher/teaInfo?teaId=' + $("#teaId").val()
                , maxmin: true
                , area: ['550px', '550px']
                , yes: function (index, layero) {
                    layer.close(index); //关闭弹层
                }
            });
        });
    })
</script>
</body>
</html>

