package com.ratryday.controller.employee.employeeservlets;

import com.ratryday.controller.employee.Employee;
import com.ratryday.controller.employee.EmployeeDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/createEmployee")
public class CreateEmployee extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/createEmployee.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            String employeeName = httpServletRequest.getParameter("employeeName");
            String hiringDate = httpServletRequest.getParameter("hiringDate");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date convertedHiringDate = simpleDateFormat.parse(hiringDate);
            java.sql.Date convertedToSQLHiringDate = new java.sql.Date(convertedHiringDate.getTime());
            int experience = Integer.parseInt(httpServletRequest.getParameter("experience"));
            String mailingAddress = httpServletRequest.getParameter("mailingAddress");
            int departmentID = Integer.parseInt(httpServletRequest.getParameter("departmentID"));
            String departmentName = httpServletRequest.getParameter("departmentName");

            Employee employee = new Employee(employeeName, convertedToSQLHiringDate, experience, mailingAddress, departmentID);

            EmployeeDB.insert(employee);

            httpServletRequest.setAttribute("departmentID", departmentID);
            httpServletRequest.setAttribute("departmentName", departmentName);

            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/employeeList");

        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/createEmployee.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }
}