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
import java.time.LocalDate;
import java.util.ArrayList;

import static com.ratryday.controllers.Constants.*;

@WebServlet(SLASH_CREATE_EMPLOYEE)
public class CreateEmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = -7952625273354502725L;

    private Validator validator;
    private EmployeeDB employeeDB;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        int departmentID = Integer.parseInt(httpServletRequest.getParameter(DEPARTMENT_ID));
        httpServletRequest.setAttribute(DEPARTMENT_ID, departmentID);
        getServletContext().getRequestDispatcher(CREATE_EMPLOYEE_PAGE).forward(httpServletRequest, httpServletResponse);

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        try {
            String employeeName = httpServletRequest.getParameter(EMPLOYEE_NAME);

            String hiringDate = null;
            LocalDate convertedToSQLHiringDate = null;
            if (!httpServletRequest.getParameter(HIRING_DATE).isEmpty()) {
                hiringDate = httpServletRequest.getParameter(HIRING_DATE);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
                java.sql.Date convertedHiringDate = (java.sql.Date) simpleDateFormat.parse(hiringDate);
                convertedToSQLHiringDate = convertedHiringDate.toLocalDate();
            }

            Integer experience = null;
            if (!httpServletRequest.getParameter(EXPERIENCE).isEmpty()) {
                experience = Integer.parseInt(httpServletRequest.getParameter(EXPERIENCE));
            }

            String mailingAddress = httpServletRequest.getParameter(MAILING_ADDRESS);

            int departmentID = Integer.parseInt(httpServletRequest.getParameter(DEPARTMENT_ID));

            if (validator.isValidator(employeeName, convertedToSQLHiringDate, experience, mailingAddress)) {

                // Employee Builder
                Employee employee = new Employee.EmployeeBuilder()
                        .setEmployeeName(employeeName)
                        .setHiringDate(convertedToSQLHiringDate)
                        .setExperience(experience)
                        .setMailingAddress(mailingAddress)
                        .setDepartmentID(departmentID)
                        .build();

                employeeDB.insert(employee);

                ArrayList<Employee> employees = employeeDB.select(departmentID);
                Department department = DepartmentDB.selectOne(departmentID);
                httpServletRequest.setAttribute(EMPLOYEE, employees);
                httpServletRequest.setAttribute(DEPARTMENT_ID, department);
                getServletContext().getRequestDispatcher(EMPLOYEE_LIST_PAGE).forward(httpServletRequest, httpServletResponse);
            } else {

                // Employee Builder
                Employee employee = new Employee.EmployeeBuilder()
                        .setEmployeeName(employeeName)
                        .setHiringDate(convertedToSQLHiringDate)
                        .setExperience(experience)
                        .setMailingAddress(mailingAddress)
                        .setDepartmentID(departmentID)
                        .build();

                httpServletRequest.setAttribute(EMPLOYEE, employee);
                httpServletRequest.setAttribute(DEPARTMENT_ID, departmentID);
                getServletContext().getRequestDispatcher(CREATE_EMPLOYEE_PAGE).forward(httpServletRequest, httpServletResponse);
            }

        } catch (NullPointerException ex) {
            String errorMassage = EMPTY_CHAR;
            System.out.println(errorMassage);
            getServletContext().getRequestDispatcher(CREATE_EMPLOYEE_PAGE).forward(httpServletRequest, httpServletResponse);
        } catch (Exception ex) {
            String errorMassage = EMPTY_CHAR;
            System.out.println(errorMassage);
            getServletContext().getRequestDispatcher(CREATE_EMPLOYEE_PAGE).forward(httpServletRequest, httpServletResponse);
        }
    }
}
