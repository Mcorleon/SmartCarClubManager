package com.nxp.controller;

import com.nxp.domain.Members;
import com.nxp.service.MembersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class SerchMemberCl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        int pageNow = 1;//当前页数
        int pageSize = 10;//每页显示条数
        int pageCount = 0;//总共多少页
        int rowCount = 0;//数据总条数
        MembersService membersService = new MembersService();
        ArrayList<Members> arrayList = new ArrayList<Members>();
        String serchValue = request.getParameter("serchValue").trim();
//        没输入，直接点搜索,照常显示
        if (serchValue.equals("")) {
            rowCount = membersService.getRowCount("SELECT count(*) FROM member_info");
            pageCount = rowCount % pageSize == 0 ? rowCount / pageSize : rowCount / pageSize + 1;
            arrayList = membersService.getMemberMes(pageNow, pageSize);
        }//有输入东西
        else {
            rowCount = membersService.getRowCount("SELECT count(*) FROM member_info where name like '%" + serchValue +
                    "%'or major like '%" + serchValue +
                    "%'or sex like '%" + serchValue +
                    "%'or grade like '%" + serchValue +
                    "%'or position like '%" + serchValue +
                    "%'or telephone like '%" + serchValue + "%'");
            if (rowCount == 0) {
                pageCount = 1;
            } else {
                pageCount = rowCount % pageSize == 0 ? rowCount / pageSize : rowCount / pageSize + 1;
            }
            arrayList = membersService.serchMember(serchValue);
        }
        int lastPage = pageNow - 1;
        int nextPage = pageNow + 1;
        if (lastPage < 1) lastPage = 1;
        if (nextPage > pageCount) nextPage = pageCount;
        request.setAttribute("al", arrayList);
        request.setAttribute("pageNow", pageNow);
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("lastPage", lastPage);
        request.setAttribute("nextPage", nextPage);
        request.getRequestDispatcher("/WEB-INF/memberManager.jsp").forward(request, response);
    }
}
