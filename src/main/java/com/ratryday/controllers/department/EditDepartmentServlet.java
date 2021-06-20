package com.ratryday.controllers.department;

import org.apache.commons.lang3.StringUtils;
import com.ratryday.controllers.Validator;
import com.ratryday.models.Department;
import com.ratryday.dao.DepartmentDB;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import java.io.IOException;

import static com.ratryday.controllers.Constants.*;

@WebServlet(SLASH_EDIT)
public class EditDepartmentServlet extends HttpServlet {

    private static final long serialVersionUID = -673932636673914989L;

    private DepartmentDB departmentDB;
    private Validator validator;

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        int id = Integer.parseInt(httpServletRequest.getParameter(ID));
        departmentDB.selectOne(id);
        httpServletRequest.setAttribute(DEPARTMENTS, departmentDB);
        getServletContext().getRequestDispatcher(EDIT_PAGE).forward(httpServletRequest, httpServletResponse);
    }

    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        if (StringUtils.isEmpty(httpServletRequest.getParameter(NAME))) {
            int id = Integer.parseInt(httpServletRequest.getParameter(ID));
            // Department Builder
            Department department = new Department.DepartmentBuilder()
                    .setId(id)
                    .setName(null)
                    .build();

            httpServletRequest.setAttribute(DEPARTMENT, department);
            getServletContext().getRequestDispatcher(EDIT_PAGE).forward(httpServletRequest, httpServletResponse);
        }

        String name = httpServletRequest.getParameter(NAME);
        int id = Integer.parseInt(httpServletRequest.getParameter(ID));
        if (validator.isDepartmentNameValid(name)) {
            // Department Builder
            Department department = new Department.DepartmentBuilder()
                    .setId(id)
                    .setName(name)
                    .build();

            departmentDB.update(department);
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + EMPTY_CHAR);
        } else {
            // Department Builder
            Department department = new Department.DepartmentBuilder()
                    .setId(id)
                    .setName(name)
                    .build();

            httpServletRequest.setAttribute(DEPARTMENT, department);
            getServletContext().getRequestDispatcher(EDIT_PAGE).forward(httpServletRequest, httpServletResponse);
        }

    }
}
