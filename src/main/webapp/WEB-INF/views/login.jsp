<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Zheng
  Date: 16/7/8
  Time: 23:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- login&index page -->
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>南邮本科招生|后台管理|登录</title>
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css">
    <link rel='stylesheet' href='<c:url value = "/css/jquery-ui.css"/>'>
    <link rel='stylesheet prefetch' href='<c:url value= "/css/bootstrap.min.css"/>'>
    <link rel="stylesheet" href="<c:url value="/css/login.css"/>" media="screen" type="text/css"/>
    <script src="<c:url value="/js/modernizr.js"/>"></script>
</head>
<body class="login-page">
<div class="login-form">
    <div class="login-content" style="color:white;"><h3>南邮本科招生|后台管理</h3></div>
    <div class="login-content">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">
                    <i class="fa fa-user"></i>
                </div>
                <input type="text" class="form-control" name="username" id="name" placeholder="Username"
                       autocomplete="off"/>
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">
                    <i class="fa fa-key"></i>
                </div>
                <input type="password" class="form-control" name="password" id="password" placeholder="Password"
                       autocomplete="off"/>
            </div>
        </div>
        <div class="form-group">
            <button class="btn btn-primary btn-block btn-login" id="btnLogin">
                <i class="fa fa-sign-in"></i>
                Login In
            </button>
        </div>
    </div>
</div>
<script src="<c:url value = "/js/jquery-1.11.3.min.js"/>"></script>
<script src="<c:url value = "/js/bootstrap.min.js"/>"></script>
<script type="text/javascript">
    $("#btnLogin").click(function () {
        var name = $("#name").val();
        var password = $("#password").val();
        $.post("<c:url value="/admin"/>",
                {"username": name, "password": password},
                function (back) {
                    console.log(back);
                    if (back.status == 0) {
                        window.location.href = "<c:url value="/operate"/> ";
                    }
                    else if (back.status == 1) {
                        alert("用户名或密码错误，请重试");
                        window.location.reload();
                    }
                    else {
                        alert("未知错误，请联系管理员！");
                        window.location.reload();
                    }
                });
    });
</script>
</body>
</html>