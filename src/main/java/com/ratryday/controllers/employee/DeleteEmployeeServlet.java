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

@WebServlet("/deleteEmployee")
public class DeleteEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 5857248080450667250L;

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        try {
            int idEmployee = Integer.parseInt(httpServletRequest.getParameter("idEmployee"));
            int departmentID = Integer.parseInt(httpServletRequest.getParameter("departmentID"));
            String departmentName = httpServletRequest.getParameter("departmentName");
            EmployeeDB.delete(idEmployee, departmentID);

            ArrayList<Employee> employee = EmployeeDB.select(departmentID);

            httpServletRequest.setAttribute("employee", employee);
            httpServletRequest.setAttribute("departmentID", departmentID);
            httpServletRequest.setAttribute("departmentName", departmentName);

            getServletContext().getRequestDispatcher("/employeeList.jsp").forward(httpServletRequest, httpServletResponse);
        }catch (Exception ex) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }
}
