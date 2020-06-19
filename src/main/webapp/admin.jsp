 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head >
    <meta charset="UTF-8">
    <title>教务系统</title>
    <meta name="author" content="kilig">
    <link rel="stylesheet" type="text/css" href="./lib/layui/layui-v2.5.6/layui/css/layui.css">
    <script src="./lib/layui/layui-v2.5.6/layui/layui.js"></script>
    <link rel="icon" href="./lib/images/icon.ico" type="image/icon">
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="./lib/css/admin.css">
    <script type="text/html" id="con">
        <a class="layui-btn layui-btn-xs" lay-event="store">保存</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>

</head>
<body>
<!--导航栏-->
<header>
    <ul class="layui-nav layui-bg-green" style="text-align: right; font-size: 20px">
        <li class="layui-nav-item" style="right: 75em" >
            <a style="font-size: 20px;color: aliceblue ;text-align: left">教师操作界面</a>
        </li>
        <li class="layui-nav-item"><span href="#">欢迎您,<%= request.getSession().getAttribute("username")%></span></li>
        <li class="layui-nav-item"><a href="#">首页</a></li>
        <li class="layui-nav-item"><a onclick="exit()">退出</a></li>
    </ul>
</header>
<!--主体内容-->


<main>
<!--  垂直导航栏-->
    <ul class="layui-nav layui-nav-tree layui-inline layui-nav-tree layui-nav-side" lay-filter="demo" style="margin-right: 10px;">
        <li class="layui-nav-item layui-nav-itemed">
            <a href="javascript:">用户信息</a>
            <dl class="layui-nav-child">
                <dd><a onclick="show_info()">查看个人信息</a></dd>
                <dd><a onclick="changeprofile()">修改个人信息</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item">
            <a href="javascript:">课程信息</a>
            <dl class="layui-nav-child">
                <dd><a onclick="show_courseInfo()">课程信息&课程计划</a></dd>
                <dd><a onclick="addcourse()">增加课程</a></dd>
                <dd><a onclick="addcourseplan()">增加课程计划</a></dd>
                <dd><a onclick="addteachcourse()">增加可选课程</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item">
            <a href="javascript:">部门信息</a>
            <dl class="layui-nav-child">
                <dd><a onclick="addpartdemt()">增加部门</a></dd>
                <dd><a href="">查看部门</a></dd>
            </dl>
        </li>

        <li class="layui-nav-item">
            <a href="javascript:">成绩管理</a>
            <dl class="layui-nav-child">
                <dd><a href="">查看课程成绩</a></dd>
                <dd><a href="">录入成绩</a></dd>
                <dd><a href="">修改成绩</a></dd>
            </dl>
        </li>

    </ul>
<!--    主体内容区-->
    <div id="content" style="text-align: center">

    </div>
</main>
</body>
<script src="./lib/js/admin.js" ></script>
<script src="./lib/js/department.js"></script>

</html>

