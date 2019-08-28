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
    <title>update</title>
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="service.UserService" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 23.08.2019
  Time: 1:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>update</title>
</head>
<body>
<c:set var="users" scope="session" value="${UserService.getInstance().getAllUsers()}"/>
<p>
<form action="/delete/" method="get" style="display: inline">
    Users: <button type="submit">Delete all users</button>
</form>
</p>
<table border="1" rules="rows">
    <c:forEach var="user" items="${users}">
        <tr>
            <c:if test="${user.getId()!=id}">
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
            </c:if>
            <c:if test="${user.getId()==id}">
                <td>
                    <c:out value="User: ${user.getName()}"/>
                    <form action="/update/" method="post" style="display: inline">
                        <table>
                            <tr>
                                <td>
                                    Login:<br>
                                    Password:<br>
                                    Name:
                                </td>
                                <td>
                                    <label>
                                        <input type="text" name="login" value="${user.getLogin()}">
                                    </label><br>
                                    <label>
                                        <input type="password" name="password" value="${user.getPassword()}">
                                    </label><br>
                                    <label>
                                        <input type="text" name="name" value="${user.getName()}">
                                    </label>
                                </td>
                            </tr>
                        </table>
                        <button type="submit" value="${id}" name="id">Save</button>
                    </form>
                    <form action="/delete/" method="post" style="display:inline">
                        <button type="submit" value="${id}" name="id">Delete</button>
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>
</body>
</html>

</body>
</html>
