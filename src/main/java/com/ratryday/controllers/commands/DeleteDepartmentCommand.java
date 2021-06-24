package com.ratryday.controllers.commands;

import javax.servlet.ServletException;
import java.io.IOException;

import static com.ratryday.controllers.Constants.INDEX_PAGE;

public class DeleteDepartmentCommand extends FrontCommand {

    @Override
    public void doGetProcess() throws ServletException, IOException {

    }

    @Override
    public void doPostProcess() throws ServletException, IOException {
        // Delete department and its employee
        departmentService.delete(httpServletRequest);

        // Get department list and return to employeeList.jsp
        departmentService.getList(httpServletRequest);
        forward(INDEX_PAGE);
    }
}
