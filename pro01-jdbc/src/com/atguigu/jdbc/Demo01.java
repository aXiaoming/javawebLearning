package com.atguigu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//目标：和数据库建立连接
public class Demo01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.添加jar
        //2.加载驱动
        //org.gjt.mm.mysql.Driver
        Class.forName("com.mysql.jdbc.Driver");
        //3.通过驱动管理器获取连接对象
        //3-1.准备URL
        String url = "jdbc:mysql://localhost:3306/fruitdb?useSSL=false";
        //3-2.准备用户名
        String user = "root";
        //3-3.准备密码
        String pwd = "123456";
        Connection conn = DriverManager.getConnection(url,user,pwd);

        System.out.println("conn = " + conn);
    }
}

/*
1. JDBC : sun发布的 一个  java程序和数据库之间通信的 规范（接口）

2. 各大数据库厂商去实现JDBC规范（实现类），这些实现类打成压缩包，就是所谓的jar包


3. 常见错误：
1. ClassNotFoundException
2. 中文乱码


 */