<%--
  Created by IntelliJ IDEA.
  User: olegromanov
  Date: 12/5/20
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Админ Товары</title>
    <title><%=request.getAttribute("name")%></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/products.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script>
        function sendDeleteProductRequest(name, productId, categoryId) {
            if(confirm("Удалить продукт " + name + "?")) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/admin?id=" + categoryId + "&productId=" + productId,
                    method: "DELETE"
                }).done(function() {
                    let productTag = $('#product' + productId);
                    productTag.hide(300, function() {
                        productTag.remove();
                    });
                });
            }
        }
    </script>
</head>
<body>
<header>
    <ul class="menu-main">
        <li><a href="/">На сайт</a> </li>
        <li><a href="/admin">Все категории</a> </li>
        <li><a href="/admin/products/add">Добавить продукт</a> </li>
    </ul>
</header>

<div class="container">
    <h1><%=request.getAttribute("name")%></h1>

    <c:forEach items="${products}" var="product">
        <div id="product${product.getId()}" class="product">
            <div class="product-photo">
                <img style="width: 200px" src="/images/${product.getImageId()}" alt="img"/>
            </div>
            <a class="productName" href="${pageContext.request.contextPath}/product/${product.getId()}">${product.getName()}</a>
            <a href="${pageContext.request.contextPath}/admin/products/edit/${product.getId()}">EDIT</a>
            <button onclick="sendDeleteProductRequest('${product.getName()}', '${product.getId()}', '${product.getCategoryId()}')" style="margin: 40px; color: #b03333">DELETE</button>
        </div>
    </c:forEach>
</div>
</body>
</html>
