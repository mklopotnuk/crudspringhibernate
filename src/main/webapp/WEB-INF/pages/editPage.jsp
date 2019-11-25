<%--
  Created by IntelliJ IDEA.
  User: merlin
  Date: 09.10.2019
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${empty user.name}">
        <title> Add user</title>
    </c:if>
    <c:if test="${!empty user.name}">
        <title> Edit user</title>
    </c:if>

</head>
<body>
<c:if test="${empty user.name}">
    <c:url value="/add" var="var"/>
</c:if>

<c:if test="${!empty user.name}">
    <c:url value="/edit" var="var"/>
</c:if>

<form action="${var}" method="post">
    <c:if test="${!empty user.name}">
        <input type="hidden" name="id" value="${user.id}">
    </c:if>
    <label>Name
        <input type="text" name="name" value="${user.name}"></label>
    <label>Password
        <input type="password" name="password">
    </label>
    <label>Re-type password
        <input type="password" name="confirmPassword">
    </label>
    <c:if test="${!empty user.name}">
        <input type="submit" value="Edit user">
    </c:if>

    <c:if test="${empty user.name}">
        <input type="submit" value="Add user">
    </c:if>


</form>
</body>
</html>
