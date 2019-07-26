<%--
  Created by IntelliJ IDEA.
  User: 13681864361
  Date: 2019/4/20
  Time: 8:47
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
                <li class="layui-this">专业信息修改</li>
                <li>学院信息修改</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <div class="layui-form layui-card-header layuiadmin-card-header-auto">
                        <div class="layui-form-item" id="form">
                            <%--学院和专业联动复选框--%>
                            <div class="layui-inline">
                                <label class="layui-form-label">学院</label>
                                <div class="layui-input-inline">
                                    <select id="majorCollege" name="majorCollege" lay-search lay-filter="college">
                                        <option value="">请输入或选择学院</option>

                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">专业</label>
                                <div class="layui-input-inline">
                                    <select id="major" name="major" lay-search>
                                        <option value="">请输入或选择专业</option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">性质</label>
                                <div class="layui-input-inline">
                                    <select id="property" name="property">
                                        <option value="">请选择标签</option>
                                        <option value="工科">工科</option>
                                        <option value="文科">文科</option>
                                        <option value="理科">理科</option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">筛选排序</label>
                                <div class="layui-input-inline">
                                    <select id="condition1" name="condition1" lay-filter="condition1">
                                        <option value="">请选择要排序的类别</option>
                                        <option value="majorCollegeProperty">按学科性质排序</option>
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
                                        lay-filter="LAY-app-contcomm-search-major">
                                    <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                                </button>
                                <button class="layui-btn layuiadmin-btn-comm" data-type="reload"
                                        id="clear">
                                    清空
                                </button>
                            </div>
                        </div>
                    </div>
                    <div style="padding-bottom: 10px;">
                        <button class="layui-btn layuiadmin-btn-list" data-type="batchdel1">删除</button>
                        <button class="layui-btn layuiadmin-btn-list" data-type="add1">添加</button>
                        <button class="layui-btn layuiadmin-btn-comm" data-type="batchdel"
                                style="background-color: #FFB800" id="query-all-info1">查询所有信息
                        </button>
                    </div>
                    <table id="majorInfoQuery" lay-filter="LAY-app-content-comm-major"></table>
                    <script type="text/html" id="test-table-operate-barDemo2">
                        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="tea_detail1">查看负责人详情</a>
                    </script>
                    <script type="text/html" id="table-content-list1">
                        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i
                                class="layui-icon layui-icon-edit"></i>编辑</a>
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i
                                class="layui-icon layui-icon-delete"></i>删除</a>
                    </script>
                </div>
                <div class="layui-tab-item">
                    <div class="layui-tab-item layui-show">
                        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
                            <div class="layui-form-item" id="form2">
                                <div class="layui-inline">
                                    <label class="layui-form-label">学院</label>
                                    <div class="layui-input-inline">
                                        <select id="college" name="college" lay-search lay-filter="college">
                                            <option value="">请输入或选择学院</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <label class="layui-form-label">性质</label>
                                    <div class="layui-input-inline">
                                        <select id="property2" name="property2">
                                            <option value="">请选择标签</option>
                                            <option value="工科">工科</option>
                                            <option value="文科">文科</option>
                                            <option value="理科">理科</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <label class="layui-form-label">筛选排序</label>
                                    <div class="layui-input-inline">
                                        <select id="condition12" name="condition12" lay-filter="condition12">
                                            <option value="">请选择要排序的类别</option>
                                            <option value="collegeProperty">按学科性质排序</option>
                                        </select>
                                    </div>
                                    <div class="layui-input-inline">
                                        <select id="condition22" name="condition22" lay-filter="condition22">
                                            <option value="">请选择要排序方式</option>
                                            <option value="asc">从低到高</option>
                                            <option value="desc">从高到低</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <button class="layui-btn layuiadmin-btn-comm" data-type="reload" lay-submit
                                            lay-filter="LAY-app-contcomm-search-college">
                                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                                    </button>
                                    <button class="layui-btn layuiadmin-btn-comm" data-type="reload"
                                            id="clear2">
                                        清空
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div style="padding-bottom: 10px;">
                            <button class="layui-btn layuiadmin-btn-list" data-type="batchdel2">删除</button>
                            <button class="layui-btn layuiadmin-btn-list" data-type="add2">添加</button>
                            <button class="layui-btn layuiadmin-btn-comm" data-type="batchdel"
                                    style="background-color: #FFB800" id="query-all-info2">查询所有信息
                            </button>
                        </div>
                        <table id="collegeInfoQuery" lay-filter="LAY-app-content-comm-college"></table>
                        <script type="text/html" id="test-table-operate-barDemo3">
                            <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="tea_detail2">查看负责人详情</a>
                        </script>
                        <script type="text/html" id="table-content-list2">
                            <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i
                                    class="layui-icon layui-icon-edit"></i>编辑</a>
                            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i
                                    class="layui-icon layui-icon-delete"></i>删除</a>
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


        //方法级渲染
        table.render({
            elem: '#majorInfoQuery'
            , url: '${ctx}/major/showAllMajorInfo' //向后端默认传page和limit
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'majorId', title: '专业号', hide: true}
                , {field: 'majorName', title: '专业', sort: true, fixed: true}
                , {field: 'majorCollegeProperty', title: '性质', sort: true}
                , {field: 'majorCollegeName', title: '学院', sort: true}
                , {field: 'majorTeaNum', title: '专业负责人工号', hide: true}
                , {field: 'majorTeaName', title: '专业负责人'}
                , {width: 130, title: '操作', toolbar: '#test-table-operate-barDemo2'}
                , {field: 'majorRemark', title: '专业备注', width: 300}
                , {title: '操作', minWidth: 150, align: 'center', fixed: 'right', toolbar: '#table-content-list1'}
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

        form.on('select(condition1)', function (data) {
            //获取部门的ID通过异步查询子集
            $("#condition2").val("");
            form.render('select');
        });

        $("#clear").click(function () {
            $("#form input").val("");
            $("#form select").val("");
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
                    , majorCollegeProperty: field.property
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

        //工具条监听
        table.on('tool(LAY-app-content-comm-major)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

            if (obj.event === 'tea_detail1') {
                layer.open({
                    type: 2
                    , title: '查看专业负责人'
                    , content: '/teacher/teaInfo?teaNum=' + data.majorTeaNum
                    , maxmin: true
                    , area: ['550px', '550px']
                    , yes: function (index, layero) {
                        layer.close(index); //关闭弹层
                    }
                });
            } else if (obj.event === 'del') {
                layer.confirm('确定删除此专业吗？删除后其下所有班级、学生、教师也都将被删除！！', function (index) {

                    //提交删除ajax
                    $.ajax({
                        data: data,
                        type: 'post',
                        url: "${ctx}/major/deleteOne",
                        success: function (data) {
                            if (data.data == "deleteSuccess") {
                                layer.msg('删除成功', {
                                    icon: 1
                                    , time: 1000
                                });

                                obj.del();

                                layer.close(index); //关闭弹层
                            } else {
                                return layer.msg('未知错误');
                            }
                        }
                    });
                });
            } else if (obj.event === 'edit') {
                layer.open({
                    type: 2
                    ,
                    title: '编辑专业'
                    ,
                    content: '${ctx}/major/edit?majorId=' + data.majorId + '&majorName=' + data.majorName + '&majorCollegeName=' + data.majorCollegeName
                    ,
                    maxmin: true
                    ,
                    area: ['700px', '720px']
                    ,
                    btn: ['确定', '取消']
                    ,
                    yes: function (index, layero) {
                        var iframeWindow = window['layui-layer-iframe' + index]
                            , submit = layero.find('iframe').contents().find("#layuiadmin-app-form-edit");

                        //监听提交
                        iframeWindow.layui.form.on('submit(layuiadmin-app-form-edit)', function (data) {
                            var field = data.field; //获取提交的字段
                            var json = {
                                majorOriId: field.oriId
                                , majorOriName: field.oriName
                                , majorName: field.name
                                , majorTeaNum: field.teaId
                                , majorCollegeName: field.college
                                , majorRemark: field.remark
                            };

                            $.ajax({
                                data: json,
                                type: 'post',
                                url: "${ctx}/major/updateInfo",
                                success: function (data) {
                                    if (data.data == "majorNameExist") {
                                        return layer.msg('对不起，该专业名称已存在！');
                                    } else if (data.data == "majorTeaNumNotExist") {
                                        return layer.msg('对不起，该指定为负责人的教师工号不存在！');
                                    } else if (data.data == "majorTeaNumRepeat") {
                                        return layer.msg('对不起，该指定为负责人的教师已经担任了某专业的负责人！');
                                    } else if (data.data == "updateSuccess") {
                                        layer.msg('修改成功', {
                                            icon: 1
                                            , time: 1000
                                        });

                                        obj.update(json); //数据更新


                                        form.render();

                                        layer.close(index); //关闭弹层
                                    } else {
                                        return layer.msg('未知错误');
                                    }
                                }
                            });

                        });

                        submit.trigger('click');
                    }
                    ,
                    success: function (layero, index) {
                        //给iframe元素赋值
                        var othis = layero.find('iframe').contents().find("#layuiadmin-app-form-list").click();
                        othis.find('input[name="name"]').val(data.majorName);
                        othis.find('input[name="teaId"]').val(data.majorTeaNum);
                        // 学院，专业，班级名称通过url传值给后端，后端存于model，如果通过iframe传值，无法在子页面启动数据库查询所有学院名称填充select的ajax
                        // othis.find('select[name="college"]').val(data.stuCollegeName);
                        // othis.find('select[name="major"]').val(data.stuMajorName);
                        // othis.find('select[name="class"]').val(data.stuClassName);
                        othis.find('textarea[name="remark"]').val(data.majorRemark);
                    }
                });
            }
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
                    , majorCollegeProperty: ''
                }
                , page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        });

        form.on('select(condition12)', function (data) {
            //获取部门的ID通过异步查询子集
            $("#condition22").val("");
            form.render('select');
        });

        $("#clear2").click(function () {
            $("#form2 input").val("");
            $("#form2 select").val("");
        });
        //方法级渲染
        table.render({
            elem: '#collegeInfoQuery'
            , url: '${ctx}/college/showAllCollegeInfo' //向后端默认传page和limit
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'collegeId', title: '学院号', hide: true}
                , {field: 'collegeName', title: '学院', sort: true, fixed: true}
                , {field: 'collegeProperty', title: '学院性质', sort: true}
                , {field: 'collegeTeaNum', title: '学院负责人工号', hide: true}
                , {field: 'collegeTeaName', title: '学院负责人'}
                , {width: 130, title: '操作', toolbar: '#test-table-operate-barDemo3'}
                , {field: 'collegeRemark', title: '专业备注', width: 300}
                , {title: '操作', minWidth: 150, align: 'center', fixed: 'right', toolbar: '#table-content-list2'}
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
                    , collegeProperty: field.property2
                    , condition1: field.condition12
                    , condition2: field.condition22
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
            // console.log(data);
            if (obj.event === 'tea_detail2') {
                layer.open({
                    type: 2
                    , title: '查看学院负责人'
                    , content: '/teacher/teaInfo?teaNum=' + data.collegeTeaNum
                    , maxmin: true
                    , area: ['550px', '550px']
                    , yes: function (index, layero) {
                        layer.close(index); //关闭弹层
                    }
                });
            } else if (obj.event === 'del') {
                layer.confirm('确定删除此学院吗？删除后其下所有专业、班级、学生、教师也都将被删除！！', function (index) {

                    //提交删除ajax
                    $.ajax({
                        data: data,
                        type: 'post',
                        url: "${ctx}/college/deleteOne",
                        success: function (data) {
                            if (data.data == "deleteSuccess") {
                                layer.msg('删除成功', {
                                    icon: 1
                                    , time: 1000
                                });

                                obj.del();

                                layer.close(index); //关闭弹层
                            } else {
                                return layer.msg('未知错误');
                            }
                        }
                    });
                });
            } else if (obj.event === 'edit') {
                layer.open({
                    type: 2
                    ,
                    title: '编辑学院'
                    ,
                    content: '${ctx}/college/edit?collegeId=' + data.collegeId + '&collegeName=' + data.collegeName
                    ,
                    maxmin: true
                    ,
                    area: ['700px', '720px']
                    ,
                    btn: ['确定', '取消']
                    ,
                    yes: function (index, layero) {
                        var iframeWindow = window['layui-layer-iframe' + index]
                            , submit = layero.find('iframe').contents().find("#layuiadmin-app-form-edit");

                        //监听提交
                        iframeWindow.layui.form.on('submit(layuiadmin-app-form-edit)', function (data) {
                            var field = data.field; //获取提交的字段
                            var json = {
                                collegeOriName: field.oriName
                                , collegeOriId: field.oriId
                                , collegeName: field.name
                                , collegeTeaNum: field.teaId
                                , collegeProperty: field.property
                                , collegeRemark: field.remark
                            };

                            $.ajax({
                                data: json,
                                type: 'post',
                                url: "${ctx}/college/updateInfo",
                                success: function (data) {
                                    if (data.data == "collegeNameExist") {
                                        return layer.msg('对不起，该学院名称已存在！');
                                    } else if (data.data == "collegeTeaNumNotExist") {
                                        return layer.msg('对不起，该指定为负责人的教师工号不存在！');
                                    } else if (data.data == "collegeTeaNumRepeat") {
                                        return layer.msg('对不起，该指定为负责人的教师已经担任了某学院的负责人！');
                                    } else if (data.data == "updateSuccess") {
                                        layer.msg('修改成功', {
                                            icon: 1
                                            , time: 1000
                                        });

                                        obj.update(json); //数据更新


                                        form.render();

                                        layer.close(index); //关闭弹层
                                    } else {
                                        return layer.msg('未知错误');
                                    }
                                }
                            });

                        });

                        submit.trigger('click');
                    }
                    ,
                    success: function (layero, index) {
                        //给iframe元素赋值
                        var othis = layero.find('iframe').contents().find("#layuiadmin-app-form-list").click();
                        othis.find('input[name="name"]').val(data.collegeName);
                        othis.find('input[name="teaId"]').val(data.collegeTeaNum);
                        othis.find('select[name="property"]').val(data.collegeProperty);
                        othis.find('textarea[name="remark"]').val(data.collegeRemark);
                    }
                });
            }
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
                    , collegeProperty: ''
                }
                , page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        });


        var $ = layui.$, active = {
            batchdel1: function () {
                var checkStatus = table.checkStatus('majorInfoQuery')
                    , checkData = checkStatus.data; //得到选中的数据

                if (checkData.length === 0) {
                    return layer.msg('请选择数据');
                }

                // console.log(JSON.stringify(checkData));
                // console.log(checkStatus);
                // console.log(checkData);
                layer.confirm('确定要删除' + checkData.length + '个专业吗？删除后其下所有班级、学生、教师也都将被删除！！', function (index) {
                    //执行 Ajax 后重载
                    $.ajax({
                        type: 'post',
                        data: {majors: JSON.stringify(checkData)},
                        url: "${ctx}/major/deleteMany",
                        success: function (data) {
                            if (data.data == "deleteSuccess") {
                                table.reload('majorInfoQuery', {
                                    url: '${ctx}/major/showAllMajorInfo' //向后端默认传page和limit); //重载表格
                                    , request: {
                                        pageName: 'pageNum',
                                        limitName: 'pageSize'  //如不配置，默认为page=1&limit=10
                                    }
                                });
                                layer.msg('已删除');
                            } else {
                                layer.msg('未知错误');
                            }
                        }

                    });

                });
            },
            add1: function () {
                layer.open({
                    type: 2
                    , title: '添加专业'
                    , content: '${ctx}/major/add'
                    , maxmin: true
                    , area: ['700px', '720px']
                    , btn: ['确定', '取消']
                    , yes: function (index, layero) {
                        //点击确认触发 iframe 内容中的按钮提交
                        var iframeWindow = window['layui-layer-iframe' + index]
                            , submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");

                        iframeWindow.layui.form.on('submit(layuiadmin-app-form-submit)', function (data) {
                            var field = data.field; //获取提交的字段
                            // var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            var json = {
                                majorName: field.name
                                , majorTeaNum: field.teaId
                                , majorCollegeName: field.college
                                , majorRemark: field.remark
                            };

                            //提交 Ajax 成功后，关闭当前弹层并重载表格
                            $.ajax({
                                data: json,
                                type: 'post',
                                url: "${ctx}/major/insert",
                                success: function (data) {
                                    if (data.data == "majorNameExist") {
                                        return layer.msg('对不起，该专业名称已存在！');
                                    } else if (data.data == "majorTeaNumNotExist") {
                                        return layer.msg('对不起，该指定为负责人的教师工号不存在！');
                                    } else if (data.data == "majorTeaNumRepeat") {
                                        return layer.msg('对不起，该指定为负责人的教师已经担任了某专业的负责人！');
                                    } else if (data.data == "insertSuccess") {
                                        layer.msg('添加成功', {
                                            icon: 1
                                            , time: 1000
                                        });

                                        layer.close(index); //再执行关闭

                                    } else {
                                        return layer.msg('未知错误');
                                    }
                                }
                            });

                        });
                        submit.trigger('click');

                    }
                });
            },
            batchdel2: function () {
                var checkStatus = table.checkStatus('collegeInfoQuery')
                    , checkData = checkStatus.data; //得到选中的数据

                if (checkData.length === 0) {
                    return layer.msg('请选择数据');
                }

                // console.log(JSON.stringify(checkData));
                // console.log(checkStatus);
                // console.log(checkData);
                layer.confirm('确定要删除' + checkData.length + '个学院吗？删除后其下所有专业、班级、学生、教师也都将被删除！！', function (index) {
                    //执行 Ajax 后重载
                    $.ajax({
                        type: 'post',
                        data: {colleges: JSON.stringify(checkData)},
                        url: "${ctx}/college/deleteMany",
                        success: function (data) {
                            if (data.data == "deleteSuccess") {
                                table.reload('collegeInfoQuery', {
                                    url: '${ctx}/college/showAllCollegeInfo' //向后端默认传page和limit); //重载表格
                                    , request: {
                                        pageName: 'pageNum',
                                        limitName: 'pageSize'  //如不配置，默认为page=1&limit=10
                                    }
                                });
                                layer.msg('已删除');
                            } else {
                                layer.msg('未知错误');
                            }
                        }

                    });

                });
            },
            add2: function () {
                layer.open({
                    type: 2
                    , title: '添加学院'
                    , content: '${ctx}/college/add'
                    , maxmin: true
                    , area: ['700px', '720px']
                    , btn: ['确定', '取消']
                    , yes: function (index, layero) {
                        //点击确认触发 iframe 内容中的按钮提交
                        var iframeWindow = window['layui-layer-iframe' + index]
                            , submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");

                        iframeWindow.layui.form.on('submit(layuiadmin-app-form-submit)', function (data) {
                            var field = data.field; //获取提交的字段
                            // var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            var json = {
                                collegeName: field.name
                                , collegeTeaNum: field.teaId
                                , collegeProperty: field.property
                                , collegeRemark: field.remark
                            };

                            //提交 Ajax 成功后，关闭当前弹层并重载表格
                            $.ajax({
                                data: json,
                                type: 'post',
                                url: "${ctx}/college/insert",
                                success: function (data) {
                                    if (data.data == "collegeNameExist") {
                                        return layer.msg('对不起，该学院名称已存在！');
                                    } else if (data.data == "collegeTeaNumNotExist") {
                                        return layer.msg('对不起，该指定为负责人的教师工号不存在！');
                                    } else if (data.data == "collegeTeaNumRepeat") {
                                        return layer.msg('对不起，该指定为负责人的教师已经担任了某学院的负责人！');
                                    } else if (data.data == "insertSuccess") {
                                        layer.msg('添加成功', {
                                            icon: 1
                                            , time: 1000
                                        });

                                        layer.close(index); //再执行关闭

                                    } else {
                                        return layer.msg('未知错误');
                                    }
                                }
                            });

                        });
                        submit.trigger('click');

                    }
                });
            }
        };

        $('.layui-btn.layuiadmin-btn-list').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>
</body>
</html>
