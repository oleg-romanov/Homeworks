<%--
  Created by IntelliJ IDEA.
  User: olegromanov
  Date: 12/6/20
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <h1>Админ - Панель. Редактировать Продукт ${product.getName()}</h1>
    <form action="/admin/products/edit/${product.getId()}" enctype="multipart/form-data" method="post">
        <input type="text" name="name" value="${product.getName()}" placeholder="Наименование товара" required>
        <input type="hidden" name="imageId" value="${product.getImageId()}"/>
        <input type="file" name="image" accept="image/jpeg,image/png">
        <input type="text" name="description" placeholder="Описание Товара">
        <input type="hidden" name="categoryId" value="${product.getCategoryId()}"/>
        <input type="submit">
    </form>
</div>

</body>
</html>
