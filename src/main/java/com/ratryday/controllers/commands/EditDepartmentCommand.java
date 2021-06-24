package com.ratryday.controllers.commands;

import javax.servlet.ServletException;
import java.io.IOException;

import static com.ratryday.controllers.Constants.*;

public class EditDepartmentCommand extends FrontCommand{


    @Override
    public void doGetProcess() throws ServletException, IOException {
        departmentService.prepareForUpdate(httpServletRequest);
        forward(EDIT_PAGE);
    }

    @Override
    public void doPostProcess() throws ServletException, IOException {
        if (departmentService.isUpdate(httpServletRequest)) {
            departmentService.getList(httpServletRequest);
            forward(DEPARTMENT_LIST_PAGE);
        } else {
            forward(DEPARTMENT_LIST_PAGE);
        }
    }
}
