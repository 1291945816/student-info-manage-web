<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head >
    <meta charset="UTF-8">
    <title>学生管理系统</title>
    <meta name="author" content="kilig">
    <link rel="stylesheet" type="text/css" href="./lib/layui/layui-v2.5.6/layui/css/layui.css" media="all">
    <script type="text/javascript" src="./lib/layui/layui-v2.5.6/layui/layui.js"></script>
    <link rel="icon" href="./lib/images/icon.ico" type="image/icon">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.4.1/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="./lib/css/student.css">

</head>
<body>
<!--导航栏-->
<header>

    <ul class="layui-nav layui-bg-green" style="text-align: right; font-size: 20px">
        <li class="layui-nav-item" style="right: 75em" >
            <a style="font-size: 25px;color: aliceblue">学生信息操作界面</a>
        </li>
        <li class="layui-nav-item"><span href="#">欢迎您,<%= request.getSession().getAttribute("username")%></span></li>
        <li class="layui-nav-item"><a href="#">首页</a></li>
        <li class="layui-nav-item"><a href="#">退出</a></li>
    </ul>

</header>
<!--主体内容-->


<main>
    <div class="bodycontent">
    <div class="one_menu">
    <ul id="menu" class="layui-nav layui-nav-tree layui-inline layui-nav-tree layui-nav-side" lay-filter="demo" style="margin-right: 10px;">
        <li class="layui-nav-item layui-nav-itemed">
            <a href="javascript:">用户信息</a>
            <dl class="layui-nav-child">
                <dd><a onclick="show_info()">查看个人信息</a></dd>
                <dd><a href="javascript:">修改个人信息</a></dd>
                <dd><a onclick="show_classInfo()">查看班级信息</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item">
            <a href="javascript:">课程信息</a>
            <dl class="layui-nav-child">
                <dd><a onclick="show_allCourseInfo()">查看已选课程</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item"><a  onclick="show_grade()" >查看成绩</a></li>
    </ul>
    </div>
    <div id="content" >

    </div>
    </div>
</main>
</body>
<script type="text/javascript" src="lib/js/student.js"></script>
</html>
