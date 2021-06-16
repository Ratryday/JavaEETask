package com.ratryday.controllers.department;

import com.ratryday.controllers.Validator;
import com.ratryday.models.Department;
import com.ratryday.dao.DepartmentDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create")
public class CreateDepartmentServlet extends HttpServlet {
    private static final long serialVersionUID = 7038334119164896401L;

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/create.jsp").forward(httpServletRequest, httpServletResponse);
    }

    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        try {
            String name = httpServletRequest.getParameter("name");
            if (Validator.departmentNameValidator(name)) {
                Department department = new Department(name);
                DepartmentDB.insert(department);
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath());
            } else {
                String errorMassage = "Департамент с таким именем уже есть";
                System.out.println(errorMassage);
                httpServletRequest.setAttribute("name", name);
                getServletContext().getRequestDispatcher("/create.jsp").forward(httpServletRequest, httpServletResponse);
            }
        } catch (NullPointerException ex) {
            System.err.println(ex);
            getServletContext().getRequestDispatcher("/create.jsp").forward(httpServletRequest, httpServletResponse);
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/create.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }
}
