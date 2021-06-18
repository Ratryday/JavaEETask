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

import static com.ratryday.controllers.Constants.*;

@WebServlet(SLASH_DELETE)
public class DeleteDepartmentServlet extends HttpServlet {

    private static final long serialVersionUID = 4135431999742926051L;

    private EmployeeDB employeeDB;

    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        int id = Integer.parseInt(httpServletRequest.getParameter(getID()));
        ArrayList<Employee> employeeArrayList;

        employeeArrayList = employeeDB.select(id);

        for (Employee emp : employeeArrayList) {
            employeeDB.delete(emp.getIdEmployee(), id);
        }

        DepartmentDB.delete(id);
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath());
    }
}
