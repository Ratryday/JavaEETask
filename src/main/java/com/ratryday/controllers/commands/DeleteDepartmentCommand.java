package com.ratryday.controllers.commands;

import com.ratryday.dao.DepartmentDB;
import com.ratryday.models.Employee;
import com.ratryday.dao.EmployeeDB;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;

import static com.ratryday.controllers.Constants.ID;

public class DeleteDepartmentCommand extends FrontCommand {

    private ArrayList<Employee> employeeArrayList = new ArrayList<>();
    private DepartmentDB departmentDB = new DepartmentDB();
    private EmployeeDB employeeDB = new EmployeeDB();

    @Override
    public void doGetProcess() throws ServletException, IOException {

    }

    @Override
    public void doPostProcess() throws ServletException, IOException {
        int id = Integer.parseInt(httpServletRequest.getParameter(ID));
        employeeArrayList = employeeDB.select(id);
        for (Employee emp : employeeArrayList) {
            employeeDB.delete(emp.getIdEmployee(), id);
        }
        departmentDB.delete(id);
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath());
    }
}
