package com.ratryday.controllers.employee;

import com.ratryday.controllers.Validator;
import com.ratryday.models.Department;
import com.ratryday.dao.DepartmentDB;
import com.ratryday.models.Employee;
import com.ratryday.dao.EmployeeDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.ratryday.controllers.Constants.*;
import static com.ratryday.controllers.Constants.getEmployeeListPage;

@WebServlet(SLASH_EDIT_EMPLOYEE)
public class EditEmployeeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        int idEmployee = Integer.parseInt(httpServletRequest.getParameter(getIdEmployee()));
        int departmentID = Integer.parseInt(httpServletRequest.getParameter(getDepartmentId()));
        Department department = DepartmentDB.selectOne(departmentID);
        Employee employee = EmployeeDB.selectOne(idEmployee);
        ArrayList<Department> departments = DepartmentDB.select();

        httpServletRequest.setAttribute(getEMPLOYEE(), employee);
        httpServletRequest.setAttribute(getDEPARTMENT(), department);
        httpServletRequest.setAttribute(getDEPARTMENTS(), departments);
        getServletContext().getRequestDispatcher(getEditEmployeePage()).forward(httpServletRequest,
                httpServletResponse);

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        int id = Integer.parseInt(httpServletRequest.getParameter(getIdEmployee()));

        String employeeName = httpServletRequest.getParameter(getNAME());

        String hiringDate = null;
        java.sql.Date convertedToSQLHiringDate = null;
        if (!httpServletRequest.getParameter(getHiringDate()).isEmpty()) {
            hiringDate = httpServletRequest.getParameter(getHiringDate());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(getDateFormat());
            Date convertedHiringDate = null;
            try {
                convertedHiringDate = simpleDateFormat.parse(hiringDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            convertedToSQLHiringDate = new java.sql.Date(convertedHiringDate.getTime());
        }

        Integer experience = null;
        if (!httpServletRequest.getParameter(getEXPERIENCE()).isEmpty()) {
            experience = Integer.parseInt(httpServletRequest.getParameter(getEXPERIENCE()));
        }

        String mailingAddress = httpServletRequest.getParameter(getEXPERIENCE());

        int departmentID = Integer.parseInt(httpServletRequest.getParameter(getDepartmentId()));

        int oldDepartmentID = Integer.parseInt(httpServletRequest.getParameter(getOldDepartmentId()));

        if (Validator.isValidator(employeeName, convertedToSQLHiringDate, experience, mailingAddress)) {
            Employee employee = new Employee(id, employeeName, convertedToSQLHiringDate, experience,
                    mailingAddress, departmentID);
            EmployeeDB.update(employee);
            Department oldDepartment = DepartmentDB.selectOne(oldDepartmentID);
            ArrayList<Employee> employees = EmployeeDB.select(oldDepartmentID);

            httpServletRequest.setAttribute(getEMPLOYEE(), employees);
            httpServletRequest.setAttribute(getDEPARTMENT(), oldDepartment);

            getServletContext().getRequestDispatcher(getEmployeeListPage()).forward(httpServletRequest,
                    httpServletResponse);
        } else {
            Employee employee = new Employee(employeeName, convertedToSQLHiringDate, experience, mailingAddress,
                    departmentID);
            Department department = DepartmentDB.selectOne(departmentID);
            ArrayList<Department> departments = DepartmentDB.select();

            httpServletRequest.setAttribute(getEMPLOYEE(), employee);
            httpServletRequest.setAttribute(getDEPARTMENT(), department);
            httpServletRequest.setAttribute(getDEPARTMENTS(), departments);
            getServletContext().getRequestDispatcher(getEmployeeListPage()).forward(httpServletRequest,
                    httpServletResponse);
        }
    }
}
