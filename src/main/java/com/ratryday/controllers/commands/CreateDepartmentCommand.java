package com.ratryday.controllers.commands;

import org.apache.commons.lang3.StringUtils;
import com.ratryday.dao.DepartmentDaoImpl;
import com.ratryday.controllers.Validator;
import com.ratryday.dao.DepartmentDao;
import com.ratryday.models.Department;

import javax.servlet.ServletException;
import java.io.IOException;

import static com.ratryday.controllers.Constants.*;

public class CreateDepartmentCommand extends FrontCommand {

    private DepartmentDao departmentDao = new DepartmentDaoImpl();
    private Validator validator = new Validator();

    @Override
    public void doGetProcess() throws ServletException, IOException {
        forward("create");
    }

    @Override
    public void doPostProcess() throws ServletException, IOException {
        if (StringUtils.isEmpty(httpServletRequest.getParameter(NAME))) {
            forward("create");
        }
        String name = httpServletRequest.getParameter(NAME);
        if (validator.isDepartmentNameValid(name)) {
            // Department Builder
            Department department = new Department.DepartmentBuilder().setName(name).build();

            departmentDao.insert(department);

            IndexCommand indexCommand = new IndexCommand();
            indexCommand.doGetProcess();

            //  forward("index");
            //  httpServletResponse.sendRedirect(httpServletRequest.getContextPath());
        } else {
            httpServletRequest.setAttribute(NAME, name);
            forward("create");
        }
    }
}
