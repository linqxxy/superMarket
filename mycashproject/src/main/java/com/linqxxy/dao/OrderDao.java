package com.linqxxy.dao;

import com.linqxxy.common.OrderStatus;
import com.linqxxy.entity.Order;
import com.linqxxy.entity.OrderItems;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends BaseDao {
    Connection conn = null;
    PreparedStatement statement = null;
    ResultSet rs=null;
    public boolean commitOrder(Order order) {
        try {
            conn = this.getConnection(false);
            String insertOrderSql = "insert into `order`" +
                    "(id, account_id, create_time, finish_time, " +
                    "actual_amount, total_money, order_status, " +
                    "account_name) values (?,?,now(),now(),?,?,?,?)";

            String insertOrderItemSql = "insert into order_item(order_id, goods_id, goods_name," +
                    "goods_introduce, goods_num, goods_unit, goods_price, goods_discount) values (?,?,?,?,?,?,?,?)";
            statement = conn.prepareStatement(insertOrderSql);
            statement.setString(1, order.getId());
            statement.setInt(2, order.getAccount_Id());
            statement.setInt(3, order.getActual_ammount());
            statement.setInt(4, order.getTotal_money());
            statement.setInt(5, order.getOrderStatus().getFlg());
            statement.setString(6, order.getAccount_name());
            if (statement.executeUpdate() == 0) {
                throw new RuntimeException("插入订单失败");
            }
            statement = conn.prepareStatement(insertOrderItemSql);
            for (OrderItems items : order.orderItemsList) {
                statement.setString(1, items.getOrderId());
                statement.setInt(2, items.getGoodsId());
                statement.setString(3, items.getGoodsName());
                statement.setString(4, items.getGoodsIntroduce());
                statement.setInt(5, items.getGoodsNum());
                statement.setString(6, items.getGoodUnit());
                statement.setInt(7, items.getGoodsPrice());
                statement.setInt(8, items.getGoodDiscount());
                //将每一项的items缓存
                statement.addBatch();
            }
            //批量操作缓存的数据
            int[] effects=statement.executeBatch();
            for (int i:effects){
                if (i==0){
                    throw new RuntimeException("订单明细插入失败！");
                }
            }
            conn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();

            if (conn!=null){
                try {
                    conn.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }finally {
            this.closeResource(null,statement,conn);
        }
        return true;
    }

    public List<Order> queryOrderByAccount(Integer id) {
        List<Order> list=new ArrayList<>();
        try {
            conn=this.getConnection(false);
            String sql=this.getSql("@query_order_by_account");
            statement=conn.prepareStatement(sql);
            statement.setInt(1,id);
            rs=statement.executeQuery();
            Order order=null;
            while (rs.next()){
                //当第一次进来首先生成一张订单
                if (order==null){
                    order=new Order();
                    this.extractOrder(order,rs);
                    list.add(order);
                }
                String orderId=rs.getString("order_id");
                //只有当订单信息不同时，才会生成一个新的订单
                //订单对象只有一个，其中包含了很多的订单信息
                if (!orderId.equals(order.getId())){
                    order=new Order();
                    this.extractOrder(order,rs);
                    list.add(order);
                }
                OrderItems orderItems=this.extractOrderItems(rs);
                order.getOrderItemsList().add(orderItems);
            }
            conn.commit();
            return list;
        }catch (Exception ex){
            ex.printStackTrace();
            if (conn!=null){
                try {
                    conn.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    private OrderItems extractOrderItems(ResultSet resultSet) {
        try {
            OrderItems orderItem = new OrderItems();
            orderItem.setId(resultSet.getInt("item_id"));
            orderItem.setGoodsId(resultSet.getInt("goods_id"));
            orderItem.setGoodsName(resultSet.getString("goods_name"));
            orderItem.setGoodsIntroduce(resultSet.getString("goods_introduce"));
            orderItem.setGoodsNum(resultSet.getInt("goods_num"));
            orderItem.setGoodUnit(resultSet.getString("goods_unit"));
            orderItem.setGoodsPrice(resultSet.getInt("goods_price"));
            orderItem.setGoodDiscount(resultSet.getInt("goods_discount"));
            return orderItem;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public void extractOrder(Order order,ResultSet resultSet) throws SQLException {
        order.setId(resultSet.getString("order_id"));
        order.setAccount_Id(resultSet.getInt("account_id"));
        order.setAccount_name(resultSet.getString("account_name"));
        order.setCreate_time(resultSet.getTimestamp("create_time").toLocalDateTime());
        Timestamp finishTime = resultSet.getTimestamp("finish_time");
        if (finishTime != null) {
            order.setFinish_time(finishTime.toLocalDateTime());
        }
        order.setActual_ammount(resultSet.getInt("actual_amount"));
        order.setTotal_money(resultSet.getInt("total_money"));
        order.setOrderStatus(OrderStatus.valueOf(resultSet.getInt("order_status")));
    }

}
