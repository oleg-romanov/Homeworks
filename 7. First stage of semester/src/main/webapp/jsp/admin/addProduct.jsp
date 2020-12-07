<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: olegromanov
  Date: 12/6/20
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Админ продукты</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header>
    <ul class="menu-main">
        <li><a href="/">На сайт</a> </li>
    </ul>
</header>

<div class="container">
    <h1>Админ - Панель. Добавить Продукт</h1>
    <form action="/admin/products/add" enctype="multipart/form-data" method="post">
        <input type="text" name="name" placeholder="Название продукта"/>
        <input type="text" name="description" placeholder="Описание"/>
        <select name="categoryId">
            <c:forEach items="${categories}" var="category">
                <option value="${category.getId()}">${category.getName()}</option>
            </c:forEach>
        </select>
        <input type="file" name="image" accept="image/jpeg,image/png" required>
        <input type="submit">
    </form>
</div>

</body>
</html>
