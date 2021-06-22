package com.ratryday.controllers.employee;

import com.ratryday.controllers.Validator;
import com.ratryday.models.Department;
import com.ratryday.dao.DepartmentDB;
import com.ratryday.models.Employee;
import com.ratryday.dao.EmployeeDB;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static com.ratryday.controllers.Constants.*;

@WebServlet(SLASH_EDIT_EMPLOYEE)
public class EditEmployeeServlet extends HttpServlet {

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
        int idEmployee = Integer.parseInt(httpServletRequest.getParameter(ID_EMPLOYEE));
        int departmentID = Integer.parseInt(httpServletRequest.getParameter(DEPARTMENT_ID));
        departmentDB.selectOne(departmentID);

        ArrayList<Department> departments = departmentDB.select();

        httpServletRequest.setAttribute(EMPLOYEE, employeeDB.select(idEmployee));
        httpServletRequest.setAttribute(DEPARTMENT, departmentDB);
        httpServletRequest.setAttribute(DEPARTMENTS, departments);
        getServletContext().getRequestDispatcher(EDIT_EMPLOYEE_PAGE).forward(httpServletRequest, httpServletResponse);

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        int id = Integer.parseInt(httpServletRequest.getParameter(ID_EMPLOYEE));

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

        int oldDepartmentID = Integer.parseInt(httpServletRequest.getParameter(OLD_DEPARTMENT_ID));

        if (validator.isValid(employeeName, hiringDate, experience, mailingAddress)) {

            // Employee Builder
            Employee employee = new Employee.EmployeeBuilder()
                    .setIdEmployee(id)
                    .setEmployeeName(employeeName)
                    .setHiringDate(hiringDate)
                    .setExperience(experience)
                    .setMailingAddress(mailingAddress)
                    .setDepartmentID(departmentID)
                    .build();

            employeeDB.update(employee);

            departmentDB.selectOne(oldDepartmentID);
            ArrayList<Employee> employees = employeeDB.select(oldDepartmentID);

            httpServletRequest.setAttribute(EMPLOYEE, employees);
            httpServletRequest.setAttribute(DEPARTMENT, departmentDB);

            getServletContext().getRequestDispatcher(EMPLOYEE_LIST_PAGE).forward(httpServletRequest,
                    httpServletResponse);
        } else {

            // Employee Builder
            Employee employee = new Employee.EmployeeBuilder()
                    .setEmployeeName(employeeName)
                    .setHiringDate(hiringDate)
                    .setExperience(experience)
                    .setMailingAddress(mailingAddress)
                    .setDepartmentID(departmentID)
                    .build();

            departmentDB.selectOne(departmentID);
            ArrayList<Department> departments = departmentDB.select();

            httpServletRequest.setAttribute(EMPLOYEE, employee);
            httpServletRequest.setAttribute(DEPARTMENT, departmentDB);
            httpServletRequest.setAttribute(DEPARTMENTS, departments);
            getServletContext().getRequestDispatcher(EMPLOYEE_LIST_PAGE).forward(httpServletRequest, httpServletResponse);
        }
    }
}
