<%@ page import="java.util.ArrayList" %>
<%@ page import="com.nxp.service.MembersService" %>
<%@ page import="com.nxp.domain.Members" %><%--
  Created by IntelliJ IDEA.
  User: Tang
  Date: 2017/11/15
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="myCSS/memberManagerCSS.css">
</head>
<%
    int pageNow = 1;//当前页数
    int pageSize = 10;//每页显示条数
    int pageCount = 0;//总共多少页
    int rowCount = 0;//数据总条数
    MembersService membersService=new MembersService();
    if (!request.getParameter("pageNow").equals("1")) {
        //不是第一页就取通过点击传递的页数
        pageNow = Integer.parseInt(request.getParameter("pageNow"));
    }
    rowCount=membersService.getRowCount();
    pageCount = rowCount % pageSize == 0 ? rowCount / pageSize : rowCount / pageSize + 1;
    ArrayList<Members> arrayList=membersService.getMemberMes(pageNow,pageSize);
%>
<body>
<table class="table">
    <tr><th>序号</th><th>姓名</th><th>性别</th><th>年级</th><th>专业</th><th>手机号码</th><th>职务</th></tr>

    <% for (Members members:arrayList){%>
    <tr>
        <td> <%=members.getId()%></td>
        <td> <%=members.getName()%></td>
        <td> <%=members.getSex()%></td >
        <td ><%=members.getGrade()%></td >
        <td > <%=members.getMajor()%></td>
        <td > <%=members.getTelephone()%></td>
        <td > <%=members.getPosition()%></td>
    </tr>
    <%}%>


</table>
<%
    int lastPage=pageNow-1;
    int nextPage=pageNow+1;
    if(lastPage<1)lastPage=1;
    if (nextPage>pageCount)nextPage=pageCount;
%>
<a class="a1" href="<%="gotoMemberManager.jsp?pageNow="+lastPage%>">上一页</a>
<a class="a2" href="<%="gotoMemberManager.jsp?pageNow="+nextPage%>">下一页</a>
<span class="span1"><%=pageNow%>/<%=pageCount%></span>
</body>
</html>
