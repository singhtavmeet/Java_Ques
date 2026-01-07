package com.test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Employee {

    private int id;
    private String empName;
    private String location;

    public Employee() {}

    public Employee(int id, String empName, String location) {
        this.id = id;
        this.empName = empName;
        this.location = location;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}


interface EmployeeDao {

    Employee addEmployee(Employee emp);
    Employee updateEmployee(Employee emp);
    List<Employee> deleteEmployee(int id);
    List<Employee> getAllEmployees();
}


class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public Employee addEmployee(Employee emp) {
        String sql = "INSERT INTO employee VALUES (?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, emp.getId());
            ps.setString(2, emp.getEmpName());
            ps.setString(3, emp.getLocation());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }

    @Override
    public Employee updateEmployee(Employee emp) {
        String sql = "UPDATE employee SET emp_name=?, location=? WHERE id=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, emp.getEmpName());
            ps.setString(2, emp.getLocation());
            ps.setInt(3, emp.getId());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }

    @Override
    public List<Employee> deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE id=?";
        List<Employee> list = new ArrayList<>();

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

            list = getAllEmployees();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM employee";
        List<Employee> list = new ArrayList<>();

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setEmpName(rs.getString("emp_name"));
                emp.setLocation(rs.getString("location"));
                list.add(emp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}


public class EmployeeManagement {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EmployeeDao dao = new EmployeeDaoImpl();

        while (true) {
            System.out.println("\n1.Add  2.Update  3.Delete  4.View All  5.Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter Name: ");
                    String name = sc.next();
                    System.out.print("Enter Location: ");
                    String loc = sc.next();

                    dao.addEmployee(new Employee(id, name, loc));
                    System.out.println("Employee Added");
                    break;

                case 2:
                    System.out.print("Enter ID: ");
                    id = sc.nextInt();
                    System.out.print("Enter New Name: ");
                    name = sc.next();
                    System.out.print("Enter New Location: ");
                    loc = sc.next();

                    dao.updateEmployee(new Employee(id, name, loc));
                    System.out.println("Employee Updated");
                    break;

                case 3:
                    System.out.print("Enter ID to delete: ");
                    id = sc.nextInt();
                    dao.deleteEmployee(id);
                    System.out.println("Employee Deleted");
                    break;

                case 4:
                    List<Employee> list = dao.getAllEmployees();
                    for (Employee e : list) {
                        System.out.println(
                                e.getId() + " " +
                                e.getEmpName() + " " +
                                e.getLocation()
                        );
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
            }
        }
    }
}
