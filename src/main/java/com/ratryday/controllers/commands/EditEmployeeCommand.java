package com.ratryday.controllers.commands;

import javax.servlet.ServletException;
import java.io.IOException;

import static com.ratryday.controllers.Constants.*;

public class EditEmployeeCommand extends FrontCommand {


    @Override
    public void doGetProcess() throws ServletException, IOException {
        employeeService.prepareForUpdate(httpServletRequest);
        forward(EDIT_EMPLOYEE_PAGE);
    }

    @Override
    public void doPostProcess() throws ServletException, IOException {
        if (employeeService.isUpdate(httpServletRequest)) {
            employeeService.getList(httpServletRequest);
            forward(EMPLOYEE_LIST_PAGE);
        } else {
            forward(EDIT_EMPLOYEE_PAGE);
        }
    }
}
