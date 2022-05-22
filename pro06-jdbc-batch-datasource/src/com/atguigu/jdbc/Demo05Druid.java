package com.atguigu.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 读取外部的配置文件设置连接池，并自动配置
 * 这里需要配置文件固定的配置格式
 */
public class Demo05Druid {
    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        InputStream is = Demo05Druid.class.getClassLoader().getResourceAsStream("jdbc2.properties");
        properties.load(is);

        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        for(int i = 0 ; i<10 ; i++){
            Connection conn1 = dataSource.getConnection();
            System.out.println(i+"-------->"+conn1);

        }
    }
}

