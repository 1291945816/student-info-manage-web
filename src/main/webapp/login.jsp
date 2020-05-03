<%--
Created by IntelliJ IDEA.
User: mac12
Date: 2020/4/26
Time: 17:51
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>学生信息管理系统</title>
    <meta name="author" content="kilig">
    <link rel="stylesheet" type="text/css" href="./lib/layui/layui-v2.5.6/layui/css/layui.css">
    <script src="./lib/layui/layui-v2.5.6/layui/layui.js"></script>

    <link rel="icon" href="./lib/images/icon.ico" type="image/icon">
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.js"></script>
    <link rel="stylesheet" href="./lib/css/login.css">
</head>
<body class="body">
<!-- 别看了 写的是真的烂，真不会调位置，全程前端框架改改改，整自闭了都，就一个字，难受啊-->
<!--导航栏-->
<header>
<ul class="layui-nav layui-bg-green" style="text-align: right; font-size: 20px">
    <li class="layui-nav-item" style="right: 75em" >
        <a style="font-size: 30px;color: aliceblue">学生信息管理系统</a>
    </li>
    <li class="layui-nav-item"><a href="#">首页</a></li>
    <li class="layui-nav-item"><a href="login.jsp">登陆</a></li>
    <li class="layui-nav-item"><a href="#">关于系统</a></li>
</ul>
</header>

<!--主体内容-->
<main >
    <div class="container">

        <form class="layui-form" id="form_login" method="post">
            <h1 class="welcome" style="color: whitesmoke;font-size: 40px ;position: relative; text-align: center;text-shadow: 0 0 0.3em #FFB800;top: -10px">欢迎您,请登陆</h1>
            <div class="layui-form-item">
                <br />
                <label class="layui-form-label" style="color: whitesmoke;font-size: 20px ">用户名</label>
                <div class="layui-input-block">
                    <input type="text"  name="username" required  lay-verify="required" placeholder="请输入用户名"  class="layui-input" style="width:200px;">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="color: whitesmoke;font-size: 20px " >密码</label>
                <div class="layui-input-block">
                    <input type="password" name="password" required lay-verify="required" placeholder="请输入密码"   style="width:200px;" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="color: whitesmoke;font-size: 20px ">验证码</label>
                <div class="layui-input-block">
                    <input type="text" name="checkedCode" required lay-verify="required" placeholder="请输入验证码（不区分大小写）" autocomplete="off"  style="width:200px;" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <img id="checkcode" alt="加载失败" src="/test/checkcode">
                    <a href="login.jsp" style="color: white">图片看不清?点击试试看</a>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <div class="layui-input-block">
                        <table border="0" style="border-collapse: separate;border-spacing: 10px">
                            <tr>
                                <td><button  class="layui-btn" type="submit"  formaction="userLoginServlet" >学生登陆</button>
                                <td> <button  class="layui-btn" type="submit"  formaction="adminLoginServlet" >教师登陆</button>
                            </tr>
                        </table>

                    </div>
                </div>
            </div>
            <!--输出错误消息-->
            <p style="font-size: 20px;color: red ;text-shadow: 0 0 0.2em #F87, 0 0 0.2em #F87"><%= request.getAttribute("error_msg")== null?" ":request.getAttribute("error_msg").toString()%></p>
        </form>


    </div>

</main>


<!-- 页脚-->
<footer >
    <ul class="layui-nav layui-bg-green" style="text-align: center;  font-size: 20px">
        <li class="layui-nav-item"><a href="login.jsp">帮助与反馈</a></li>
        <li class="layui-nav-item"><a href="mailto:huangpisong@kilig.ink">联系我们</a></li>
        <div class="copyright">
            &copy; Copyright. All rights reserved. Design by <a href="http://www.github.com/1291945816/">kilig</a>
        </div>
    </ul>
</footer>




</body>
<script src="./lib/js/login.js" ></script>
</html>