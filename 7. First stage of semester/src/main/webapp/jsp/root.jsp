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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/root.css">
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
<h1>Здесь пока происходят эксперименты, все появится, но чууть позднее</h1>
    <a href="/profile">Профиль</a>
    <button onclick="sendCountRequest()">Count</button>
    <div id="counter">Counter value from server</div>
</body>
</html>
