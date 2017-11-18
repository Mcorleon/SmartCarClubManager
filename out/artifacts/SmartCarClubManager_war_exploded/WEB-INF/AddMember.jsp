<%--
  Created by IntelliJ IDEA.
  User: Tang
  Date: 2017/11/18
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="myCSS/AddMemberCSS.css">
</head>
<body>
<span class="span">添加成员</span>
<div class="div">
    <form class="form" action="/AddMemberCl" method="post">
        姓　名　　<input type="text" class="name" name="name"/><br><br>
        性　别 　 <input type="radio" name="sex"/>男　　　　<input type="radio" value="女" name="sex"/>女<br><br>
        年　级　 <input type="radio" value="大一" name="grade"/>大一<input type="radio" value="大二" name="grade"/>大二
        <input type="radio" value="大三" name="grade"/>大三<input type="radio" value="大四" name="grade"/>大四<br><br>
        专　业　　<input type="text" class="major" name="major"/><br><br>
        手机号码　<input type="text" class="phoneNumber" name="phoneNumber"/><br><br>
        职　务　　<input type="text" class="position" name="position"/><br><br><br><br>
        <input type="submit" value="确　定" class="submit"/><a href="/MemberManagerCl?pageNow=1" class="a">返　回</a>

    </form>
</div>
</body>
</html>
