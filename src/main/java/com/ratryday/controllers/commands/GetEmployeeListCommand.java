package com.ratryday.controllers.commands;

import com.ratryday.dao.DepartmentDB;
import com.ratryday.dao.EmployeeDB;
import com.ratryday.models.Department;
import com.ratryday.models.Employee;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;

import static com.ratryday.controllers.Constants.*;

public class GetEmployeeListCommand extends FrontCommand {

    private DepartmentDB departmentDB = new DepartmentDB();
    private Department department = new Department();
    private EmployeeDB employeeDB = new EmployeeDB();

    @Override
    public void doGetProcess() throws ServletException, IOException {
        int departmentID = Integer.parseInt(httpServletRequest.getParameter(ID));

        department = departmentDB.selectOne(departmentID);
        ArrayList<Employee> employee = employeeDB.select(departmentID);

        httpServletRequest.setAttribute(EMPLOYEE, employee);
        httpServletRequest.setAttribute(DEPARTMENTS, department);

        httpServletRequest.getServletContext().getRequestDispatcher(EMPLOYEE_LIST_PAGE)
                .forward(httpServletRequest, httpServletResponse);
    }

    @Override
    public void doPostProcess() throws ServletException, IOException {
    }
}
