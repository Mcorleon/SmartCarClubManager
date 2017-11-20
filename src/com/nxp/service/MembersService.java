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
                Members member = new Members();
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
        } finally {
            SqlHelper.close(SqlHelper.getRs(), SqlHelper.getPs(), SqlHelper.getCt());
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return arrayList;
    }

    public void addMember(String[] parameters) {

        SqlHelper.execute("insert into member_info values(id,?,?,?,?,?,?,null,null)", parameters);
    }

    public void DeleteMember(String[] parameters) {
        SqlHelper.execute("delete from member_info where id=?", parameters);
    }

    public ArrayList serchMember(String serchValue) {
        ResultSet rs = null;
        ArrayList<Members> al = new ArrayList<Members>();
        try {

            rs = SqlHelper.executeQuery("SELECT * FROM member_info where name like '%" + serchValue +
                            "%'or major like '%" + serchValue +
                            "%'or sex like '%" + serchValue +
                            "%'or grade like '%" + serchValue +
                            "%'or position like '%" + serchValue +
                            "%'or telephone like '%" + serchValue +"%'",
                    null);
            while (rs.next()) {
                Members member = new Members();
                member.setId(rs.getInt(1));
                member.setName(rs.getString(2));
                member.setSex(rs.getString(3));
                member.setGrade(rs.getString(4));
                member.setMajor(rs.getString(5));
                member.setTelephone(rs.getString(6));
                member.setPosition(rs.getString(7));
                al.add(member);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SqlHelper.close(SqlHelper.getRs(), SqlHelper.getPs(), SqlHelper.getCt());
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return al;
    }

    public int getRowCount(String sql) {
        ResultSet rs = null;
        int rowCount = 0;
        try {
            rs = SqlHelper.executeQuery(sql, null);
            rs.next();
            rowCount = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        return rowCount;
    }
}
