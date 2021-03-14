package com.ratryday.controller.employee.employeeservlets;

import com.ratryday.controller.department.Department;
import com.ratryday.controller.department.DepartmentDB;
import com.ratryday.controller.employee.Employee;
import com.ratryday.controller.employee.EmployeeDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/employeeList")
public class EmployeeList extends HttpServlet {
    private static final long serialVersionUID = 8130011401819399469L;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        int departmentID = Integer.parseInt(httpServletRequest.getParameter("id"));

        Department department = DepartmentDB.selectOne(departmentID);
        ArrayList<Employee> employee = EmployeeDB.select(departmentID);

        httpServletRequest.setAttribute("employee", employee);
        httpServletRequest.setAttribute("department", department);

        getServletContext().getRequestDispatcher("/employeeList.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
