package com.test;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateEmployee")
public class UpdateEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        Employee e = new Employee();
        e.setId(Integer.parseInt(req.getParameter("eId")));
        e.setName(req.getParameter("eName"));
        e.setSalary(Double.parseDouble(req.getParameter("eSalary")));
        e.setDepartment(req.getParameter("eDept"));

        try {
            new EmployeeDAO().updateEmployee(e);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        res.sendRedirect("home.jsp");
    }
}
