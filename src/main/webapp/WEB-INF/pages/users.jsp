<%--
  Created by IntelliJ IDEA.
  User: merlin
  Date: 07.10.2019
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Users</h2>
<table border="2">
    <tr>
        <th>id</th>
        <th>username</th>
        <th>password</th>
        <th>roles</th>
        <th>users actions</th>
    </tr>
    <c:forEach var="user" items="${usersList}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.password}</td>
            <td>
                <c:forEach var="role" items="${user.roles}">
                    ${role.name}
                </c:forEach>

            </td>
            <td>
                <a href="/admin/edit/${user.id}">edit</a>
                <a href="/user/view/${user.id}">profile</a>
                <a href="/admin/delete/${user.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>Actions</h2>

<form:form action="/add" method="POST" modelAttribute="user">
    <tr>
        <td>Create new user</td>
        <td>
            Name <form:input path="name"></form:input>
        </td>
        <td>
            Password <form:input path="password"></form:input>
        </td>

            <%--        <td>--%>
            <%--            <form:checkboxes items="${roles}" path="roles" itemValue="id" title="name"/>--%>
            <%--        </td>--%>

        <td>Role <select name="role">
            <option value="1">USER</option>
            <option value="2">ADMIN</option>
        </select>
        </td>
        <td>
            <button type="submit">Submit</button>
        </td>
    </tr>
</form:form>

<c:url value="/logout" var="logout"/>
<a href="${logout}"> Logout </a>
</body>
</html>
