package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
 
public class NewApp {
 
    public static void main(String[] args) throws Exception {
 
        String url = "jdbc:mysql://localhost:3306/mladec";
        String user = "root";
        String pass = "root@39";
 
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, pass);
 
        Scanner sc = new Scanner(System.in);
 
        Statement stmt = con.createStatement();
 
//       
//        stmt.execute("CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY, name VARCHAR(50), email VARCHAR(50))");
//        stmt.execute("CREATE TABLE IF NOT EXISTS travel_info (tid INT PRIMARY KEY, user_id INT, source VARCHAR(50), destination VARCHAR(50))");
//      
        System.out.println("Enter User ID:");
        int uid = sc.nextInt();
        sc.nextLine();
 
        System.out.println("Enter Name:");
        String name = sc.nextLine();
 
        System.out.println("Enter Email:");
        String email = sc.nextLine();
 
        String sql1 = "INSERT INTO users VALUES (?,?,?)";
        PreparedStatement pst1 = con.prepareStatement(sql1);
 
        pst1.setInt(1, uid);
        pst1.setString(2, name);
        pst1.setString(3, email);
        pst1.executeUpdate();
 
        System.out.println("Enter Travel ID:");
        int tid = sc.nextInt();
        System.out.println("Enter User ID:");
        int tUserId = sc.nextInt();
        sc.nextLine();
 
        System.out.println("Enter Source:");
        String source = sc.nextLine();
 
        System.out.println("Enter Destination:");
        String destination = sc.nextLine();
 
        String sql3 = "INSERT INTO travel_info VALUES (?,?,?,?)";
        PreparedStatement pst3 = con.prepareStatement(sql3);
 
        pst3.setInt(1, tid);
        pst3.setInt(2, tUserId);
        pst3.setString(3, source);
        pst3.setString(4, destination);
        pst3.executeUpdate();
 
        String sql4 = "SELECT * FROM users";
        PreparedStatement pst4 = con.prepareStatement(sql4);
 
        ResultSet rs = pst4.executeQuery();
    
        while (rs.next()) {
            System.out.println(
                    "ID: " + rs.getInt(1) +
                    ", Name: " + rs.getString(2) +
                    ", Email: " + rs.getString(3)
            );
        }
 
   
        String sql5 = "SELECT * FROM travel_info";
        PreparedStatement pst5 = con.prepareStatement(sql5);
 
        ResultSet rs2 = pst5.executeQuery();
       
        while (rs2.next()) {
            System.out.println(
                    "Travel ID: " + rs2.getInt(1) +
                    ", User ID: " + rs2.getInt(2) +
                    ", Source: " + rs2.getString(3) +
                    ", Destination: " + rs2.getString(4)
            );
        }
 
//        System.out.println("Enter User ID to Update Email:");
//        int upId = sc.nextInt();
//        sc.nextLine();
//
//        System.out.println("Enter New Email:");
//        String newEmail = sc.nextLine();
//
//        String sql2 = "UPDATE users SET email=? WHERE id=?";
//        PreparedStatement pst2 = con.prepareStatement(sql2);
//
//        pst2.setString(1, newEmail);
//        pst2.setInt(2, upId);
//        pst2.executeUpdate();
        
        sc.close();
        con.close();
    }
}
