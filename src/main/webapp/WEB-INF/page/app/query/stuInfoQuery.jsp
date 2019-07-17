<%--
  Created by IntelliJ IDEA.
  User: 13681864361
  Date: 2019/4/15
  Time: 14:29
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
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">学号</label>
                    <div class="layui-input-inline">
                        <input type="text" name="stuNum" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">姓名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="stuName" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">性别</label>
                    <div class="layui-input-inline">
                        <select name="stuSex">
                            <option value="">请选择性别</option>
                            <option value="男">男</option>
                            <option value="女">女</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">年级</label>
                    <div class="layui-input-inline">
                        <select id="stuGrade" name="stuGrade">
                            <option value="">请选择年级</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">学位</label>
                    <div class="layui-input-inline">
                        <select name="stuDegree">
                            <option value="">请选择学位</option>
                            <option value="本科">本科</option>
                            <option value="硕士">硕士</option>
                            <option value="博士">博士</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">学院</label>
                    <div class="layui-input-inline">
                        <select id="stuCollege" name="stuCollege" lay-filter="stuCollege" lay-search>
                            <option value="">请输入或选择学院</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">专业</label>
                    <div class="layui-input-inline">
                        <select id="stuMajor" name="stuMajor" lay-filter="stuMajor" lay-search>
                            <option value="">请输入或选择专业</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">班级</label>
                    <div class="layui-input-inline">
                        <select id="stuClass" name="stuClass" lay-filter="stuClass" lay-search>
                            <option value="">请输入或选择班级</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn layuiadmin-btn-comm" data-type="reload" lay-submit
                            lay-filter="LAY-app-contcomm-search">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
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
            <table id="stuInfoQuery" lay-filter="LAY-app-content-comm"></table>
            <%--<script type="text/html" id="table-content-com">--%>
            <%--<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>--%>
            <%--<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>--%>
            <%--</script>--%>
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

        //从数据库异步获取年级数据填充到年级select框中
        $.ajax({
            type: "get",
            url: "${ctx}/grade/getGradeName",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var json = data[i];
                    var str = "";
                    str += '<option value="' + json.gradeName + '">' + json.gradeName + '</option>';
                    $("#stuGrade").append(str);
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
                    $("#stuCollege").append(str);
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
                    $("#stuMajor").append('<option value="' + json.majorName + '">' + json.majorName + '</option>');
                }
                form.render('select');
            }
        });
        //从数据库异步获取班级数据填充到班级select框中
        $.ajax({
            type: "get",
            data: {majorName: $(this).attr("lay-value")},
            url: "${ctx}/class/getClassNameByMajor",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var json = data[i];
                    $("#stuClass").append('<option value="' + json.className + '">' + json.className + '</option>');
                }
                form.render('select');
            }
        });

        //联动监听select
        form.on('select(stuCollege)', function (data) {
            //获取部门的ID通过异步查询子集
            $("#stuMajor").empty();
            $("#stuMajor").append('<option value="">请输入或选择专业</option>');
            var college_name = $(this).attr("lay-value");
            $.ajax({
                type: "get",
                data: {collegeName: college_name},
                url: "${ctx}/major/getMajorNameByCollege",
                success: function (data) {
                    for (var i = 0; i < data.length; i++) {
                        var json = data[i];
                        $("#stuMajor").append('<option value="' + json.majorName + '">' + json.majorName + '</option>');
                    }
                    form.render('select');
                }
            });
        });
        form.on('select(stuMajor)', function (data) {
            //获取部门的ID通过异步查询子集
            $("#stuClass").empty();
            $("#stuClass").append('<option value="">请输入或选择班级</option>');
            var major_name = $(this).attr("lay-value");
            $.ajax({
                type: "get",
                data: {majorName: major_name},
                url: "${ctx}/class/getClassNameByMajor",
                success: function (data) {
                    for (var i = 0; i < data.length; i++) {
                        var json = data[i];
                        $("#stuClass").append('<option value="' + json.className + '">' + json.className + '</option>');
                    }
                    form.render('select');
                }
            });
        });


        //方法级渲染
        table.render({
            elem: '#stuInfoQuery'
            , url: '${ctx}/student/showAllStuInfo' //向后端默认传page和limit
            , cols: [[
                {field: 'stuNum', title: '学号', sort: true, fixed: true}
                , {field: 'stuName', title: '姓名'}
                , {field: 'stuSex', title: '性别', sort: true}
                , {field: 'stuAge', title: '年龄',sort: true}
                , {field: 'stuGradeName', title: '年级', sort: true}
                , {field: 'stuDegree', title: '学位', sort: true}
                , {field: 'stuClassName', title: '班级', sort: true}
                , {field: 'stuMajorName', title: '专业', sort: true}
                , {field: 'stuCollegeName', title: '学院', sort: true}
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
                //console.log(res);

                //得到当前页码
                //console.log(curr);

                //得到数据总量
                //console.log(count);
            }

        });


        //监听搜索
        form.on('submit(LAY-app-contcomm-search)', function (data) {
            var field = data.field;
            //执行重载
            table.reload('stuInfoQuery', {
                url: '${ctx}/student/showAllStuInfo' //向后端默认传page和limit
                , where: { //设定异步数据接口的额外参数，任意设
                    stuNum: field.stuNum
                    , stuName: field.stuName
                    , stuSex: field.stuSex
                    , stuGradeName: field.stuGrade
                    , stuDegree: field.stuDegree
                    , stuCollegeName: field.stuCollege
                    , stuMajorName: field.stuMajor
                    , stuClassName: field.stuClass
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

        $("#query-my-info").click(function () {
            table.reload('stuInfoQuery', {
                url: '${ctx}/student/myOwnInfo'
                , request: {
                    pageName: 'pageNum',
                    limitName: 'pageSize'  //如不配置，默认为page=1&limit=10
                }
                , page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        });

        $("#query-all-info").click(function () {
            table.reload('stuInfoQuery', {
                url: '${ctx}/student/showAllStuInfo'
                , request: {
                    pageName: 'pageNum',
                    limitName: 'pageSize'  //如不配置，默认为page=1&limit=10
                }
                , where: { //设定异步数据接口的额外参数，任意设
                    stuNum: ''
                    , stuName: ''
                    , stuSex: ''
                    , stuGradeName: ''
                    , stuDegree: ''
                    , stuCollegeName: ''
                    , stuMajorName: ''
                    , stuClassName: ''
                }
                , page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        });

    });
</script>
</body>
</html>
