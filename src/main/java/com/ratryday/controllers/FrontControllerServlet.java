package com.ratryday.controllers;

import com.ratryday.controllers.commands.UnknownCommand;
import com.ratryday.controllers.commands.FrontCommand;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class FrontControllerServlet extends HttpServlet {

    private static final long serialVersionUID = 3217561200041947857L;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        System.out.println(1);
        FrontCommand command = getCommand(httpServletRequest);
        System.out.println(command);
        command.init(getServletContext(), httpServletRequest, httpServletResponse);
        command.doGetProcess();
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        System.out.println(2);
        FrontCommand command = getCommand(httpServletRequest);
        System.out.println(command);
        command.init(getServletContext(), httpServletRequest, httpServletResponse);
        command.doPostProcess();
    }

    private FrontCommand getCommand(HttpServletRequest httpServletRequest) {
        try {
            Class type = Class.forName(String.format("com.ratryday.controllers.commands.%sCommand",
                    httpServletRequest.getParameter("command")));
            return (FrontCommand) type.asSubclass(FrontCommand.class).newInstance();
        } catch (Exception e) {
            return new UnknownCommand();
        }
    }
}
