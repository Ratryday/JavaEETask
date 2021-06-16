package com.ratryday.controllers.department;

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

@WebServlet("/delete")
public class DeleteDepartmentServlet extends HttpServlet {
    private static final long serialVersionUID = 4135431999742926051L;

    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(httpServletRequest.getParameter("id"));
            ArrayList<Employee> employee = EmployeeDB.select(id);
            for (Employee emp : employee) {
                EmployeeDB.delete(emp.getIdEmployee(), id);
            }
            DepartmentDB.delete(id);
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath());
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }
}
