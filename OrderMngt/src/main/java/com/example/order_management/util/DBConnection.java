
package com.example.order_management.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class DBConnection {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/orderdb";
    private static final String USER = "root";
    private static final String PASS = "root@39";

    private DBConnection() {}

    public static Connection getConnection() throws SQLException {
        try {
            // Explicit driver load helps some environments
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC driver not found", e);
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

