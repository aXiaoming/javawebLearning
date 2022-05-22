package com.atguigu.fruit.dao.impl;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.pojo.Fruit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FruitDAOImpl implements FruitDAO {
    Connection conn ;
    PreparedStatement psmt ;
    ResultSet rs ;

    final String DRIVER = "com.mysql.jdbc.Driver" ;
    final String URL = "jdbc:mysql://localhost:3306/fruitdb?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    final String USER = "root";
    final String PWD = "123456" ;

    @Override
    public List<Fruit> getFruitList() {
        List<Fruit> fruitList = new ArrayList<>();

        /**
         * 这里不可以抛出异常
         * 因为在继承中，访问修饰符不能比父类小，抛出的异常不能比父类大
         */
        try {
            //1.加载驱动
            Class.forName(DRIVER);
            //2.通过驱动管理器获取连接对象
            conn = DriverManager.getConnection(URL, USER, PWD);
            //3.编写SQL语句
            String sql = "select * from t_fruit";
            //4.创建预处理命令对象
            psmt = conn.prepareStatement(sql);
            //5.执行查询
            rs = psmt.executeQuery();

            //6.解析rs
            while(rs.next()){
                int fid = rs.getInt(1);
                String fname = rs.getString(2);
                int price = rs.getInt(3);
                int fcount = rs.getInt(4);
                String remark = rs.getString(5);

                Fruit fruit = new Fruit(fid, fname , price , fcount , remark) ;
                fruitList.add(fruit);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
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
        return fruitList ;
    }

    @Override
    public boolean addFruit(Fruit fruit) {

        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL,USER,PWD);
            String sql = "insert into t_fruit values(0,?,?,?,?)";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1,fruit.getFname());
            psmt.setInt(2,fruit.getPrice());
            psmt.setInt(3,fruit.getFcount());
            psmt.setString(4,fruit.getRemark());

            return psmt.executeUpdate() > 0 ;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            try {
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

        return false;
    }

    @Override
    public boolean updateFruit(Fruit fruit) {

        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL,USER,PWD);
            String sql = "update t_fruit set fcount = ? where fid = ? " ;
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1,fruit.getFcount());
            psmt.setInt(2,fruit.getFid());
            return psmt.executeUpdate() > 0  ;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            try {
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


        return false;
    }

    @Override
    public Fruit getFruitByFname(String fname) {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PWD);
            String sql = "select * from t_fruit where fname like ? ";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1,fname);
            rs = psmt.executeQuery();
            if(rs.next()){
                int fid = rs.getInt(1);
                int price = rs.getInt(3);
                int fcount = rs.getInt(4);
                String remark = rs.getString(5);

                return new Fruit(fid , fname , price , fcount , remark ) ;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
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

        return null;
    }

    @Override
    public boolean delFruit(String fname) {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL,USER,PWD);
            String sql = "delete from t_fruit where fname like ? " ;
            psmt = conn.prepareStatement(sql);
            psmt.setString(1,fname);
            return psmt.executeUpdate() > 0 ;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
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
        return false;
    }
}
