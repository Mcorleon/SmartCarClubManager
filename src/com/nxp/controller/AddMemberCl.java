package com.nxp.controller;

import com.nxp.service.MembersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.regex.Pattern;


public class AddMemberCl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        String name=request.getParameter("name");
        String sex=request.getParameter("sex");
        String grade=request.getParameter("grade");
        String major=request.getParameter("major");
        String position=request.getParameter("position");
        String phoneNumber=request.getParameter("phoneNumber");
        String pageCount=request.getParameter("pageCount");
        //检查输入是否有错
        if(name==null||name.trim().equals("")){
            request.setAttribute("nameErr","nullErr");
        }else if(name.length()>30){
            request.setAttribute("nameErr","longErr");
        }
        if(sex==null){
            request.setAttribute("sexErr","nullErr");
        }
        if(grade==null){
            request.setAttribute("gradeErr","nullErr");
        }
        if(major==null||major.trim().equals("")){
            request.setAttribute("majorErr","nullErr");
        }else if(major.length()>30){
            request.setAttribute("majorErr","longErr");
        }
        if(position==null||position.trim().equals("")){
            request.setAttribute("positionErr","nullErr");
        }else if(position.length()>20){
            request.setAttribute("positionErr","longErr");
        }
        //简单的检查下，手机是不是1开头，11位号码
         String pattern="^1\\d{10}";
        boolean phoneNumberMatch= Pattern.matches(pattern,phoneNumber);
        if(phoneNumber==null||phoneNumber.trim().equals("")){
            request.setAttribute("phoneNumberErr","nullErr");
        }
        else if(phoneNumberMatch==false){
            request.setAttribute("phoneNumberErr","formatErr");
        }
       // 有输入错误返回到表单页面重填，没有则insert完成后返回到表格
        if(request.getAttribute("nameErr")!=null||request.getAttribute("sexErr")!=null||
        request.getAttribute("gradeErr")!=null||request.getAttribute("majorErr")!=null||
        request.getAttribute("positionErr")!=null||request.getAttribute("phoneNumberErr")!=null){
            request.getRequestDispatcher("/GotoAddMember").forward(request,response);
        }
        else {
            String[] parameters={name,sex,grade,major,phoneNumber,position};
            MembersService membersService=new MembersService();
            membersService.addMember(parameters);
            request.getRequestDispatcher("/MemberManagerCl?pageNow="+pageCount+"&sucessTip=1").forward(request,response);
        }

    }
}
