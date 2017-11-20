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
    <script language="JavaScript">
        function deleteConfirm() {
            if (confirm("确认删除该队员？")) {
                return true;
            }
            else return false;
        }
    </script>
</head>

<body>
<%
    ArrayList<Members> arrayList = (ArrayList<Members>) request.getAttribute("al");
    String pageNow = request.getAttribute("pageNow").toString();
    String pageCount = request.getAttribute("pageCount").toString();
    String lastPage = request.getAttribute("lastPage").toString();
    String nextPage = request.getAttribute("nextPage").toString();
    String sucessTip = (String) request.getAttribute("sucessTip");
    //计算每页第一条记录序号，此处定为每页显示10条
    int startNum = (Integer.parseInt(pageNow) - 1) * 10;
    if ("1".equals(sucessTip)) {%>
<script language="JavaScript">
    window.alert("添加成功！")
</script>
<%}%>

<a class="a3" href="/GotoAddMember?pageCount=<%=pageCount%>">添加成员</a>
<form class="form" action="/SerchMemberCl" method="post">
    <input type="text" class="text" placeholder="Serch..." name="serchValue"> <input class="submit" type="submit"
                                                                                       value="查　找"/>
</form>
<table class="table">
    <tr>
        <th>序号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>年级</th>
        <th>专业</th>
        <th>手机号码</th>
        <th>职务</th>
        <th>选项</th>
    </tr>

    <% for (Members members : arrayList) {%>
    <tr>
        <td><%=++startNum%>
        </td>
        <td><%=members.getName()%>
        </td>
        <td><%=members.getSex()%>
        </td>
        <td><%=members.getGrade()%>
        </td>
        <td><%=members.getMajor()%>
        </td>
        <td><%=members.getTelephone()%>
        </td>
        <td><%=members.getPosition()%>
        </td>
        <td><a href="/DeleteMemberCl?id=<%=members.getId()%>&pageNow=<%=pageNow%>" onclick="return deleteConfirm()"
               class="a4">删除</a></td>
    </tr>
    <%}%>


</table>

<a class="a1" href="<%="/MemberManagerCl?pageNow="+lastPage%>">上一页</a>
<a class="a2" href="<%="/MemberManagerCl?pageNow="+nextPage%>">下一页</a>
<span class="span1"><%=pageNow%>/<%=pageCount%></span>
</body>
</html>
