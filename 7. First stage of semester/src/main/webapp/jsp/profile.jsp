<%@ page import="ru.itis.dto.UserDto" %><%--
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
</head>
<body>
    <div>
        <p>
            <%
                UserDto dto = (UserDto) request.getAttribute("userDtoForJsp");
                System.out.println(dto);
            %>
        </p>
    </div>
</body>
</html>
