package com.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/mladec";
    private static final String USER = "root";
    private static final String PASS = "root@39";

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }

    
}
