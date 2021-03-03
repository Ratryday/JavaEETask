package com.ratryday.controller.department.departmentservlets;

import com.ratryday.controller.department.Department;
import com.ratryday.controller.department.DepartmentDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("")
public class IndexServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Department> department = DepartmentDB.select();
        request.setAttribute("department", department);

        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

}
