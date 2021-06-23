package com.ratryday.controllers.commands;

import org.apache.commons.lang3.StringUtils;
import com.ratryday.dao.DepartmentDaoImpl;
import com.ratryday.controllers.Validator;
import com.ratryday.dao.DepartmentDao;
import com.ratryday.models.Department;

import javax.servlet.ServletException;
import java.io.IOException;

import static com.ratryday.controllers.Constants.*;

public class EditDepartmentCommand extends FrontCommand{

    private DepartmentDao departmentDao = new DepartmentDaoImpl();
    private Validator validator = new Validator();

    @Override
    public void doGetProcess() throws ServletException, IOException {
        int id = Integer.parseInt(httpServletRequest.getParameter(ID));
        departmentDao.selectOne(id);
        httpServletRequest.setAttribute(DEPARTMENTS, departmentDao);
        forward("edit");
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
            forward("edit");
        }

        String name = httpServletRequest.getParameter(NAME);
        int id = Integer.parseInt(httpServletRequest.getParameter(ID));
        if (validator.isDepartmentNameValid(name)) {
            // Department Builder
            Department department = new Department.DepartmentBuilder()
                    .setId(id)
                    .setName(name)
                    .build();

            departmentDao.update(department);

            IndexCommand indexCommand = new IndexCommand();
            indexCommand.doGetProcess();

            //  forward("index");
        } else {
            // Department Builder
            Department department = new Department.DepartmentBuilder()
                    .setId(id)
                    .setName(name)
                    .build();

            httpServletRequest.setAttribute(DEPARTMENT, department);
            forward("edit");
        }
    }
}
