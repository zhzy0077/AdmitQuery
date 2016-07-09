<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Zheng
  Date: 16/7/9
  Time: 00:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<jsp:useBean id="student" class="student.model.Student"/>--%>
<html>
<head>
    <meta charset="UTF-8">
    <title>南邮本科招生|后台管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/css/operate.css"/>" media="screen" type="text/css"/>
</head>
<body>

<p class="navbar-text navbar-right welcome">欢迎您，管理员
    <a href="<c:url value="/logout"/>">登出</a>
</p>
<ul class="nav nav-tabs">
    <li role="presentation" class="active"><a href="#">录取系统</a></li>
</ul>
<form style="margin-top:20px" method="post" enctype="multipart/form-data" action="<c:url value="/upload"/>">
    <div class="form-group">
        <label for="exampleInputFile">导入录取数据文件</label>
        <a href="<c:url value="/file/template.xlsx"/>">模板下载</a>
        <input type="file" id="exampleInputFile" name="file"
               accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
</form>
<div id="status"></div>
<form action="<c:url value="/operate"/>" method="post">
    <div class="container" style="width:260px;">
        <input class="form-control" style="width: 300px;position: absolute" placeholder="考生号" name="studentId">
        <button class="btn btn-primary" id="search" type="submit">查询</button>
    </div>
</form>

<div class="container" style="width: 60%;margin-top: 20px">
    <table class="table table-striped table-hover">
        <tr>
            <th width="10%">姓名</th>
            <th width="20%">考生号</th>
            <th width="20%">身份证</th>
            <th>专业</th>
            <th>是否具有贝尔学院资格</th>
            <th>ems</th>
            <th width="80px">删除</th>
        </tr>
        <tr>
            <td width="7%"><c:out value="${student.name}"/></td>
            <td width="20%" id="studentId"><c:out value="${student.studentId}"/></td>
            <td width="20%"><c:out value="${student.idCard}"/></td>
            <td><c:out value="${student.major}"/></td>
            <td><c:out value="${student.bell}"/></td>
            <td><c:out value="${student.ems}"/></td>
            <td width="80px"><a class="btn btn-danger" role="button" href="javascript:void(0)" onclick='del()'>删除</a>
            </td>
        </tr>
    </table>
</div>
<script src="<c:url value="/js/jquery-1.11.3.min.js"/>"></script>
<script type="text/javascript">
    function del() {
        var question = confirm("你确认要删除此条记录吗？");
        if (question) {
            var studentId = $("#studentId").html();
            $.post("<c:url value="/delete"/>",
                    {"studentId": studentId},
                    function (back) {
                        if (back.status == 0) {
                            alert("删除成功！");
                            location.reload();//在html中移除对应项
                        }
                        else {
                            alert("删除失败！");
                            location.reload();
                        }
                    })
        }
    }
</script>
</body>
</html>