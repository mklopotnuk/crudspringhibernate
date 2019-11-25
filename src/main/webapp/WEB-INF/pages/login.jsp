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

<form action="/login" method="post">
    <label>Name
        <input type="text" name="name"></label>
    <label>Password
        <input type="password" name="password">
    </label>
    <input type="submit" value="Login">
</form>

</body>
</html>
