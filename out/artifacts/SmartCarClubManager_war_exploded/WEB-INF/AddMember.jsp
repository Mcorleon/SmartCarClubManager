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
<%
    String nameErr = (String) request.getAttribute("nameErr");
    String sexErr = (String) request.getAttribute("sexErr");
    String gradeErr = (String) request.getAttribute("gradeErr");
    String majorErr = (String) request.getAttribute("majorErr");
    String phoneNumberErr = (String) request.getAttribute("phoneNumberErr");
    String positionErr = (String) request.getAttribute("positionErr");
    String pageCount = request.getParameter("pageCount");

    if ("nullErr".equals(nameErr)) {
        nameErr = "姓名不能为空";
    } else if ("longErr".equals(nameErr)) {
        nameErr = "长度不能超过30个字符";
    } else {
        nameErr = "";
    }
    if ("nullErr".equals(sexErr)) {
        sexErr = "请选择性别";
    } else {
        sexErr = "";
    }
    if ("nullErr".equals(gradeErr)) {
        gradeErr = "请选择年级";
    } else {
        gradeErr = "";
    }
    if ("nullErr".equals(majorErr)) {
        majorErr = "专业不能为空";
    } else if ("longErr".equals(majorErr)) {
        majorErr = "长度不能超过30个字符";
    } else {
        majorErr = "";
    }
    if ("nullErr".equals(phoneNumberErr)) {
        phoneNumberErr = "手机号码不能为空";
    } else if ("formatErr".equals(phoneNumberErr)) {
        phoneNumberErr = "请正确填写11位手机号码";
    } else {
        phoneNumberErr = "";
    }
    if ("nullErr".equals(positionErr)) {
        positionErr = "职务不能为空";
    } else if ("longErr".equals(positionErr)) {
        positionErr = "长度不能超过20个字符";
    } else {
        positionErr = "";
    }
%>

<span class="span">添加成员</span>
<div class="div">
    <form class="form" action="/AddMemberCl?pageCount=<%=pageCount%>" method="post">
        姓　名　　<input type="text" class="name" name="name"/><span class="Err"> <%=nameErr%></span><br><br>
        性　别 　 <input type="radio" value="男" name="sex"/>男　　　　<input type="radio" value="女" name="sex"/>女　　　 　　 　　<span
            class="Err"><%=sexErr%></span><br><br>
        年　级　 <input type="radio" value="大一" name="grade"/>大一<input type="radio" value="大二" name="grade"/>大二
        <input type="radio" value="大三" name="grade"/>大三<input type="radio" value="大四" name="grade"/>大四　　　<span
            class="Err"><%=gradeErr%></span><br><br>
        专　业　　<input type="text" class="major" name="major"/><span class="Err"> <%=majorErr%></span><br><br>
        手机号码　<input type="text" class="phoneNumber" name="phoneNumber"/><span
            class="Err"> <%=phoneNumberErr%></span><br><br>
        职　务　　<input type="text" class="position" name="position"/><span class="Err"> <%=positionErr%></span><br><br><br><br>
        <input type="submit" value="确　定" class="submit"/><a href="/MemberManagerCl?pageNow=1" class="a">返　回</a>

    </form>
</div>
</body>
</html>
