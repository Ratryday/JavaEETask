package com.ratryday.controllers.commands;

import com.ratryday.models.Department;
import com.ratryday.dao.DepartmentDB;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;

import static com.ratryday.controllers.Constants.DEPARTMENTS;

public class IndexCommand extends FrontCommand{

    private DepartmentDB departmentDB = new DepartmentDB();

    @Override
    public void doGetProcess() throws ServletException, IOException {
        ArrayList<Department> departments = departmentDB.select();
        httpServletRequest.setAttribute(DEPARTMENTS, departments);
        forward("index");
    }

    @Override
    public void doPostProcess() throws ServletException, IOException {

    }
}
