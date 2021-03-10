package com.ratryday.controller.employee.employeeservlets;

import com.ratryday.controller.employee.EmployeeDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteEmployee")
public class DeleteEmployee extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        try {
            int idEmployee = Integer.parseInt(httpServletRequest.getParameter("idEmployee"));
            int departmentID = Integer.parseInt(httpServletRequest.getParameter("departmentID"));
            String departmentName = httpServletRequest.getParameter("departmentName");
            EmployeeDB.delete(idEmployee, departmentID);

            httpServletRequest.setAttribute("departmentID", departmentID);
            httpServletRequest.setAttribute("departmentName", departmentName);

            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/employeeList");

        }catch (Exception ex) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }
}
