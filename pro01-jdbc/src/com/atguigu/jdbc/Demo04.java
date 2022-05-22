package com.atguigu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//JDBC - 修改和删除
public class Demo04 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.gjt.mm.mysql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fruitdb?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","123456");
        String sql = "delete from t_fruit where fid = ? " ;
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setInt(1,6);
        int count = psmt.executeUpdate();

        System.out.println(count > 0 ? "删除成功！" : "删除失败！");

        psmt.close();
        conn.close();
    }
}
