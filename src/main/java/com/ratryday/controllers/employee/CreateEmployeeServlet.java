package com.ratryday.controllers.employee;

import org.apache.commons.lang3.StringUtils;
import com.ratryday.controllers.Validator;
import com.ratryday.dao.DepartmentDB;
import com.ratryday.models.Employee;
import com.ratryday.dao.EmployeeDB;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static com.ratryday.controllers.Constants.*;

@WebServlet(SLASH_CREATE_EMPLOYEE)
public class CreateEmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = -7952625273354502725L;

    private DepartmentDB departmentDB = new DepartmentDB();
    private EmployeeDB employeeDB = new EmployeeDB();
    private Validator validator = new Validator();
    private String mailingAddress;
    private LocalDate hiringDate;
    private String employeeName;
    private Integer experience;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        int departmentID = Integer.parseInt(httpServletRequest.getParameter(DEPARTMENT_ID));
        httpServletRequest.setAttribute(DEPARTMENT_ID, departmentID);
        getServletContext().getRequestDispatcher(CREATE_EMPLOYEE_PAGE).
                forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        if (!StringUtils.isEmpty(httpServletRequest.getParameter(EMPLOYEE_NAME))) {
            employeeName = httpServletRequest.getParameter(EMPLOYEE_NAME);
        }

        if (!StringUtils.isEmpty(httpServletRequest.getParameter(HIRING_DATE))) {
            hiringDate = LocalDate.parse(httpServletRequest
                    .getParameter(HIRING_DATE), DateTimeFormatter.ofPattern(DATE_FORMAT));
        }

        if (!httpServletRequest.getParameter(EXPERIENCE).isEmpty()) {
            experience = Integer.parseInt(httpServletRequest.getParameter(EXPERIENCE));
        }

        if (!StringUtils.isEmpty(httpServletRequest.getParameter(MAILING_ADDRESS))) {
            mailingAddress = httpServletRequest.getParameter(MAILING_ADDRESS);
        }

        int departmentID = Integer.parseInt(httpServletRequest.getParameter(DEPARTMENT_ID));

        if (validator.isValid(employeeName, hiringDate, experience, mailingAddress)) {

            // Employee Builder
            Employee employee = new Employee.EmployeeBuilder()
                    .setEmployeeName(employeeName)
                    .setHiringDate(hiringDate)
                    .setExperience(experience)
                    .setMailingAddress(mailingAddress)
                    .setDepartmentID(departmentID)
                    .build();

            employeeDB.insert(employee);

            ArrayList<Employee> employees = employeeDB.select(departmentID);
            departmentDB.selectOne(departmentID);
            httpServletRequest.setAttribute(EMPLOYEE, employees);
            httpServletRequest.setAttribute(DEPARTMENT_ID, departmentDB);
            getServletContext().getRequestDispatcher(EMPLOYEE_LIST_PAGE).
                    forward(httpServletRequest, httpServletResponse);
        } else {
            // Employee Builder
            Employee employee = new Employee.EmployeeBuilder()
                    .setEmployeeName(employeeName)
                    .setHiringDate(hiringDate)
                    .setExperience(experience)
                    .setMailingAddress(mailingAddress)
                    .setDepartmentID(departmentID)
                    .build();

            httpServletRequest.setAttribute(EMPLOYEE, employee);
            httpServletRequest.setAttribute(DEPARTMENT_ID, departmentID);
            getServletContext().getRequestDispatcher(CREATE_EMPLOYEE_PAGE).
                    forward(httpServletRequest, httpServletResponse);
        }
    }
}
