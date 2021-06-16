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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.ratryday.controllers.Constants.*;

@WebServlet(SLASH_CREATE_EMPLOYEE)
public class CreateEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = -7952625273354502725L;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        int departmentID = Integer.parseInt(httpServletRequest.getParameter(getDepartmentId()));
        httpServletRequest.setAttribute(getDepartmentId(), departmentID);
        getServletContext().getRequestDispatcher(getCreateEmployeePage()).forward(httpServletRequest, httpServletResponse);

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        try {
            String employeeName = httpServletRequest.getParameter(getEmployeeName());

            String hiringDate = null;
            java.sql.Date convertedToSQLHiringDate = null;
            if (!httpServletRequest.getParameter(getHiringDate()).isEmpty()) {
                hiringDate = httpServletRequest.getParameter(getHiringDate());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(getDateFormat());
                Date convertedHiringDate = simpleDateFormat.parse(hiringDate);
                convertedToSQLHiringDate = new java.sql.Date(convertedHiringDate.getTime());
            }

            Integer experience = null;
            if (!httpServletRequest.getParameter(getEXPERIENCE()).isEmpty()) {
                experience = Integer.parseInt(httpServletRequest.getParameter(getEXPERIENCE()));
            }

            String mailingAddress = httpServletRequest.getParameter(getMailingAddress());

            int departmentID = Integer.parseInt(httpServletRequest.getParameter(getDepartmentId()));

            if (Validator.isValidator(employeeName, convertedToSQLHiringDate, experience, mailingAddress)) {
                Employee employee = new Employee(employeeName, convertedToSQLHiringDate, experience, mailingAddress, departmentID);
                EmployeeDB.insert(employee);
                ArrayList<Employee> employees = EmployeeDB.select(departmentID);
                Department department = DepartmentDB.selectOne(departmentID);
                httpServletRequest.setAttribute(getEMPLOYEE(), employees);
                httpServletRequest.setAttribute(getDepartmentId(), department);
                getServletContext().getRequestDispatcher(getEmployeeListPage()).forward(httpServletRequest, httpServletResponse);
            } else {
                Employee employee = new Employee(employeeName, convertedToSQLHiringDate, experience, mailingAddress, departmentID);
                httpServletRequest.setAttribute(getEMPLOYEE(), employee);
                httpServletRequest.setAttribute(getDepartmentId(), departmentID);
                getServletContext().getRequestDispatcher(getCreateEmployeePage()).forward(httpServletRequest, httpServletResponse);
            }

        } catch (NullPointerException ex) {
            String errorMassage = getEmptyChar();
            System.out.println(errorMassage);
            getServletContext().getRequestDispatcher(getCreateEmployeePage()).forward(httpServletRequest, httpServletResponse);
        } catch (Exception ex) {
            String errorMassage = getEmptyChar();
            System.out.println(errorMassage);
            getServletContext().getRequestDispatcher(getCreateEmployeePage()).forward(httpServletRequest, httpServletResponse);
        }
    }
}
