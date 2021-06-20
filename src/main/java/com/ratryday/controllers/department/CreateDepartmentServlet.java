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

@WebServlet(SLASH_CREATE)
public class CreateDepartmentServlet extends HttpServlet {

    private static final long serialVersionUID = 7038334119164896401L;

    private DepartmentDB departmentDB;
    private Validator validator;

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher(CREATE_PAGE).forward(httpServletRequest, httpServletResponse);
    }

    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        if (StringUtils.isEmpty(httpServletRequest.getParameter(NAME))) {
            getServletContext().getRequestDispatcher(CREATE_PAGE).forward(httpServletRequest, httpServletResponse);
        }
        String name = httpServletRequest.getParameter(NAME);
        if (validator.isDepartmentNameValid(name)) {
            // Department Builder
            Department department = new Department.DepartmentBuilder().setName(name).build();

            departmentDB.insert(department);
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath());
        } else {
            // Department name already exist
            httpServletRequest.setAttribute(NAME, name);
            getServletContext().getRequestDispatcher(CREATE_PAGE).forward(httpServletRequest, httpServletResponse);
        }
    }
}
