package com.ratryday.controllers.employee;

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

@WebServlet(SLASH_DELETE_EMPLOYEE)
public class DeleteEmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = 5857248080450667250L;

    private EmployeeDB employeeDB = new EmployeeDB();

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        int idEmployee = Integer.parseInt(httpServletRequest.getParameter(ID_EMPLOYEE));
        int departmentID = Integer.parseInt(httpServletRequest.getParameter(DEPARTMENT_ID));
        String departmentName = httpServletRequest.getParameter(DEPARTMENT_NAME);

        employeeDB.delete(idEmployee, departmentID);

        ArrayList<Employee> employee = employeeDB.select(departmentID);

        httpServletRequest.setAttribute(EMPLOYEE, employee);
        httpServletRequest.setAttribute(DEPARTMENT_ID, departmentID);
        httpServletRequest.setAttribute(DEPARTMENT_NAME, departmentName);

        getServletContext().getRequestDispatcher(EMPLOYEE_LIST_PAGE).forward(httpServletRequest, httpServletResponse);
    }
}
