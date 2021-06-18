package com.ratryday.controllers.employee;

import com.ratryday.models.Department;
import com.ratryday.dao.DepartmentDB;
import com.ratryday.models.Employee;
import com.ratryday.dao.EmployeeDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.ratryday.controllers.Constants.*;

@WebServlet(SLASH_EMPLOYEE_LIST)
public class EmployeeListServlet extends HttpServlet {

    private static final long serialVersionUID = 8130011401819399469L;

    private EmployeeDB employeeDB;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        int departmentID = Integer.parseInt(httpServletRequest.getParameter(getID()));

        Department department = DepartmentDB.selectOne(departmentID);
        ArrayList<Employee> employee = employeeDB.select(departmentID);

        httpServletRequest.setAttribute(getEMPLOYEE(), employee);
        httpServletRequest.setAttribute(getDEPARTMENTS(), department);

        getServletContext().getRequestDispatcher(getEmployeeListPage()).forward(httpServletRequest, httpServletResponse);
    }
}
