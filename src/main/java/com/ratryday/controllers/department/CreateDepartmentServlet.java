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

import static com.ratryday.controllers.Constants.*;

@WebServlet(SLASH_CREATE)
public class CreateDepartmentServlet extends HttpServlet {

    private static final long serialVersionUID = 7038334119164896401L;

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher(CREATE_PAGE).forward(httpServletRequest, httpServletResponse);
    }

    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        try {
            String name = httpServletRequest.getParameter(NAME);
            if (Validator.departmentNameValidator(name)) {

                // Department Builder
                Department department = new Department.DepartmentBuilder().setName(name).build();

                DepartmentDB.insert(department);
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath());
            } else {
                String errorMassage = EMPTY_CHAR;
                System.out.println(errorMassage);
                httpServletRequest.setAttribute(NAME, name);
                getServletContext().getRequestDispatcher(CREATE_PAGE).forward(httpServletRequest, httpServletResponse);
            }
        } catch (NullPointerException ex) {
            System.err.println(ex);
            getServletContext().getRequestDispatcher(CREATE_PAGE).forward(httpServletRequest, httpServletResponse);
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher(CREATE_PAGE).forward(httpServletRequest, httpServletResponse);
        }
    }
}
