package com.ratryday.controller.employee.employeeservlets;

import com.ratryday.controller.Validator;
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
            int idEmployee = Integer.parseInt(httpServletRequest.getParameter("idEmployee"));
            int departmentID = Integer.parseInt(httpServletRequest.getParameter("departmentID"));
            Department department = DepartmentDB.selectOne(departmentID);
            Employee employee = EmployeeDB.selectOne(idEmployee);
            ArrayList<Department> departments = DepartmentDB.select();

            if (employee != null) {
                httpServletRequest.setAttribute("employee", employee);
                httpServletRequest.setAttribute("department", department);
                httpServletRequest.setAttribute("departments", departments);
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

            String hiringDate = null;
            java.sql.Date convertedToSQLHiringDate = null;
            if (!httpServletRequest.getParameter("hiringDate").isEmpty()) {
                hiringDate = httpServletRequest.getParameter("hiringDate");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date convertedHiringDate = simpleDateFormat.parse(hiringDate);
                convertedToSQLHiringDate = new java.sql.Date(convertedHiringDate.getTime());
            }

            Integer experience = null;
            if (!httpServletRequest.getParameter("experience").isEmpty()) {
                experience = Integer.parseInt(httpServletRequest.getParameter("experience"));
            }

            String mailingAddress = httpServletRequest.getParameter("mailingAddress");

            int departmentID = Integer.parseInt(httpServletRequest.getParameter("departmentId"));

            int oldDepartmentID = Integer.parseInt(httpServletRequest.getParameter("oldDepartmentID"));

            if (Validator.isValidator(employeeName, convertedToSQLHiringDate, experience, mailingAddress)) {
                Employee employee = new Employee(id, employeeName, convertedToSQLHiringDate, experience, mailingAddress, departmentID);
                EmployeeDB.update(employee);
                Department oldDepartment = DepartmentDB.selectOne(oldDepartmentID);
                ArrayList<Employee> employees = EmployeeDB.select(oldDepartmentID);

                httpServletRequest.setAttribute("employee", employees);
                httpServletRequest.setAttribute("department", oldDepartment);

                getServletContext().getRequestDispatcher("/employeeList.jsp").forward(httpServletRequest, httpServletResponse);
            } else {
                Employee employee = new Employee(employeeName, convertedToSQLHiringDate, experience, mailingAddress, departmentID);
                Department department = DepartmentDB.selectOne(departmentID);
                ArrayList<Department> departments = DepartmentDB.select();

                httpServletRequest.setAttribute("employee", employee);
                httpServletRequest.setAttribute("department", department);
                httpServletRequest.setAttribute("departments", departments);
                getServletContext().getRequestDispatcher("/editEmployee.jsp").forward(httpServletRequest, httpServletResponse);
            }

        } catch (Exception ex){
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }
}
