<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Zheng
  Date: 16/7/8
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<script>
    alert("<c:out value="${status}"/>");
    window.location = "<c:url value="/operate"/>";
</script>
</body>
</html>
