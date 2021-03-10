package com.ratryday.controller.employee.employeeservlets;

import com.ratryday.controller.employee.Employee;
import com.ratryday.controller.employee.EmployeeDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/employeeList")
public class EmployeeList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String departmentsName = request.getParameter("departmentName");
        int departmentID = Integer.parseInt(request.getParameter("id"));
        ArrayList<Employee> employees = EmployeeDB.select(departmentID);
        request.setAttribute("employee", employees);
        request.setAttribute("departmentsName", departmentsName);
        request.setAttribute("departmentID", departmentID);

        getServletContext().getRequestDispatcher("/employeeList.jsp").forward(request, response);
    }
}
