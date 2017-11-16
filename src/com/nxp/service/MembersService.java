package com.nxp.service;

import com.nxp.domain.Members;
import com.nxp.util.SqlHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MembersService {

    public ArrayList getMemberMes(int pageNow, int pageSize) {
        ArrayList<Members> arrayList = new ArrayList<Members>();
        ResultSet rs = null;
        //limit后面注意跟个空格，不然会直接接数字
        rs = SqlHelper.executeQuery("SELECT *FROM member_info limit " + (pageNow - 1) * pageSize + "," + pageSize, null);
        try {
            while (rs.next()) {
                Members member = new  Members();
                member.setId(rs.getInt(1));
                member.setName(rs.getString(2));
                member.setSex(rs.getString(3));
                member.setGrade(rs.getString(4));
                member.setMajor(rs.getString(5));
                member.setTelephone(rs.getString(6));
                member.setPosition(rs.getString(7));
                arrayList.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            SqlHelper.close(SqlHelper.getRs(), SqlHelper.getPs(), SqlHelper.getCt());

        }
        return arrayList;
    }
    public int getRowCount(){
        ResultSet rs = null;
        int rowCount=0;
        try {
            rs=SqlHelper.executeQuery("SELECT count(*) FROM member_info",null);
            rs.next();
            rowCount = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
        return rowCount;
    }
}
