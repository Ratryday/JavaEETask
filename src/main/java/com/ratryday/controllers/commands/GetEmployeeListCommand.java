package com.ratryday.controllers.commands;

import com.ratryday.dao.DepartmentDaoImpl;
import com.ratryday.dao.EmployeeDaoImpl;
import com.ratryday.models.Department;
import com.ratryday.dao.DepartmentDao;
import com.ratryday.dao.EmployeeDao;
import com.ratryday.models.Employee;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

import static com.ratryday.controllers.Constants.*;

public class GetEmployeeListCommand extends FrontCommand {

    private DepartmentDao departmentDao = new DepartmentDaoImpl();
    private EmployeeDao employeeDao = new EmployeeDaoImpl();
    private Department department = new Department();

    @Override
    public void doGetProcess() throws ServletException, IOException {
        int departmentID = Integer.parseInt(httpServletRequest.getParameter(ID));

        department = departmentDao.selectOne(departmentID);
        List<Employee> employee = employeeDao.select(departmentID);

        httpServletRequest.setAttribute(EMPLOYEE, employee);
        httpServletRequest.setAttribute(DEPARTMENTS, department);

        forward("employeeList");
    }

    @Override
    public void doPostProcess() throws ServletException, IOException {
    }
}
