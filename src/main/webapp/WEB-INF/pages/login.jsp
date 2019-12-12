<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: merlin
  Date: 19.11.2019
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Войти</title>
</head>
<body>

<c:if test="${param.logout != null}">
    <div class="logout">${message}</div>
</c:if>

<form action="<c:url value='/login' />" method="post">
    <label>Name
        <input type="text" name="name"></label><br>
    <label>Password
        <input type="password" name="password">
    </label><br>
    <input type="submit" value="Login">
</form>

</body>
</html>
