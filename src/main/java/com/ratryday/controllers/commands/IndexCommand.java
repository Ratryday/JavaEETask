package com.ratryday.controllers.commands;

import javax.servlet.ServletException;
import java.io.IOException;

import static com.ratryday.controllers.Constants.DEPARTMENT_LIST_PAGE;

public class IndexCommand extends FrontCommand{

    @Override
    public void doGetProcess() throws ServletException, IOException {
        departmentService.getList(httpServletRequest);
        forward(DEPARTMENT_LIST_PAGE);
    }

    @Override
    public void doPostProcess() throws ServletException, IOException {

    }
}
