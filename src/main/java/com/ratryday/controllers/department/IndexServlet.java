package com.ratryday.controllers.department;

import com.ratryday.models.Department;
import com.ratryday.dao.DepartmentDB;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.ArrayList;

import static com.ratryday.controllers.Constants.*;

@WebServlet(EMPTY_CHAR)
public class IndexServlet extends HttpServlet {

    private static final long serialVersionUID = -2434980003597933186L;

    private DepartmentDB departmentDB = new DepartmentDB();

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        ArrayList<Department> departments = departmentDB.select();
        httpServletRequest.setAttribute(DEPARTMENTS, departments);
        getServletContext().getRequestDispatcher(INDEX_PAGE).forward(httpServletRequest, httpServletResponse);
    }

}
