package com.ratryday.controllers.commands;

import org.apache.commons.lang3.StringUtils;
import com.ratryday.dao.DepartmentDaoImpl;
import com.ratryday.controllers.Validator;
import com.ratryday.dao.EmployeeDaoImpl;
import com.ratryday.dao.DepartmentDao;
import com.ratryday.models.Department;
import com.ratryday.dao.EmployeeDao;
import com.ratryday.models.Employee;

import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static com.ratryday.controllers.Constants.*;

public class EditEmployeeCommand extends FrontCommand {

    private DepartmentDao departmentDao = new DepartmentDaoImpl();
    private EmployeeDao employeeDao = new EmployeeDaoImpl();
    private Department department = new Department();
    private Validator validator = new Validator();
    private Employee employee = new Employee();
    private String mailingAddress;
    private LocalDate hiringDate;
    private String employeeName;
    private Integer experience;

    @Override
    public void doGetProcess() throws ServletException, IOException {
        int idEmployee = Integer.parseInt(httpServletRequest.getParameter(ID_EMPLOYEE));
        int departmentID = Integer.parseInt(httpServletRequest.getParameter(DEPARTMENT_ID));
        department = departmentDao.selectOne(departmentID);
        List<Department> departments = departmentDao.select();
        employee = employeeDao.selectOne(idEmployee);

        httpServletRequest.setAttribute(EMPLOYEE, employee);
        httpServletRequest.setAttribute(DEPARTMENT, department);
        httpServletRequest.setAttribute(DEPARTMENTS, departments);
        forward("editEmployee");
    }

    @Override
    public void doPostProcess() throws ServletException, IOException {
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
            employee = new Employee.EmployeeBuilder()
                    .setIdEmployee(id)
                    .setEmployeeName(employeeName)
                    .setHiringDate(hiringDate)
                    .setExperience(experience)
                    .setMailingAddress(mailingAddress)
                    .setDepartmentID(departmentID)
                    .build();

            employeeDao.update(employee);

            departmentDao.selectOne(oldDepartmentID);

            List<Employee> employees = employeeDao.select(oldDepartmentID);
            httpServletRequest.setAttribute(EMPLOYEE, employees);
            httpServletRequest.setAttribute(DEPARTMENT, departmentDao);

            GetEmployeeListCommand getEmployeeListCommand = new GetEmployeeListCommand();
            getEmployeeListCommand.doGetProcess();

            // forward("employeeList");
        } else {
            // Employee Builder
            employee = new Employee.EmployeeBuilder()
                    .setEmployeeName(employeeName)
                    .setHiringDate(hiringDate)
                    .setExperience(experience)
                    .setMailingAddress(mailingAddress)
                    .setDepartmentID(departmentID)
                    .build();

            departmentDao.selectOne(departmentID);

            List<Department> departments = departmentDao.select();

            httpServletRequest.setAttribute(EMPLOYEE, employee);
            httpServletRequest.setAttribute(DEPARTMENT, departmentDao);
            httpServletRequest.setAttribute(DEPARTMENTS, departments);
            forward("editEmployee");
        }
    }
}
