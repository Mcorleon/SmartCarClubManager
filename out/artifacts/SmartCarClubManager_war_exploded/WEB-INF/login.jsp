<%--
  Created by IntelliJ IDEA.
  User: Tang
  Date: 2017/11/14
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="myCSS/loginCSS.css">
</head>
<%
    String err = "";
    if("CodeErr".equals(request.getAttribute("Err"))){
        err="输入验证码有误";
    }else if("UserErr".equals(request.getAttribute("Err"))){
        err="用户名或密码错误";
    }

%>
<body>
<h1 class="head">南昌大学智能车基地管理系统</h1>
<span class="span">Designed by Mcorleon</span>
<img src="${pageContext.servletContext.contextPath}/img/logo-2.png" class="logo" />
<div class="div">
    <form action="/LoginCl" method="post">
        用户名　<input  class="input" type="text" name="userName"><br><br>
        密　码　<input class="input" type="password" name="passWord"><br><br>
        验证码　<input class="input" type="text" name="checkCode">　　<img   src='/CreateCode' /><br><br>

        <span class="span2"><%=err%></span><br><br>
        <input class="button" type="submit" value="登　录" class="submit">
    </form>
</div>
</body>
</html>
