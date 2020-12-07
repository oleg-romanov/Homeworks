<%--
  Created by IntelliJ IDEA.
  User: olegromanov
  Date: 11/27/20
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ошибка</title>
</head>
<body>
    <%
        String error = (String) request.getAttribute("error");
    %>
    <h1><%=error%></h1>
</body>
</html>
