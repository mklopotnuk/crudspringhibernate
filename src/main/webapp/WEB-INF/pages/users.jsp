<%--
  Created by IntelliJ IDEA.
  User: merlin
  Date: 07.10.2019
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Users</h2>
<table>
    <tr>
        <th>id</th>
        <th>username</th>
        <th>password</th>
    </tr>
    <c:forEach var="user" items="${usersList}">
    <tr>
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.password}</td>
        <td>
            <a href="/edit/${user.id}">edit</a>
            <a href="/view/${user.id}">profile</a>
            <a href="/delete/${user.id}">delete</a>
        </td>
    </tr>

    </c:forEach>
</table>

<h2>Add</h2>
<c:url value="/add" var="add"/>
<a href="${add}"> Add new user </a>
</body>
</html>
