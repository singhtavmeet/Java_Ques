
package com.example.order_management.dao;

import com.example.order_management.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {

    List<Order> addOrder(Order order) throws SQLException;

    List<Order> updateOrderById(long id, Order order) throws SQLException;

    List<Order> deleteOrderById(long id) throws SQLException;

    List<Order> getAllOrders() throws SQLException;
}
