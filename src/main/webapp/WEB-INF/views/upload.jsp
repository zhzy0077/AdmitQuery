<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Zheng
  Date: 16/7/8
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="<c:url value="/file/template.xlsx"/>">点击下载模板</a>
<form method="post" enctype="multipart/form-data">
    <input type="file" name="file" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
    <input type="submit">
</form>
</body>
<script src="http://cdn.bootcss.com/jquery/3.0.0/jquery.min.js"></script>
</html>
