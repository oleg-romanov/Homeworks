<%--
  Created by IntelliJ IDEA.
  User: olegromanov
  Date: 29.10.2020
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/signUp" method="post">
    <input name="email" type="email" placeholder="Email">
    <input name="firstName" placeholder="Name">
    <input name="lastName" placeholder="Surname">
    <input name="password" type="password" placeholder="Password">
    <input type="submit" value="SignUp">
</form>
</body>
</html>
