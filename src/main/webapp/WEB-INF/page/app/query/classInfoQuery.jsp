<%--
  Created by IntelliJ IDEA.
  User: 13681864361
  Date: 2019/4/16
  Time: 15:26
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
                    <label class="layui-form-label">班级名称</label>
                    <div class="layui-input-inline">
                        <input type="text" name="className" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <%--学院和专业联动复选框--%>
                <div class="layui-inline">
                    <label class="layui-form-label">学院</label>
                    <div class="layui-input-inline">
                        <select id="classCollege" name="classCollege" lay-search lay-filter="college">
                            <option value="">请选择标签</option>

                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">专业</label>
                    <div class="layui-input-inline">
                        <select id="classMajor" name="classMajor" lay-search>
                            <option value="">请选择标签</option>
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
                <button class="layui-btn layuiadmin-btn-comm" data-type="batchdel" id="query-my-info">查询我的班级信息</button>
                <button class="layui-btn layuiadmin-btn-comm" data-type="batchdel" style="background-color: #FFB800"
                        id="query-all-info">查询所有信息
                </button>
            </div>
            <%--<div style="padding-bottom: 10px;">--%>
            <%--<button class="layui-btn layuiadmin-btn-comm" data-type="batchdel">删除</button>--%>
            <%--</div>--%>
            <table id="classInfoQuery" lay-filter="LAY-app-content-comm"></table>
            <script type="text/html" id="test-table-operate-barDemo1">
                <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="moni_detail">查看班长详情</a>
            </script>
            <script type="text/html" id="test-table-operate-barDemo2">
                <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="tea_detail">查看班主任详情</a>
            </script>
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

        //从数据库异步获取学院数据填充到学院select框中
        $.ajax({
            type: "get",
            url: "${ctx}/college/getCollegeName",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var json = data[i];
                    $("#classCollege").append('<option value="' + json.collegeName + '">' + json.collegeName + '</option>');
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
                    $("#classMajor").append('<option value="' + json.majorName + '">' + json.majorName + '</option>');
                }
                form.render('select');
            }
        });


        //联动监听select
        form.on('select(college)', function (data) {
            //获取部门的ID通过异步查询子集
            $("#classMajor").empty();
            $("#classMajor").append('<option value="">请选择标签</option>');
            var college_name = $(this).attr("lay-value");
            console.log(college_name);
            $.ajax({
                type: "get",
                data: {collegeName: college_name},
                url: "${ctx}/major/getMajorNameByCollege",
                success: function (data) {
                    for (var i = 0; i < data.length; i++) {
                        var json = data[i];
                        $("#classMajor").append('<option value="' + json.majorName + '">' + json.majorName + '</option>');
                    }
                    form.render('select');
                }
            });
        });

        //方法级渲染
        table.render({
            elem: '#classInfoQuery'
            , url: '${ctx}/class/showAllClassInfo' //向后端默认传page和limit
            , cols: [[
                {field: 'className', title: '班级', sort: true, fixed: true}
                , {field: 'classStuNum', title: '班级人数', sort: true}
                , {field: 'classMoniName', title: '班长'}
                , {field: 'classMoniId', title: '班长学号', hide: true}
                , {width: 120, title: '操作', toolbar: '#test-table-operate-barDemo1'}
                , {field: 'classTeaName', title: '班主任'}
                , {field: 'classTeaId', title: '班主任学号', hide: true}
                , {width: 130, title: '操作', toolbar: '#test-table-operate-barDemo2'}
                , {field: 'classMajorName', title: '专业', sort: true}
                , {field: 'classCollegeName', title: '学院', width: 200, sort: true}
                , {field: 'classRemark', title: '班级描述', width: 300}
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

        //工具条监听
        table.on('tool(LAY-app-content-comm)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

            if (obj.event === 'moni_detail') {
                layer.open({
                    type: 2
                    , title: '查看班长'
                    , content: '/student/stuInfo?stuId=' + data.classMoniId
                    , maxmin: true
                    , area: ['550px', '630px']
                    , yes: function (index, layero) {
                        layer.close(index); //关闭弹层
                    }
                });
            } else if (obj.event === 'tea_detail') {
                layer.open({
                    type: 2
                    , title: '查看班主任'
                    , content: '/teacher/teaInfo?teaId=' + data.classTeaId
                    , maxmin: true
                    , area: ['550px', '550px']
                    , yes: function (index, layero) {
                        layer.close(index); //关闭弹层
                    }
                });
            }
        });


        //监听搜索
        form.on('submit(LAY-app-contcomm-search)', function (data) {
            var field = data.field;

            console.log(field);
            //执行重载
            table.reload('classInfoQuery', {
                url: '${ctx}/class/showAllClassInfo' //向后端默认传page和limit
                , where: { //设定异步数据接口的额外参数，任意设
                    className: field.className
                    , classMajorName: field.classMajor
                    , classCollegeName: field.classCollege
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
            table.reload('classInfoQuery', {
                url: '${ctx}/class/myOwnInfo'
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
            table.reload('classInfoQuery', {
                url: '${ctx}/class/showAllClassInfo'
                , request: {
                    pageName: 'pageNum',
                    limitName: 'pageSize'  //如不配置，默认为page=1&limit=10
                }
                , where: { //设定异步数据接口的额外参数，任意设
                    className: ''
                    , classMajorName: ''
                    , classCollegeName: ''
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
