package com.atguigu.jdbc;

import java.sql.*;

/**
 * Statement注入式漏洞演示
 */
public class Demo06 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf-8","root","123456");
        //String fname = "西瓜";
        String fname = "西瓜' or 1=1 or fname='";
        // 这里，字符串需要专门拼接''
        String sql = "select * from t_fruit where fname = '" + fname +"'";

        System.out.println(sql);

        Statement stmt = conn.createStatement() ;
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()){
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString("fname"));
            System.out.println(rs.getInt(3));
            System.out.println(rs.getInt("fcount"));
            System.out.println(rs.getString("remark"));
        }
    }
}

/*
Statement - 存在注入式漏洞,一般情况下，不使用
    PreparedStatement - 预处理命令对象
        CallableStatement - 执行存储过程的
 */
