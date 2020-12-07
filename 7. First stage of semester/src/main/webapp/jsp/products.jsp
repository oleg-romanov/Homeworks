<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.itis.model.Product" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: olegromanov
  Date: 11/24/20
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%=request.getAttribute("name")%></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/products.css">
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
    <h1><%=request.getAttribute("name")%></h1>

    <c:forEach items="${products}" var="product">
        <div class="product">
            <div class="product-photo">
            <img style="width: 200px" src="/images/${product.getImageId()}" alt="img"/>
            </div>
            <a class="productName" href="${pageContext.request.contextPath}/product/${product.getId()}"><c:out value="${product.getName()}"/></a>
        </div>
    </c:forEach>
</div>

</body>
</html>
