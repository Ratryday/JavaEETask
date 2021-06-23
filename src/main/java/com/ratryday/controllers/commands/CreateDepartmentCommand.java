package com.ratryday.controllers.commands;

import org.apache.commons.lang3.StringUtils;
import com.ratryday.controllers.Validator;
import com.ratryday.models.Department;
import com.ratryday.dao.DepartmentDB;

import javax.servlet.ServletException;
import java.io.IOException;

import static com.ratryday.controllers.Constants.*;

public class CreateDepartmentCommand extends FrontCommand {

    private DepartmentDB departmentDB = new DepartmentDB();
    private Validator validator = new Validator();

    @Override
    public void doGetProcess() throws ServletException, IOException {
        forward("create");
    }

    @Override
    public void doPostProcess() throws ServletException, IOException {
        if (StringUtils.isEmpty(httpServletRequest.getParameter(NAME))) {
            httpServletRequest.getServletContext().getRequestDispatcher("create")
                    .forward(httpServletRequest, httpServletResponse);
        }
        String name = httpServletRequest.getParameter(NAME);
        if (validator.isDepartmentNameValid(name)) {
            // Department Builder
            Department department = new Department.DepartmentBuilder().setName(name).build();

            departmentDB.insert(department);
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath());
        } else {
            httpServletRequest.setAttribute(NAME, name);
            httpServletRequest.getServletContext().getRequestDispatcher("create")
                    .forward(httpServletRequest, httpServletResponse);
        }
    }
}
