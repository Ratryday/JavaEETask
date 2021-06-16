package com.ratryday.controllers;

public class Constants {

    // Department page constants
    private static final String EDIT_PAGE = "/edit.jsp";
    private static final String INDEX_PAGE = "/index.jsp";
    private static final String CREATE_PAGE = "/create.jsp";

    // Departments constants
    public static final String SLASH_INDEX = "";
    public static final String SLASH_EDIT = "/edit";
    public static final String SLASH_CREATE = "/create";
    public static final String SLASH_DELETE = "/delete";

    // Employee page constants
    private static final String EMPLOYEE_LIST_PAGE = "/employeeList.jsp";
    private static final String EDIT_EMPLOYEE_PAGE = "/editEmployee.jsp";
    private static final String CREATE_EMPLOYEE_PAGE = "/createEmployee.jsp";

    // Employee constants
    public static final String SLASH_EDIT_EMPLOYEE = "/editEmployee";
    public static final String SLASH_CREATE_EMPLOYEE = "/createEmployee";
    public static final String SLASH_DELETE_EMPLOYEE = "/deleteEmployee";
    public static final String SLASH_EMPLOYEE_LIST = "/employeeList";

    // Data constants
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMPTY_CHAR = "";
    private static final String EMPLOYEE = "employee";
    private static final String DEPARTMENT = "department";
    private static final String EXPERIENCE = "experience";
    private static final String HIRING_DATE = "hiringDate";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String ID_EMPLOYEE = "idEmployee";
    private static final String DEPARTMENTS = "departments";
    private static final String EMPLOYEE_NAME = "employeeName";
    private static final String DEPARTMENT_ID = "departmentID";
    private static final String DEPARTMENT_NAME = "departmentName";
    private static final String MAILING_ADDRESS = "mailingAddress";
    private static final String OLD_DEPARTMENT_ID = "oldDepartmentID";

    // Department page constants getters
    public static String getEditPage() {
        return EDIT_PAGE;
    }

    public static String getIndexPage() {
        return INDEX_PAGE;
    }

    public static String getCreatePage() {
        return CREATE_PAGE;
    }

    // Employee page constants getters
    public static String getEmployeeListPage() {
        return EMPLOYEE_LIST_PAGE;
    }

    public static String getEditEmployeePage() {
        return EDIT_EMPLOYEE_PAGE;
    }

    public static String getCreateEmployeePage() {
        return CREATE_EMPLOYEE_PAGE;
    }

    // Data constants getters
    public static String getID() {
        return ID;
    }

    public static String getNAME() {
        return NAME;
    }

    public static String getEmptyChar() {
        return EMPTY_CHAR;
    }

    public static String getEMPLOYEE() {
        return EMPLOYEE;
    }

    public static String getDEPARTMENT() {
        return DEPARTMENT;
    }

    public static String getEXPERIENCE() {
        return EXPERIENCE;
    }

    public static String getHiringDate() {
        return HIRING_DATE;
    }

    public static String getDateFormat() {
        return DATE_FORMAT;
    }

    public static String getIdEmployee() {
        return ID_EMPLOYEE;
    }

    public static String getDEPARTMENTS() {
        return DEPARTMENTS;
    }

    public static String getEmployeeName() {
        return EMPLOYEE_NAME;
    }

    public static String getDepartmentId() {
        return DEPARTMENT_ID;
    }

    public static String getDepartmentName() {
        return DEPARTMENT_NAME;
    }

    public static String getMailingAddress() {
        return MAILING_ADDRESS;
    }

    public static String getOldDepartmentId() {
        return OLD_DEPARTMENT_ID;
    }
}
