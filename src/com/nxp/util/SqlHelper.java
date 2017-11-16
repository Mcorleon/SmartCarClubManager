package com.nxp.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class SqlHelper {
    //定义变量
    private static Connection ct = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs;

    //连接数据库的参数
    private static String url = "";
    private static String username = "";
    private static String driver = "";
    private static String passwd = "";
    private static Properties pp = null;
    private static InputStream fis = null;

    //加载驱动，只要一次
    static {
        try {
            pp = new Properties();
            fis = SqlHelper.class.getClassLoader().getResourceAsStream("mysql.properties");
            pp.load(fis);
            url = pp.getProperty("url");
            username = pp.getProperty("username");
            driver = pp.getProperty("driver");
            passwd = pp.getProperty("passwd");

            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            fis = null;//垃圾回收站上收拾
        }
        rs = null;
    }

    //得到连接
    public static Connection getConnection() {
        try {
            ct = DriverManager.getConnection(url, username, passwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ct;
    }

    public static void close(ResultSet rs, Statement ps, Connection ct) {
        //关闭资源(先开后关)
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ps = null;
        }
        if (null != ct) {
            try {
                ct.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ct = null;
        }
    }

    public static ResultSet executeQuery(String sql, String[] parameters) {
        try {
            ct = getConnection();
            ps = ct.prepareStatement(sql);
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    ps.setString(i + 1, parameters[i]);
                }
            }
            rs = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {

        }
        return rs;
    }

    public static Connection getCt() {
        return ct;
    }

    public static PreparedStatement getPs() {
        return ps;
    }

    public static ResultSet getRs() {
        return rs;
    }
}
