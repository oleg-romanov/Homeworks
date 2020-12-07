<%@ page import="ru.itis.dto.UserDto" %>
<%@ page import="java.io.PrintWriter" %>
<%--
  Created by IntelliJ IDEA.
  User: olegromanov
  Date: 11/14/20
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
    <link rel="script" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js">
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<body>
<%
    UserDto dto = (UserDto) request.getAttribute("userDtoForJsp");
%>

<header>
    <ul class="menu-main">
        <li><a href="/">Главная</a> </li>
        <li><a href="/categories">Каталог</a></li>
        <li><a href="/about">About</a></li>
        <li><a href="/profile" class="current">Профиль</a></li>
    </ul>
</header>

    <script>
        function sendUser() {
            let email = $('#newEmail').val();
            let firstName = $('#newFirstName').val();
            let lastName = $('#newLastName').val();

            let data = {
                "email": email,
                "firstName": firstName,
                "lastName": lastName
            };

            let request = $.ajax({
                type: "POST", // метод запроса
                url: "/profile", //урл запроса
                data: JSON.stringify(data), // данные для отправки из JSON-объекта превращаем в JSON-строку
                //тип данных, который мы отпралвяем
                // dataType: "json",
                // contentType: "application/json"
            });

            request.done(function () {
                if (email !== '') {
                    $('#emailLabel').html(email);
                    $('#newEmail').val('');
                }
                if (firstName !== '') {
                    $('#nameLabel').html(firstName);
                }
                if (lastName !== '') {
                    $('#surnameLabel').html(lastName);
                }
            });

            request.fail(function () {
                alert("Fail")
            });
        }
    </script>

    <div id="settings" class="container">
        <h1>Настройки</h1>

            <div class="image">
                <img style="width: 400px; border-radius: 40px; margin: 40px 0;" alt="avatar" src="/images/<%=dto.getImageId()%>"/>
                <form action="/image-upload?profile_id=<%=dto.getId()%>" method="post" enctype="multipart/form-data">
                    <input name="image" type="file" accept="image/jpeg,image/png" required>
                    <input type="submit">
                </form>
            </div>

            <div class="email">
                <h3>Ваш текущий адрес электронной почты:</h3>
                <h4 id="emailLabel"><%=dto.getEmail()%></h4>
                <h3>Если вы хотите изменить электронную почту, укажите в поле ниже новый адрес электронной почты:</h3>
                <input type="text" id="newEmail" placeholder="Новый адрес электронной почты">
            </div>

            <div class="fio">
                <h3>Ваше имя</h3>
                <h4 id="nameLabel"><%=dto.getFirstName()%></h4>
                <h3>Если вы хотите изменить имя, введите новое имя в поле ниже:</h3>
                <input type="text" id="newFirstName" placeholder="Обновленное имя">

                <h3>Ваша фамилия</h3>
                <h4 id="surnameLabel"><%=dto.getLastName()%></h4>
                <h3>Если вы хотите изменить имя, введите новое имя в поле ниже:</h3>
                <input type="text" id="newLastName" placeholder="Обновленная фамилия">
            </div>
                <button onclick="sendUser()">Изменить</button>
    </div>
</body>
</html>
