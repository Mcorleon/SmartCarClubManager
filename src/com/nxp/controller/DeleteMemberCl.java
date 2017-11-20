package com.nxp.controller;

import com.nxp.service.MembersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteMemberCl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String[] parameters={request.getParameter("id")};
        String pageNow=request.getParameter("pageNow");
        MembersService membersService=new MembersService();
        membersService.DeleteMember(parameters);
        request.getRequestDispatcher("/MemberManagerCl?pageNow="+pageNow).forward(request,response);

    }
}
