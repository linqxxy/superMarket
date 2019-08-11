package com.linqxxy.service;

import com.linqxxy.dao.OrderDao;
import com.linqxxy.entity.Order;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

public class OrderService {

    public OrderDao orderDao;

    public OrderService() {
        this.orderDao = new OrderDao();
    }

    public boolean commitOrder(Order order) {
        return this.orderDao.commitOrder(order);
    }

    public List<Order> queryOrderByAccount(Integer id) {
        return this.orderDao.queryOrderByAccount(id);
    }
}
