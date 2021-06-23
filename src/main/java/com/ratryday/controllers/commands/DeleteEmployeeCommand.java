package com.ratryday.controllers.commands;

import com.ratryday.models.Employee;
import com.ratryday.dao.EmployeeDB;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;

import static com.ratryday.controllers.Constants.*;

public class DeleteEmployeeCommand extends FrontCommand {

    private EmployeeDB employeeDB = new EmployeeDB();

    @Override
    public void doGetProcess() throws ServletException, IOException {

    }

    @Override
    public void doPostProcess() throws ServletException, IOException {
        int idEmployee = Integer.parseInt(httpServletRequest.getParameter(ID_EMPLOYEE));
        int departmentID = Integer.parseInt(httpServletRequest.getParameter(DEPARTMENT_ID));
        String departmentName = httpServletRequest.getParameter(DEPARTMENT_NAME);

        employeeDB.delete(idEmployee, departmentID);

        ArrayList<Employee> employee = employeeDB.select(departmentID);

        httpServletRequest.setAttribute(EMPLOYEE, employee);
        httpServletRequest.setAttribute(DEPARTMENT_ID, departmentID);
        httpServletRequest.setAttribute(DEPARTMENT_NAME, departmentName);

        httpServletRequest.getServletContext().getRequestDispatcher(EMPLOYEE_LIST_PAGE)
                .forward(httpServletRequest, httpServletResponse);
    }
}
