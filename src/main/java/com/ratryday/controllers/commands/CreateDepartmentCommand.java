package com.ratryday.controllers.commands;

import javax.servlet.ServletException;
import java.io.IOException;

import static com.ratryday.controllers.Constants.*;

public class CreateDepartmentCommand extends FrontCommand {

    @Override
    public void doGetProcess() throws ServletException, IOException {
        forward(CREATE_PAGE);
    }

    @Override
    public void doPostProcess() throws ServletException, IOException {
        if (departmentService.isCreate(httpServletRequest)) {
            departmentService.getList(httpServletRequest);
            forward(INDEX_PAGE);
        } else {
            forward(CREATE_PAGE);
        }
    }
}
