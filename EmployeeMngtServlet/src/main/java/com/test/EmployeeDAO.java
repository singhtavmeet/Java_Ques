package com.test;

import java.sql.*;
import java.util.*;
import com.test.Employee;

//import com.example.emp.model.Employee;

public class EmployeeDAO {

    private static final String URL =
        "jdbc:mysql://localhost:3306/day1";
    private static final String USER = "root";
    private static final String PASS = "root@39";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // ADD
    public void addEmployee(Employee e) throws Exception {
        String sql = "INSERT INTO employee (id, name, salary, department) VALUES (?,?,?,?)";
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, e.getId());
        ps.setString(2, e.getName());
        ps.setDouble(3, e.getSalary());
        ps.setString(4, e.getDepartment());
        ps.executeUpdate();
        con.close();
    }

    // UPDATE
    public void updateEmployee(Employee e) throws Exception {
        String sql =
            "UPDATE employee SET name=?, salary=?, department=? WHERE id=?";
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, e.getName());
        ps.setDouble(2, e.getSalary());
        ps.setString(3, e.getDepartment());
        ps.setInt(4, e.getId());
        ps.executeUpdate();
        con.close();
    }

    // DELETE
    public void deleteEmployee(int id) throws Exception {
        String sql = "DELETE FROM employee WHERE id=?";
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        con.close();
    }

    // VIEW
    public List<Employee> getAllEmployees() throws Exception {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        Connection con = getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            Employee e = new Employee();
            e.setId(rs.getInt("id"));
            e.setName(rs.getString("name"));
            e.setSalary(rs.getDouble("salary"));
            e.setDepartment(rs.getString("department"));
            list.add(e);
        }
        con.close();
        return list;
    }
}