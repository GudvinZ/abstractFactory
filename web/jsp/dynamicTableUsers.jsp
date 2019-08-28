<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.08.2019
  Time: 7:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>dynamicTable</title>
</head>
<body>
<table border="1" rules="rows">
    <c:forEach var="user" items="${users}">
        <tr>
            <td>
                <c:out value="User: ${user.getName()}"/><br>
                <c:out value="Login: ${user.getLogin()}"/><br>
                <c:out value="Password: ${user.getPassword()}"/><br>
                <form action="<c:url value="/update/"/>" method="get" style="display:inline">
                    <button type="submit" name="id" value=${user.getId()}>Update</button>
                </form>
                <form action="<c:url value="/delete/"/>" method="post" style="display:inline">
                    <button type="submit" value="${user.getId()}" name="id">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
