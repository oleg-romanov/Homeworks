<%@ page import="java.util.List" %>
<%@ page import="ru.itis.model.Category" %>
<%@ page import="java.util.Optional" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: olegromanov
  Date: 11/21/20
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Категории</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/categories.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <header>
        <ul class="menu-main">
            <li><a href="/">Главная</a> </li>
            <li><a href="/categories" class="current">Каталог</a></li>
            <li><a href="/about">About</a></li>
            <li><a href="/profile">Профиль</a></li>
        </ul>
    </header>

    <div class="container">
        <h1>Категории</h1>
    <div class="categories">
        <c:forEach items="${category}" var="cat">
            <div class="category">
                <div class="category-photo">
                <img style="width: 200px" src="/images/${cat.getImageId()}" alt="img"/>
                </div>
                <a class="categoryName" href="${pageContext.request.contextPath}/categories?id=${cat.getId()}"><c:out value="${cat.getName()}"/></a>
            </div>
        </c:forEach>
    </div>
    </div>

</body>
</html>
