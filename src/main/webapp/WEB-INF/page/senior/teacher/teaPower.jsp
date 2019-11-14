<%--
  Created by IntelliJ IDEA.
  User: 13681864361
  Date: 2019/7/23
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Echarts集成 - 地图</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
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
        <div class="layui-col-sm12">
            <div class="layui-card">
                <div class="layui-card-header">查询相应学院、专业下的师资力量</div>
                <div class="layui-card-body">
                    <div class="layui-form layui-card-header layuiadmin-card-header-auto">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">学院</label>
                                <div class="layui-input-inline">
                                    <select id="college" name="college" lay-search lay-filter="college">
                                        <option value="">请输入或选择学院</option>
                                        <%--<option value="电子信息与电气工程学院">电子信息与电气工程学院</option>--%>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">专业</label>
                                <div class="layui-input-inline">
                                    <select id="major" name="major" lay-search lay-filter="major">
                                        <option value="">请输入或选择专业</option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <button class="layui-btn layuiadmin-btn-comm" data-type="reload" lay-submit
                                        lay-filter="LAY-app-contcomm-search">
                                    <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                                </button>
                                <button class="layui-btn layuiadmin-btn-comm" data-type="batchdel" id="wholeSchool"
                                        style="background-color: #FF5722">全校师资力量
                                </button>
                                <button class="layui-btn layuiadmin-btn-comm" data-type="batchdel" id="allCollege"
                                        style="background-color: #FFB800">所有学院师资力量
                                </button>
                                <button class="layui-btn layuiadmin-btn-comm" data-type="batchdel" id="allMajor"
                                        style="background-color: #01AAED">所有专业师资力量
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="layui-carousel layadmin-carousel layadmin-dataview" data-anim="fade"
                         lay-filter="LAY-index-plat">
                        <div id="container" style="height: 100%"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${ctx}/static/plugins/echarts/echarts.js"></script>
<script src="${ctx}/static/plugins/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '${ctx}/static/plugins/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'form'], function () {
        var $ = layui.$
            , form = layui.form;

        //从数据库异步获取学院数据填充到学院select框中
        $.ajax({
            async: false,
            type: "get",
            url: "${ctx}/college/getCollegeName",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var json = data[i];
                    $("#college").append('<option value="' + json.collegeName + '">' + json.collegeName + '</option>');
                    $("#college2").append('<option value="' + json.collegeName + '">' + json.collegeName + '</option>');
                }
                form.render('select');
            }
        });

        $("#major").empty();
        $("#major").append('<option value="">请输入或选择专业</option>');
        var college_name = $("#college").val();
        $.ajax({
            async: false,
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

        //联动监听select
        form.on('select(college)', function (data) {
            //获取部门的ID通过异步查询子集
            $("#major").empty();
            $("#major").append('<option value="">请输入或选择专业</option>');
            var college_name = $(this).attr("lay-value");
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

        var dom = document.getElementById("container");
        var myChart = echarts.init(dom);
        $.ajax({
            async: false,
            type: "get",
            data: {
                type: "allCollegeByTeaTitle"
            },
            url: "${ctx}/findTeaTotalGroupByTitle",
            success: function (res) {
                var app = {};
                option = null;
                option = {
                    legend: {},
                    tooltip: {},
                    dataset: {
                        source: res.source
                    },
                    xAxis: [
                        {type: 'category', gridIndex: 0},
                        {type: 'category', gridIndex: 1}
                    ],
                    yAxis: [
                        {gridIndex: 0},
                        {gridIndex: 1}
                    ],
                    grid: [
                        {bottom: '55%'},
                        {top: '55%'}
                    ],
                    series: res.series
                };
                ;
                if (option && typeof option === "object") {
                    myChart.setOption(option, true);
                }
            }
        });


        $("#allCollege").click(function () {
            $.ajax({
                async: false,
                type: "get",
                data: {
                    type: "allCollegeByTeaTitle"
                },
                url: "${ctx}/findTeaTotalGroupByTitle",
                success: function (res) {
                    var app = {};
                    option = null;
                    option = {
                        legend: {},
                        tooltip: {},
                        dataset: {
                            source: res.source
                        },
                        xAxis: [
                            {type: 'category', gridIndex: 0},
                            {type: 'category', gridIndex: 1}
                        ],
                        yAxis: [
                            {gridIndex: 0},
                            {gridIndex: 1}
                        ],
                        grid: [
                            {bottom: '55%'},
                            {top: '55%'}
                        ],
                        series: res.series
                    };
                    ;
                    if (option && typeof option === "object") {
                        myChart.setOption(option, true);
                    }
                }
            });
        });


        $("#wholeSchool").click(function () {
            $.ajax({
                async: false,
                type: "get",
                data: {
                    type: "wholeSchoolByTeaTitle"
                },
                url: "${ctx}/findTeaTotalGroupByTitle",
                success: function (res) {
                    var app = {};
                    option = null;
                    option = {
                        legend: {},
                        tooltip: {},
                        dataset: {
                            source: res.source
                        },
                        xAxis: [
                            {type: 'category', gridIndex: 0},
                            {type: 'category', gridIndex: 1}
                        ],
                        yAxis: [
                            {gridIndex: 0},
                            {gridIndex: 1}
                        ],
                        grid: [
                            {bottom: '55%'},
                            {top: '55%'}
                        ],
                        series: res.series
                    };
                    ;
                    if (option && typeof option === "object") {
                        myChart.setOption(option, true);
                    }
                }
            });
        });

        $("#allMajor").click(function () {
            $.ajax({
                async: false,
                type: "get",
                data: {
                    type: "allMajorByTeaTitle"
                },
                url: "${ctx}/findTeaTotalGroupByTitle",
                success: function (res) {
                    var app = {};
                    option = null;
                    option = {
                        legend: {},
                        tooltip: {},
                        dataset: {
                            source: res.source
                        },
                        xAxis: [
                            {type: 'category', gridIndex: 0},
                            {type: 'category', gridIndex: 1}
                        ],
                        yAxis: [
                            {gridIndex: 0},
                            {gridIndex: 1}
                        ],
                        grid: [
                            {bottom: '55%'},
                            {top: '55%'}
                        ],
                        series: res.series
                    };
                    ;
                    if (option && typeof option === "object") {
                        myChart.setOption(option, true);
                    }
                }
            });
        });

        //监听搜索
        form.on('submit(LAY-app-contcomm-search)', function (data) {
            var field = data.field;
            $.ajax({
                type: "get",
                data: field,
                url: "${ctx}/findTeaTotalGroupByTitle?type=byMajorOrCollege",
                success: function (res) {
                    var app = {};
                    option = null;
                    option = {
                        legend: {},
                        tooltip: {},
                        dataset: {
                            source: res.source
                        },
                        xAxis: [
                            {type: 'category', gridIndex: 0},
                            {type: 'category', gridIndex: 1}
                        ],
                        yAxis: [
                            {gridIndex: 0},
                            {gridIndex: 1}
                        ],
                        grid: [
                            {bottom: '55%'},
                            {top: '55%'}
                        ],
                        series: res.series
                    };
                    ;
                    if (option && typeof option === "object") {
                        myChart.setOption(option, true);
                    }
                }
            });
        });
    });
</script>

</body>
</html>