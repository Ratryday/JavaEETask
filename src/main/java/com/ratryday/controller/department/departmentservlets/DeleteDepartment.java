package com.ratryday.controller.department.departmentservlets;

import com.ratryday.controller.department.DepartmentDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteDepartment extends HttpServlet {
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(httpServletRequest.getParameter("id"));
            DepartmentDB.delete(id);
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath());
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }
}
