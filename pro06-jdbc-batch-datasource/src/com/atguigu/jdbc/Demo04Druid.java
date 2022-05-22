package com.atguigu.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

// 读取外部的配置文件设置连接池，并一个一个的手动配置
public class Demo04Druid {
    public static void main(String[] args) throws SQLException, IOException {

        Properties properties = new Properties();
        // 表示从src下面读文件
        InputStream is = Demo04Druid.class.getClassLoader().getResourceAsStream("jdbc.properties");
        // 读取配置文件中的键值对
        properties.load(is);

        // 用配置文件中的信息配置数据源
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setDriverClassName(properties.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(properties.getProperty("jdbc.url"));
        dataSource.setUsername(properties.getProperty("jdbc.username"));
        dataSource.setPassword(properties.getProperty("jdbc.pwd"));

        dataSource.setInitialSize(Integer.parseInt(properties.getProperty("jdbc.initSize")));
        dataSource.setMaxActive(Integer.parseInt(properties.getProperty("jdbc.maxActive")));
        dataSource.setMaxWait(Integer.parseInt(properties.getProperty("jdbc.maxWait")));

        for(int i = 0 ; i<10 ; i++){
            Connection conn1 = dataSource.getConnection();
            System.out.println(i+"-------->"+conn1);

        }
    }
}
