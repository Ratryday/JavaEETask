package com.ratryday.controllers.commands;

import javax.servlet.ServletException;
import java.io.IOException;

import static com.ratryday.controllers.Constants.INDEX_PAGE;

public class IndexCommand extends FrontCommand{

    @Override
    public void doGetProcess() throws ServletException, IOException {
        employeeService.getList(httpServletRequest);
        forward(INDEX_PAGE);
    }

    @Override
    public void doPostProcess() throws ServletException, IOException {

    }
}
