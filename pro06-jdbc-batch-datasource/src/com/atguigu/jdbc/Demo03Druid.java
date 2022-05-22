package com.atguigu.jdbc;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


/**
 * 验证连接池的各项参数：初始化大小、最大激活数量、最大等待时间
 * 等待超时的问题
 */
public class Demo03Druid {
    public static void main(String[] args) throws SQLException {

        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setDriverClassName("org.gjt.mm.mysql.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        // 初始有2个连接对象
        dataSource.setInitialSize(2);
        // 最大激活数量5
        dataSource.setMaxActive(5);
        // 最大等待时间，后面的如果想要获取conn对象，最多等待5s，如果从连接池获取不到，则抛出异常
        dataSource.setMaxWait(5000);

        for(int i = 0 ; i<10 ; i++){
            Connection conn1 = dataSource.getConnection();
            System.out.println(i+"-------->"+conn1);

        }
    }
}
