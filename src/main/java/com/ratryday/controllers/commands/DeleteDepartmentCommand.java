package com.ratryday.controllers.commands;

import com.ratryday.dao.EmployeeDaoImpl;
import com.ratryday.dao.DepartmentDaoImpl;
import com.ratryday.dao.DepartmentDao;
import com.ratryday.dao.EmployeeDao;
import com.ratryday.models.Employee;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ratryday.controllers.Constants.ID;

public class DeleteDepartmentCommand extends FrontCommand {

    private DepartmentDao departmentDao = new DepartmentDaoImpl();
    private EmployeeDao employeeDao = new EmployeeDaoImpl();
    private List<Employee> employeeList = new ArrayList<>();

    @Override
    public void doGetProcess() throws ServletException, IOException {

    }

    @Override
    public void doPostProcess() throws ServletException, IOException {
        int id = Integer.parseInt(httpServletRequest.getParameter(ID));
        employeeList = employeeDao.select(id);
        for (Employee emp : employeeList) {
            employeeDao.delete(emp.getIdEmployee(), id);
        }
        departmentDao.delete(id);

        IndexCommand indexCommand = new IndexCommand();
        indexCommand.doGetProcess();

        //  forward("index");
    }
}
