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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/signUp.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
</head>
</head>
<body>
<%--<form action="/signUp" method="post">--%>
<%--    <input name="email" type="email" placeholder="Email">--%>
<%--    <input name="firstName" placeholder="Name">--%>
<%--    <input name="lastName" placeholder="Surname">--%>
<%--    <input name="password" type="password" placeholder="Password">--%>
<%--    <input type="submit" value="SignUp">--%>
<%--</form>--%>

<div class="container">
<div class="row">
    <div class="col-md-offset-3 col-md-6">
        <form class="form-horizontal" action="/signUp" method="post">
            <span class="heading">РЕГИСТРАЦИЯ</span>
            <div class="form-group">
                <input name="firstName" placeholder="Name" class="form-control" required aria-placeholder="Укажите ваше имя">

            </div>
            <div class="form-group">
                <input name="lastName" placeholder="Surname" class="form-control" required aria-placeholder="Укажите вашу фамилию">

            </div>
            <div class="form-group">
                <input name="email" type="email" class="form-control" placeholder="E-mail" required aria-placeholder="Введите вашу почту">

            </div>
            <div class="form-group help">
                <input name="password" type="password" class="form-control" placeholder="Password" required aria-placeholder="Укажите ваш пароль">

            </div>
            <div class="form-group">
                <button type="submit" class="btn-right" value="SignIn">РЕГИСТРАЦИЯ</button>
            </div>
        </form>
    </div>

</div><!-- /.row -->
</div><!-- /.container -->

</body>
</html>
