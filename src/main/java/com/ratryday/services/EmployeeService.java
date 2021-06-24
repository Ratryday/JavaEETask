package com.ratryday.services;

import org.apache.commons.lang3.StringUtils;
import com.ratryday.controllers.Validator;
import com.ratryday.dao.DepartmentDaoImpl;
import com.ratryday.dao.EmployeeDaoImpl;
import com.ratryday.models.Department;
import com.ratryday.dao.DepartmentDao;
import com.ratryday.dao.EmployeeDao;
import com.ratryday.models.Employee;

import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.List;

import static com.ratryday.controllers.Constants.*;

public class EmployeeService {

    private DepartmentDao departmentDao = new DepartmentDaoImpl();
    private EmployeeDao employeeDao = new EmployeeDaoImpl();
    private Department department = new Department();
    private Validator validator = new Validator();
    private Employee employee = new Employee();
    private String mailingAddress;
    private LocalDate hiringDate;
    private String employeeName;
    private Integer experience;

    public void prepareForCreate(HttpServletRequest httpServletRequest) {
        // Get request parameters
        int departmentID = Integer.parseInt(httpServletRequest.getParameter(DEPARTMENT_ID));

        // Set attribute
        httpServletRequest.setAttribute(DEPARTMENT_ID, departmentID);
    }

    public boolean isCreate(HttpServletRequest httpServletRequest) {
        // Get request parameters
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

        // Check on validity
        if (validator.isValid(employeeName, hiringDate, experience, mailingAddress)) {
            // Employee Builder
            employee = new Employee.EmployeeBuilder()
                    .setEmployeeName(employeeName)
                    .setHiringDate(hiringDate)
                    .setExperience(experience)
                    .setMailingAddress(mailingAddress)
                    .setDepartmentID(departmentID)
                    .build();

            // Do DB command
            employeeDao.insert(employee);
            List<Employee> employees = employeeDao.select(departmentID);
            departmentDao.selectOne(departmentID);

            // Set attributes
            httpServletRequest.setAttribute(EMPLOYEE, employees);
            httpServletRequest.setAttribute(DEPARTMENT_ID, departmentDao);

            return true;
        } else {
            // Employee Builder
            employee = new Employee.EmployeeBuilder()
                    .setEmployeeName(employeeName)
                    .setHiringDate(hiringDate)
                    .setExperience(experience)
                    .setMailingAddress(mailingAddress)
                    .setDepartmentID(departmentID)
                    .build();

            // Set attributes
            httpServletRequest.setAttribute(EMPLOYEE, employee);
            httpServletRequest.setAttribute(DEPARTMENT_ID, departmentID);

            return false;
        }
    }

    public void prepareForUpdate(HttpServletRequest httpServletRequest) {
        // Get request parameters
        int idEmployee = Integer.parseInt(httpServletRequest.getParameter(ID_EMPLOYEE));
        int departmentID = Integer.parseInt(httpServletRequest.getParameter(DEPARTMENT_ID));

        // Do DB command
        department = departmentDao.selectOne(departmentID);
        List<Department> departments = departmentDao.select();
        employee = employeeDao.selectOne(idEmployee);

        // Set attribute
        httpServletRequest.setAttribute(EMPLOYEE, employee);
        httpServletRequest.setAttribute(DEPARTMENT, department);
        httpServletRequest.setAttribute(DEPARTMENTS, departments);
    }

    public boolean isUpdate(HttpServletRequest httpServletRequest) {
        // Get request parameters
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
        int id = Integer.parseInt(httpServletRequest.getParameter(ID_EMPLOYEE));

        // Check on validity
        if (validator.isValid(employeeName, hiringDate, experience, mailingAddress)) {
            // Employee Builder
            employee = new Employee.EmployeeBuilder()
                    .setIdEmployee(id)
                    .setEmployeeName(employeeName)
                    .setHiringDate(hiringDate)
                    .setExperience(experience)
                    .setMailingAddress(mailingAddress)
                    .setDepartmentID(departmentID)
                    .build();

            // Do DB command
            employeeDao.update(employee);
            departmentDao.selectOne(oldDepartmentID);
            List<Employee> employees = employeeDao.select(oldDepartmentID);

            // Set attributes
            httpServletRequest.setAttribute(EMPLOYEE, employees);
            httpServletRequest.setAttribute(DEPARTMENT, departmentDao);

            return true;
        } else {
            // Employee Builder
            employee = new Employee.EmployeeBuilder()
                    .setEmployeeName(employeeName)
                    .setHiringDate(hiringDate)
                    .setExperience(experience)
                    .setMailingAddress(mailingAddress)
                    .setDepartmentID(departmentID)
                    .build();

            // Do DB command
            departmentDao.selectOne(departmentID);
            List<Department> departments = departmentDao.select();

            // Set attributes
            httpServletRequest.setAttribute(EMPLOYEE, employee);
            httpServletRequest.setAttribute(DEPARTMENT, departmentDao);
            httpServletRequest.setAttribute(DEPARTMENTS, departments);

            return false;
        }
    }

    public void delete(HttpServletRequest httpServletRequest) {
        // Get request parameters
        int idEmployee = Integer.parseInt(httpServletRequest.getParameter(ID_EMPLOYEE));
        int departmentID = Integer.parseInt(httpServletRequest.getParameter(DEPARTMENT_ID));
        String departmentName = httpServletRequest.getParameter(DEPARTMENT_NAME);

        // Do DB command
        employeeDao.delete(idEmployee, departmentID);
        List<Employee> employee = employeeDao.select(departmentID);

        // Set attributes
        httpServletRequest.setAttribute(EMPLOYEE, employee);
        httpServletRequest.setAttribute(DEPARTMENT_ID, departmentID);
        httpServletRequest.setAttribute(DEPARTMENT_NAME, departmentName);
    }

    public List<Employee> getList(HttpServletRequest httpServletRequest) {
        // Get request parameters
        int departmentID = Integer.parseInt(httpServletRequest.getParameter(ID));

        // Do DB command
        department = departmentDao.selectOne(departmentID);
        List<Employee> employeeList = employeeDao.select(departmentID);

        // Set attributes
        httpServletRequest.setAttribute(EMPLOYEE, employeeList);
        httpServletRequest.setAttribute(DEPARTMENTS, department);

        return employeeList;
    }
}
