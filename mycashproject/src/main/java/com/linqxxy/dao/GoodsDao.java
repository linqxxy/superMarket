package com.linqxxy.dao;

import com.linqxxy.entity.Goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDao extends BaseDao {
    Connection conn = null;
    PreparedStatement statement = null;
    ResultSet rs = null;

    public List<Goods> queryAllGoos() {
        List<Goods> list = new ArrayList<>();
        try {
            conn = this.getConnection(true);
            String sql = "select * from goods";
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                Goods goods = this.extractGoods(rs);
                if (goods != null) {
                    list.add(goods);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResource(rs, statement, conn);
        }
        return list;
    }

    public Goods extractGoods(ResultSet resultSet) {
        try {
            Goods goods = new Goods();
            goods.setId(resultSet.getInt("id"));
            goods.setDiscount(resultSet.getInt("discount"));
            goods.setIntroduce(resultSet.getString("introduce"));
            goods.setStock(resultSet.getInt("stock"));
            goods.setName(resultSet.getString("name"));
            goods.setPrice(resultSet.getInt("price"));
            goods.setUnit(resultSet.getString("unit"));
            return goods;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean goodsPutAway(Goods goods) {
        try {
            conn = this.getConnection(true);
            String sql = "insert into goods (name,introduce,stock,unit,price,discount) value (?,?,?,?,?,?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, goods.getName());
            statement.setString(2, goods.getIntroduce());
            statement.setInt(3, goods.getStock());
            statement.setString(4, goods.getUnit());
            statement.setInt(5, goods.getPrice());
            statement.setInt(6, goods.getDiscount());
            int flag = statement.executeUpdate();
            if (flag == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResource(rs, statement, conn);
        }
        return false;
    }

    public Goods getGoods(int goodsId) {
        Goods goods=null;
        try {
            conn=this.getConnection(true);
            String sql="select * from goods where id=?";
            statement=conn.prepareStatement(sql);
            statement.setInt(1,goodsId);
            rs=statement.executeQuery();
            if (rs.next()){
                goods=this.extractGoods(rs);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return goods;
    }

    public boolean modifyGood(Goods goods) {
        try {
            conn=getConnection(true);
            String sql="update goods set name=?,introduce=?,stock=?,unit=?,discount=? where id=?";
            statement=conn.prepareStatement(sql);
            statement.setString(1,goods.getName());
            statement.setString(2,goods.getIntroduce());
            statement.setInt(3,goods.getStock());
            statement.setString(4,goods.getUnit());
            statement.setInt(5,goods.getDiscount());
            statement.setInt(6,goods.getId());
            int flag=statement.executeUpdate();
            if (flag==1){
                return true;
            }else {
                return false;
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteGoods(int goodsId) {
        try {
            conn=this.getConnection(true);
            String sql="delete from goods where id=?";
            statement=conn.prepareStatement(sql);
            statement.setInt(1,goodsId);
            int flag=statement.executeUpdate();
            if (flag==1){
                return true;
            }else {
                return false;
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
}
