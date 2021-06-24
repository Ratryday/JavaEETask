package com.ratryday.controllers.commands;

import javax.servlet.ServletException;
import java.io.IOException;

import static com.ratryday.controllers.Constants.*;

public class CreateEmployeeCommand extends FrontCommand {

    @Override
    public void doGetProcess() throws ServletException, IOException {
        employeeService.prepareForCreate(httpServletRequest);
        forward(CREATE_EMPLOYEE_PAGE);
    }

    @Override
    public void doPostProcess() throws ServletException, IOException {
        if (employeeService.isCreate(httpServletRequest)) {
            employeeService.getList(httpServletRequest);
            forward(EMPLOYEE_LIST_PAGE);
        } else {
            forward(CREATE_EMPLOYEE_PAGE);
        }
    }
}
