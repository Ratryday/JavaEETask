package com.ratryday.controllers.commands;

import javax.servlet.ServletException;
import java.io.IOException;

public class UnknownCommand extends FrontCommand {
    @Override
    public void doGetProcess() throws ServletException, IOException {
        forward("notfound");
    }

    @Override
    public void doPostProcess() throws ServletException, IOException {

    }
}
