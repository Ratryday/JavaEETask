package com.ratryday.controllers.employee;

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

@WebServlet(SLASH_DELETE_EMPLOYEE)
public class DeleteEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 5857248080450667250L;

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        int idEmployee = Integer.parseInt(httpServletRequest.getParameter(getIdEmployee()));
        int departmentID = Integer.parseInt(httpServletRequest.getParameter(getDepartmentId()));
        String departmentName = httpServletRequest.getParameter(getDepartmentName());
        EmployeeDB.delete(idEmployee, departmentID);

        ArrayList<Employee> employee = EmployeeDB.select(departmentID);

        httpServletRequest.setAttribute(getEMPLOYEE(), employee);
        httpServletRequest.setAttribute(getDepartmentId(), departmentID);
        httpServletRequest.setAttribute(getDepartmentName(), departmentName);

        getServletContext().getRequestDispatcher(getEmployeeListPage()).forward(httpServletRequest, httpServletResponse);
    }
}
