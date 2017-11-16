package com.nxp.service;

import com.nxp.domain.Users;
import com.nxp.util.SqlHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsersService {
    public boolean checkUser(Users user) {

        ResultSet rs = null;
        boolean res = false;
        try {
            rs = SqlHelper.executeQuery("select *from USERs WHERE userName=? and passWord=?",
                    new String[]{user.getUserName(), user.getPassWord()});

            if (rs.next()) {
                res = true;
            } else {
                res = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SqlHelper.close(SqlHelper.getRs(), SqlHelper.getPs(), SqlHelper.getCt());

        }

        return res;
    }


}
