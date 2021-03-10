package com.ratryday.controller.department.departmentservlets;

import com.ratryday.controller.department.Department;
import com.ratryday.controller.department.DepartmentDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create")
public class CreateDepartment extends HttpServlet {
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/create.jsp").forward(httpServletRequest, httpServletResponse);
    }

    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        try {
            String name = httpServletRequest.getParameter("name");

            Department department = new Department(name);

            DepartmentDB.insert(department);
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath());
        } catch (Exception ex) {

            getServletContext().getRequestDispatcher("/create.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }
}
