package com.atguigu.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//JDBC - 查询所有的库存
//可以根据索引获取列的值，也可以根据列名获取列的值
public class Demo05 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.gjt.mm.mysql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fruitdb?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "123456");
        //3.编写SQL语句
        String sql = "select * from t_fruit";
        //4.创建预处理命令对象
        PreparedStatement psmt = conn.prepareStatement(sql);
        //5.执行查询，返回结果集
        ResultSet rs = psmt.executeQuery();
        //6.解析结果集
        List<Fruit> fruitList = new ArrayList<>();
        while (rs.next()) {
            //1表示读取当前行的第一列的数据
            //getInt ， 因为这一列是int类型，所以使用getInt
            //getInt(结果集的列名)
            //int fid = rs.getInt("fid");
            int fid = rs.getInt(1);
            String fname = rs.getString("fname");
            int price = rs.getInt(3);
            int fcount = rs.getInt(4);
            String remark = rs.getString(5);

            Fruit fruit = new Fruit(fid, fname, price, fcount, remark);
            fruitList.add(fruit);
        }
        //7.释放资源
        rs.close();
        psmt.close();
        conn.close();

        fruitList.forEach(System.out::println);
    }
}
