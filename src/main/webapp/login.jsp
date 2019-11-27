<%--
  Created by IntelliJ IDEA.
  User: 50462
  Date: 2019/10/31
  Time: 1:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/LoginServlet">
    <table>
        <tr>
            <td><label for="email">邮箱:</label></td>
            <td><input type="email" name="email" id="email"/></td>
        </tr>
        <tr>
            <td><label for="password">密码:</label></td>
            <td><input type="password" name="password" id="password" /></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="登录" /></td>
        </tr>
    </table>
</form>
</body>
</html>
