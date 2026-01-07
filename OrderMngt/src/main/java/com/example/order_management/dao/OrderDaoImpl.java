
package com.example.order_management.dao;

import com.example.order_management.entity.Order;
import com.example.order_management.util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PreparedStatement-based DAO implementation.
 * All methods return List<Order>:
 *  - addOrder: returns list with inserted order (with generated id), or empty list if insert failed.
 *  - updateOrderById: returns list with updated order if id exists; empty list if no row was updated.
 *  - deleteOrderById: returns list with the deleted order snapshot; empty list if id not found.
 *  - getAllOrders: returns all orders; empty if none.
 *
 * Uses DBConnection without db.properties (as per project requirement).
 */
public class OrderDaoImpl implements OrderDao {

    @Override
    public List<Order> addOrder(Order order) throws SQLException {
        final String sql = "INSERT INTO orders (customer_name, total_amount) VALUES (?, ?)";
        validateOrder(order);

        List<Order> result = new ArrayList<>(1);
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, order.getCustomerName());
            ps.setBigDecimal(2, order.getTotalAmount());

            int affected = ps.executeUpdate();
            if (affected == 1) {
                // Get generated id
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        order.setId(rs.getLong(1));
                    }
                }
                // Return a copy so callers don’t mutate internal state inadvertently
                result.add(copy(order));
            }
        }
        return result;
    }

    @Override
    public List<Order> updateOrderById(long id, Order order) throws SQLException {
        final String sql = "UPDATE orders SET customer_name = ?, total_amount = ? WHERE id = ?";
        validateOrder(order);

        List<Order> result = new ArrayList<>(1);
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, order.getCustomerName());
            ps.setBigDecimal(2, order.getTotalAmount());
            ps.setLong(3, id);

            int affected = ps.executeUpdate();
            if (affected == 1) {
                Order updated = fetchById(conn, id);
                if (updated != null) {
                    result.add(updated);
                }
            }
        }
        return result;
    }

    @Override
    public List<Order> deleteOrderById(long id) throws SQLException {
        final String sql = "DELETE FROM orders WHERE id = ?";

        List<Order> result = new ArrayList<>(1);
        try (Connection conn = DBConnection.getConnection()) {
            // Snapshot before delete
            Order toDelete = fetchById(conn, id);

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, id);
                int affected = ps.executeUpdate();

                if (affected == 1 && toDelete != null) {
                    result.add(toDelete);
                }
            }
        }
        return result;
    }

    @Override
    public List<Order> getAllOrders() throws SQLException {
        final String sql = "SELECT id, customer_name, total_amount FROM orders";

        List<Order> result = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getLong("id"));
                o.setCustomerName(rs.getString("customer_name"));
                o.setTotalAmount(rs.getBigDecimal("total_amount"));
                result.add(o);
            }
        }
        return result;
    }

    /* ------------------ helpers ------------------ */

    private void validateOrder(Order order) {
        if (order == null) throw new IllegalArgumentException("Order cannot be null");
        if (order.getCustomerName() == null || order.getCustomerName().isBlank()) {
            throw new IllegalArgumentException("customerName cannot be null/blank");
        }
        BigDecimal amt = order.getTotalAmount();
        if (amt == null || amt.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("totalAmount cannot be null or negative");
        }
    }

    private Order fetchById(Connection conn, long id) throws SQLException {
        final String sql = "SELECT id, customer_name, total_amount FROM orders WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Order o = new Order();
                    o.setId(rs.getLong("id"));
                    o.setCustomerName(rs.getString("customer_name"));
                    o.setTotalAmount(rs.getBigDecimal("total_amount"));
                    return o;
                }
            }
        }
        return null;
    }

    private Order copy(Order src) {
        return new Order(src.getId(), src.getCustomerName(), src.getTotalAmount());
    }
}
