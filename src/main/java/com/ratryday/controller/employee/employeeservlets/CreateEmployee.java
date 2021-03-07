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
            /*String hiringDate = httpServletRequest.getParameter("hiringDate");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date convertedHiringDate = simpleDateFormat.parse(hiringDate);*/
            Date hiringDate = (Date) httpServletRequest.getAttribute("hiringDate");
            int experience = Integer.parseInt(httpServletRequest.getParameter("experience"));
            String mailingAddress = httpServletRequest.getParameter("mailingAddress");
            int departmentID = Integer.parseInt(httpServletRequest.getParameter("departmentID"));

            Employee employee = new Employee(employeeName, hiringDate, experience, mailingAddress, departmentID);

            EmployeeDB.insert(employee);
            httpServletResponse.sendRedirect("/employeeList.jsp");

        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/createEmployee.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }
}
