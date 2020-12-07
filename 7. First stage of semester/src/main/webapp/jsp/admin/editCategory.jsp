<%--
  Created by IntelliJ IDEA.
  User: olegromanov
  Date: 12/6/20
  Time: 00:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Редактировать</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/categories.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header>
    <ul class="menu-main">
        <li><a href="/">На сайт</a> </li>
        <li><a href="/admin">Все категории</a> </li>
    </ul>
</header>

<div class="container">
    <h1>Админ - Панель. Редактировать категорию ${category.getName()}</h1>
    <form action="/admin/categories/edit/${category.getId()}" enctype="multipart/form-data" method="post">
        <input type="text" name="name" value="${category.getName()}" required>
        <input type="hidden" name="imageId" value="${category.getImageId()}"/>
        <input type="file" name="image" accept="image/jpeg,image/png">
        <input type="submit">
    </form>
</div>
</body>
</html>

