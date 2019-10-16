<%--
  Created by IntelliJ IDEA.
  User: 13681864361
  Date: 2019/5/4
  Time: 22:56
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
                <div class="layui-card-header">查询相应学院、专业、班级的学生男女比</div>
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
                                <label class="layui-form-label">班级</label>
                                <div class="layui-input-inline">
                                    <select id="class" name="class" lay-search lay-filter="class">
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
                    <div class="layui-carousel layadmin-carousel layadmin-dataview" data-anim="fade"
                         lay-filter="LAY-index-plat">
                        <div id="container" style="height: 100%"></div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
<script type="text/javascript"
        src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
<script type="text/javascript"
        src="https://api.map.baidu.com/api?v=2.0&ak=HAyXKM0od6KqNdGCqwmx07WPm111ZF5B&__ec_v__=20190126"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>

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

        $("#class").empty();
        $("#class").append('<option value="">请输入或选择班级</option>');
        var major_name = $("#major").val();
        $.ajax({
            async: false,
            type: "get",
            data: {majorName: major_name},
            url: "${ctx}/class/getClassNameByMajor",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var json = data[i];
                    $("#class").append('<option value="' + json.className + '">' + json.className + '</option>');
                }
                form.render('select');
            }
        });

        form.render('select');

        //联动监听select
        form.on('select(college)', function (data) {
            $("#class").val("");
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
        form.on('select(major)', function (data) {
            //获取部门的ID通过异步查询子集
            $("#class").empty();
            $("#class").append('<option value="">请输入或选择班级</option>');
            var major_name = $(this).attr("lay-value");
            $.ajax({
                type: "get",
                data: {majorName: major_name},
                url: "${ctx}/class/getClassNameByMajor",
                success: function (data) {
                    for (var i = 0; i < data.length; i++) {
                        var json = data[i];
                        $("#class").append('<option value="' + json.className + '">' + json.className + '</option>');
                    }
                    form.render('select');
                }
            });
        });

        var dom = document.getElementById("container");
        var myChart = echarts.init(dom);

        $.ajax({
            type: "get",
            url: "${ctx}/findSexPercent",
            success: function (res) {

                var app = {};
                option = null;
                option = {
                    title: {
                        text: '全校学生男女比',
                        subtext: '总人数:' + res.studentPercent.total,
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: ['男', '女']
                    },
                    series: [
                        {
                            name: '性别',
                            type: 'pie',
                            radius: '65%',
                            center: ['50%', '60%'],
                            data: [
                                {value: res.studentPercent.male, name: '男'},
                                {value: res.studentPercent.female, name: '女'}
                            ],
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };
                ;
                if (option && typeof option === "object") {
                    myChart.setOption(option, true);
                }
            }
        });

        //监听搜索
        form.on('submit(LAY-app-contcomm-search)', function (data) {
            var field = data.field;

            console.log(field);

            $.ajax({
                type: "get",
                data: field,
                url: "${ctx}/findSexPercent",
                success: function (res) {
                    var myTitle = '';
                    if (field.college == '' && field.major == '' && field.class == '') {
                        myTitle = '全校';
                    } else {
                        myTitle = field.college + (field.college == '' ? '' : '-') + field.major + (field.major == '' ? '' : '-') + field.class + (field.class == '' ? '' : '-');
                    }
                    var option = myChart.getOption();

                    var hTsZ = [
                        {value: res.studentPercent.male, name: '男'},
                        {value: res.studentPercent.female, name: '女'}
                    ];

                    option.series[0].data = hTsZ;
                    option.title[0].text = myTitle + "学生男女比";
                    option.title[0].subtext = '总人数:' + res.studentPercent.total;
                    myChart.setOption(option, true);
                }
            });
        });


    });
</script>

</body>
</html>
