<%@ page import="ru.itis.model.Product" %><%--
  Created by IntelliJ IDEA.
  User: olegromanov
  Date: 11/25/20
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        Product product = (Product) request.getAttribute("product");
    %>
    <title><%=product.getName()%></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/detailProduct.css">
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
        <img style="width: 200px" src="/images/<%=product.getImageId()%>" alt="img"/>
        <h1><%=product.getName()%></h1>
        <p>
            <%=product.getDescription()%>
        </p>
    </div>


</body>
</html>
