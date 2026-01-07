package com.test;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addEmployee")
public class AddEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        Employee e = new Employee();
        e.setId(Integer.parseInt(req.getParameter("eId")));
        e.setName(req.getParameter("eName"));
        e.setSalary(Double.parseDouble(req.getParameter("eSalary")));
        e.setDepartment(req.getParameter("eDept"));

        try {
            new EmployeeDAO().addEmployee(e);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        res.sendRedirect("home.jsp");
    }
}
