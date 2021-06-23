package com.ratryday.controllers.commands;

import com.ratryday.models.Department;
import com.ratryday.dao.DepartmentDaoImpl;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

import static com.ratryday.controllers.Constants.DEPARTMENTS;

    public class IndexCommand extends FrontCommand{

    private DepartmentDaoImpl departmentDao = new DepartmentDaoImpl();

    @Override
    public void doGetProcess() throws ServletException, IOException {
        List<Department> departments = departmentDao.select();
        httpServletRequest.setAttribute(DEPARTMENTS, departments);
        forward("index");
    }

    @Override
    public void doPostProcess() throws ServletException, IOException {

    }
}
