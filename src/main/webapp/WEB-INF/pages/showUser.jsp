<%--
  Created by IntelliJ IDEA.
  User: merlin
  Date: 15.10.2019
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show user</title>
</head>
<body>
<table>
    <tr>
        <td><b>User:</b></td><td>${user.name}</td>
    </tr>
    <tr>
        <td><b>Password:</b></td><td>${user.password}</td>
    </tr>
    <tr>
        <td><b>Barcode:</b></td><td><img src='data:image/png;base64, ${barcode.barcodeImage}'/></td>
    </tr>
    <tr>
        <td><b>BarcodeId:</b></td><td> ${barcode.barcodeId}</td>
    </tr>

</table>
</body>
</html>
