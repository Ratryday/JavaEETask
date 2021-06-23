package com.ratryday.controllers.commands;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class FrontCommand {
    protected ServletContext servletContext;
    protected HttpServletRequest httpServletRequest;
    protected HttpServletResponse httpServletResponse;

    public void init(
            ServletContext servletContext,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse) {
        this.servletContext = servletContext;
        this.httpServletRequest = servletRequest;
        this.httpServletResponse = servletResponse;
    }

    public abstract void doGetProcess() throws ServletException, IOException;

    public abstract void doPostProcess() throws ServletException, IOException;

    protected void forward(String target) throws ServletException, IOException {
        target = String.format("/%s.jsp", target);
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher(target);
        dispatcher.forward(httpServletRequest, httpServletResponse);
    }
}
