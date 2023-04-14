<%--
  Created by IntelliJ IDEA.
  User: red256
  Date: 2023/4/13
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login JSP</title>
</head>
<body>
<h2>Login JSP</h2>
<form action="/user/logon.action" method="post">
  <input type="text" name="username"></br>
  <input type="password" name="password"></br>
  <button>登录</button>
</form>
</body>
</html>
