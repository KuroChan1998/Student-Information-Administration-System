<%--
  Created by IntelliJ IDEA.
  User: 13681864361
  Date: 2019/4/17
  Time: 17:00
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
        <div class="layui-tab layui-tab-brief layadmin-latestData">
            <ul class="layui-tab-title">
                <li class="layui-this">专业信息查询</li>
                <li>学院信息查询</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <div class="layui-form layui-card-header layuiadmin-card-header-auto">
                        <div class="layui-form-item">
                            <%--学院和专业联动复选框--%>
                            <div class="layui-inline">
                                <label class="layui-form-label">学院</label>
                                <div class="layui-input-inline">
                                    <select id="majorCollege" name="majorCollege" lay-search lay-filter="college">
                                        <option value="">请选择标签</option>

                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">专业</label>
                                <div class="layui-input-inline">
                                    <select id="major" name="major" lay-search>
                                        <option value="">请选择标签</option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <button class="layui-btn layuiadmin-btn-comm" data-type="reload" lay-submit
                                        lay-filter="LAY-app-contcomm-search-major">
                                    <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div style="padding-bottom: 10px;">
                        <button class="layui-btn layuiadmin-btn-comm" data-type="batchdel" id="query-my-info1">
                            查询我的专业信息
                        </button>
                        <button class="layui-btn layuiadmin-btn-comm" data-type="batchdel"
                                style="background-color: #FFB800" id="query-all-info1">查询所有信息
                        </button>
                    </div>
                    <table id="majorInfoQuery" lay-filter="LAY-app-content-comm-major"></table>
                    <script type="text/html" id="test-table-operate-barDemo2">
                        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="tea_detail1">查看负责人详情</a>
                    </script>
                </div>
                <div class="layui-tab-item">
                    <div class="layui-tab-item layui-show">
                        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
                            <div class="layui-form-item">
                                <div class="layui-inline">
                                    <label class="layui-form-label">学院</label>
                                    <div class="layui-input-inline">
                                        <select id="college" name="college" lay-search lay-filter="college">
                                            <option value="">请选择标签</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <button class="layui-btn layuiadmin-btn-comm" data-type="reload" lay-submit
                                            lay-filter="LAY-app-contcomm-search-college">
                                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div style="padding-bottom: 10px;">
                            <button class="layui-btn layuiadmin-btn-comm" data-type="batchdel" id="query-my-info2">
                                查询我的学院信息
                            </button>
                            <button class="layui-btn layuiadmin-btn-comm" data-type="batchdel"
                                    style="background-color: #FFB800" id="query-all-info2">查询所有信息
                            </button>
                        </div>
                        <table id="collegeInfoQuery" lay-filter="LAY-app-content-comm-college"></table>
                        <script type="text/html" id="test-table-operate-barDemo3">
                            <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="tea_detail2">查看负责人详情</a>
                        </script>
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
    }).use(['index', 'contlist', 'table', 'laypage'], function () {
        var $ = layui.$
            , form = layui.form
            , table = layui.table
            , laypage = layui.laypage;

        //从数据库异步获取学院数据填充到学院select框中
        $.ajax({
            type: "get",
            url: "${ctx}/college/getCollegeName",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var json = data[i];
                    $("#majorCollege").append('<option value="' + json.collegeName + '">' + json.collegeName + '</option>');
                    $("#college").append('<option value="' + json.collegeName + '">' + json.collegeName + '</option>');
                }
                form.render('select');
            }
        });
        //从数据库异步获取专业数据填充到学院select框中
        $.ajax({
            type: "get",
            data: {collegeName: $(this).attr("lay-value")},
            url: "${ctx}/major/getMajorNameByCollege",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var json = data[i];
                    $("#major").append('<option value="' + json.majorName + '">' + json.majorName + '</option>');
                }
                form.render('select');
            }
        });


        //联动监听select
        form.on('select(college)', function (data) {
            //获取部门的ID通过异步查询子集
            $("#major").empty();
            $("#major").append('<option value="">请选择标签</option>');
            var college_name = $(this).attr("lay-value");
            console.log(college_name);
            $.ajax({
                type: "get",
                data: {collegeName: college_name},
                url: "${ctx}/major/getMajorNameByCollege",
                success: function (data) {
                    for (var i = 0; i < data.length; i++) {
                        var json = data[i];
                        $("#major").append('<option value="' + json.majorName + '">' + json.majorName + '</option>');
                    }
                    form.render('select');
                }
            });
        });


        //方法级渲染
        table.render({
            elem: '#majorInfoQuery'
            , url: '${ctx}/major/showAllMajorInfo' //向后端默认传page和limit
            , cols: [[
                {field: 'majorName', title: '专业', sort: true, fixed: true}
                , {field: 'majorCollegeName', title: '学院', sort: true}
                , {field: 'majorStuNum', title: '专业人数', sort: true}
                , {field: 'majorClassNum', title: '专业班级数', sort: true}
                , {field: 'majorTeaId', title: '专业负责人工号', hide: true}
                , {field: 'majorTeaName', title: '专业负责人'}
                , {width: 130, title: '操作', toolbar: '#test-table-operate-barDemo2'}
                , {field: 'majorRemark', title: '专业备注', width: 300}
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
        form.on('submit(LAY-app-contcomm-search-major)', function (data) {
            var field = data.field;

            //执行重载
            table.reload('majorInfoQuery', {
                url: '${ctx}/major/showAllMajorInfo' //向后端默认传page和limit
                , where: { //设定异步数据接口的额外参数，任意设
                    majorName: field.major
                    , majorCollegeName: field.majorCollege
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

        //工具条监听
        table.on('tool(LAY-app-content-comm-major)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

            if (obj.event === 'tea_detail1') {
                layer.open({
                    type: 2
                    , title: '查看专业负责人'
                    , content: '/teacher/teaInfo?teaId=' + data.majorTeaId
                    , maxmin: true
                    , area: ['550px', '550px']
                    , yes: function (index, layero) {
                        layer.close(index); //关闭弹层
                    }
                });
            }
        });

        $("#query-my-info1").click(function () {
            table.reload('majorInfoQuery', {
                url: '${ctx}/major/myOwnInfo'
                , request: {
                    pageName: 'pageNum',
                    limitName: 'pageSize'  //如不配置，默认为page=1&limit=10
                }
                , page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        });

        $("#query-all-info1").click(function () {
            table.reload('majorInfoQuery', {
                url: '${ctx}/major/showAllMajorInfo'
                , request: {
                    pageName: 'pageNum',
                    limitName: 'pageSize'  //如不配置，默认为page=1&limit=10
                }
                , where: { //设定异步数据接口的额外参数，任意设
                    majorName: ''
                    , majorCollegeName: ''
                }
                , page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        });
        //方法级渲染
        table.render({
            elem: '#collegeInfoQuery'
            , url: '${ctx}/college/showAllCollegeInfo' //向后端默认传page和limit
            , cols: [[
                {field: 'collegeName', title: '学院', sort: true, fixed: true}
                , {field: 'collegeStuNum', title: '学院人数', sort: true}
                , {field: 'collegeMajorNum', title: '学院班级数', sort: true}
                , {field: 'collegeProperty', title: '学院性质', sort: true}
                , {field: 'collegeTeaId', title: '学院负责人工号', hide: true}
                , {field: 'collegeTeaName', title: '学院负责人'}
                , {width: 130, title: '操作', toolbar: '#test-table-operate-barDemo3'}
                , {field: 'collegeRemark', title: '专业备注', width: 300}
            ]]
            , page: true
            , limit: 10
            , limits: [1, 5, 10, 15, 20]
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
        form.on('submit(LAY-app-contcomm-search-college)', function (data) {
            var field = data.field;

            //执行重载
            table.reload('collegeInfoQuery', {
                url: '${ctx}/college/showAllCollegeInfo' //向后端默认传page和limit
                , where: { //设定异步数据接口的额外参数，任意设
                    collegeName: field.college
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

        //工具条监听
        table.on('tool(LAY-app-content-comm-college)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            console.log(data);
            if (obj.event === 'tea_detail2') {
                layer.open({
                    type: 2
                    , title: '查看学院负责人'
                    , content: '/teacher/teaInfo?teaId=' + data.collegeTeaId
                    , maxmin: true
                    , area: ['550px', '550px']
                    , yes: function (index, layero) {
                        layer.close(index); //关闭弹层
                    }
                });
            }
        });

        $("#query-my-info2").click(function () {
            table.reload('collegeInfoQuery', {
                url: '${ctx}/college/myOwnInfo'
                , request: {
                    pageName: 'pageNum',
                    limitName: 'pageSize'  //如不配置，默认为page=1&limit=10
                }
                , page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        });

        $("#query-all-info2").click(function () {
            table.reload('collegeInfoQuery', {
                url: '${ctx}/college/showAllCollegeInfo'
                , request: {
                    pageName: 'pageNum',
                    limitName: 'pageSize'  //如不配置，默认为page=1&limit=10
                }
                , where: { //设定异步数据接口的额外参数，任意设
                    collegeName: ''
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

