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

@WebServlet(SLASH_EDIT)
public class EditDepartmentServlet extends HttpServlet {

    private static final long serialVersionUID = -673932636673914989L;

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        int id = Integer.parseInt(httpServletRequest.getParameter(ID));
        Department department = DepartmentDB.selectOne(id);
        httpServletRequest.setAttribute(DEPARTMENTS, department);
        getServletContext().getRequestDispatcher(EDIT_PAGE).forward(httpServletRequest, httpServletResponse);
    }

    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        try {
            String name = httpServletRequest.getParameter(NAME);
            int id = Integer.parseInt(httpServletRequest.getParameter(ID));
            if (Validator.departmentNameValidator(name)) {

                // Department Builder
                Department department = new Department.DepartmentBuilder()
                        .setId(id)
                        .setName(name)
                        .build();

                DepartmentDB.update(department);
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + EMPTY_CHAR);
            } else {
                String errorMassage = EMPTY_CHAR;
                System.out.println(errorMassage);

                // Department Builder
                Department department = new Department.DepartmentBuilder()
                        .setId(id)
                        .setName(name)
                        .build();

                httpServletRequest.setAttribute(DEPARTMENT, department);
                getServletContext().getRequestDispatcher(EDIT_PAGE).forward(httpServletRequest, httpServletResponse);
            }
        } catch (NullPointerException ex) {
            String errorMassage = EMPTY_CHAR;
            System.out.println(errorMassage);
            int id = Integer.parseInt(httpServletRequest.getParameter(ID));

            // Department Builder
            Department department = new Department.DepartmentBuilder()
                    .setId(id)
                    .setName(null)
                    .build();

            httpServletRequest.setAttribute(DEPARTMENT, department);
            getServletContext().getRequestDispatcher(EDIT_PAGE).forward(httpServletRequest, httpServletResponse);
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher(EDIT_PAGE).forward(httpServletRequest, httpServletResponse);
        }
    }
}
