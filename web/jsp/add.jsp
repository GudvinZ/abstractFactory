<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.08.2019
  Time: 4:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add</title>
</head>
<body>
<c:if test="${isEmptyForm}">
    <c:out value="all fields must be filled"/>
</c:if>
<c:if test="${isNotValidate}">
    <c:out value="change the login or password"/>
</c:if>
<form action="<c:url value="/add/"/>" method="post">
    <table>
        <tr>
            <td>
                Login:<br>
                Password:<br>
                Name:
            </td>
            <td>
                <label>
                    <input type="text" name="login">
                </label><br>
                <label>
                    <input type="password" name="password">
                </label><br>
                <label>
                    <input type="text" name="name">
                </label>
            </td>
        </tr>
    </table>
    <button type="submit">Add user</button>
</form>
</body>
</html>
