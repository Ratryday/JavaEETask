package com.ratryday.controllers.commands;

import javax.servlet.ServletException;
import java.io.IOException;

import static com.ratryday.controllers.Constants.EMPLOYEE_LIST_PAGE;


public class GetEmployeeListCommand extends FrontCommand {

    @Override
    public void doGetProcess() throws ServletException, IOException {
        employeeService.getList(httpServletRequest);
        forward(EMPLOYEE_LIST_PAGE);
    }

    @Override
    public void doPostProcess() throws ServletException, IOException {
    }
}
