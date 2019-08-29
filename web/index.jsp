<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="service.UserService" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.08.2019
  Time: 3:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>
<c:set var="users" scope="session" value="${UserService.getInstance().getAllUsers()}"/>
<c:if test="${users.size()<1}">
    <p>
        You have no users, add user?
    </p>
</c:if>
<jsp:include page="/jsp/add.jsp"/>
<c:if test="${users.size()>0}">
    <p>
        <label>Users:
            <form action="/delete/" method="get" style="display: inline">
                <button type="submit">Delete all users</button>
            </form>
        </label>
    </p>
    <jsp:include page="/jsp/dynamicTableUsers.jsp"/>
</c:if>
</body>
</html>
