package com.atguigu.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//JDBC - 查询指定fid的库存记录
public class Demo06 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.gjt.mm.mysql.Driver");
        //url ->    jdbc:mysql://ip:port/dbname?参数列表
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fruitdb?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","123456");
        //3.编写SQL语句
        String sql = "select * from t_fruit where fid = ? " ;
        //4.创建预处理命令对象
        PreparedStatement psmt = conn.prepareStatement(sql);
        //5.填充参数
        psmt.setInt(1,33);
        //6.执行查询，返回结果集
        ResultSet rs = psmt.executeQuery();
        //6.解析结果集

        if(rs.next()){
            int fid = rs.getInt(1);
            String fname = rs.getString("fname");
            int price = rs.getInt(3);
            int fcount = rs.getInt(4);
            String remark = rs.getString(5);
            Fruit fruit = new Fruit(fid , fname , price , fcount , remark );
            System.out.println(fruit);
        }
        //7.释放资源
        rs.close();
        psmt.close();
        conn.close();
    }
}
