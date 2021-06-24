package com.ratryday.controllers.commands;

import javax.servlet.ServletException;
import java.io.IOException;

import static com.ratryday.controllers.Constants.EMPLOYEE_LIST_PAGE;

public class DeleteEmployeeCommand extends FrontCommand {

    @Override
    public void doGetProcess() throws ServletException, IOException {

    }

    @Override
    public void doPostProcess() throws ServletException, IOException {
        // Delete employee
        employeeService.delete(httpServletRequest);

        // Get employee list and return to employeeList.jsp
        employeeService.getList(httpServletRequest);
        forward(EMPLOYEE_LIST_PAGE);
    }
}
