package com.ratryday.controllers.commands;

import org.apache.commons.lang3.StringUtils;
import com.ratryday.controllers.Validator;
import com.ratryday.models.Department;
import com.ratryday.dao.DepartmentDB;

import javax.servlet.ServletException;
import java.io.IOException;

import static com.ratryday.controllers.Constants.*;

public class EditDepartmentCommand extends FrontCommand{

    private DepartmentDB departmentDB = new DepartmentDB();
    private Validator validator = new Validator();

    @Override
    public void doGetProcess() throws ServletException, IOException {
        int id = Integer.parseInt(httpServletRequest.getParameter(ID));
        departmentDB.selectOne(id);
        httpServletRequest.setAttribute(DEPARTMENTS, departmentDB);
        httpServletRequest.getServletContext().getRequestDispatcher(EDIT_PAGE)
                .forward(httpServletRequest, httpServletResponse);
    }

    @Override
    public void doPostProcess() throws ServletException, IOException {
        if (StringUtils.isEmpty(httpServletRequest.getParameter(NAME))) {
            int id = Integer.parseInt(httpServletRequest.getParameter(ID));
            // Department Builder
            Department department = new Department.DepartmentBuilder()
                    .setId(id)
                    .setName(null)
                    .build();

            httpServletRequest.setAttribute(DEPARTMENT, department);
            httpServletRequest.getServletContext().getRequestDispatcher(EDIT_PAGE)
                    .forward(httpServletRequest, httpServletResponse);
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
            httpServletRequest.getServletContext().getRequestDispatcher(EDIT_PAGE)
                    .forward(httpServletRequest, httpServletResponse);
        }
    }
}
