<%--
  Created by IntelliJ IDEA.
  User: Marsel
  Date: 09.10.2020
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="application/javascript">
        function sendCountRequest() {
            $.ajax({
                url: '/',
                method: "POST"
            }).done(function( msg ) {
                alert( "From server: " + msg );
            });
        }
    </script>
</head>
<body>
    <header>
        <ul class="menu-main">
            <li><a href="/" class="current"> Главная</a> </li>
            <li><a href="/categories">Каталог</a></li>
            <li><a href="/about">About</a></li>
            <li><a href="/profile">Профиль</a></li>
        </ul>
    </header>


<div class="container">
    <h1>ОЛЕГЕА. Дома всегда праздник.</h1>
    <button onclick="sendCountRequest()">Чисто Кликер</button>
    <div class="text" style="font-size: large">Давай играть!
        Теперь, когда мы стали больше времени проводить дома, мы можем чаще играть с нашими детьми. Превратить дом в игровую площадку — проще простого! И не важно, чем именно они заняты — рисованием, воображаемым путешествием или переодеванием в любимого героя — игра помогает им развиваться и расти.
    </div>
</div>

</body>
</html>
