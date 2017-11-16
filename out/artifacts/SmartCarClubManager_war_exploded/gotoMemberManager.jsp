<%--
  Created by IntelliJ IDEA.
  User: Tang
  Date: 2017/11/15
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String pageNow = request.getParameter("pageNow");

    if (pageNow == null) {
        pageNow = "1";
    }

%>
<jsp:forward page="WEB-INF/memberManager.jsp">

    <jsp:param name="pageNow" value='<%=pageNow%>'/>
</jsp:forward>
</body>
</html>
