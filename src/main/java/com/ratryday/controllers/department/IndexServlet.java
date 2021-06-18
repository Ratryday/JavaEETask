package com.ratryday.controllers.department;

import com.ratryday.models.Department;
import com.ratryday.dao.DepartmentDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.ratryday.controllers.Constants.*;

@WebServlet(EMPTY_CHAR)
public class IndexServlet extends HttpServlet {

    private static final long serialVersionUID = -2434980003597933186L;

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        ArrayList<Department> department = DepartmentDB.select();
        httpServletRequest.setAttribute(DEPARTMENTS, department);

        getServletContext().getRequestDispatcher(INDEX_PAGE).forward(httpServletRequest, httpServletResponse);
    }

}
