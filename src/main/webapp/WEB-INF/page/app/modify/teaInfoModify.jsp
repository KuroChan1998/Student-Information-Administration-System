<%--
  Created by IntelliJ IDEA.
  User: 13681864361
  Date: 2019/4/27
  Time: 12:23
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
                    <label class="layui-form-label">工号</label>
                    <div class="layui-input-inline">
                        <input type="text" name="teaId" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">姓名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="teaName" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">职称</label>
                    <div class="layui-input-inline">
                        <select name="teaTitle">
                            <option value="">请选择标签</option>
                            <option value="教授">教授</option>
                            <option value="副教授">副教授</option>
                            <option value="讲师">讲师</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">学院</label>
                    <div class="layui-input-inline">
                        <select id="teaCollege" name="teaCollege" lay-search>
                            <option value="">请输入或选择标签</option>
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
                <button class="layui-btn layuiadmin-btn-list" data-type="batchdel">删除</button>
                <button class="layui-btn layuiadmin-btn-list" data-type="add">添加</button>
                <button class="layui-btn layuiadmin-btn-comm" data-type="batchdel" style="background-color: #FFB800"
                        id="query-all-info">查询所有信息
                </button>
            </div>
            <table id="teaInfoQuery" lay-filter="LAY-app-content-comm"></table>
            <script type="text/html" id="table-content-list1">
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i
                        class="layui-icon layui-icon-edit"></i>编辑</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i
                        class="layui-icon layui-icon-delete"></i>删除</a>
            </script>
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
            , admin = layui.admin
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
                    var str = "";
                    str += '<option value="' + json.collegeName + '">' + json.collegeName + '</option>';
                    $("#teaCollege").append(str);
                }
                form.render('select');
            }
        });

        //方法级渲染
        table.render({
            elem: '#teaInfoQuery'
            , url: '${ctx}/teacher/showAllTeaInfo' //向后端默认传page和limit
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'teaId', title: '工号', width: 130, sort: true, fixed: true}
                , {field: 'teaName', title: '姓名'}
                , {field: 'teaSex', title: '性别', width: 80, sort: true}
                , {field: 'teaAge', title: '年龄', width: 80}
                , {field: 'teaTitle', title: '职称', width: 80, sort: true}
                , {field: 'teaMajorName', title: '专业', sort: true}
                , {field: 'teaCollegeName', title: '学院', width: 200, sort: true}
                , {field: 'teaPhone', title: '联系方式'}
                , {field: 'teaRemark', title: '评价', width: 300}
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

        $("#query-all-info").click(function () {
            table.reload('teaInfoQuery', {
                url: '${ctx}/teacher/showAllTeaInfo'
                , request: {
                    pageName: 'pageNum',
                    limitName: 'pageSize'  //如不配置，默认为page=1&limit=10
                }
                , where: { //设定异步数据接口的额外参数，任意设
                    teaId: ''
                    , teaName: ''
                    , teaTitle: ''
                    , teaCollege: ''
                }
                , page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        })

        //监听搜索
        form.on('submit(LAY-app-contcomm-search)', function (data) {
            var field = data.field;

            // console.log(field);
            //执行重载
            table.reload('teaInfoQuery', {
                url: '${ctx}/teacher/showAllTeaInfo' //向后端默认传page和limit
                , where: { //设定异步数据接口的额外参数，任意设
                    teaId: field.teaId
                    , teaName: field.teaName
                    , teaTitle: field.teaTitle
                    , teaCollege: field.teaCollege
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


        var $ = layui.$, active = {
            batchdel: function () {
                var checkStatus = table.checkStatus('teaInfoQuery')
                    , checkData = checkStatus.data; //得到选中的数据

                if (checkData.length === 0) {
                    return layer.msg('请选择数据');
                }
                //
                // console.log(JSON.stringify(checkData));
                // console.log(checkStatus);
                // console.log(checkData);
                layer.confirm('确定要删除' + checkData.length + '条数据吗？', function (index) {
                    //执行 Ajax 后重载
                    $.ajax({
                        type: 'post',
                        data: {teachers: JSON.stringify(checkData)},
                        url: "${ctx}/teacher/deleteMany",
                        success: function (data) {
                            if (data.data == "deleteSuccess") {
                                table.reload('teaInfoQuery', {
                                    url: '${ctx}/teacher/showAllTeaInfo' //向后端默认传page和limit); //重载表格
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
            add: function () {
                layer.open({
                    type: 2
                    , title: '添加教师'
                    , content: '${ctx}/teacher/edit'
                    , maxmin: true
                    , area: ['680px', '680px']
                    , btn: ['确定', '取消']
                    , yes: function (index, layero) {
                        //点击确认触发 iframe 内容中的按钮提交
                        var iframeWindow = window['layui-layer-iframe' + index]
                            , submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");

                        iframeWindow.layui.form.on('submit(layuiadmin-app-form-submit)', function (data) {
                            var field = data.field; //获取提交的字段
                            // var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            var json = {
                                teaId: field.id
                                , teaName: field.name
                                , teaSex: field.sex
                                , teaAge: field.age
                                , teaTitle: field.title
                                , teaMajorName: field.major
                                , teaCollegeName: field.college
                                , teaPhone: field.phone
                                , teaRemark: field.remark
                            };

                            //提交 Ajax 成功后，关闭当前弹层并重载表格
                            $.ajax({
                                data: json,
                                type: 'post',
                                url: "${ctx}/teacher/insert",
                                success: function (data) {
                                    if (data.data == "teaIdExist") {
                                        return layer.msg('对不起，该工号已存在！');
                                    } else if (data.data == "insertSuccess") {
                                        layer.msg('添加成功', {
                                            icon: 1
                                            , time: 1000
                                        });
                                        table.reload('teaInfoQuery', {
                                            url: '${ctx}/teacher/showAllTeaInfo' //向后端默认传page和limit); //重载表格
                                            , request: {
                                                pageName: 'pageNum',
                                                limitName: 'pageSize'  //如不配置，默认为page=1&limit=10
                                            }
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


        //监听工具条
        table.on('tool(LAY-app-content-comm)', function (obj) {
            var data = obj.data;

            if (obj.event === 'del') {
                layer.confirm('确定删除此教师吗？', function (index) {

                    //提交删除ajax
                    $.ajax({
                        data: data,
                        type: 'post',
                        url: "${ctx}/teacher/deleteOne",
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
                    title: '编辑教师'
                    ,
                    content: '${ctx}/teacher/edit?teaId=' + data.teaId + '&teaCollegeName=' + data.teaCollegeName + '&teaMajorName=' + data.teaMajorName
                    ,
                    maxmin: true
                    ,
                    area: ['680px', '680px']
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
                                teaId: field.id
                                , teaName: field.name
                                , teaSex: field.sex
                                , teaAge: field.age
                                , teaTitle: field.title
                                , teaMajorName: field.major
                                , teaCollegeName: field.college
                                , teaPhone: field.phone
                                , teaRemark: field.remark
                            };


                            $.ajax({
                                data: json,
                                type: 'post',
                                url: "${ctx}/teacher/updateInfo?teaOriId=" + field.oriId,
                                success: function (data) {
                                    if (data.data == "teaIdExist") {
                                        return layer.msg('对不起，该工号已存在！');
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
                        othis.find('input[name="id"]').val(data.teaId);
                        othis.find('input[name="name"]').val(data.teaName);
                        othis.find('input[name="sex"][value="男"]').attr("checked", data.teaSex == '男' ? true : false);
                        othis.find('input[name="sex"][value="女"]').attr("checked", data.teaSex == '女' ? true : false);
                        othis.find('input[name="age"]').val(data.teaAge);
                        othis.find('select[name="title"]').val(data.teaTitle);
                        othis.find('input[name="phone"]').val(data.teaPhone);
                        // 学院，专业，班级名称通过url传值给后端，后端存于model，如果通过iframe传值，无法在子页面启动数据库查询所有学院名称填充select的ajax
                        // othis.find('select[name="college"]').val(data.stuCollegeName);
                        // othis.find('select[name="major"]').val(data.stuMajorName);
                        // othis.find('select[name="class"]').val(data.stuClassName);
                        othis.find('textarea[name="remark"]').val(data.teaRemark);
                    }
                });
            }
        });

    });
</script>
</body>
</html>
