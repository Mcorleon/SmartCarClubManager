<%--
  Created by IntelliJ IDEA.
  User: Tang
  Date: 2017/11/14
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理系统-主页</title>
    <link rel="stylesheet" type="text/css" href="myCSS/mainFrameCSS.css">
    <script language="JavaScript">
        function changeURL(num) {
            if(num==1){
                document.getElementById("iframe").setAttribute("src","/MemberManagerCl?pageNow=1")
            }else if(num==2){
                document.getElementById("iframe").setAttribute("src","test.html")
            }
            else if(num==3){
                document.getElementById("iframe").setAttribute("src","test.html")
            }
            document.getElementById("a"+num).setAttribute("class","active");
            for(var i=1;i<=5;i++){
                if(i!=num){
                    document.getElementById("a"+i).setAttribute("class","");
                }
            }

        }
    </script>

</head>
<%
 String userName=request.getParameter("userName");
 String lastLoginDate=(String) request.getAttribute("lastLoginDate");
 String visitCount=(String)request.getServletContext().getAttribute("visitCount");
%>
<body>
<div class="div1">
    　南昌大学智能车基地管理系统
    <marquee  onmouseover=stop() onmouseout=start() direction="left" class="loop">
        欢迎登录,<%=userName%>　上次登录时间:<%=lastLoginDate%> 　累计访问量:<%=visitCount%>
    </marquee>
</div>
<ul>
    <li><a href="#" onclick="changeURL(1)" id="a1" class="active" >队员管理</a></li>
    <li><a href="#" onclick="changeURL(2)" id="a2">签到查询</a></li>
    <li><a href="#" onclick="changeURL(3)" id="a3">报名表管理</a></li>
    <li><a href="#" onclick="changeURL(4)" id="a4">器材管理</a></li>
    <li><a href="#" onclick="changeURL(5)" id="a5">其他</a></li>
</ul>
<iframe class="iframe" id="iframe" src="/MemberManagerCl?pageNow=1"></iframe>
</body>
</html>
