<%@ page import="ru.itis.models.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: olegromanov
  Date: 14.10.2020
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h1>FROM JSP USERS</h1>
    <div>
        <table>
            <tr>
                <th>ID</th>
                <th>FIRST NAME</th>
                <th>LAST NAME</th>
                <th>AGE</th>
            </tr>
            <%
                List<User> users = (List<User>) request.getAttribute("usersForJsp");
                for(User user : users) {
            %>
            <tr>
                <td><%=user.getId()%></td>
                <td><%=user.getFirstName()%></td>
                <td><%=user.getLastName()%></td>
                <td><%=user.getAge()%></td>
            </tr>
            <%}%>
        </table>
    </div>

</body>
</html>
