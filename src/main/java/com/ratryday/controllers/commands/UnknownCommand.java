package com.ratryday.controllers.commands;

import javax.servlet.ServletException;
import java.io.IOException;

import static com.ratryday.controllers.Constants.NOT_FOUND_PAGE;

public class UnknownCommand extends FrontCommand {

    @Override
    public void doGetProcess() throws ServletException, IOException {
        forward(NOT_FOUND_PAGE);
    }

    @Override
    public void doPostProcess() throws ServletException, IOException {

    }
}
