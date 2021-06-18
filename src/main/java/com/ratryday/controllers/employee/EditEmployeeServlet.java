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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import static com.ratryday.controllers.Constants.*;

@WebServlet(SLASH_EDIT_EMPLOYEE)
public class EditEmployeeServlet extends HttpServlet {

    private EmployeeDB employeeDB;
    private Validator validator;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        int idEmployee = Integer.parseInt(httpServletRequest.getParameter(ID_EMPLOYEE));
        int departmentID = Integer.parseInt(httpServletRequest.getParameter(DEPARTMENT_ID));
        Department department = DepartmentDB.selectOne(departmentID);

        ArrayList<Department> departments = DepartmentDB.select();

        httpServletRequest.setAttribute(EMPLOYEE, employeeDB.select(idEmployee));
        httpServletRequest.setAttribute(DEPARTMENT, department);
        httpServletRequest.setAttribute(DEPARTMENTS, departments);
        getServletContext().getRequestDispatcher(EDIT_EMPLOYEE_PAGE).forward(httpServletRequest, httpServletResponse);

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        int id = Integer.parseInt(httpServletRequest.getParameter(ID_EMPLOYEE));

        String employeeName = httpServletRequest.getParameter(NAME);

        String hiringDate = null;
        LocalDate convertedToSQLHiringDate = null;
        if (!httpServletRequest.getParameter(HIRING_DATE).isEmpty()) {
            hiringDate = httpServletRequest.getParameter(HIRING_DATE);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
            java.sql.Date convertedHiringDate = null;
            try {
                convertedHiringDate = (java.sql.Date) simpleDateFormat.parse(hiringDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            convertedToSQLHiringDate = convertedHiringDate.toLocalDate();
        }

        Integer experience = null;
        if (!httpServletRequest.getParameter(EXPERIENCE).isEmpty()) {
            experience = Integer.parseInt(httpServletRequest.getParameter(EXPERIENCE));
        }

        String mailingAddress = httpServletRequest.getParameter(EXPERIENCE);

        int departmentID = Integer.parseInt(httpServletRequest.getParameter(DEPARTMENT_ID));

        int oldDepartmentID = Integer.parseInt(httpServletRequest.getParameter(OLD_DEPARTMENT_ID));

        if (validator.isValidator(employeeName, convertedToSQLHiringDate, experience, mailingAddress)) {

            // Employee Builder
            Employee employee = new Employee.EmployeeBuilder()
                    .setIdEmployee(id)
                    .setEmployeeName(employeeName)
                    .setHiringDate(convertedToSQLHiringDate)
                    .setExperience(experience)
                    .setMailingAddress(mailingAddress)
                    .setDepartmentID(departmentID)
                    .build();

            employeeDB.update(employee);

            Department oldDepartment = DepartmentDB.selectOne(oldDepartmentID);
            ArrayList<Employee> employees = employeeDB.select(oldDepartmentID);

            httpServletRequest.setAttribute(EMPLOYEE, employees);
            httpServletRequest.setAttribute(DEPARTMENT, oldDepartment);

            getServletContext().getRequestDispatcher(EMPLOYEE_LIST_PAGE).forward(httpServletRequest,
                    httpServletResponse);
        } else {

            // Employee Builder
            Employee employee = new Employee.EmployeeBuilder()
                    .setEmployeeName(employeeName)
                    .setHiringDate(convertedToSQLHiringDate)
                    .setExperience(experience)
                    .setMailingAddress(mailingAddress)
                    .setDepartmentID(departmentID)
                    .build();

            Department department = DepartmentDB.selectOne(departmentID);
            ArrayList<Department> departments = DepartmentDB.select();

            httpServletRequest.setAttribute(EMPLOYEE, employee);
            httpServletRequest.setAttribute(DEPARTMENT, department);
            httpServletRequest.setAttribute(DEPARTMENTS, departments);
            getServletContext().getRequestDispatcher(EMPLOYEE_LIST_PAGE).forward(httpServletRequest, httpServletResponse);
        }
    }
}
