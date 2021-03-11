package com.ratryday.controller.employee.employeeservlets;

import com.ratryday.controller.department.Department;
import com.ratryday.controller.department.DepartmentDB;
import com.ratryday.controller.employee.Employee;
import com.ratryday.controller.employee.EmployeeDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/editEmployee")
public class EditEmployee extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(httpServletRequest.getParameter("id"));
            int departmentID = Integer.parseInt(httpServletRequest.getParameter("departmentID"));
            Employee employee = EmployeeDB.selectOne(id);
            ArrayList<Department> department = DepartmentDB.select();
            Department departmentName = DepartmentDB.selectOne(departmentID);

            if (employee != null) {
                httpServletRequest.setAttribute("employee", employee);
                httpServletRequest.setAttribute("department", department);
                httpServletRequest.setAttribute("departmentName", departmentName);
                getServletContext().getRequestDispatcher("/editEmployee.jsp").forward(httpServletRequest, httpServletResponse);
            } else {
                getServletContext().getRequestDispatcher("/notfound.jsp").forward(httpServletRequest, httpServletResponse);
            }
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(httpServletRequest.getParameter("idEmployee"));

            String employeeName = httpServletRequest.getParameter("name");

            String hiringDate = httpServletRequest.getParameter("hiringDate");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date convertedHiringDate = simpleDateFormat.parse(hiringDate);
            java.sql.Date convertedToSQLHiringDate = new java.sql.Date(convertedHiringDate.getTime());

            int experience = Integer.parseInt(httpServletRequest.getParameter("experience"));

            String mailingAddress = httpServletRequest.getParameter("mailingAddress");

            int departmentID = Integer.parseInt(httpServletRequest.getParameter("departmentId"));

            String departmentName = httpServletRequest.getParameter("departmentName");

            Employee employee = new Employee(id, employeeName, convertedToSQLHiringDate, experience, mailingAddress, departmentID);
            EmployeeDB.update(employee);

            ArrayList<Employee> employees = EmployeeDB.select(departmentID);
            httpServletRequest.setAttribute("employee", employees);
            httpServletRequest.setAttribute("departmentName", departmentName);
            httpServletRequest.setAttribute("departmentID", departmentID);

            getServletContext().getRequestDispatcher("/employeeList.jsp").forward(httpServletRequest, httpServletResponse);
        } catch (Exception ex){
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }
}
