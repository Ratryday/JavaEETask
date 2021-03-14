package com.ratryday.controller.department.departmentservlets;

import com.ratryday.controller.Validator;
import com.ratryday.controller.department.Department;
import com.ratryday.controller.department.DepartmentDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
public class EditDepartment extends HttpServlet {
    private static final long serialVersionUID = -673932636673914989L;

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(httpServletRequest.getParameter("id"));
            Department department = DepartmentDB.selectOne(id);
            if (department != null) {
                httpServletRequest.setAttribute("department", department);
                getServletContext().getRequestDispatcher("/edit.jsp").forward(httpServletRequest, httpServletResponse);
            } else {
                getServletContext().getRequestDispatcher("/notfound.jsp").forward(httpServletRequest, httpServletResponse);
            }
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }

    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        try {
            String name = httpServletRequest.getParameter("name");
            int id = Integer.parseInt(httpServletRequest.getParameter("id"));
            if(Validator.departmentNameValidator(name)) {
                Department department = new Department(id, name);
                DepartmentDB.update(department);
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "");
            } else {
                Department department = DepartmentDB.selectOne(name);
                httpServletRequest.setAttribute("department", department);
                getServletContext().getRequestDispatcher("/edit.jsp").forward(httpServletRequest, httpServletResponse);
            }
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/edit.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }
}
