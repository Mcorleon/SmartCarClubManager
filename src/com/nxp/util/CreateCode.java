package com.nxp.util;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class CreateCode extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        // 禁止浏览器缓存随机图片
        response.setDateHeader("Expires", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        // 通知客户机以图片方式打开发送过去的数据
        response.setHeader("Content-Type", "image/jpeg");
        // 在内存中创建一副图片
        BufferedImage image = new BufferedImage(60, 30,BufferedImage.TYPE_INT_RGB);
        // 向图片上写数据
        Graphics g = image.getGraphics();
        // 设背景色
        g.setColor(new Color(176,196,222));
        g.fillRect(0, 0, 60, 30);
        // 3.设置写入数据的颜色和字体
        g.setColor(Color.black);
        g.setFont(new Font(null, Font.BOLD, 20));
        // 4.向图片上写数据
        String num = makeNum();
        //这句话就是把随机生成的数值，保存到session .通过session就可以直接去到随即生成的验证码了
        request.getSession().setAttribute("checkCode", num);
        g.drawString(num, 10, 20);
        // 5.把写好数据的图片输出给浏览器
        ImageIO.write(image, "jpg", response.getOutputStream());

    }

    public String makeNum() {
        Random r = new Random();
        //9999 可以生成4位
        String num = r.nextInt(9999) + "";
        StringBuffer sb = new StringBuffer();
        //如果不够4位，前面补零
        for (int i = 0; i < 4 - num.length(); i++) {
            sb.append("0");
        }
        num = sb.toString() + num;
        return num;
    }

}