<%--
  Created by IntelliJ IDEA.
  User: 13681864361
  Date: 2019/4/16
  Time: 13:16
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
    <title></title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${ctx}/static/plugins/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${ctx}/static/plugins/layuiadmin/style/admin.css" media="all">
</head>
<body>

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item" id="form">
                <div class="layui-inline">
                    <label class="layui-form-label">工号</label>
                    <div class="layui-input-inline">
                        <input type="text" name="teaNum" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">姓名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="teaName" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">性别</label>
                    <div class="layui-input-inline">
                        <select name="teaSex">
                            <option value="">请选择性别</option>
                            <option value="男">男</option>
                            <option value="女">女</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">职称</label>
                    <div class="layui-input-inline">
                        <select name="teaTitle" id="teaTitle">
                            <option value="">请选择职称</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">学院</label>
                    <div class="layui-input-inline">
                        <select id="teaCollege" name="teaCollege" lay-filter="teaCollege" lay-search>
                            <option value="">请输入或选择学院</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">专业</label>
                    <div class="layui-input-inline">
                        <select id="teaMajor" name="teaMajor" lay-filter="teaMajor" lay-search>
                            <option value="">请输入或选择专业</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">筛选排序</label>
                    <div class="layui-input-inline">
                        <select id="condition1" name="condition1" lay-filter="condition1">
                            <option value="">请选择要排序的类别</option>
                            <option value="teaNum">按工号排序</option>
                            <option value="teaBirthday">按年龄排序</option>
                            <option value="teaTitle">按职称排序</option>
                        </select>
                    </div>
                    <div class="layui-input-inline">
                        <select id="condition2" name="condition2" lay-filter="condition2">
                            <option value="">请选择要排序方式</option>
                            <option value="asc">从低到高</option>
                            <option value="desc">从高到低</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn layuiadmin-btn-comm" data-type="reload" lay-submit
                            lay-filter="LAY-app-contcomm-search">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                    </button>
                    <button class="layui-btn layuiadmin-btn-comm" data-type="reload"
                            id="clear">
                        清空
                    </button>
                </div>
            </div>
        </div>
        <div class="layui-card-body">
            <div style="padding-bottom: 10px;">
                <button class="layui-btn layuiadmin-btn-comm" data-type="batchdel" id="query-my-info">查询我的个人信息</button>
                <button class="layui-btn layuiadmin-btn-comm" data-type="batchdel" style="background-color: #FFB800"
                        id="query-all-info">查询所有信息
                </button>
            </div>

            <table id="teaInfoQuery" lay-filter="LAY-app-content-comm"></table>

        </div>
    </div>
</div>

<script src="${ctx}/static/plugins/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '${ctx}/static/plugins/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'contlist', 'table', 'laypage'], function () {
        var $ = layui.$
            , form = layui.form
            , table = layui.table
            , laypage = layui.laypage;

        //从数据库异步获取职称数据填充到职称select框中
        $.ajax({
            type: "get",
            url: "${ctx}/title/getTitleName",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var json = data[i];
                    var str = "";
                    str += '<option value="' + json.titleName + '">' + json.titleName + '</option>';
                    $("#teaTitle").append(str);
                }
                form.render('select');
            }
        });

        //从数据库异步获取学院数据填充到学院select框中
        $.ajax({
            type: "get",
            url: "${ctx}/college/getCollegeName",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var json = data[i];
                    var str = "";
                    str += '<option value="' + json.collegeName + '">' + json.collegeName + '</option>';
                    $("#teaCollege").append(str);
                }
                form.render('select');
            }
        });
        //从数据库异步获取专业数据填充到专业select框中
        $.ajax({
            type: "get",
            data: {collegeName: $(this).attr("lay-value")},
            url: "${ctx}/major/getMajorNameByCollege",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var json = data[i];
                    $("#teaMajor").append('<option value="' + json.majorName + '">' + json.majorName + '</option>');
                }
                form.render('select');
            }
        });

        //联动监听select
        form.on('select(teaCollege)', function (data) {
            //获取部门的ID通过异步查询子集
            $("#teaMajor").empty();
            $("#teaMajor").append('<option value="">请输入或选择专业</option>');
            var college_name = $(this).attr("lay-value");
            $.ajax({
                type: "get",
                data: {collegeName: college_name},
                url: "${ctx}/major/getMajorNameByCollege",
                success: function (data) {
                    for (var i = 0; i < data.length; i++) {
                        var json = data[i];
                        $("#teaMajor").append('<option value="' + json.majorName + '">' + json.majorName + '</option>');
                    }
                    form.render('select');
                }
            });
        });

        //方法级渲染
        table.render({
            elem: '#teaInfoQuery'
            , url: '${ctx}/teacher/showAllTeaInfo' //向后端默认传page和limit
            , cols: [[
                {field: 'teaNum', title: '工号', sort: true, fixed: true}
                , {field: 'teaName', title: '姓名'}
                , {field: 'teaSex', title: '性别', sort: true}
                , {field: 'teaAge', title: '年龄', sort: true}
                , {field: 'teaTitleName', title: '职称', sort: true}
                , {field: 'teaMajorName', title: '专业', sort: true}
                , {field: 'teaCollegeName', title: '学院', sort: true}
                , {field: 'teaRemark', title: '评价', width: 250}
            ]]
            , page: true
            , limit: 10
            , limits: [5, 10, 15, 20]
            , request: {
                pageName: 'pageNum',
                limitName: 'pageSize'  //如不配置，默认为page=1&limit=10
            }
            , done: function (res, curr, count) {
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                console.log(res);

                //得到当前页码
                console.log(curr);

                //得到数据总量
                console.log(count);
            }

        });


        //监听搜索
        form.on('submit(LAY-app-contcomm-search)', function (data) {
            var field = data.field;

            console.log(field);
            //执行重载
            table.reload('teaInfoQuery', {
                url: '${ctx}/teacher/showAllTeaInfo' //向后端默认传page和limit
                , where: { //设定异步数据接口的额外参数，任意设
                    teaNum: field.teaNum
                    , teaName: field.teaName
                    , teaSex: field.teaSex
                    , teaTitleName: field.teaTitle
                    , teaCollegeName: field.teaCollege
                    , teaMajorName: field.teaMajor
                    , condition1: field.condition1
                    , condition2: field.condition2
                }
                , request: {
                    pageName: 'pageNum',
                    limitName: 'pageSize'  //如不配置，默认为page=1&limit=10
                }
                , page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        });

        form.on('select(condition1)', function (data) {
            //获取部门的ID通过异步查询子集
            $("#condition2").val("");
            form.render('select');
        });

        $("#clear").click(function () {
            $("#form input").val("");
            $("#form select").val("");
        });

        $("#query-my-info").click(function () {
            table.reload('teaInfoQuery', {
                url: '${ctx}/teacher/myOwnInfo'
                , request: {
                    pageName: 'pageNum',
                    limitName: 'pageSize'  //如不配置，默认为page=1&limit=10
                }
                , page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        })

        $("#query-all-info").click(function () {
            table.reload('teaInfoQuery', {
                url: '${ctx}/teacher/showAllTeaInfo'
                , request: {
                    pageName: 'pageNum',
                    limitName: 'pageSize'  //如不配置，默认为page=1&limit=10
                }
                , where: { //设定异步数据接口的额外参数，任意设
                    teaNum: ''
                    , teaName: ''
                    , teaSex: ''
                    , teaTitleName: ''
                    , teaCollegeName: ''
                    , teaMajorName: ''
                }
                , page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        })
    });
</script>
</body>
</html>
