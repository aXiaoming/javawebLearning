package com.atguigu.fruit.dao.impl;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.base.BaseDAO;
import com.atguigu.fruit.pojo.Fruit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FruitDAOImpl extends BaseDAO implements FruitDAO {
    @Override
    public List<Fruit> getFruitList() {
        List<Fruit> fruitList = new ArrayList<>();
        try {
            conn = getConn() ;
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

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,psmt,conn);
        }
        return fruitList ;
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        return super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark())>0;
    }

    @Override
    public boolean updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fcount = ? where fid = ? " ;
        return super.executeUpdate(sql,fruit.getFcount(),fruit.getFid())>0;
    }

    @Override
    public Fruit getFruitByFname(String fname) {
        try {
            conn = getConn();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,psmt,conn);
        }

        return null;
    }

    @Override
    public boolean delFruit(String fname) {
        String sql = "delete from t_fruit where fname like ? " ;
        return super.executeUpdate(sql,fname)>0;
    }
}