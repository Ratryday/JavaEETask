package com.ratryday.controllers.employee;

import com.ratryday.controllers.Validator;
import com.ratryday.models.Department;
import com.ratryday.dao.DepartmentDB;
import com.ratryday.models.Employee;
import com.ratryday.dao.EmployeeDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/createEmployee")
public class CreateEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = -7952625273354502725L;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            int departmentID = Integer.parseInt(httpServletRequest.getParameter("departmentID"));
            httpServletRequest.setAttribute("departmentID", departmentID);
            getServletContext().getRequestDispatcher("/createEmployee.jsp").forward(httpServletRequest, httpServletResponse);
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            String employeeName = httpServletRequest.getParameter("employeeName");

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

            int departmentID = Integer.parseInt(httpServletRequest.getParameter("departmentID"));

            if (Validator.isValidator(employeeName, convertedToSQLHiringDate, experience, mailingAddress)) {
                Employee employee = new Employee(employeeName, convertedToSQLHiringDate, experience, mailingAddress, departmentID);
                EmployeeDB.insert(employee);
                ArrayList<Employee> employees = EmployeeDB.select(departmentID);
                Department department = DepartmentDB.selectOne(departmentID);
                httpServletRequest.setAttribute("employee", employees);
                httpServletRequest.setAttribute("department", department);
                getServletContext().getRequestDispatcher("/employeeList.jsp").forward(httpServletRequest, httpServletResponse);
            } else {
                Employee employee = new Employee(employeeName, convertedToSQLHiringDate, experience, mailingAddress, departmentID);
                httpServletRequest.setAttribute("employee", employee);
                httpServletRequest.setAttribute("departmentID", departmentID);
                getServletContext().getRequestDispatcher("/createEmployee.jsp").forward(httpServletRequest, httpServletResponse);
            }

        } catch (NullPointerException ex) {
            String errorMassage = "Имя сотрудника или почтовый адресс не указаны";
            System.out.println(errorMassage);
            getServletContext().getRequestDispatcher("/createEmployee.jsp").forward(httpServletRequest, httpServletResponse);
        } catch (Exception ex) {
            String errorMassage = "Дата или опыт работы не указаны";
            System.out.println(errorMassage);
            getServletContext().getRequestDispatcher("/createEmployee.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }
}
