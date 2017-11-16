package com.nxp.controller;

import com.nxp.domain.Users;
import com.nxp.service.UsersService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginCl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String userName=request.getParameter("userName");
        String passWord=request.getParameter("passWord");
        String checkCode=request.getParameter("checkCode");
        Users users=new Users();
        Cookie[] cookies=request.getCookies();
        users.setUserName(userName);
        users.setPassWord(passWord);
        UsersService usersService =new UsersService();
        //先验证码，再用户名密码
        if(request.getSession().getAttribute("checkCode").equals(checkCode)){
            if(usersService.checkUser(users)){
                //获取上次登录日期
                if(cookies!=null){
                    for(Cookie cookie:cookies){
                        if("lastLoginDate".equals(cookie.getName())){
                            //获取上次日期
                            request.setAttribute("lastLoginDate",cookie.getValue());
                            break;
                        }
                    }
                }
                //写入本次日期
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String nowTime = simpleDateFormat.format(new Date());
                Cookie newCookie = new Cookie("lastLoginDate", nowTime);
                newCookie.setMaxAge(3 * 24 * 3600);
                response.addCookie(newCookie);
               // System.out.println("添加成功" + newCookie.getName() + "  " + newCookie.getValue());
                //获取访问次数
                String visitCount=(String)this.getServletContext().getAttribute("visitCount");
                if(visitCount==null){
                    //第一个访问者
                    this.getServletContext().setAttribute("visitCount", "1");
                }
                else{
                    this.getServletContext().setAttribute("visitCount",(Integer.parseInt(visitCount)+1)+"");
                }
                request.getRequestDispatcher("/WEB-INF/mainFrame.jsp").forward(request,response);
            }
            else {
                request.setAttribute("Err","UserErr");
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
            }
        }
        else {
            request.setAttribute("Err","CodeErr");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
        }
    }

    //这个servlet初始化时读取总访问次数，销毁时记录下次数
    @Override
    public void init() throws ServletException {
        super.init();
        String filePath=this.getServletContext().getRealPath("record");
        FileReader fileReader =null;
        BufferedReader bufferedReader=null;
        try {
            fileReader = new FileReader(filePath);
            bufferedReader=new BufferedReader(fileReader);
            //读取文件的num
            String visitCount=bufferedReader.readLine();
            this.getServletContext().setAttribute("visitCount",visitCount);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void destroy() {
        super.destroy();
        String filePath=this.getServletContext().getRealPath("record");
        FileWriter fileWriter=null;
        BufferedWriter bufferedWriter=null;
        try {
            fileWriter=new FileWriter(filePath);
            bufferedWriter=new BufferedWriter(fileWriter);
            //得到关闭时的visitCount
            String visitCount=(String)this.getServletContext().getAttribute("visitCount");
            //写入
            bufferedWriter.write(visitCount);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
