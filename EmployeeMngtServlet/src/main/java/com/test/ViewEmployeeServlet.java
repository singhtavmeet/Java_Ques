package com.test;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewEmployees")
public class ViewEmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            List<Employee> list = new EmployeeDAO().getAllEmployees();
            req.setAttribute("employees", list);
            req.getRequestDispatcher("viewEmployees.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
