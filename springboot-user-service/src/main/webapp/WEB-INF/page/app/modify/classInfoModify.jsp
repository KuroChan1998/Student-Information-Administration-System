<%--
  Created by IntelliJ IDEA.
  User: 13681864361
  Date: 2019/4/28
  Time: 15:57
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
                <%--学院和专业联动复选框--%>
                <div class="layui-inline">
                    <label class="layui-form-label">学院</label>
                    <div class="layui-input-inline">
                        <select id="classCollege" name="classCollege" lay-search lay-filter="college">
                            <option value="">请输入或选择学院</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">专业</label>
                    <div class="layui-input-inline">
                        <select id="classMajor" name="classMajor" lay-filter="major" lay-search>
                            <option value="">请输入或选择专业</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">班级</label>
                    <div class="layui-input-inline">
                        <select id="classClass" name="classClass" lay-filter="class" lay-search>
                            <option value="">请输入或选择班级</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">年级</label>
                    <div class="layui-input-inline">
                        <select id="classGrade" name="classGrade">
                            <option value="">请选择年级</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">筛选排序</label>
                    <div class="layui-input-inline">
                        <select id="condition1" name="condition1" lay-filter="condition1">
                            <option value="">请选择要排序的类别</option>
                            <option value="classGrade">按年级排序</option>
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
                <button class="layui-btn layuiadmin-btn-list" data-type="batchdel">删除</button>
                <button class="layui-btn layuiadmin-btn-list" data-type="add">添加</button>
                <button class="layui-btn layuiadmin-btn-comm" data-type="batchdel" style="background-color: #FFB800"
                        id="query-all-info">查询所有信息
                </button>
            </div>
            <table id="classInfoQuery" lay-filter="LAY-app-content-comm"></table>
            <script type="text/html" id="test-table-operate-barDemo1">
                <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="moni_detail">查看班长详情</a>
            </script>
            <script type="text/html" id="test-table-operate-barDemo2">
                <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="tea_detail">查看班主任详情</a>
            </script>
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

        //从数据库异步获取年级数据填充到年级select框中
        $.ajax({
            type: "get",
            url: "${ctx}/grade/getGradeName",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var json = data[i];
                    var str = "";
                    str += '<option value="' + json.gradeName + '">' + json.gradeName + '</option>';
                    $("#classGrade").append(str);
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
        //从数据库异步获取班级数据填充到班级select框中
        $.ajax({
            type: "get",
            data: {majorName: $(this).attr("lay-value")},
            url: "${ctx}/class/getClassNameByMajor",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var json = data[i];
                    $("#classClass").append('<option value="' + json.className + '">' + json.className + '</option>');
                }
                form.render('select');
            }
        });


        //联动监听select
        form.on('select(college)', function (data) {
            $("#classClass").val("");
            //获取部门的ID通过异步查询子集
            $("#classMajor").empty();
            $("#classMajor").append('<option value="">请输入或选择专业</option>');
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
        form.on('select(major)', function (data) {
            //获取部门的ID通过异步查询子集
            $("#classClass").empty();
            $("#classClass").append('<option value="">请输入或选择班级</option>');
            var major_name = $(this).attr("lay-value");
            $.ajax({
                type: "get",
                data: {majorName: major_name},
                url: "${ctx}/class/getClassNameByMajor",
                success: function (data) {
                    for (var i = 0; i < data.length; i++) {
                        var json = data[i];
                        $("#classClass").append('<option value="' + json.className + '">' + json.className + '</option>');
                    }
                    form.render('select');
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

        //方法级渲染
        table.render({
            elem: '#classInfoQuery'
            , url: '${ctx}/class/showAllClassInfo' //向后端默认传page和limit
            ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'classId', title: '班号', hide: true}
                , {field: 'className', title: '班级', sort: true, fixed: true}
                , {field: 'classStuName', title: '班长'}
                , {field: 'classStuNum', title: '班长学号', hide: true}
                , {width: 120, title: '操作', toolbar: '#test-table-operate-barDemo1'}
                , {field: 'classTeaName', title: '班主任'}
                , {field: 'classTeaNum', title: '班主任工号', hide: true}
                , {width: 130, title: '操作', toolbar: '#test-table-operate-barDemo2'}
                , {field: 'classGradeName', title: '年级', sort: true}
                , {field: 'classMajorName', title: '专业', sort: true}
                , {field: 'classCollegeName', title: '学院', sort: true}
                , {field: 'classRemark', title: '班级描述', width: 250}
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
        });

        //工具条监听
        table.on('tool(LAY-app-content-comm)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if (obj.event === 'moni_detail') {
                var index = layer.open({
                    type: 2
                    , title: '查看班长'
                    , content: '/student/stuInfo?stuNum=' + data.classStuNum
                    , maxmin: true
                    , yes: function (index, layero) {
                        layer.close(index); //关闭弹层
                    }
                });
                layer.full(index);
            } else if (obj.event === 'tea_detail') {
                var index = layer.open({
                    type: 2
                    , title: '查看班主任'
                    , content: '/teacher/teaInfo?teaNum=' + data.classTeaNum
                    , maxmin: true
                    , yes: function (index, layero) {
                        layer.close(index); //关闭弹层
                    }
                });
                layer.full(index);
            } else if (obj.event === 'del') {
                layer.confirm('确定删除此班级吗？删除后其下所有学生也将被删除！！', function (index) {

                    //提交删除ajax
                    $.ajax({
                        data: data,
                        type: 'post',
                        url: "${ctx}/class/deleteOne?csrfToken=${csrfToken}",
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
                var index = layer.open({
                    type: 2
                    ,
                    title: '编辑班级'
                    ,
                    content: '${ctx}/class/edit?classId=' + data.classId + '&className=' + data.className + '&classCollegeName=' + data.classCollegeName + '&classMajorName=' + data.classMajorName + '&classGradeName=' + data.classGradeName
                    ,
                    maxmin: true
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
                                classOriName: field.oriName
                                , classOriId: field.oriId
                                , className: field.name
                                , classStuNum: field.moniId
                                , classTeaNum: field.teaId
                                , classGradeName: field.grade
                                , classMajorName: field.major
                                , classCollegeName: field.college
                                , classRemark: field.remark
                            };

                            $.ajax({
                                data: json,
                                type: 'post',
                                url: "${ctx}/class/updateInfo?csrfToken=${csrfToken}",
                                success: function (data) {
                                    if (data.data == "classNameExist") {
                                        return layer.msg('对不起，该班级名称已存在！');
                                    } else if (data.data == "classMoniNumNotExist") {
                                        return layer.msg('对不起，该指定为班长的学生学号不存在！');
                                    } else if (data.data == "classMoniNumRepeat") {
                                        return layer.msg('对不起，该指定为班长的学生已经担任了某班班长！');
                                    } else if (data.data == "classTeaNumNotExist") {
                                        return layer.msg('对不起，该指定为班主任的教师工号不存在！');
                                    } else if (data.data == "classTeaNumRepeat") {
                                        return layer.msg('对不起，该指定为班主任的教师已经担任了某班班主任！');
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
                        othis.find('input[name="name"]').val(data.className);
                        othis.find('input[name="moniId"]').val(data.classStuNum);
                        othis.find('input[name="teaId"]').val(data.classTeaNum);
                        // 学院，专业，班级名称通过url传值给后端，后端存于model，如果通过iframe传值，无法在子页面启动数据库查询所有学院名称填充select的ajax
                        // othis.find('select[name="college"]').val(data.stuCollegeName);
                        // othis.find('select[name="major"]').val(data.stuMajorName);
                        // othis.find('select[name="class"]').val(data.stuClassName);
                        othis.find('textarea[name="remark"]').val(data.classRemark);
                    }
                });
                layer.full(index);
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
                    classGradeName: field.classGrade
                    , className: field.classClass
                    , classMajorName: field.classMajor
                    , classCollegeName: field.classCollege
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


        var $ = layui.$, active = {
            batchdel: function () {
                var checkStatus = table.checkStatus('classInfoQuery')
                    , checkData = checkStatus.data; //得到选中的数据

                if (checkData.length === 0) {
                    return layer.msg('请选择数据');
                }

                // console.log(JSON.stringify(checkData));
                // console.log(checkStatus);
                // console.log(checkData);
                layer.confirm('确定要删除' + checkData.length + '个班级吗？删除后其下所有学生也将被删除！！', function (index) {
                    //执行 Ajax 后重载
                    $.ajax({
                        type: 'post',
                        data: {classes: JSON.stringify(checkData)},
                        url: "${ctx}/class/deleteMany?csrfToken=${csrfToken}",
                        success: function (data) {
                            if (data.data == "deleteSuccess") {
                                table.reload('classInfoQuery', {
                                    url: '${ctx}/class/showAllClassInfo' //向后端默认传page和limit); //重载表格
                                    , request: {
                                        pageName: 'pageNum',
                                        limitName: 'pageSize'  //如不配置，默认为page=1&limit=10
                                    }
                                    , page: {
                                        curr: 1 //重新从第 1 页开始
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
                var index = layer.open({
                    type: 2
                    , title: '添加班级'
                    , content: '${ctx}/class/add'
                    , maxmin: true
                    , btn: ['确定', '取消']
                    , yes: function (index, layero) {
                        //点击确认触发 iframe 内容中的按钮提交
                        var iframeWindow = window['layui-layer-iframe' + index]
                            , submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");

                        iframeWindow.layui.form.on('submit(layuiadmin-app-form-submit)', function (data) {
                            var field = data.field; //获取提交的字段
                            // var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            var json = {
                                className: field.name
                                , classStuNum: field.moniId
                                , classTeaNum: field.teaId
                                , classGradeName: field.grade
                                , classMajorName: field.major
                                , classCollegeName: field.college
                                , classRemark: field.remark
                            };
                            //提交 Ajax 成功后，关闭当前弹层并重载表格
                            $.ajax({
                                data: json,
                                type: 'post',
                                url: "${ctx}/class/insert?csrfToken=${csrfToken}",
                                success: function (data) {
                                    if (data.data == "classNameExist") {
                                        return layer.msg('对不起，该班级名称已存在！');
                                    } else if (data.data == "classMoniNumNotExist") {
                                        return layer.msg('对不起，该指定为班长的学生学号不存在！');
                                    } else if (data.data == "classMoniNumRepeat") {
                                        return layer.msg('对不起，该指定为班长的学生已经担任了某班班长！');
                                    } else if (data.data == "classTeaNumNotExist") {
                                        return layer.msg('对不起，该指定为班主任的教师工号不存在！');
                                    } else if (data.data == "classTeaNumRepeat") {
                                        return layer.msg('对不起，该指定为班主任的教师已经担任了某班班主任！');
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
                layer.full(index);
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