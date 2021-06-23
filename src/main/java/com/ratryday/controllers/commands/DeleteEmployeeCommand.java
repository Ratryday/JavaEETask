package com.ratryday.controllers.commands;

import com.ratryday.dao.EmployeeDao;
import com.ratryday.models.Employee;
import com.ratryday.dao.EmployeeDaoImpl;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

import static com.ratryday.controllers.Constants.*;

public class DeleteEmployeeCommand extends FrontCommand {

    private EmployeeDao employeeDao = new EmployeeDaoImpl();

    @Override
    public void doGetProcess() throws ServletException, IOException {

    }

    @Override
    public void doPostProcess() throws ServletException, IOException {
        int idEmployee = Integer.parseInt(httpServletRequest.getParameter(ID_EMPLOYEE));
        int departmentID = Integer.parseInt(httpServletRequest.getParameter(DEPARTMENT_ID));
        String departmentName = httpServletRequest.getParameter(DEPARTMENT_NAME);

        employeeDao.delete(idEmployee, departmentID);

        List<Employee> employee = employeeDao.select(departmentID);

        httpServletRequest.setAttribute(EMPLOYEE, employee);
        httpServletRequest.setAttribute(DEPARTMENT_ID, departmentID);
        httpServletRequest.setAttribute(DEPARTMENT_NAME, departmentName);

        GetEmployeeListCommand getEmployeeListCommand = new GetEmployeeListCommand();
        getEmployeeListCommand.doGetProcess();

        //  forward("employeeList");
    }
}
