package com.ratryday.controllers.employee;

import com.ratryday.dao.DepartmentDB;
import com.ratryday.models.Department;
import com.ratryday.models.Employee;
import com.ratryday.dao.EmployeeDB;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;

import static com.ratryday.controllers.Constants.*;

@WebServlet(SLASH_EMPLOYEE_LIST)
public class EmployeeListServlet extends HttpServlet {

    private static final long serialVersionUID = 8130011401819399469L;

    private DepartmentDB departmentDB = new DepartmentDB();
    private Department department = new Department();
    private EmployeeDB employeeDB = new EmployeeDB();

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        int departmentID = Integer.parseInt(httpServletRequest.getParameter(ID));

        department = departmentDB.selectOne(departmentID);
        ArrayList<Employee> employee = employeeDB.select(departmentID);

        httpServletRequest.setAttribute(EMPLOYEE, employee);            // employee
        httpServletRequest.setAttribute(DEPARTMENTS, department);     // departments

        getServletContext().getRequestDispatcher(EMPLOYEE_LIST_PAGE).forward(httpServletRequest, httpServletResponse);
    }
}
