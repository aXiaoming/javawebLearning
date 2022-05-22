package com.atguigu.jdbc;

import java.sql.*;

//JDBC - 查询count(*)
public class Demo07 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.gjt.mm.mysql.Driver");
        //url ->    jdbc:mysql://ip:port/dbname?参数列表
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fruitdb?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","123456");
        //3.编写SQL语句
        String sql = "select count(*) from t_fruit" ;
        //4.创建预处理命令对象
        PreparedStatement psmt = conn.prepareStatement(sql);
        //5.执行查询，返回结果集
        ResultSet rs = psmt.executeQuery();
        //6.解析结果集
        if(rs.next()){
            int count = rs.getInt(1);
            System.out.println("总记录条数："+count);
        }
        //7.释放资源
        rs.close();
        psmt.close();
        conn.close();
    }
}
