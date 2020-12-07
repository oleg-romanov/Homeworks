<%--
  Created by IntelliJ IDEA.
  User: olegromanov
  Date: 12/5/20
  Time: 23:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Админ Категории</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/categories.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header>
    <ul class="menu-main">
        <li><a href="/">На сайт</a> </li>
        <li><a class="current" href="admin/categories/add">Добавить категорию</a> </li>
    </ul>
</header>

<div class="container">
    <h1>Админ - Панель. Добавить категорию</h1>
    <form action="/admin/categories/add" enctype="multipart/form-data" method="post">
        <input type="text" name="name"/>
        <input type="file" name="image" accept="image/jpeg,image/png" required>
        <input type="submit">
    </form>
</div>
</body>
</html>

