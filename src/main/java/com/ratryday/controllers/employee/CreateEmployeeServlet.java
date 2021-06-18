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
import java.time.LocalDate;
import java.util.ArrayList;

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
            LocalDate convertedToSQLHiringDate = null;
            if (!httpServletRequest.getParameter(getHiringDate()).isEmpty()) {
                hiringDate = httpServletRequest.getParameter(getHiringDate());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(getDateFormat());
                java.sql.Date convertedHiringDate = (java.sql.Date) simpleDateFormat.parse(hiringDate);
                convertedToSQLHiringDate = convertedHiringDate.toLocalDate();
            }

            Integer experience = null;
            if (!httpServletRequest.getParameter(getEXPERIENCE()).isEmpty()) {
                experience = Integer.parseInt(httpServletRequest.getParameter(getEXPERIENCE()));
            }

            String mailingAddress = httpServletRequest.getParameter(getMailingAddress());

            int departmentID = Integer.parseInt(httpServletRequest.getParameter(getDepartmentId()));

            if (Validator.isValidator(employeeName, convertedToSQLHiringDate, experience, mailingAddress)) {

                // EmployeeBuilder
                Employee employee = new Employee.EmployeeBuilder()
                        .setEmployeeName(employeeName)
                        .setHiringDate(convertedToSQLHiringDate)
                        .setExperience(experience)
                        .setMailingAddress(mailingAddress)
                        .setDepartmentID(departmentID)
                        .build();

                EmployeeDB.insert(employee);
                ArrayList<Employee> employees = EmployeeDB.select(departmentID);
                Department department = DepartmentDB.selectOne(departmentID);
                httpServletRequest.setAttribute(getEMPLOYEE(), employees);
                httpServletRequest.setAttribute(getDepartmentId(), department);
                getServletContext().getRequestDispatcher(getEmployeeListPage()).forward(httpServletRequest, httpServletResponse);
            } else {

                // EmployeeBuilder
                Employee employee = new Employee.EmployeeBuilder()
                        .setEmployeeName(employeeName)
                        .setHiringDate(convertedToSQLHiringDate)
                        .setExperience(experience)
                        .setMailingAddress(mailingAddress)
                        .setDepartmentID(departmentID)
                        .build();

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
