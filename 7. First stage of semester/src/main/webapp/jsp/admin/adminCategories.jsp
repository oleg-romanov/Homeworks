<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: olegromanov
  Date: 12/5/20
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Админ Категории</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/categories.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script>
        function sendDeleteCategoryRequest(name, id) {
            if(confirm("Удалить категорию " + name + "?")) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/admin?id=" + id,
                    method: "DELETE"
                }).done(function() {
                    let categoryTag = $('#category' + id);
                    categoryTag.hide(300, function() {
                        categoryTag.remove();
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
        <li><a href="/admin/categories/add">Добавить категорию</a> </li>
    </ul>
</header>

<div class="container">
    <h1>Админ - Панель. Категории</h1>
    <div class="categories">
        <c:forEach items="${categories}" var="cat">
            <div id="category${cat.getId()}" class="category">
                <div class="category-photo">
                    <img style="width: 200px" src="/images/${cat.getImageId()}" alt="img"/>
                </div>
                <a class="categoryName" href="${pageContext.request.contextPath}/admin?id=${cat.getId()}">${cat.getName()}</a>
                <a href="${pageContext.request.contextPath}/admin/categories/edit/${cat.getId()}">EDIT</a>
                <button onclick="sendDeleteCategoryRequest('${cat.getName()}', '${cat.getId()}')" style="margin: 40px; color: #b03333">DELETE</button>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
