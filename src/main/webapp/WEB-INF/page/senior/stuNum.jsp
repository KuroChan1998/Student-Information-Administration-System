<%--
  Created by IntelliJ IDEA.
  User: 13681864361
  Date: 2019/5/5
  Time: 19:32
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
                <div class="layui-card-header">学院人数比</div>
                <div class="layui-card-body">
                    <div class="layui-carousel layadmin-carousel layadmin-dataview" data-anim="fade"
                         lay-filter="LAY-index-plat">
                        <div id="container" style="height: 100%"></div>
                    </div>

                </div>
                <div class="layui-card-header"></div>
                <div class="layui-card-header">查询相应学院下的专业人数比</div>
                <div class="layui-card-body">
                    <div class="layui-form layui-card-header layuiadmin-card-header-auto">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">学院</label>
                                <div class="layui-input-inline">
                                    <select id="college" name="college" lay-search lay-filter="college">
                                        <option value="">请选择学院</option>
                                        <%--<option value="电子信息与电气工程学院">电子信息与电气工程学院</option>--%>
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
                        <div id="container2" style="height: 100%"></div>
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


        $.ajax({
            async: false,
            type: "get",
            url: "${ctx}/findCollegeStuNumPercent",
            success: function (res) {
                console.log(res);
                var dom = document.getElementById("container");
                var myChart = echarts.init(dom);
                var app = {};
                option = null;
                app.title = '坐标轴刻度与标签对齐';

                option = {
                    color: ['#3398DB'],
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: [
                        {
                            type: 'category',
                            data: res.collegeName,
                            axisTick: {
                                alignWithLabel: true
                            }
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: [
                        {
                            name: '人数',
                            type: 'bar',
                            barWidth: '50%',
                            data: res.collegeStuNum
                        }
                    ]
                };
                ;
                if (option && typeof option === "object") {
                    myChart.setOption(option, true);
                }

            }
        });

        var dom2 = document.getElementById("container2");
        var myChart2 = echarts.init(dom2);
        $.ajax({
            async: false,
            type: "get",
            url: "${ctx}/findMajorStuNumPercent",
            success: function (res) {
                console.log(res);

                var app = {};
                option = null;
                app.title = '坐标轴刻度与标签对齐';

                option = {
                    color: ['#FF5722'],
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: [
                        {
                            type: 'category',
                            data: res.majorName,
                            axisTick: {
                                alignWithLabel: true
                            }
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: [
                        {
                            name: '人数',
                            type: 'bar',
                            barWidth: '50%',
                            data: res.majorStuNum
                        }
                    ]
                };
                ;
                if (option && typeof option === "object") {
                    myChart2.setOption(option, true);
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
                url: "${ctx}/findMajorStuNumPercent",
                success: function (res) {
                    var option = myChart2.getOption();

                    option.series[0].data = res.majorStuNum;
                    option.xAxis[0].data = res.majorName;

                    myChart2.setOption(option, true);
                }
            });
        });
    });
</script>

</body>
</html>

