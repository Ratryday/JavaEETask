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

        int id = Integer.parseInt(httpServletRequest.getParameter(getID()));
        Department department = DepartmentDB.selectOne(id);
        httpServletRequest.setAttribute(getDEPARTMENTS(), department);
        getServletContext().getRequestDispatcher(getEditPage()).forward(httpServletRequest, httpServletResponse);
    }

    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        try {
            String name = httpServletRequest.getParameter(getNAME());
            int id = Integer.parseInt(httpServletRequest.getParameter(getID()));
            if (Validator.departmentNameValidator(name)) {

                // Department Builder
                Department department = new Department.DepartmentBuilder()
                        .setId(id)
                        .setName(name)
                        .build();

                DepartmentDB.update(department);
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + getEmptyChar());
            } else {
                String errorMassage = getEmptyChar();
                System.out.println(errorMassage);

                // Department Builder
                Department department = new Department.DepartmentBuilder()
                        .setId(id)
                        .setName(name)
                        .build();

                httpServletRequest.setAttribute(getDEPARTMENT(), department);
                getServletContext().getRequestDispatcher(getEditPage()).forward(httpServletRequest, httpServletResponse);
            }
        } catch (NullPointerException ex) {
            String errorMassage = getEmptyChar();
            System.out.println(errorMassage);
            int id = Integer.parseInt(httpServletRequest.getParameter(getID()));

            // Department Builder
            Department department = new Department.DepartmentBuilder()
                    .setId(id)
                    .setName(null)
                    .build();

            httpServletRequest.setAttribute(getDEPARTMENT(), department);
            getServletContext().getRequestDispatcher(getEditPage()).forward(httpServletRequest, httpServletResponse);
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher(getEditPage()).forward(httpServletRequest, httpServletResponse);
        }
    }
}
