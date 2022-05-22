package com.atguigu.fruit.dao.base;

import java.sql.*;

public abstract class BaseDAO {
    public final String DRIVER = "com.mysql.jdbc.Driver" ;
    public final String URL = "jdbc:mysql://localhost:3306/fruitdb?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    public final String USER = "root";
    public final String PWD = "123456" ;

    protected Connection conn ;
    protected PreparedStatement psmt ;
    protected ResultSet rs ;

    protected Connection getConn(){
        try {
            //1.加载驱动
            Class.forName(DRIVER);
            //2.通过驱动管理器获取连接对象
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null ;
    }

    protected void close(ResultSet rs , PreparedStatement psmt , Connection conn){
        try {
            if (rs != null) {
                rs.close();
            }
            if(psmt!=null){
                psmt.close();
            }
            if(conn!=null && !conn.isClosed()){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //执行更新，返回影响行数
    protected int executeUpdate(String sql , Object... params){
        try {
            conn = getConn();
            psmt = conn.prepareStatement(sql);
            if(params!=null && params.length>0){
                for (int i = 0; i < params.length; i++) {
                    psmt.setObject(i+1,params[i]);
                }
            }
            return psmt.executeUpdate() ;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,psmt,conn);
        }
        return 0;
    }
}
